package com.orgmanagement.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.entities.OrgInfo;
import com.entities.Tree;
import com.orgmanagement.domain.OrgInfoDO;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.OrgInfoService;
import com.sys.controller.BaseController;
import com.utils.PageUtils;
import com.utils.Query;
import com.utils.R;
import com.utils.mallQuery;
import lombok.experimental.var;
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
 * @date 2018-12-28 15:35:45
 */
 
@Controller
@RequestMapping("/system/orgInfo")
public class OrgInfoController extends BaseController {
	@Autowired
	private OrgInfoService orgInfoService;

	@RequestMapping("/initOrgSelect")
	@ResponseBody
	public List<Tree<OrgInfoDO>> initOrgSelect(@RequestParam Map<String, Object> params){
		UserDO user = getUser();
		params.put("orgId",user.getOrgId());
		params.put("pid",user.getOrgInfoDO().getPid());
//		params.put("orgId",1);
//		params.put("pid",0);
		List<Tree<OrgInfoDO>> treeList = orgInfoService.initOrgSelect(params);
		return treeList;
	}

    /**
     * 判断当前组织是否是最后一级组织(true==是，false==否)
     * @param id
     * @return
     */
	@RequestMapping("/checkBottomOrg")
    @ResponseBody
	public R checkBottomOrg(Integer id){
        Map<String, Object> query = new HashMap<>();
        query.put("orgId",id);
        List<OrgInfoDO> orgInfoList = orgInfoService.childList(query);
        Map<String, Object> map = new HashMap<>();
        if(orgInfoList != null && orgInfoList.size()>0){
            map.put("isBottomOrg",false);
            return R.ok(map);
        }else{
            map.put("isBottomOrg",true);
            return R.ok(map);
        }
    }

    /**
     * 获取当前组织的最后一级组织
     * @param orgId
     * @return
     */
    @RequestMapping("/findBottomOrgList")
    @ResponseBody
    public R findBottomOrgList(Integer orgId){
        if (orgId == null){
            return R.error("请传入本组织id");
        }
        List<OrgInfoDO> bottomOrgList = orgInfoService.findBottomOrgList(orgId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("bottomOrgList",bottomOrgList);
        return R.ok(map);
    }

    /**
	 * 获取组织详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/getOrgDetail")
    @ResponseBody
	public R getOrgDetail(Integer id){
        OrgInfoDO orgInfoDO = orgInfoService.get(id);
        Map<String,Object> map = new HashMap<>();
        map.put("orgInfo",orgInfoDO);
        return R.ok(map);
    }


    /**
     * 获取最顶级组织列表
     * @param params
     * @return
     */
    @RequestMapping("/findTopParentOrgList")
    @ResponseBody
    public PageUtils  findTopParentOrgList(@RequestParam Map<String, Object> params){
        //查询列表数据
//        Query mallQuery = new Query(params);
        mallQuery mallQuery = new mallQuery(params);
        List<OrgInfoDO> orgInfoList = orgInfoService.findTopParentOrgList(mallQuery);
        orgInfoList = orgInfoList.stream().filter(item -> item.getPid() == 0).collect(Collectors.toList());
        int total = orgInfoService.topOrgCount(mallQuery);
        PageUtils pageUtils = new PageUtils(orgInfoList, total);
        return  pageUtils;
    }



    /**
     * 平台管理员加载组织下拉
     * @return
     */
    @RequestMapping("/initOrgDropDown")
    @ResponseBody
    public R initOrgDropDown(){
        List<OrgInfoDO> orgInfoList = orgInfoService.findTopParentOrgList(new HashMap<>());
        Map<String, Object> map = new HashMap<>();
        map.put("orgList",orgInfoList);
        return R.ok(map);
    }

	/**
	 * 获取下级所有子组织(包含本身，分页，列表)
	 * @param params
	 * @return
	 */
	@RequestMapping("/findOrgList")
	@ResponseBody
	public PageUtils  findOrgList(@RequestParam Map<String, Object> params){
		//查询列表数据
		mallQuery mallQuery = new mallQuery(params);
		PageUtils pageUtils = orgInfoService.findAll(mallQuery);
		return  pageUtils;
	}

	/**
	 * 获取当前组织以及所有子组织(不分页)
	 * @return
	 */
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("system:orgInfo:orgInfo")
	public R list(Integer orgId){
		//查询列表数据
        if(orgId == null){
			UserDO userDO = getUser();
//			map.put("orgId",userDO.getOrgInfoDO().getId());
			orgId =userDO.getOrgInfoDO().getId();
		}
        List<OrgInfoDO> orgInfoList = orgInfoService.list(orgId);
		Map<String, Object> result = new HashMap<>();
		result.put("orgInfoList",orgInfoList);
		return R.ok(result);
	}



	/**
	 * 获取当前组织下的所有子组织(不包含本身)
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/childOrglist")
//	@RequiresPermissions("system:orgInfo:orgInfo")
	public R childOrglist(@RequestParam Map<String, Object> params){
        Map<String, Object> query = new HashMap<>();
        //查询列表数据
		if(params.get("orgId") == null){
			UserDO userDO = getUser();
			query.put("orgId",userDO.getOrgInfoDO().getId());
		}
        //测试
//        int orgId = 1;
//        query.put("orgId",orgId);
		List<OrgInfoDO> orgInfoList = orgInfoService.childList(query);
        Map<String, Object> map = new HashMap<>();
        map.put("orgInfoList",orgInfoList);
		return R.ok(map);
	}


	/**
	 * 获取直接下级组织
	 * @param orgId
	 * @return
	 */
	@RequestMapping("/getOrgSubList")
	@ResponseBody
	public R getOrgSubList(Integer orgId){
		if (orgId == null || orgId == 0){
			orgId = getUser().getOrgId();
		}
		List<OrgInfoDO> orgSubList = orgInfoService.getOrgSubList(orgId);
		HashMap<String, Object> map = new HashMap<>();
		map.put("getOrgSubList",orgSubList);
		return R.ok(map);
	};

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("system:orgInfo:add")
	public R save(OrgInfoDO orgInfo){
		return orgInfoService.save(orgInfo);
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("system:orgInfo:edit")
	public R update( OrgInfoDO orgInfo){
		orgInfoService.update(orgInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("system:orgInfo:remove")
	public R remove( Integer id){
		if(orgInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("system:orgInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		orgInfoService.batchRemove(ids);
		return R.ok();
	}

}
