package com.controller;

import com.entities.RecipientDO;
import com.service.RecipientService;
import com.utils.PageUtils;
import com.utils.Query;
import com.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:45:27
 */
 
@Controller
@RequestMapping("/biz/recipient")
public class RecipientController {
	@Autowired
	private RecipientService recipientService;
	
	@GetMapping()
	@RequiresPermissions("system:recipient:recipient")
	String Recipient(){
	    return "system/recipient/recipient";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:recipient:recipient")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RecipientDO> recipientList = recipientService.list(query);
		int total = recipientService.count(query);
		PageUtils pageUtils = new PageUtils(recipientList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:recipient:add")
	String add(){
	    return "system/recipient/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:recipient:edit")
	String edit(@PathVariable("id") Long id,Model model){
		RecipientDO recipient = recipientService.get(id);
		model.addAttribute("recipient", recipient);
	    return "system/recipient/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("system:recipient:add")
	public R save( @RequestBody RecipientDO recipient){
		recipient.setCreateTime(new Date());
		if(recipientService.save(recipient)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:recipient:edit")
	public R update( RecipientDO recipient){
		recipientService.update(recipient);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:recipient:remove")
	public R remove( Long id){
		if(recipientService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:recipient:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		recipientService.batchRemove(ids);
		return R.ok();
	}
	
}
