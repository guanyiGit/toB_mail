package com.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.vo.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entities.ExecutionPlanDO;
import com.entities.TBudgetAllocations;
import com.entities.TPlanDetail;
import com.github.pagehelper.PageInfo;
import com.model.OrgPlan;
import com.service.BudegtService;
import com.utils.DateUtils;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.ReqPage;

@RestController
@RequestMapping("/biz/budget")
public class BudgetController {
	private static Logger logger = LoggerFactory.getLogger(BudgetController.class);
	@Autowired
	private BudegtService budgetService;





	
	/**
	 * 新增预算分配
	 * @param 
	 * @return
	 */
	@RequestMapping("/addBudget")
	@RequiresPermissions("/biz/budget/addBudget")
	public R addBudget(@RequestBody AddBudgetVo prams) {
		try {
			budgetService.addBudget(prams);
			return R.ok();
		} catch (Exception e) {
			logger.warn("新增预算分配失败!");
			return R.error();
		}
	}
	
	
	
	/**
	 * 新增预算分配(根据科目)
	 * @param 
	 * @return
	 */
	@RequestMapping("/addBudgetBySub")
	public R addBudgetBySub(@RequestBody List<ChildSub> child) {
		try {
			budgetService.addBudgetBySub(child);
			return R.ok();
		} catch (Exception e) {
			logger.warn("新增预算分配失败!");
			return R.error();
		}
	}

	/**
	 * 新增月度预算分配
	 * @param
	 * @return
	 */
	@RequestMapping("/addBudgetMonth")
	public R addBudgetMonth(@RequestBody List<BudgetMonthVO> budgetMonthVO) {
		try {
			budgetService.addBudgetMonth(budgetMonthVO);
			return R.ok();
		} catch (Exception e) {
			logger.warn("新增月度预算分配失败!");
			return R.error();
		}
	}


	/**
	 * 当前月度预算
	 * @param
	 * @return
	 */
	@RequestMapping("/findBudgetAmount")
	public R findBudgetAmount(Integer orgId, Integer month, Integer year) {
		try {
            Double budgetAmount = budgetService.findBudgetAmount(orgId, month, year);
            return R.ok(budgetAmount);
		} catch (Exception e) {
			logger.warn("操作失败!");
			return R.error();
		}
	}

	/**
	 * 检查部门科目是否被分配
	 * @param 
	 * @return
	 */
	@RequestMapping("/checkAllocation")
	@RequiresPermissions("/biz/budget/checkAllocation")
	public R checkAllocation(Integer orgId) {
		try {
			return R.ok(budgetService.checkAllocation(orgId));
		} catch (Exception e) {
			logger.warn("检查部门科目是否被分配失败!");
			return R.error();
		}
	}
	
	
	/**
	 * 检查部门是否给下级部门分配
	 * @param orgId:当前登录人的组织id
	 * @param year:选择分配的年份
	 * @return
	 */
	@RequestMapping("/checkAllocation2")
	public R checkAllocation2(Integer orgId,Integer year) {
		try {
			return R.ok(budgetService.checkAllocation2(orgId,year));
		} catch (Exception e) {
			logger.warn("检查部门科目是否被分配失败!");
			return R.error();
		}
	}
	
	
	
	/**
	 * 预算分配列表
	 * @param pageNum,
	 * @param pageSize,
	 * @param searchKey
	 * @param budgetYear
	 * @param orgId 前端选择的预算实体
	 * @param currOrgId 当前登录人的组织
	 * @return
	 */
	@RequestMapping("/budgetAllocationList")
	@RequiresPermissions("/biz/budget/budgetAllocationList")
	public R budgetAllocationList(@RequestParam Map<String,Object>map) {
		try {
			PageUtils	page=budgetService.budgetAllocationList(map);
			return R.ok(page);
		} catch (Exception e) {
			logger.warn("查找预算分配列表失败!",e);
			return R.error();
		}
	}


	/**
	 * 预算接收列表
	 * @param pageNum,
	 * @param pageSize,
	 * @param searchKey
	 * @param budgetYear
	 * @param subjectId
	 * @param currOrgId 当前登录人组织
	 * @param budgetCategory
	 * @return
	 */
	@RequestMapping("/budgetReceiveList")
	@RequiresPermissions("/biz/budget/budgetReceiveList")
	public R budgetReceiveList(@RequestParam Map<String,Object>map) {
		try {
			PageUtils page=budgetService.budgetReceiveList(map);
			return R.ok(page);
		} catch (Exception e) {
			logger.warn("查找预算接收列表失败!",e);
			return R.error();
		}
		
	}
	
