package com.controller;

import java.util.List;
import java.util.Map;

import com.entities.CategoryDO;
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
import com.service.CategoryService;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:17:17
 */
 
@Controller
@RequestMapping("/com/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping()
	@RequiresPermissions("com:category:category")
	String Category(){
	    return "com/category/category";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("com:category:category")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CategoryDO> categoryList = categoryService.list(query);
		int total = categoryService.count(query);
		PageUtils pageUtils = new PageUtils(categoryList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("com:category:add")
	String add(){
	    return "com/category/add";
	}

	@GetMapping("/edit/{scId}")
	@RequiresPermissions("com:category:edit")
	String edit(@PathVariable("scId") Integer scId,Model model){
		CategoryDO category = categoryService.get(scId);
		model.addAttribute("category", category);
	    return "com/category/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("com:category:add")
	public R save(CategoryDO category){
		if(categoryService.save(category)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("com:category:edit")
	public R update( CategoryDO category){
		categoryService.update(category);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("com:category:remove")
	public R remove( Integer scId){
		if(categoryService.remove(scId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("com:category:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] scIds){
		categoryService.batchRemove(scIds);
		return R.ok();
	}
	/*------------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------------*/

	//查询供应商分类集合
	@RequestMapping("/getCategroyList")
	@ResponseBody
	public R getCategoryList(@RequestParam(value="orgId",required = false)Integer orgId){
		try {
			return R.ok().put("list",categoryService.getCategoryList(orgId));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	//查询经营范围
	@RequestMapping("/getCategoryType")
	@ResponseBody
	public R getCategoryType(@RequestParam(value="orgId",required = false)Integer orgId){
		try {
			return R.ok().put("list",categoryService.getCategoryType(orgId));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
}
