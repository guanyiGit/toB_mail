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

import com.entities.AttrGroupDO;
import com.service.AttrGroupService;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 15:07:00
 */
 
@Controller
@RequestMapping("/biz/attrGroup")
public class AttrGroupController {
	@Autowired
	private AttrGroupService attrGroupService;
	
	@GetMapping()
	@RequiresPermissions("com:attrGroup:attrGroup")
	String AttrGroup(){
	    return "com/attrGroup/attrGroup";
	}
	
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("com:attrGroup:attrGroup")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		mallQuery query = new mallQuery(params);
		List<AttrGroupDO> attrGroupList = attrGroupService.list(query);
		int total = attrGroupService.count(query);
		PageUtils pageUtils = new PageUtils(attrGroupList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("com:attrGroup:add")
	String add(){
	    return "com/attrGroup/add";
	}

	@GetMapping("/edit/{groupId}")
	@RequiresPermissions("com:attrGroup:edit")
	String edit(@PathVariable("groupId") Integer groupId,Model model){
		AttrGroupDO attrGroup = attrGroupService.get(groupId);
		model.addAttribute("attrGroup", attrGroup);
	    return "com/attrGroup/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	//@RequiresPermissions("com:attrGroup:add")
	public R save(AttrGroupDO attrGroup){
		if(attrGroupService.save(attrGroup)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	//@RequiresPermissions("com:attrGroup:edit")
	public R update( AttrGroupDO attrGroup){
		attrGroupService.update(attrGroup);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("com:attrGroup:remove")
	public R remove( Integer groupId){
		if(attrGroupService.remove(groupId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("com:attrGroup:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] groupIds){
		attrGroupService.batchRemove(groupIds);
		return R.ok();
	}

	/**
	 * 根据商品分类查
	 */
	@GetMapping( "/findAttrGroupByCate")
	@ResponseBody
	public List<Map<String,Object>> findAttrGroupByCate(Integer cId){
		List<Map<String,Object>> map=attrGroupService.findAttrGroupByCate(cId);
		return map;
	}


}