	/**
	 * 预算使用列表
	 * @param pageNum,
	 * @param pageSize,
	 * @param searchKey
	 * @param budgetYear
	 * @param orgId 前端选择的组织id
	 * @param currOrgId 当前登录人组织
	 * @param subjectId 最后一级组织选择科目
	 * @return
	 */
	@RequestMapping("/budgetUseList")
	@RequiresPermissions("/biz/budget/budgetUseList")
	public R budgetUseList(@RequestParam Map<String,Object>map) {
		try {
		PageUtils	page=budgetService.budgetUseList(map);
		return R.ok(page);
		} catch (Exception e) {
			logger.warn("查找预算使用列表失败!",e);
			return R.error();
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
	@RequestMapping("/budgetDetailUseList")
	@RequiresPermissions("/biz/budget/budgetDetailUseList")
	public R budgetDetailUseList(@RequestParam Map<String,Object>map) {
		try {
			PageUtils	page=budgetService.budgetDetailUseList(map);
			return R.ok(page);
		} catch (Exception e) {
			logger.warn("查找预算详细使用失败!",e);
			return R.error();
		}
		
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
	@RequestMapping("/findSchedule")
	public R findSchedule(@RequestParam Map<String,Object>map) {
		try {
		List<TPlanDetail>list=budgetService.findSchedule(map);
			return R.ok(list);
		} catch (Exception e) {
			logger.warn("查看进度失败!");
			return R.error();
		}
	}
	
	
	/**
	 * 封装公共的预算使用接口
	 * @param orgId 登录人组织id
	 * @return
	 */
	@RequestMapping("/budgetPublic2")
	public R budgetPublic(Integer orgId) {
		try {
			List<BudgetDetailVo>list=budgetService.budgetPublic(orgId);
			return R.ok(list);
		} catch (Exception e) {
			logger.warn("查看公共预算接口失败!");
			return R.error();
		}
	}

	@RequestMapping("/budgetPublic")
	public R budgetPublic2(Integer orgId) {
		try {
			List<BudgetDetailVo>list=budgetService.budgetPublic2(orgId);
			return R.ok(list);
		} catch (Exception e) {
			logger.warn("查看公共预算接口失败!");
			return R.error();
		}
	}

	
	/**
	 * 订单审核时的预算使用情况
	 * @param map
	 * @param orgId:当前登录人组织id
	 * @param subId:科目id
	 * @param orderDate:下单时间
	 * @return
	 */
	@RequestMapping("/budgetOrder")
	public R budgetOrder(@RequestBody BudgetOrderVo vo) {
		try {
			List<BudgetDetailVo>list=budgetService.budgetOrder(vo);
			return R.ok(list);
		} catch (Exception e) {
			logger.warn("订单审核时的预算使用情况失败!");
			return R.error();
		}
	}


	/**
	 * 订单结算时的预算结算
	 * @param map
	 * @return
	 */
	@PostMapping("/judgeBudget")
	public R judgeBudget(@RequestBody List<JudgBudgetVo> judgBudgetVo) {
		try {
			R r	=budgetService.judgeBudget(judgBudgetVo);
			return r;
		} catch (Exception e) {
			logger.warn("订单结算时的预算结算失败!");
			return R.error();
		}
	}
	
	/**
	 * @param map
	 * @return
	 */
	@RequestMapping("/findYearList")
	public R findYearList() {
		try {
			return R.ok(DateUtils.getYearList());
		} catch (Exception e) {
			logger.warn("查看年份下拉框失败!");
			return R.error();
		}
	}
	

	/**
	 * 查看我的可选用执行计划列表
	 * @param orgId 登录人机构id required
	 * @param orgId1 分配的组织id
	 * @param year  年份  4位整数
	 * @param reqPage
	 * @return
	 */
	@GetMapping(value = "/plans/{orgId}")
	public R plans(@PathVariable long orgId,Integer orgId1,Integer subjectId, @RequestParam(required = false) Integer year, ReqPage reqPage){
		try {
			year  = year !=null ? year : Calendar.getInstance().getWeekYear();
			OrgPlan op = budgetService.plans(orgId,orgId1,subjectId, year, reqPage);
			if(op!= null){
				PageInfo<ExecutionPlanDO> pi = new PageInfo<>(op.getMyExecutionPlanDOS());
				return R.ok(pi).put("default", op.getDefualtExecutionPlanDO()).put("selected", op.getSelectPlan());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
		}
		return R.error();
	}

	/**
	 * 修改我的默认执行计划
	 * @param params
	 * @param year
	 * @return
	 */
	@PutMapping(value = "/plan/{orgId}")
	public R modifyPlan(@PathVariable Integer orgId,
						Integer subjectId,
						@RequestParam Integer planId,
						@RequestParam Integer year){
		try {
			return R.ok(budgetService.modifyDefaultPlan(orgId,subjectId, planId,year));
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
		}
		
		return R.error();
	}

	/**
	 * 根据部门,年份查找预算
	 * @param map
	 * @return
	 */
	@RequestMapping("/findAllocation2")
	public R findAllocation2(@RequestParam Map<String,Object>map) {
		try {
			TBudgetAllocations a=budgetService.findAllocation2(map);
			return R.ok(a);
		} catch (Exception e) {
			logger.warn("查看年份下拉框失败!");
			return R.error();
		}
	}

	/**
	 * 查询预算明细里面的所有采购人员
	 * @param map
	 *           key:subjectId 可null
	 *           key:userId 可null
	 * @return 所有采购员:userID,userName
	 */
	@GetMapping(value = "/users")
	public R findPrucUsers(@RequestParam Map<String,String> map){
		try {
			return R.ok(budgetService.findPrucUsers(map));
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("查询预算明细里面的所有采购人员!",e);
			return R.error();
		}
	}
}
