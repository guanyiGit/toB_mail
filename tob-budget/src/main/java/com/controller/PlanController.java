package com.controller;

import java.util.Map;

import com.vo.BudgetVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.entities.TSubject;
import com.service.PlanService;
import com.service.SubjectService;
import com.utils.PageUtils;
import com.utils.R;

@RestController
@RequestMapping("/biz/plan")
public class PlanController {
	private static Logger logger = LoggerFactory.getLogger(PlanController.class);
	@Autowired
	private PlanService planService;

	/**
	 * 新增执行计划
	 * @param
	 * @return
	 */
	@RequestMapping("/addPlan")
	@RequiresPermissions("/biz/sub/addPlan")
	public R addPlan( @RequestBody BudgetVO budgetVO) {
		int a=planService.addPlan(budgetVO);
		if(a>0) {
			return R.ok();
		}
		return R.error();
	}



//	/**
//	 * 删除科目
//	 * @param id 科目主键
//	 * @return
//	 */
//	@RequestMapping("/delSub")
//	@ResponseBody
//	@RequiresPermissions("/biz/sub/delSub")
//	public R delSub(Integer id) {
//		int a=subjectService.delSub(id);
//		if(a>0) {
//			return R.ok();
//		}
//		return R.error();
//	}
//	
//	
//	/**
//	 * 修改科目
//	 * @param id 科目主键
//	 * @return
//	 */
//	@RequestMapping("/editSub")
//	@ResponseBody
//	@RequiresPermissions("/biz/sub/editSub")
//	public R editSub(TSubject sub) {
//		int a=subjectService.editSub(sub);
//		if(a>0) {
//			return R.ok();
//		}
//		return R.error();
//	}


	/**
	 * 执行计划列表
	 * @param pageNum,
	 * @param pageSize,
	 * @param searchKey
	 * @return
	 */
	@RequestMapping("/planList")
	@RequiresPermissions("/biz/sub/planList")
	public PageUtils planList(@RequestParam Map<String,Object>map) {
		PageUtils page=null;
		try {
			page=planService.planList(map);
		} catch (Exception e) {
			logger.warn("查找执行计划列表失败!",e);
		}
		return page;

	}

	//delete
	@RequestMapping("/deletePlan")
	//@RequiresPermissions("/biz/sub/planList")
	public R deletee(Integer planId) {
		planService.deletePlan(planId);
		return R.ok();
	}

	//detail
	@RequestMapping("/findDetail")
	//@RequiresPermissions("/biz/sub/planList")
	public R findDetail(Integer planId) {
		try {
			BudgetVO budgetVO = planService.findDetail(planId);
			return R.ok(budgetVO);
		} catch (Exception e) {
			return R.ok(400,"查找执行计划失败");
		}
	}

	//update
	@RequestMapping("/updatePlan")
	public R updatePlan( @RequestBody BudgetVO budgetVO) {
		try {
			planService.updatePlan(budgetVO);
			return R.ok(budgetVO);
		} catch (Exception e) {
			return R.ok(400,"修改执行计划失败");
		}
	}

	//根据id得到执行计划详情
	@RequestMapping("/getPlanDetail")
	@ResponseBody
	public R getPlanDetail(Integer id){
		try {
			return R.ok().put("list",planService.getPlanDetail(id));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

}
