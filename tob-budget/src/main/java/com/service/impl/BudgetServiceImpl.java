package com.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dao.TSubjectMapper;
import com.entities.*;
import com.entities.OrgInfoDO;
import com.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.dao.ExecutionPlanDB;
import com.dao.TBudgetAllocationsMapper;
import com.dao.TPlanDetailMapper;
import com.entities.TPlanDetailExample.Criteria;
import com.github.pagehelper.PageHelper;
import com.model.OrgPlan;
import com.service.BudegtService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.ReqPage;
import com.utils.mallQuery;

@Service
public class BudgetServiceImpl implements BudegtService {


	@Autowired
	private TBudgetAllocationsMapper allocationMapper;
	@Autowired
	private TPlanDetailMapper detailMapper;
	 @Autowired
	   private RestTemplate restTemplate;
	@Autowired
	private ExecutionPlanDB executionPlanDB;

	@Autowired
	private TSubjectMapper subMapper;


	/**
	 * 预算分配列表
	 * @param pageNum,
	 * @param pageSize,
	 * @param searchKey
	 * @param budgetYear
	 * @param subjectId
	 * @param orgId 前端选择的预算实体
	 * @param currOrgId 当前登录人的组织
	 * @return
	 */
	@Override
	public PageUtils budgetAllocationList(Map<String, Object> map) {
		mallQuery query=new mallQuery(map);
		//页面信息
		List<TBudgetAllocationsVo>list=allocationMapper.allocationList(query);
		//总记录数
		int total=allocationMapper.countAllocation(query);
		return new PageUtils(list, total);
	}
	
	/**
	 * 预算接收列表
	 * @param pageNum,
	 * @param pageSize,
	 * @param searchKey
	 * @param budgetYear
	 * @param subjectId
	 * @param currOrgId 当前登录人组织
	 * @return
	 */
	@Override
	public PageUtils budgetReceiveList(Map<String, Object> map) {
		mallQuery query=new mallQuery(map);
		//页面信息
		List<TBudgetAllocationsVo>list=allocationMapper.receiveList(query);
		//总记录数
		int total=allocationMapper.countReceive(query);
		return new PageUtils(list, total);
	}



    /**
     * 预算使用列表
     *
     * @param pageNum,
     * @param pageSize,
     * @param searchKey
     * @param budgetYear
     * @param subjectId
     * @param orgId      前端选择的组织id
     * @param currOrgId  当前登录人组织
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public PageUtils budgetUseList(Map<String, Object> map) {
        Integer orgId = Integer.parseInt(map.get("currOrgId").toString());
        //第三种方式
        R response =
                restTemplate.getForObject("http://tob-user/system/orgInfo/findBottomOrgList?orgId=" + orgId, R.class);
        LinkedHashMap<String, Object> obj = (LinkedHashMap<String, Object>) response.get("obj");
        ArrayList<OrgInfoDO> orgInfoList = (ArrayList<OrgInfoDO>) obj.get("bottomOrgList");

        mallQuery query = new mallQuery(map);

        query.put("leafList", orgInfoList);
        //总记录数
        int total = allocationMapper.countUse(query);

        //页面信息
        List<TBudgetAllocationsVo> list = allocationMapper.useList(query);
        for (TBudgetAllocationsVo l : list) {
            Map<String, Object> map2 = new HashMap<>();
            map2.put("subjectId", l.getSubjectId());
            map2.put("budgetYear", l.getBudgetYear());
            map2.put("orgId", l.getOrgId());
            l.setActualUse(allocationMapper.actualUseByYear(map2));
        }

        return new PageUtils(list, total);
    }


	/**
	 * 封装公共的预算使用接口
	 * @param orgId 登录人组织id
	 * @return
	 */

	@Override
	@SuppressWarnings("all")
	public List<BudgetDetailVo> budgetPublic(Integer orgId) {
		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);
		int month=date.get(Calendar.MONTH)+1;
		int season=CalSeason(month);
		List<BudgetDetailVo> list = allocationMapper.budgetPublicByYear(orgId,year,new Date());
		//查询出当前所有预算分配记录
		List<TBudgetAllocationsVo> budgetAllocation = allocationMapper.getBudgetAllocation(orgId, null,year);
		//如果上级给下级分配了科目预算，
		//获取总额的预算分配记录（只有一条）
		TBudgetAllocationsVo budgetTotal = budgetAllocation.stream().filter(item -> item.getBudgetType() == 0).collect(Collectors.toList()).get(0);
		//获取各科目的预算分配记录
		List<TBudgetAllocationsVo> budgetforSubs = budgetAllocation.stream().filter(item -> item.getBudgetType() == 1).collect(Collectors.toList());
		//可用预算(默认0.0)
		Double actualUse = 0.0;

