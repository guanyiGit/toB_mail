package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.entities.TSubDefine;
import com.entities.TSubRefOrg;
import com.entities.TSubject;
import com.entities.Tree;
import com.util.SubUtils;
import com.utils.mallQuery;
import com.vo.SubDefineVo;
import com.vo.SubVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.service.SubjectService;
import com.utils.PageUtils;
import com.utils.R;

@RestController
@RequestMapping("/subject")
public class SubjectController {
	private static Logger logger = LoggerFactory.getLogger(SubjectController.class);
	@Autowired
	private SubjectService subjectService;




	@RequestMapping("/initSubTree/{orgId}/{subId}")
	@RequiresPermissions("/subject/initSubTree")
	public List<Tree<TSubject>> initSubTree(@PathVariable Integer orgId, @PathVariable Integer subId){
		List<Tree<TSubject>> trees = subjectService.initSubTree(orgId, subId);
		return  trees;
	}


	@RequestMapping("/addSub")
	@RequiresPermissions("/subject/addSub")
	public R addSub(@RequestBody SubVo subVo) {
		int a=subjectService.addSub(subVo);
		if(a>0) {
			return R.ok();
		}
		return R.error();
	}






	@RequestMapping("/addSubRefOrgs")
	@RequiresPermissions("/subject/addSubRefOrgs")
	public R addSubRefOrgs(@RequestBody List<TSubRefOrg> subRefOrgs){
		if (subRefOrgs == null || subRefOrgs.equals("")){
			return R.error("无数据添加");
		}
//		List<TSubRefOrg> tSubRefOrgs = JSONObject.parseArray(subRefOrgs, TSubRefOrg.class);
//		List<TSubRefOrg> tSubRefOrg = JSONObject.parseObject(subRefOrgs, TSubRefOrg.class);
		int i = subjectService.batchSaveSubRefOrg(subRefOrgs);
		if(i>0){
			return R.ok("添加成功");
		}
		return   R.ok();
	}
	
//	/**
//	 * 删除科目
//	 * @param id 科目主键
//	 * @return
//	 */
//	@RequestMapping("/delSub")
//	@ResponseBody
//	@RequiresPermissions("/biz/sub/delSub")
//	public R delSub(Integer id) {
//		int a=subjectService.delSub(id);
//		if(a>0) {
//			return R.ok();
//		}
//		return R.error();
//	}
//	
//	
//	/**
//	 * 修改科目
//	 * @param id 科目主键
//	 * @return
//	 */
//	@RequestMapping("/editSub")
//	@ResponseBody
//	@RequiresPermissions("/biz/sub/editSub")
//	public R editSub(TSubject sub) {
//		int a=subjectService.editSub(sub);
//		if(a>0) {
//			return R.ok();
//		}
//		return R.error();
//	}
	
	
	/**
	 * 科目列表
	 * @param pageNum,
	 * @param pageSize,
	 * @param searchKey
	 * @param orgId
	 * @return
	 */
	@RequestMapping("/subList")
//	@RequiresPermissions("/subject/subList")
	public R subList(@RequestParam Map<String,Object>map) {
		PageUtils page=null;
		try {
			page=subjectService.subList(map);
		} catch (Exception e) {
			logger.warn("查找科目列表失败!",e);
		}
		return R.ok(page);
		
	}


	@RequestMapping("/subListByOrgId/{orgId}")
//	@RequiresPermissions("/subject/subListByOrgId")
	public R subListByOrgId(@PathVariable(name = "orgId") Integer orgId){
		if (orgId == null){
			return R.error();
		}else {
			List<TSubject> subjects = subjectService.subListByOrgId(orgId);
			return R.ok(subjects);
		}
	}

	@RequestMapping("/deleteById")
//	@RequiresPermissions("/subject/deleteById")
	public R deleteById(Integer subId){
		return subjectService.deleteById(subId);
	}


	/**
	 * 添加科目定义
	 * @return
	 */
	@PostMapping("/addSubDefine")
	@RequiresPermissions("/subject/addSubDefine")
	public R addSubDefine(@RequestBody SubDefineVo subDefineVo){
		try {
			subjectService.addSubDefine(subDefineVo);
			return R.ok("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("添加失败");
		}
	}

	/**
	 * 添加科目
	 * @param subject(改)
	 * @return
	 */
	@RequestMapping("/addSub1")
	@RequiresPermissions("/subject/addSub")
	public R addSub1(@RequestBody TSubject subject){
		try {
			subjectService.addSub1(subject);
			return R.ok("添加成功");
		} catch (Exception e) {
			return R.error("添加失败");
		}
	}

	/**
	 * 删除科目定义(同时删除科目)
	 * @param subDefineId
	 * @return
	 */
	@RequestMapping("/deleteSubDefine")
	@RequiresPermissions("/subject/deleteSubDefine")
	public R deleteSubDefine(Integer subDefineId){
		R result = subjectService.deleteSubDefine(subDefineId);
		return  result;
	}


	/**
	 * 查询科目定义列表
	 * @param map
	 * @return
	 */
	@RequestMapping("/getSubDefines")
	@RequiresPermissions("/subject/getSubDefines")
	public R getSubDefines(@RequestParam Map<String,Object> map){
		try {
			mallQuery mallQuery = new mallQuery(map);
			List<TSubDefine> subDefines = subjectService.getSubDefines(mallQuery);
			return R.ok(subDefines);
		} catch (Exception e) {
			return R.error("服务器异常");
		}
	}

	/**
	 * 获取科目列表
	 * @param subDefineId
	 * @return
	 */
	@RequestMapping("/getSubs/{subDefineId}")
	@RequiresPermissions("/subject/getSubs")
	public R getSubs(@PathVariable Integer subDefineId) {
		try {
			List<TSubject> subs = subjectService.getSubs(subDefineId);
			return R.ok(subs);
		} catch (Exception e) {
			return R.error("服务器异常");
		}
	}


	/**
	 * 获取最底层科目列表
	 * @param orgId
	 * @param year
	 * @param type
	 * @return
	 */
	@RequestMapping("/getButtomSubs")
	@RequiresPermissions("/subject/getButtomSubs")
	public R getButtomSubs(Integer orgId,String year,Integer type){
		try {
			List<TSubject> buttomSubList = subjectService.getButtomSubs(orgId, year,type);
			return R.ok(buttomSubList);
		}catch (Exception e){
			return  R.error("服务器异常");
		}
	}

}
