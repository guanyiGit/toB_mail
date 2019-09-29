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

import com.entities.SkuValueRefDO;
import com.service.SkuValueRefService;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 15:19:29
 */
 
@Controller
@RequestMapping("/com/skuValueRef")
public class SkuValueRefController {
	@Autowired
	private SkuValueRefService skuValueRefService;
	
	@GetMapping()
	@RequiresPermissions("com:skuValueRef:skuValueRef")
	String SkuValueRef(){
	    return "com/skuValueRef/skuValueRef";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("com:skuValueRef:skuValueRef")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SkuValueRefDO> skuValueRefList = skuValueRefService.list(query);
		int total = skuValueRefService.count(query);
		PageUtils pageUtils = new PageUtils(skuValueRefList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("com:skuValueRef:add")
	String add(){
	    return "com/skuValueRef/add";
	}

	@GetMapping("/edit/{svrId}")
	@RequiresPermissions("com:skuValueRef:edit")
	String edit(@PathVariable("svrId") Long svrId,Model model){
		SkuValueRefDO skuValueRef = skuValueRefService.get(svrId);
		model.addAttribute("skuValueRef", skuValueRef);
	    return "com/skuValueRef/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("com:skuValueRef:add")
	public R save(SkuValueRefDO skuValueRef){
		if(skuValueRefService.save(skuValueRef)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("com:skuValueRef:edit")
	public R update( SkuValueRefDO skuValueRef){
		skuValueRefService.update(skuValueRef);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("com:skuValueRef:remove")
	public R remove( Long svrId){
		if(skuValueRefService.remove(svrId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("com:skuValueRef:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] svrIds){
		skuValueRefService.batchRemove(svrIds);
		return R.ok();
	}
	
}
