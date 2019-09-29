package com.orgmanagement.controller;

import java.util.List;
import java.util.Map;

import com.orgmanagement.domain.UserRefSubDO;
import com.orgmanagement.service.UserRefSubService;
import com.utils.PageUtils;
import com.utils.Query;
import com.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-27 19:00:43
 */
 
@Controller
@RequestMapping("/system/userRefSub")
public class UserRefSubController {
	@Autowired
	private UserRefSubService userRefSubService;
	
	@GetMapping()
	@RequiresPermissions("system:userRefSub:userRefSub")
	String UserRefSub(){
	    return "system/userRefSub/userRefSub";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:userRefSub:userRefSub")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UserRefSubDO> userRefSubList = userRefSubService.list(query);
		int total = userRefSubService.count(query);
		PageUtils pageUtils = new PageUtils(userRefSubList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:userRefSub:add")
	String add(){
	    return "system/userRefSub/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:userRefSub:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		UserRefSubDO userRefSub = userRefSubService.get(id);
		model.addAttribute("userRefSub", userRefSub);
	    return "system/userRefSub/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:userRefSub:add")
	public R save(UserRefSubDO userRefSub){
		if(userRefSubService.save(userRefSub)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:userRefSub:edit")
	public R update( UserRefSubDO userRefSub){
		userRefSubService.update(userRefSub);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:userRefSub:remove")
	public R remove( Integer id){
		if(userRefSubService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:userRefSub:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		userRefSubService.batchRemove(ids);
		return R.ok();
	}
	
}
