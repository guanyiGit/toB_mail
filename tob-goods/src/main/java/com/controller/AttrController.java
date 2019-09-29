package com.controller;

import java.util.List;
import java.util.Map;

import com.entities.AttrDO;
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
import com.service.AttrService;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:40:29
 */
 
@Controller
@RequestMapping("/biz/attr")
public class AttrController {
	@Autowired
	private AttrService attrService;
	
	@GetMapping()
	@RequiresPermissions("com:attr:attr")
	String Attr(){
	    return "com/attr/attr";
	}
	
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("com:attr:attr")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		mallQuery query = new mallQuery(params);
		List<AttrDO> attrList = attrService.list(query);
		int total = attrService.count(query);
		PageUtils pageUtils = new PageUtils(attrList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("com:attr:add")
	String add(){
	    return "com/attr/add";
	}

	@GetMapping("/edit/{attrId}")
	@RequiresPermissions("com:attr:edit")
	String edit(@PathVariable("attrId") Integer attrId,Model model){
		AttrDO attr = attrService.get(attrId);
		model.addAttribute("attr", attr);
	    return "com/attr/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	//@RequiresPermissions("com:attr:add")
	public R save(AttrDO attr){
		if(attrService.save(attr)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("com:attr:edit")
	public R update( AttrDO attr){
		attrService.update(attr);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("com:attr:remove")
	public R remove( Integer attrId){
		if(attrService.remove(attrId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("com:attr:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] attrIds){
		attrService.batchRemove(attrIds);
		return R.ok();
	}


	/**
	 * 查询商品分类
	 */
	@GetMapping( "/findGoodsCategory")
	@ResponseBody
	public List<Map<String,Object>> findGoodsCategory(){
		List<Map<String,Object>> map = attrService.findGoodsCategory();
		return map;
	}
}
