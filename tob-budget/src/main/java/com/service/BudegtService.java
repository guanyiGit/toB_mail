package com.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.entities.TBudgetAllocations;
import com.entities.TPlanDetail;
import com.model.OrgPlan;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.ReqPage;
import com.vo.*;

public interface BudegtService {
	
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
	PageUtils budgetAllocationList(Map<String, Object> map);
	
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
	PageUtils budgetReceiveList(Map<String, Object> map);

	
	/**
	 * 预算使用列表
	 * @param pageNum,
	 * @param pageSize,
	 * @param searchKey
	 * @param budgetYear
	 * @param subjectId
	 * @param orgId 前端选择的组织id
	 * @param currOrgId 当前登录人组织
	 * @return
	 */
	PageUtils budgetUseList(Map<String, Object> map);
	
	/**
	 * 封装公共的预算使用接口
	 * @param orgId 登录人组织id
	 * @return
	 */
	List<BudgetDetailVo> budgetPublic(Integer orgId);

	/**
	 * 封装公共的预算使用接口(改)
	 * @param orgId 登录人组织id
	 * @return
	 */
	List<BudgetDetailVo> budgetPublic2(Integer orgId);
	
	/**
	 * 订单结算时的预算结算
	 * @param list
	 * @return
	 */
	R judgeBudget(List<JudgBudgetVo> judgBudgetVo);
	
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
	PageUtils budgetDetailUseList(Map<String, Object> map);

	/**
	 * 查看进度
	 * @param budgetYear
	 * @param subjectId
	 * @param orgId 组织id
	 * @param planId
	 * @param budegtAmount 年度预算额
	 * @return
	 */
	List<TPlanDetail> findSchedule(Map<String, Object> map);
	
	/**
	 * 下拉框科目
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findSubjectList(Integer currOrgId);
	
	/**
	 * 订单审核时的预算使用情况
	 * @param map
	 * @param orgId:当前登录人组织id
	 * @param subId:科目id
	 * @param orderDate:下单时间
	 * @return
	 */
	List<BudgetDetailVo> budgetOrder(BudgetOrderVo vo);

	/**
	 * 查看我的可选用执行计划列表
	 * @param orgId 机构id required
	 * @param year  年份  4位整数 required
	 * @param reqPage
	 * @return
	 */
	OrgPlan plans(long orgId,Integer orgId1, Integer subjectId,int year, ReqPage reqPage);

	/**
	 * 修改我的默认执行计划
	 *
	 * @param orgId
	 * @param planId
	 * @param year
	 * @param year2 
	 * @return
	 */
    boolean modifyDefaultPlan(Integer orgId, Integer subjectId, Integer planId, Integer year);
    
    /**
	 * 根据部门,年份查找预算
	 * @param map
	 * @return
	 */
    TBudgetAllocations findAllocation2(Map<String, Object> map);

    /**
     * 新增预算2
     * @param list
     */
	void addBudget(AddBudgetVo prams);
	
	/**
	 * 检查部门科目是否被分配
	 * @param query
	 * @return
	 */
	int checkAllocation(Integer orgId);
	
	/**
	 * 检查本部门是否给下级分配预算
	 * @param query
	 * @return
	 */
	int checkAllocation2(Integer orgId,Integer year);

	void addBudgetBySub(List<ChildSub> child);

	/**
	 * 查询预算明细里面的所有采购人员
	 * @param map
	 *           key:subjectId 可null
	 *           key:userId 可null
	 * @return 所有采购员:userID,userName
	 */
	List<Map<Long,String>> findPrucUsers(Map<String, String> map);



	void addBudgetMonth(List<BudgetMonthVO> budgetMonthVO);

	Double findBudgetAmount(@Param("orgId")Integer orgId,
							@Param("month")Integer month,
							@Param("year")Integer year
	);
}
