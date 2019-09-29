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
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class OrgInfoServiceImpl implements OrgInfoService {
	@Value("${urls.budget-url}")
	private String budgetUrl;

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
		Integer offset = Integer.parseInt(map.get("offset").toString());
		Integer limit = Integer.parseInt(map.get("limit").toString());
		//当前组织下所有组织
		List<OrgInfoDO> depts = orgtills.getDeptsById(orgs,deptId,new ArrayList<>());
		//list
		ArrayList<OrgInfoDO> orgInfoDOS = new ArrayList<>();
		for (Integer i = 0;i<depts.size();i++){
			if (i>=offset && i<limit ){
				orgInfoDOS.add(depts.get(i));
			}
		}
		int count = depts.size();
		PageUtils pageUtils = new PageUtils(orgInfoDOS,count);
		return pageUtils;
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
	public R save(OrgInfoDO orgInfo){
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
        if (orgInfo.getCreateBy() == null){
			orgInfo.setCreateBy(1);
//            orgInfo.setCreateBy(ShiroUtils.getUserId().intValue());
        }
		orgInfoDao.save(orgInfo);
		//查询父组织的所有科目
		R result = restTemplate.getForObject(budgetUrl + "/subject/subListByOrgId/{orgId}", R.class,orgInfo.getPid());
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
			R result2 = restTemplate.getForObject(budgetUrl + "/subject/addSubRefOrgs/{subRefOrgs}", R.class,JSONObject.toJSONString(subRefOrgs));
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
		//获取当前组织的所有子组织
		Integer  orgId = Integer.parseInt(map.get("orgId").toString());
		Integer  pid = Integer.parseInt(map.get("pid").toString());
		List<OrgInfoDO> orgInfos = this.list(orgId);
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
