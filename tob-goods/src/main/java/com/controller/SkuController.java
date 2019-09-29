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

import com.entities.SkuDO;
import com.service.SkuService;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 15:15:43
 */
 
@Controller
@RequestMapping("/com/sku")
public class SkuController {
	@Autowired
	private SkuService skuService;
	
	@GetMapping()
	@RequiresPermissions("com:sku:sku")
	String Sku(){
	    return "com/sku/sku";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("com:sku:sku")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SkuDO> skuList = skuService.list(query);
		int total = skuService.count(query);
		PageUtils pageUtils = new PageUtils(skuList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("com:sku:add")
	String add(){
	    return "com/sku/add";
	}

	@GetMapping("/edit/{skuId}")
	@RequiresPermissions("com:sku:edit")
	String edit(@PathVariable("skuId") Long skuId,Model model){
		SkuDO sku = skuService.get(skuId);
		model.addAttribute("sku", sku);
	    return "com/sku/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("com:sku:add")
	public R save(SkuDO sku){
		if(skuService.save(sku)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("com:sku:edit")
	public R update( SkuDO sku){
		skuService.update(sku);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("com:sku:remove")
	public R remove( Long skuId){
		if(skuService.remove(skuId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("com:sku:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] skuIds){
		skuService.batchRemove(skuIds);
		return R.ok();
	}
	
}
