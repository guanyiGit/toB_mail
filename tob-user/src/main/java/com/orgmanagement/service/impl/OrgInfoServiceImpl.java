package com.orgmanagement.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.entities.OrgInfo;
import com.entities.Tree;
import com.exception.NormalException;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.DeptDO;
import com.orgmanagement.domain.OrgInfoDO;
import com.orgmanagement.service.OrgInfoService;
import com.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class OrgInfoServiceImpl implements OrgInfoService {

	@Autowired
	private OrgInfoDao orgInfoDao;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public PageUtils findAll(Map<String, Object> map) {
		List<OrgInfoDO> orgs = orgInfoDao.findAll();
		Integer  deptId = 0;
		if (map.get("orgId") != null){
			deptId = Integer.parseInt(map.get("orgId").toString());
		}
		Integer  offset = 0;
		Integer  limit = 10;
		//获取当前组织的所有子组织
		if (map.get("offset") != null){
			offset = Integer.parseInt(map.get("offset").toString());
		}
		if (map.get("limit") != null){
			limit = Integer.parseInt(map.get("limit").toString());
		}
		Integer maxlimit = offset + limit;

		//当前组织下所有组织
		List<OrgInfoDO> depts = orgtills.getDeptsById(orgs,deptId,new ArrayList<>());
		//list
		ArrayList<OrgInfoDO> orgInfoDOS = new ArrayList<>();
		for (Integer i = 0;i<depts.size();i++){
			if (i>=offset && i<maxlimit ){
				orgInfoDOS.add(depts.get(i));
			}
		}
		int count = depts.size();
		PageUtils pageUtils = new PageUtils(orgInfoDOS,count);
		return pageUtils;
	}

	@Override
	public List<OrgInfoDO> findAll(Integer orgId) {
		List<OrgInfoDO> orgs = orgInfoDao.findAll();
		//当前组织下所有组织
		orgs = orgtills.getDeptsById(orgs,orgId,new ArrayList<>());
		return orgs;
	}

	@Override
	public OrgInfoDO get(Integer id){
		return orgInfoDao.get(id);
	}


	@Override
	public List<OrgInfoDO> list(Integer orgId){
		Integer  deptId = 0;
		if (orgId != null){
			  deptId = orgId;
		}
		List<OrgInfoDO> list = orgInfoDao.list();
		List<OrgInfoDO> depts = orgtills.getDeptsById(list,deptId,new ArrayList<>());
		return depts;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orgInfoDao.count(map);
	}

	@Override
	public int topOrgCount(Map<String, Object> map) {
		return orgInfoDao.topOrgCount(map);
	}

	@Override
	public R save(OrgInfoDO orgInfo) throws Exception{
		if (orgInfo.getPid() == null){
			orgInfo.setPid(0);
		}
		orgInfo.setType(0);
		orgInfo.setPower(0);
		orgInfo.setOrgLevel(0);
        if (orgInfo.getCreateDate() == null){
            orgInfo.setCreateDate(new Date());
        }
        if (orgInfo.getUpdateDate() == null){
            orgInfo.setUpdateDate(new Date());
        }
		orgInfoDao.save(orgInfo);
		//查询父组织的所有科目
		R result = null;
		try {
			result = restTemplate.getForObject( "http://tob-budget/subject/subListByOrgId/{orgId}", R.class,orgInfo.getPid());
		} catch (Exception e) {
			throw new Exception(e);
		}
		ArrayList<LinkedHashMap<String, Object>>  obj = (ArrayList<LinkedHashMap<String, Object>> ) result.get("obj");
		if (obj.size() == 0 || obj == null){
			return R.ok("暂无科目信息");
		}else{
			List<Integer> subIds = new ArrayList<>();
			obj.stream().forEach(item->{
				subIds.add(Integer.parseInt(item.get("id").toString()));
			});
			List<Map<String,Object>> subRefOrgs = new ArrayList<>();
			subIds.stream().forEach(subId->{
				Map<String,Object> subRefOrg = new HashMap<>();
				subRefOrg.put("orgId",orgInfo.getId());
				subRefOrg.put("subId",subId);
				subRefOrg.put("createDate",new Date());
				subRefOrgs.add(subRefOrg);
			});
            R result2 = restTemplate.postForObject( "http://tob-budget/subject/addSubRefOrgs",subRefOrgs, R.class );
            System.out.println(JSONObject.toJSONString(result2));
			return result2;
		}
	}
	
	@Override
	public int update(OrgInfoDO orgInfo){
		return orgInfoDao.update(orgInfo);
	}
	
	@Override
	public int remove(Integer id){
		//查询当前组织id是否还有子组织
		List<OrgInfoDO> childrenOrg = orgInfoDao.findChildrenOrg(id);
		if (childrenOrg != null && childrenOrg.size()>0){
			List<Integer> orgIds = new ArrayList<>();
			childrenOrg.stream().forEach(item -> orgIds.add(item.getId()));
			//将orgIds转为数组
//			Integer[] orgIdsArray	 =  orgIds.stream().toArray(Integer[]::new);
			Integer[] orgIdsArray	 =  orgIds.toArray(new Integer[orgIds.size()]);
			//删除所有子组织
			orgInfoDao.batchRemove(orgIdsArray);
		}
		return orgInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return orgInfoDao.batchRemove(ids);
	}

	@Override
	public List<OrgInfoDO> childList(Map<String, Object> map) {
		Integer  deptId = 0;
		if (map.get("orgId") != null){
			deptId = Integer.parseInt(map.get("orgId").toString());
		}
		List<OrgInfoDO> list = orgInfoDao.list();
		List<OrgInfoDO> depts = orgtills.getChildren(list,deptId,new ArrayList<>());
		return depts;
	}

	@Override
	public List<OrgInfoDO> findTopParentOrgList(Map<String, Object> map) {
		List<OrgInfoDO> orgList = orgInfoDao.findTopParentOrgList(map);
		return orgList;
	}

	@Override
	public List<OrgInfoDO> findBottomOrgList(Integer orgId) {
		List<OrgInfoDO> list = orgInfoDao.list();
		List<OrgInfoDO> bottomOrgList = orgtills.findBottomOrgList(list, orgId, new ArrayList<>());
		return bottomOrgList;
	}

	@Override
	public List<OrgInfoDO> getOrgSubList(Integer orgId) {
		List<OrgInfoDO> orgSubList = orgInfoDao.getOrgSubList(orgId);
		return orgSubList;
	}

	@Override
	public List<Tree<OrgInfoDO>> initOrgSelect(Map<String, Object> map) {
		//type 标识是添加组织还是角色 1 == 添加组织加载下拉 2 == 添加角色加载下拉(包含供应商)
		Integer type = 1;
		Integer  orgId =0;
		Integer  pid = 0;
		//获取当前组织的所有子组织
		if (map.get("type") != null){
			 type = Integer.parseInt(map.get("type").toString());
		}
		if (map.get("orgId") != null){
			  orgId = Integer.parseInt(map.get("orgId").toString());
		}
		if (map.get("pid") != null){
			  pid = Integer.parseInt(map.get("pid").toString());
		}
		List<OrgInfoDO> orgInfos = new ArrayList<>();
		if (type == 1){
			orgInfos = this.list(orgId);
		}else if (type == 2){
			orgInfos = orgInfoDao.findAll();
		}
		List<OrgInfoDO> depts = null;
		List<Tree<OrgInfoDO>> list = null;
		try{
			depts = orgtills.getDeptsById(orgInfos, orgId.intValue(), new ArrayList<>());
			List<Tree<OrgInfoDO>> trees = BuildTree.changeListToTreeList(depts);
			list = BuildTree.buildList(trees, pid.toString());
			return list;
		}catch (NullPointerException e){

			throw  new NormalException("用户为admin");
		}finally {
			return  list;
		}
	}
}
