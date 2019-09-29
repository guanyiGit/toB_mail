package com.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.entities.TPlanDetailExample;
import com.vo.BudgetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.TExecutionPlanMapper;
import com.dao.TPlanDetailMapper;
import com.entities.TExecutionPlan;
import com.entities.TPlanDetail;
import com.service.PlanService;
import com.utils.PageUtils;
import com.utils.mallQuery;
@Service
public class PlanServiceImpl implements PlanService {


    @Autowired
	private TExecutionPlanMapper planMapper;
	@Autowired
	private TPlanDetailMapper planDetailMappper;



	//列表
	@Override
	public PageUtils planList(Map<String, Object> map) {
		mallQuery query=new mallQuery(map);
		List<TExecutionPlan> list=planMapper.list(query);
		int total=planMapper.count(query);
		return new PageUtils(list, total);
	}



	//新增
	@Override
	@Transactional
	public int addPlan(BudgetVO budgetVO) {
			TExecutionPlan plan=budgetVO.getPlan();
			plan.setCreateDate(new Date());
			int i = planMapper.insertSelective(plan);
			//自增id
			Integer planId=plan.getId();
			List<TPlanDetail> list=budgetVO.getPlanDetailList();
			if(list.size()>0){
				for(TPlanDetail p:list)
				{
					p.setCreateDate(new Date());
					p.setPlanId(planId);
				}
				planDetailMappper.addPlan(list);
			}
			return i;
	}


	@Override
    @Transactional
	public void deletePlan(Integer PlanId) {
		planMapper.deleteByPrimaryKey(PlanId);
        TPlanDetailExample tPlanDetailExample = new TPlanDetailExample();
        TPlanDetailExample.Criteria criteria = tPlanDetailExample.createCriteria();
        criteria.andPlanIdEqualTo(PlanId);
        planDetailMappper.deleteByExample(tPlanDetailExample);
	}


    @Override
    public BudgetVO findDetail(Integer PlanId) {
        BudgetVO budgetVO = new BudgetVO();
        TExecutionPlan plan = planMapper.selectByPrimaryKey(PlanId);
        budgetVO.setPlan(plan);
        TPlanDetailExample tPlanDetailExample = new TPlanDetailExample();
        TPlanDetailExample.Criteria criteria = tPlanDetailExample.createCriteria();
        criteria.andPlanIdEqualTo(PlanId);
        List<TPlanDetail> tPlanDetails = planDetailMappper.selectByExample(tPlanDetailExample);
        budgetVO.setPlanDetailList(tPlanDetails);
        return budgetVO;
    }


	@Override
	@Transactional
	public void updatePlan(BudgetVO budget) {
		planMapper.updateByPrimaryKeySelective(budget.getPlan());
		TPlanDetailExample tPlanDetailExample = new TPlanDetailExample();
		TPlanDetailExample.Criteria criteria = tPlanDetailExample.createCriteria();
		criteria.andPlanIdEqualTo(budget.getPlan().getId());
		planDetailMappper.deleteByExample(tPlanDetailExample);
		List<TPlanDetail> list=budget.getPlanDetailList();
		if(list.size()>0){
			for(TPlanDetail p:list)
			{
				p.setCreateDate(new Date());
				p.setPlanId(budget.getPlan().getId());
			}
			planDetailMappper.addPlan(list);
		}
	}
	@Override
	public List<Map> getPlanDetail(Integer id) {
		return planDetailMappper.getPlanDetail(id);
	}

}