		ArrayList<BudgetDetailVo> budgetDetailVos = new ArrayList<>();
		//如果各科目的预算分配记录为零(无科目预算)
		if (budgetforSubs.size() <= 0){
			BudgetDetailVo budgetDetailVo = new BudgetDetailVo();
			budgetDetailVo.setYearAmount(budgetTotal.getBudgetAmount());  //年度预算
			budgetDetailVo.setLeftYearAmount(budgetTotal.getBudgetAmount()); //年度可用预算
			budgetDetailVo.setYearRate(Double.parseDouble(String.format("%.2f", actualUse/budgetTotal.getBudgetAmount()*100))); //年度预算额实际使用比例
			//是否有执行计划
			if (budgetTotal.getPlanId() != null){
				//获取到执行计划列表
				List<TPlanDetail> planDetailList = detailMapper.getPlanDetailByPlanId(budgetTotal.getPlanId());
				planDetailList.stream().forEach(x -> {
					//月度计划
					Double monthOrSeasonAmount = 0.0;
					if (x.getType() == 0 ){
						if (x.getPlanMonth() == month ){
							//月度或者季度预算
							monthOrSeasonAmount = budgetTotal.getBudgetAmount()*(x.getPlanRate())/100;
							//本月或者本季度计划使用比例
							budgetDetailVo.setPlanMonthOrSeasonRate(x.getPlanRate());
							//本月或者本季度实际使用比例
							budgetDetailVo.setActualMonthOrSeasonRate(Double.parseDouble(String.format("%.2f", actualUse/budgetTotal.getBudgetAmount()*100)));
							//月度或者季度预算
							budgetDetailVo.setMonthOrSeasonAmount(monthOrSeasonAmount);
							//月度或者季度剩余预算
							budgetDetailVo.setLeftMonthOrSeasonAmount(monthOrSeasonAmount-actualUse);
						}
						//季度计划
					}else{
						if (x.getPlanQuarter() == season ){
							//月度或者季度预算
							monthOrSeasonAmount = budgetTotal.getBudgetAmount()*(x.getPlanRate())/100;
							//本月或者本季度计划使用比例
							budgetDetailVo.setPlanMonthOrSeasonRate(x.getPlanRate());
							//本月或者本季度实际使用比例
							budgetDetailVo.setActualMonthOrSeasonRate(Double.parseDouble(String.format("%.2f", actualUse/budgetTotal.getBudgetAmount()*100)));
							//月度或者季度预算
							budgetDetailVo.setMonthOrSeasonAmount(monthOrSeasonAmount);
							//月度或者季度剩余预算
							budgetDetailVo.setLeftMonthOrSeasonAmount(monthOrSeasonAmount-actualUse);
						};
					}
				});
			}
			budgetDetailVos.add(budgetDetailVo);
			return budgetDetailVos;
		}else {
			budgetforSubs.stream().forEach(x -> {
				BudgetDetailVo budgetDetailVo = new BudgetDetailVo();
				//计算已经使用过的科目预算
				//标识，判断当前科目预算是否已经使用过(默认为未使用)
				Boolean flag = false;
				if (list.size()>0){
					for (BudgetDetailVo t :list) {
						if (t.getSubjectId() == x.getSubjectId()){
							budgetDetailVo.setSubjectId(t.getSubjectId());
							budgetDetailVo.setSubName(t.getSubName());
							budgetDetailVo.setActualUse(t.getActualUse());  //已用预算
							budgetDetailVo.setYearAmount(x.getBudgetAmount());  //年度预算
							budgetDetailVo.setLeftYearAmount(x.getBudgetAmount() - t.getActualUse()); //年度可用预算
							budgetDetailVo.setYearRate(Double.parseDouble(String.format("%.2f",  t.getActualUse()/x.getBudgetAmount()*100))); //年度预算额实际使用比例
							if (x.getPlanId() != null){
								//获取到执行计划列表
								List<TPlanDetail> planDetailList = detailMapper.getPlanDetailByPlanId(x.getPlanId());
								planDetailList.stream().forEach(planDetail -> {
									Double monthOrSeasonAmount = 0.0;
									//月度计划
									if (planDetail.getType() == 0 ){
										if (planDetail.getPlanMonth() == month ){
											//月度或者季度计划预算
											monthOrSeasonAmount = x.getBudgetAmount()*(planDetail.getPlanRate())/100;
											//本月或者本季度计划使用比例
											budgetDetailVo.setPlanMonthOrSeasonRate(planDetail.getPlanRate());
											//本月或者本季度实际使用比例
											budgetDetailVo.setActualMonthOrSeasonRate(Double.parseDouble(String.format("%.2f", t.getActualUse()/x.getBudgetAmount()*100)));
											//月度或者季度预算
											budgetDetailVo.setMonthOrSeasonAmount(monthOrSeasonAmount);
											//月度或者季度剩余预算
											budgetDetailVo.setLeftMonthOrSeasonAmount(monthOrSeasonAmount-t.getActualUse());
										}
										//季度计划
									}else{
										if (planDetail.getPlanQuarter() == season ){
											//月度或者季度预算
											monthOrSeasonAmount = x.getBudgetAmount()*(planDetail.getPlanRate())/100;
											//本月或者本季度计划使用比例
											budgetDetailVo.setPlanMonthOrSeasonRate(planDetail.getPlanRate());
											//本月或者本季度实际使用比例
											budgetDetailVo.setActualMonthOrSeasonRate(Double.parseDouble(String.format("%.2f", t.getActualUse()/x.getBudgetAmount()*100)));
											//月度或者季度预算
											budgetDetailVo.setMonthOrSeasonAmount(monthOrSeasonAmount);
											//月度或者季度剩余预算
											budgetDetailVo.setLeftMonthOrSeasonAmount(monthOrSeasonAmount-t.getActualUse());
										};
									}
								});
							}
							flag = true;
							budgetDetailVos.add(budgetDetailVo);
						}
					}
				}
				if (!flag){
					//计算未使用的科目预算
					budgetDetailVo.setSubjectId(x.getSubjectId());
					budgetDetailVo.setSubName(x.getSubName());
					budgetDetailVo.setActualUse(actualUse);  //已用预算
					budgetDetailVo.setYearAmount(x.getBudgetAmount());  //年度预算
					budgetDetailVo.setLeftYearAmount(x.getBudgetAmount() - actualUse); //年度可用预算
					budgetDetailVo.setYearRate(Double.parseDouble(String.format("%.2f", actualUse/x.getBudgetAmount()*100))); //年度预算额实际使用比例
					if (x.getPlanId() != null){
						//获取到执行计划列表
						List<TPlanDetail> planDetailList = detailMapper.getPlanDetailByPlanId(x.getPlanId());
						planDetailList.stream().forEach(planDetail -> {
							Double monthOrSeasonAmount = 0.0;
							//月度计划
							if (planDetail.getType() == 0 ){
								if (planDetail.getPlanMonth() == month ){
									//月度或者季度计划预算
									monthOrSeasonAmount = x.getBudgetAmount()*(planDetail.getPlanRate())/100;
									//本月或者本季度计划使用比例
									budgetDetailVo.setPlanMonthOrSeasonRate(planDetail.getPlanRate());
									//本月或者本季度实际使用比例
									budgetDetailVo.setActualMonthOrSeasonRate(Double.parseDouble(String.format("%.2f", actualUse/x.getBudgetAmount()*100)));
									//月度或者季度预算
									budgetDetailVo.setMonthOrSeasonAmount(monthOrSeasonAmount);
									//月度或者季度剩余预算
									budgetDetailVo.setLeftMonthOrSeasonAmount(monthOrSeasonAmount-actualUse);
								}
								//季度计划
							}else{
								if (planDetail.getPlanQuarter() == season ){
									//月度或者季度预算
									monthOrSeasonAmount = x.getBudgetAmount()*(planDetail.getPlanRate())/100;
									//本月或者本季度计划使用比例
									budgetDetailVo.setPlanMonthOrSeasonRate(planDetail.getPlanRate());
									//本月或者本季度实际使用比例
									budgetDetailVo.setActualMonthOrSeasonRate(Double.parseDouble(String.format("%.2f", actualUse/x.getBudgetAmount()*100)));
									//月度或者季度预算
									budgetDetailVo.setMonthOrSeasonAmount(monthOrSeasonAmount);
									//月度或者季度剩余预算
									budgetDetailVo.setLeftMonthOrSeasonAmount(monthOrSeasonAmount-actualUse);
								};
							}
						});
					}
					budgetDetailVos.add(budgetDetailVo);
				}
			});
		}
		return budgetDetailVos;
	}


	@SuppressWarnings("all")
	@Override
	public List<BudgetDetailVo> budgetPublic2(Integer orgId) {
			Calendar date = Calendar.getInstance();
			int year = date.get(Calendar.YEAR);
			int month=date.get(Calendar.MONTH)+1;
			int season=CalSeason(month);
			List<BudgetDetailVo> list = allocationMapper.budgetPublicByYear(orgId,year,new Date());
			//查询出当前所有预算分配记录
			List<TBudgetAllocationsVo> budgetAllocation = allocationMapper.getBudgetAllocation(orgId, null,year);
			//如果上级给下级分配了科目预算，
			//获取总额的预算分配记录（只有一条）
			TBudgetAllocationsVo budgetTotal = budgetAllocation.stream().filter(item -> item.getBudgetType() == 0).collect(Collectors.toList()).get(0);
			//获取各科目的预算分配记录
			List<TBudgetAllocationsVo> budgetforSubs = budgetAllocation.stream().filter(item -> item.getBudgetType() == 1).collect(Collectors.toList());
			//可用预算(默认0.0)
			Double actualUse = 0.0;

			ArrayList<BudgetDetailVo> budgetDetailVos = new ArrayList<>();
			//如果各科目的预算分配记录为零(无科目预算)
			if (budgetforSubs.size() <= 0){
				BudgetDetailVo budgetDetailVo = new BudgetDetailVo();
				//如果当前科目年度定义科目
				if (budgetTotal.getBudgetCategory() == 1){
					budgetDetailVo.setType(budgetTotal.getBudgetCategory());
					budgetDetailVo.setYearAmount(budgetTotal.getBudgetAmount()-actualUse);  //年度预算
					budgetDetailVo.setLeftYearAmount(budgetTotal.getBudgetAmount()); //年度可用预算
					budgetDetailVo.setYearRate(Double.parseDouble(String.format("%.2f", actualUse/budgetTotal.getBudgetAmount()*100))); //年度预算额实际使用比例
				}else if(budgetTotal.getBudgetCategory() == 2){
					budgetDetailVo.setType(budgetTotal.getBudgetCategory());
					budgetDetailVo.setMonthAmount(budgetTotal.getBudgetAmount());
					budgetDetailVo.setLeftMonthAmount(budgetTotal.getBudgetAmount()-actualUse);
					budgetDetailVo.setMonthRate(actualUse/budgetTotal.getBudgetAmount()*100);
				}
				//是否有执行计划
				if (budgetTotal.getPlanId() != null){
					//获取到执行计划列表
					List<TPlanDetail> planDetailList = detailMapper.getPlanDetailByPlanId(budgetTotal.getPlanId());
					planDetailList.stream().forEach(x -> {
						//月度计划
						Double monthOrSeasonAmount = 0.0;
						if (x.getType() == 0 ){
							if (x.getPlanMonth() == month ){
								//月度或者季度预算
								monthOrSeasonAmount = budgetTotal.getBudgetAmount()*(x.getPlanRate())/100;
								//本月或者本季度计划使用比例
								budgetDetailVo.setPlanMonthOrSeasonRate(x.getPlanRate());
								//本月或者本季度实际使用比例
								budgetDetailVo.setActualMonthOrSeasonRate(Double.parseDouble(String.format("%.2f", actualUse/budgetTotal.getBudgetAmount()*100)));
								//月度或者季度预算
								budgetDetailVo.setMonthOrSeasonAmount(monthOrSeasonAmount);
								//月度或者季度剩余预算
								budgetDetailVo.setLeftMonthOrSeasonAmount(monthOrSeasonAmount-actualUse);
							}
							//季度计划
						}else{
							if (x.getPlanQuarter() == season ){
								//月度或者季度预算
								monthOrSeasonAmount = budgetTotal.getBudgetAmount()*(x.getPlanRate())/100;
								//本月或者本季度计划使用比例
								budgetDetailVo.setPlanMonthOrSeasonRate(x.getPlanRate());
								//本月或者本季度实际使用比例
								budgetDetailVo.setActualMonthOrSeasonRate(Double.parseDouble(String.format("%.2f", actualUse/budgetTotal.getBudgetAmount()*100)));
								//月度或者季度预算
								budgetDetailVo.setMonthOrSeasonAmount(monthOrSeasonAmount);
								//月度或者季度剩余预算
								budgetDetailVo.setLeftMonthOrSeasonAmount(monthOrSeasonAmount-actualUse);
							};
						}
					});
				}
				budgetDetailVos.add(budgetDetailVo);
				return budgetDetailVos;
			}else {
				budgetforSubs.stream().forEach(x -> {
					BudgetDetailVo budgetDetailVo = new BudgetDetailVo();
					//计算已经使用过的科目预算
					//标识，判断当前科目预算是否已经使用过(默认为未使用)
					Boolean flag = false;
					if (list.size()>0){
						for (BudgetDetailVo t :list) {
							if (t.getSubjectId() == x.getSubjectId()){
								//如果是年度科目
								if (x.getBudgetCategory() == 1){
									budgetDetailVo.setType(x.getBudgetCategory());
									budgetDetailVo.setSubjectId(t.getSubjectId());
									budgetDetailVo.setSubName(t.getSubName());
									budgetDetailVo.setActualUse(t.getActualUse());  //已用预算
									budgetDetailVo.setYearAmount(x.getBudgetAmount());  //年度预算
									budgetDetailVo.setLeftYearAmount(x.getBudgetAmount() - t.getActualUse()); //年度可用预算
									budgetDetailVo.setYearRate(Double.parseDouble(String.format("%.2f",  t.getActualUse()/x.getBudgetAmount()*100))); //年度预算额实际使用比例
								//如果是月度科目
								}else if (x.getBudgetCategory() == 2){
									budgetDetailVo.setSubjectId(t.getSubjectId());
									budgetDetailVo.setSubName(t.getSubName());
									budgetDetailVo.setType(x.getBudgetCategory());
									budgetDetailVo.setMonthAmount(x.getBudgetAmount());
									budgetDetailVo.setLeftMonthAmount(x.getBudgetAmount() - t.getActualUse());
									budgetDetailVo.setMonthRate(Double.parseDouble(String.format("%.2f",  t.getActualUse()/x.getBudgetAmount()*100)));
								}
								if (x.getPlanId() != null){
									//获取到执行计划列表
									List<TPlanDetail> planDetailList = detailMapper.getPlanDetailByPlanId(x.getPlanId());
									planDetailList.stream().forEach(planDetail -> {
										Double monthOrSeasonAmount = 0.0;
										//月度计划
										if (planDetail.getType() == 0 ){
											if (planDetail.getPlanMonth() == month ){
												//月度或者季度计划预算
												monthOrSeasonAmount = x.getBudgetAmount()*(planDetail.getPlanRate())/100;
												//本月或者本季度计划使用比例
												budgetDetailVo.setPlanMonthOrSeasonRate(planDetail.getPlanRate());
												//本月或者本季度实际使用比例
												budgetDetailVo.setActualMonthOrSeasonRate(Double.parseDouble(String.format("%.2f", t.getActualUse()/x.getBudgetAmount()*100)));
												//月度或者季度预算
												budgetDetailVo.setMonthOrSeasonAmount(monthOrSeasonAmount);
												//月度或者季度剩余预算
												budgetDetailVo.setLeftMonthOrSeasonAmount(monthOrSeasonAmount-t.getActualUse());
											}
											//季度计划
										}else{
											if (planDetail.getPlanQuarter() == season ){
												//月度或者季度预算
												monthOrSeasonAmount = x.getBudgetAmount()*(planDetail.getPlanRate())/100;
												//本月或者本季度计划使用比例
												budgetDetailVo.setPlanMonthOrSeasonRate(planDetail.getPlanRate());
												//本月或者本季度实际使用比例
												budgetDetailVo.setActualMonthOrSeasonRate(Double.parseDouble(String.format("%.2f", t.getActualUse()/x.getBudgetAmount()*100)));
												//月度或者季度预算
												budgetDetailVo.setMonthOrSeasonAmount(monthOrSeasonAmount);
												//月度或者季度剩余预算
												budgetDetailVo.setLeftMonthOrSeasonAmount(monthOrSeasonAmount-t.getActualUse());
											};
										}
									});
								}
								flag = true;
								budgetDetailVos.add(budgetDetailVo);
							}
						}
					}
					if (!flag){
						if (x.getBudgetCategory() == 1){
							//计算未使用的科目预算
							budgetDetailVo.setSubjectId(x.getSubjectId());
							budgetDetailVo.setSubName(x.getSubName());
							budgetDetailVo.setActualUse(actualUse);  //已用预算
							budgetDetailVo.setYearAmount(x.getBudgetAmount());  //年度预算
							budgetDetailVo.setLeftYearAmount(x.getBudgetAmount() - actualUse); //年度可用预算
							budgetDetailVo.setYearRate(Double.parseDouble(String.format("%.2f", actualUse/x.getBudgetAmount()*100))); //年度预算额实际使用比例

						}else if (x.getBudgetCategory() == 2){
							budgetDetailVo.setSubjectId(x.getSubjectId());
							budgetDetailVo.setSubName(x.getSubName());
							budgetDetailVo.setType(x.getBudgetCategory());
							budgetDetailVo.setMonthAmount(x.getBudgetAmount());
							budgetDetailVo.setLeftMonthAmount(x.getBudgetAmount()-actualUse);
							budgetDetailVo.setMonthRate(Double.parseDouble(String.format("%.2f",  actualUse/x.getBudgetAmount()*100)));
						}
						if (x.getPlanId() != null){
							//获取到执行计划列表
							List<TPlanDetail> planDetailList = detailMapper.getPlanDetailByPlanId(x.getPlanId());
							planDetailList.stream().forEach(planDetail -> {
								Double monthOrSeasonAmount = 0.0;
								//月度计划
								if (planDetail.getType() == 0 ){
									if (planDetail.getPlanMonth() == month ){
										//月度或者季度计划预算
										monthOrSeasonAmount = x.getBudgetAmount()*(planDetail.getPlanRate())/100;
										//本月或者本季度计划使用比例
										budgetDetailVo.setPlanMonthOrSeasonRate(planDetail.getPlanRate());
										//本月或者本季度实际使用比例
										budgetDetailVo.setActualMonthOrSeasonRate(Double.parseDouble(String.format("%.2f", actualUse/x.getBudgetAmount()*100)));
										//月度或者季度预算
										budgetDetailVo.setMonthOrSeasonAmount(monthOrSeasonAmount);
										//月度或者季度剩余预算
										budgetDetailVo.setLeftMonthOrSeasonAmount(monthOrSeasonAmount-actualUse);
									}
									//季度计划
								}else{
									if (planDetail.getPlanQuarter() == season ){
										//月度或者季度预算
										monthOrSeasonAmount = x.getBudgetAmount()*(planDetail.getPlanRate())/100;
										//本月或者本季度计划使用比例
										budgetDetailVo.setPlanMonthOrSeasonRate(planDetail.getPlanRate());
										//本月或者本季度实际使用比例
										budgetDetailVo.setActualMonthOrSeasonRate(Double.parseDouble(String.format("%.2f", actualUse/x.getBudgetAmount()*100)));
										//月度或者季度预算
										budgetDetailVo.setMonthOrSeasonAmount(monthOrSeasonAmount);
										//月度或者季度剩余预算
										budgetDetailVo.setLeftMonthOrSeasonAmount(monthOrSeasonAmount-actualUse);
									};
								}
							});
						}
						budgetDetailVos.add(budgetDetailVo);
					}
				});
			}
			return budgetDetailVos;
		}

	private int CalSeason(int month) {
        BigDecimal b1 = new BigDecimal(month);
        BigDecimal b2 = new BigDecimal(3);
        double b3 = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
        if (b3 > 0 && b3 <= 1) {
            return 1;
        } else if (b3 > 1 && b3 <= 2) {
            return 2;
        } else if (b3 > 2 && b3 <= 3) {
            return 3;
        } else {
            return 4;
        }
    }

    /**
     * 订单结算时的预算结算
     *
     * @param judgBudgetVoList
     * @return 返回结果定义：
     * 100   -> 不能下单!    R.error().put("code",100).put("subNameList1",subNameList1);
     * 101   ->
     * 102   ->
     * 103   ->
     * 104   ->
     * !
     * 105   ->
     * <p>
     * //
     * 200   -> 成功
     */
    @Override
    public R judgeBudget(List<JudgBudgetVo> judgBudgetVoList) {
        //1.按科目和执行计划 2.按总额和科目 3.按总额和执行计划 4.按总额
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH) + 1;
        int season = CalSeason(month);
        int orgId = judgBudgetVoList.get(0).getOrgId();//当前部门组织
		Integer subjectId = judgBudgetVoList.get(0).getSubjectId();//当前订单的科目id
		Double useAmount = judgBudgetVoList.get(0).getUseAmount();//当前订单的使用额
        TSubject subject = subMapper.selectByPrimaryKey(subjectId);
        if(subject.getType()==1){//年度预算
            TBudgetAllocations TBudgetAllocations = allocationMapper.findSubjetIdByOrgAndYear(orgId, year);//部门的情况
            Double budgetAmount = TBudgetAllocations.getBudgetAmount();//1.当前部门的总额
            if(budgetAmount==0){
                return R.ok(106,"当前部门没有预算额，不能下单");
            }
            Double usedDeptAmount = allocationMapper.findUsedAmount(orgId, year, new Date());//1.当前部门已使用金额
            Integer subCount = allocationMapper.findSubjetCount(orgId, year);//是否按科目分配
            if(subCount>0){//按科目
                Double accumulativeUse = allocationMapper.budgetAccumulative(orgId, year, new Date(), subjectId);//当前订单历史使用额
                if(accumulativeUse == null) {
                    accumulativeUse = 0.0;
                }
                com.entities.TBudgetAllocations budgetAllocations = allocationMapper.findPlan(orgId, year, 1, subjectId);//当前科目的情况
                if(budgetAllocations.getBudgetAmount()<(accumulativeUse+useAmount)){
                    return R.ok(103,"科目已用预算超过年度预算额，不能下单");
                }else{
                    //看执行计划
                    if(budgetAllocations.getPlanId()!=null){//科目有没有执行计划
                        int planId=budgetAllocations.getPlanId();
                        TPlanDetailExample example=new TPlanDetailExample();
                        Criteria criteria = example.createCriteria();
                        criteria.andPlanIdEqualTo(planId);
                        //根据planId查找执行计划
                        List<TPlanDetail> list2 = detailMapper.selectByExample(example);
                        for(TPlanDetail t:list2) {
                            Double monthOrSeasonAmount =0.0;
                            //月度计划
                            if(t.getType()==0) {
                                if(t.getPlanMonth()==month) {
                                    //月度累计预算额
                                    monthOrSeasonAmount = budgetAllocations.getBudgetAmount()*(t.getPlanRate())/100;
                                    if(monthOrSeasonAmount<(useAmount+accumulativeUse)) {
                                        return R.ok(104,"按科目，月度执行计划超额");
                                    }
                                }
                            }
                            //季度计划
                            else {
                                if(t.getPlanQuarter()==season) {
                                    //季度累计预算额
                                    monthOrSeasonAmount = budgetAllocations.getBudgetAmount()*(t.getPlanRate())/100;
                                    if(monthOrSeasonAmount<(useAmount+accumulativeUse)) {
                                        return R.ok(105,"按科目，季度执行计划超额");
                                    }
                                }
                            }
                        }
                    }
                };
            }else{//按总额
                if(budgetAmount<(usedDeptAmount+useAmount)){
                    return R.ok(100,"已用预算已超年度预算总额，不能下单");
                }else{
                    if(TBudgetAllocations.getPlanId()!=null) {
                        TPlanDetailExample example = new TPlanDetailExample();
                        Criteria criteria = example.createCriteria();
                        criteria.andPlanIdEqualTo(TBudgetAllocations.getPlanId());
                        //根据planId查找执行计划
                        List<TPlanDetail> list2 = detailMapper.selectByExample(example);
                        for (TPlanDetail t : list2) {
                            Double monthOrSeasonAmount = 0.0;
                            //月度计划
                            if (t.getType() == 0) {
                                if (t.getPlanMonth() == month) {
                                    //月度累计预算额
                                    monthOrSeasonAmount = budgetAmount * (t.getPlanRate()) / 100;
                                    if (monthOrSeasonAmount < (useAmount + usedDeptAmount)) {
                                        return R.ok(101, "按总额，月度计划超额");
                                    }
                                }
                            }else {//季度计划
                                if (t.getPlanQuarter() == season) {
                                    //季度累计预算额
                                    monthOrSeasonAmount = budgetAmount * (t.getPlanRate()) / 100;
                                    if (monthOrSeasonAmount < (useAmount + usedDeptAmount)) {
                                        return R.ok(102, "按总额，季度计划超额");
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return R.ok(200,"成功");
        }else{//月度
            TBudgetAllocations tBudgetAllocations = allocationMapper.findSubjetIdByOrgAndYearAndMonth(orgId, year, month);
            if(tBudgetAllocations==null){
                return R.ok(200,"成功");
            }else{
                Integer subCount = allocationMapper.findSubjetMonthCount(orgId, year,month);//是否按科目分配
                if(subCount>0){//按科目
                    //当前科目月度已使用金额
                    Double usedSubMonthAmount = allocationMapper.findUsedAmountMonthSub(orgId, year, month,subjectId);
                    //当前科目月度预算额
                    Double subMonthAmount = allocationMapper.findSubMonthAmount(orgId, year, month, subjectId);
                    if(subMonthAmount<(usedSubMonthAmount+useAmount)){
                        return R.ok(107,"当前科目月度预算额已超，不能下单");
                    }
                }else{//按总额
                    Double budgetAmount = tBudgetAllocations.getBudgetAmount();//1.当前月度总额
                    Double usedDeptAmount = allocationMapper.findUsedAmountMonth(orgId, year,month);//1.当前部门当前月已使用金额
                    if(budgetAmount<(usedDeptAmount+useAmount)){
                        return R.ok(108,"当前月度预算额已超，不能下单");
                    }
                }
            }
            return R.ok(200,"成功");
        }

    }


	/**
	 * 查看预算使用详细
	 * @param pageNum,
	 * @param pageSize,
	 * @param searchKey
	 * @param budgetYear
	 * @param subjectId
	 * @param orgId 组织id
	 * @param userId 采购员用户id
	 * @return
	 */
	@Override
	public PageUtils budgetDetailUseList(Map<String, Object> map) {
		mallQuery query=new mallQuery(map);
		//列表信息
		List<OrderVo> list=allocationMapper.budgetDetailUseList(query);

		//记录数信息
		int total=allocationMapper.countBudgetDetailUse(query);
		return new PageUtils(list, total);
	}

	/**
	 * 查看进度
	 * @param budgetYear
	 * @param subjectId
	 * @param orgId 组织id
	 * @param planId
	 * @param budegtAmount 年度预算额
	 * @return
	 */
	@Override
	public List<TPlanDetail> findSchedule(Map<String, Object> map) {
		Calendar date = Calendar.getInstance();
		//获取当前月份
		int month=date.get(Calendar.MONTH)+1;
		//获取当前季度
		int season=CalSeason(month);
		
		Integer planId=Integer.parseInt(map.get("planId").toString()) ;
		Double budegtAmount=Double.parseDouble(map.get("budegtAmount").toString());
		TPlanDetailExample example=new TPlanDetailExample();
		Criteria criteria = example.createCriteria();
		criteria.andPlanIdEqualTo(planId);
	//根据planId查找执行计划
	List<TPlanDetail> list = detailMapper.selectByExample(example);
		map.remove("planId");
		map.remove("budegtAmount");
		for(TPlanDetail t:list) 
		{
			Integer type = t.getType();
			if(type==0)//月度计划 
			{
				if(t.getPlanMonth()<=month) {
					map.put("planMonth", t.getPlanMonth());
					//查看每个月所用预算
					Double monthUse=allocationMapper.actualUseByMonth(map);
					if(monthUse != null){
					t.setActualUseRate(Double.parseDouble(String.format("%.2f", monthUse*100/budegtAmount)));
					}
				}
				
			}
			else 
			{
				if(t.getPlanQuarter()<=season) {
					map.put("planQuarter", t.getPlanQuarter());
					//查看每个季度所用预算
					Double quarterUse=allocationMapper.actualUseByQuarter(map);
					if(quarterUse != null){
						t.setActualUseRate(Double.parseDouble(String.format("%.2f", quarterUse*100/budegtAmount)));
					}
				}
			}
			
		}
		return list;
	}

    /**
     * 下拉框科目
     * @return
     */
    @Override
    public List<Map<String, Object>> findSubjectList(Integer currOrgId) {
        return allocationMapper.findSubjectList(currOrgId);
    }

    /**
     * 订单审核时的预算使用情况
     *
//     * @param orgId:当前登录人组织id
//     * @param subIdList:科目集合
//     * @param orderDate:下单时间
     * @return
     */
    @Override
    public List<BudgetDetailVo> budgetOrder(BudgetOrderVo vo) {
        List<BudgetDetailVo> list = new ArrayList<>();
        //组织id
        Integer orgId = vo.getOrgId();
        //下单日期
        Date orderDate = vo.getOrderDate();
        //科目id
        List<Integer> subIdList = vo.getSubIdList();
        for (Integer subjectId : subIdList) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(orderDate);
            //获取年份
            int year = cal.get(Calendar.YEAR);
            //获取月份
            int month = cal.get(Calendar.MONTH) + 1;
            //获取季节
            int season = CalSeason(month);
            //1.根据部门，科目，下单时间查出本科目年度已用预算额
            BudgetDetailVo b = allocationMapper.budgetOrder(orgId, subjectId, orderDate);
            if (b != null) {
                //截至到下单时间实际使用金额
                Double actualUse = b.getActualUse();
                //2.根据部门，年份，科目查出年度预算额
                TBudgetAllocationsVo allocation = allocationMapper.getBudgetAllocation(orgId, subjectId,year ).get(0);
                if (allocation != null) {
                    //执行计划
                    Integer planId = allocation.getPlanId();
                    //年度预算额
                    Double yearAmount = allocation.getBudgetAmount();
                    b.setYearAmount(yearAmount);
                    b.setLeftYearAmount(yearAmount - actualUse);
                    b.setYearRate(Double.parseDouble(String.format("%.2f", actualUse / yearAmount * 100)));
                    if (planId != null) {//月度/季度预算额
                        List<TPlanDetail> planDetailList = detailMapper.getPlanDetailByPlanId(planId);
                        if (planDetailList != null && planDetailList.size() > 0) {
                            for (TPlanDetail t : planDetailList) {
                                Double monthOrSeasonAmount = 0.0;
                                //月度计划
                                if (t.getType() == 0) {
                                    if (t.getPlanMonth() == month) {
                                        //截至本月可用预算总额
                                        monthOrSeasonAmount = yearAmount * (t.getPlanRate()) / 100;
                                        //截止到当月可使用比例
                                        b.setPlanMonthOrSeasonRate(t.getPlanRate());
                                        //截止到当月实际使用比例
                                        b.setActualMonthOrSeasonRate(Double.parseDouble(String.format("%.2f", actualUse / yearAmount * 100)));
                                        b.setMonthOrSeasonAmount(monthOrSeasonAmount);  //截止当前月可用预算总额
                                        b.setLeftMonthOrSeasonAmount(monthOrSeasonAmount - actualUse);//截止当前月剩余可用预算总额
                                    }
                                }
                                //季度计划
                                else {
                                    if (t.getPlanQuarter() == season) {
                                        //截至本季可用预算总额
                                        monthOrSeasonAmount = yearAmount * (t.getPlanRate()) / 100;
                                        b.setPlanMonthOrSeasonRate(t.getPlanRate());
                                        b.setActualMonthOrSeasonRate(Double.parseDouble(String.format("%.2f", actualUse * 100 / yearAmount)));
                                        b.setMonthOrSeasonAmount(monthOrSeasonAmount);
                                        b.setLeftMonthOrSeasonAmount(monthOrSeasonAmount - actualUse);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            list.add(b);
        }
        return list;
    }

	@Override
	public OrgPlan plans(long orgId,Integer orgId1, Integer subjectId,int year, ReqPage reqPage) {
		ExecutionPlanDO defaultPlan = executionPlanDB.findDefaultPlan(orgId, year);//查看当前登录人的执行计划(默认)
		
		PageHelper.startPage(reqPage.getPageNum(), reqPage.getLimit());
		List<ExecutionPlanDO> plans = executionPlanDB.findPlans(orgId);
		OrgPlan plan = new OrgPlan();

		
		ExecutionPlanDO myPlan ;
		if(subjectId == null || subjectId == 0) {
			myPlan = executionPlanDB.findDefaultPlan(orgId1, year);
		}else {
			myPlan = executionPlanDB.findDefaultPlan2(orgId1, year,subjectId);
		}
		
		if(myPlan != null && myPlan.getId() != null) {
			plan.setSelectPlan(myPlan);
		}else {
			plan.setSelectPlan(defaultPlan);
		}
		plan.setMyExecutionPlanDOS(plans);
		if(defaultPlan==null) {
			return plan;
		}
		plan.setDefualtExecutionPlanDO(defaultPlan);
		
		return plan;
	}

	@Override
	public boolean modifyDefaultPlan(Integer orgId, Integer subjectId, Integer planId, Integer year) {
		if(subjectId == null) {
			executionPlanDB.modifyBudgetDefaultPlanByOrgId(orgId,planId,year);
		}else {
			executionPlanDB.modifyBudgetMyPlanByOrgId(orgId,subjectId,planId,year);
		}
		return true;
	}
	
	@Override
	public TBudgetAllocations findAllocation2(Map<String, Object> map) {
		return allocationMapper.findAllocation2(map);
	}

	//新增预算2
	@Override
	@Transactional
	public void addBudget(AddBudgetVo prams) {
		
		//查找创建组织的默认执行计划
		ExecutionPlanDO plan = executionPlanDB.findDefaultPlan(prams.getpId(), prams.getBudgetYear());
		Integer planId=0;
			if(plan !=null) {
				planId=plan.getId();
			}else {
				planId=null;
			}
		
		List<UpBudget> list = prams.getList();
		
			for(UpBudget u:list) {
					u.setPlanId(planId);
				List<ChildSub> list2 = u.getChildSubs();
				for(ChildSub c :list2) {
					if(c.getSubAmount() !=null) {
						c.setPlanId(planId);
						//2.分配科目预算
						allocationMapper.add2(c);
					}
				}
				
				
			}
			
			//1.分配部门总额
			allocationMapper.add1(list);
		
		
	}
	/**
	 * 检查部门科目是否被分配
	 * @param query
	 * @return
	 */
	@Override
	public int checkAllocation(Integer orgId) {
		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);
		return allocationMapper.checkAllocation(orgId,year);
	}
	@Override
	public List<Map<Long, String>> findPrucUsers(Map<String, String> map) {
		return allocationMapper.findPrucUsers(map);
	}
	/**
	 * 叶子结点新增科目预算
	 */
	@Override
	public void addBudgetBySub(List<ChildSub> child) {
		for(ChildSub c:child) {
			ExecutionPlanDO plan = executionPlanDB.findDefaultPlan(c.getpId(), c.getBudgetYear());
			Integer planId=0;
			if(plan !=null) {
				planId=plan.getId();
			}else {
				planId=null;
			}
			c.setPlanId(planId);
			//根据科目分配
			allocationMapper.add2(c);
		}
		
	}

	
	@Override
	public int checkAllocation2(Integer orgId,Integer year) {
		return allocationMapper.checkAllocation2(orgId,year);
	}




    //添加月度预算额
	@Override
	public void addBudgetMonth(List<BudgetMonthVO> budgetMonthVO) {
        List<TBudgetAllocations> list = new ArrayList<>();
        if(budgetMonthVO.size()>0){
            for (BudgetMonthVO bm:budgetMonthVO){
                TBudgetAllocations budgetAllocations = new TBudgetAllocations();
                BeanUtils.copyProperties(bm,budgetAllocations);
                budgetAllocations.setBudgetType(0);
                budgetAllocations.setBudgetCategory(2);
                budgetAllocations.setCreateDate(new Date());
                if(!StringUtils.isEmpty(bm.getBudgetAmount())){
                    list.add(budgetAllocations);
					List<BudgetMonthSubVO> budgetMonthSubList = bm.getBudgetMonthSubList();
                    if(budgetMonthSubList.size()>0){
						for (BudgetMonthSubVO bms:budgetMonthSubList){
							TBudgetAllocations budgetAllocations2 = new TBudgetAllocations();
							BeanUtils.copyProperties(budgetAllocations,budgetAllocations2);
							budgetAllocations2.setBudgetType(1);
							budgetAllocations2.setBudgetAmount(bms.getBudgetAmount());
							budgetAllocations2.setSubjectId(bms.getSubjectId());
							if(!StringUtils.isEmpty(bms.getBudgetAmount())){
								list.add(budgetAllocations2);
							}
						}
               		 }
				}
            }
        }
		//添加预算额
        allocationMapper.insertBudgetMonth(list);
	}


    @Override
    public Double findBudgetAmount(Integer orgId, Integer month, Integer year) {
        Double budgetAmount = allocationMapper.findBudgetAmount(orgId, month, year);
        return budgetAmount;
    }



}
