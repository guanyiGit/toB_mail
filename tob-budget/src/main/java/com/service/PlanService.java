package com.service;

import java.util.List;
import java.util.Map;

import com.utils.PageUtils;
import com.vo.BudgetVO;

public interface PlanService {
	//列表
	PageUtils planList(Map<String, Object> map);

	/**
	 * 新增执行计划
	 * @param 
	 * @return
	 */
	int addPlan(BudgetVO budgetVO);

	//delete
	void deletePlan(Integer PlanId);

	//detail
	BudgetVO findDetail(Integer PlanId);
	//根据id得到执行计划详情
	List<Map> getPlanDetail(Integer id);
	//update
	void updatePlan(BudgetVO budget);

}
