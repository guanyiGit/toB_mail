package com.controller;

import java.util.List;
import java.util.Map;

import com.utils.PageUtils;
import com.utils.Query;
import com.utils.R;
import com.utils.mallQuery;
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

import com.entities.GoodsBrandDO;
import com.service.GoodsBrandService;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:55:24
 */
 
@Controller
@RequestMapping("/biz/goodsBrand")
public class GoodsBrandController {
	@Autowired
	private GoodsBrandService goodsBrandService;
	
	@GetMapping()
	@RequiresPermissions("com:goodsBrand:goodsBrand")
	String GoodsBrand(){
	    return "com/goodsBrand/goodsBrand";
	}
	
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("com:goodsBrand:goodsBrand")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		mallQuery query = new mallQuery(params);
		List<GoodsBrandDO> goodsBrandList = goodsBrandService.list(query);
		int total = goodsBrandService.count(query);
		PageUtils pageUtils = new PageUtils(goodsBrandList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("com:goodsBrand:add")
	String add(){
	    return "com/goodsBrand/add";
	}

	@GetMapping("/edit/{brandId}")
	@RequiresPermissions("com:goodsBrand:edit")
	String edit(@PathVariable("brandId") Integer brandId,Model model){
		GoodsBrandDO goodsBrand = goodsBrandService.get(brandId);
		model.addAttribute("goodsBrand", goodsBrand);
	    return "com/goodsBrand/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	//@RequiresPermissions("com:goodsBrand:add")
	public R save(GoodsBrandDO goodsBrand){
		if(goodsBrandService.save(goodsBrand)>0){
			return R.ok(goodsBrand.getBrandId());
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("com:goodsBrand:edit")
	public R update( GoodsBrandDO goodsBrand){
		goodsBrandService.update(goodsBrand);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("com:goodsBrand:remove")
	public R remove( Integer brandId){
		if(goodsBrandService.remove(brandId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("com:goodsBrand:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] brandIds){
		goodsBrandService.batchRemove(brandIds);
		return R.ok();
	}
	
}
