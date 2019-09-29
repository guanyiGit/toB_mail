package com.orgmanagement.service.impl;

import com.entities.OrgInfo;
import com.entities.Tree;
import com.exception.NormalException;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.DeptDO;
import com.orgmanagement.domain.OrgInfoDO;
import com.orgmanagement.service.OrgInfoService;
import com.utils.BuildTree;
import com.utils.PageUtils;
import com.utils.ShiroUtils;
import com.utils.orgtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrgInfoServiceImpl implements OrgInfoService {
	@Autowired
	private OrgInfoDao orgInfoDao;

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
	public List<OrgInfoDO> list(Map<String, Object> map){
		Integer  deptId = 0;
		if (map.get("orgId") != null){
			  deptId = Integer.parseInt(map.get("orgId").toString());
		}
		List<OrgInfoDO> list = orgInfoDao.list(map);
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
	public int save(OrgInfoDO orgInfo){
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
		return orgInfoDao.save(orgInfo);
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
		List<OrgInfoDO> list = orgInfoDao.list(map);
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
		List<OrgInfoDO> list = orgInfoDao.list(new HashMap<>());
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
		List<OrgInfoDO> orgInfos = this.list(map);
		Integer  orgId = Integer.parseInt(map.get("orgId").toString());
		Integer  pid = Integer.parseInt(map.get("pid").toString());
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
