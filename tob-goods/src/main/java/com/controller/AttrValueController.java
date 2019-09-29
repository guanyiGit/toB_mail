package com.controller;

import java.util.List;
import java.util.Map;

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

import com.entities.AttrValueDO;
import com.service.AttrValueService;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 15:12:27
 */
 
@Controller
@RequestMapping("/com/attrValue")
public class AttrValueController {
	@Autowired
	private AttrValueService attrValueService;
	
	@GetMapping()
	@RequiresPermissions("com:attrValue:attrValue")
	String AttrValue(){
	    return "com/attrValue/attrValue";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("com:attrValue:attrValue")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AttrValueDO> attrValueList = attrValueService.list(query);
		int total = attrValueService.count(query);
		PageUtils pageUtils = new PageUtils(attrValueList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("com:attrValue:add")
	String add(){
	    return "com/attrValue/add";
	}

	@GetMapping("/edit/{avId}")
	@RequiresPermissions("com:attrValue:edit")
	String edit(@PathVariable("avId") Long avId,Model model){
		AttrValueDO attrValue = attrValueService.get(avId);
		model.addAttribute("attrValue", attrValue);
	    return "com/attrValue/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("com:attrValue:add")
	public R save(AttrValueDO attrValue){
		if(attrValueService.save(attrValue)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("com:attrValue:edit")
	public R update( AttrValueDO attrValue){
		attrValueService.update(attrValue);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("com:attrValue:remove")
	public R remove( Long avId){
		if(attrValueService.remove(avId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("com:attrValue:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] avIds){
		attrValueService.batchRemove(avIds);
		return R.ok();
	}
	
}
