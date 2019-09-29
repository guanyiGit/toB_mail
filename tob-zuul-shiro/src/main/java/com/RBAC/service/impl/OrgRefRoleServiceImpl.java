package com.RBAC.service.impl;

import com.RBAC.dao.OrgRefRoleDao;
import com.RBAC.service.OrgRefRoleService;
import com.entities.OrgRefRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class OrgRefRoleServiceImpl implements OrgRefRoleService {
	@Autowired
	private OrgRefRoleDao orgRefRoleDao;
	
	@Override
	public OrgRefRole get(Integer id){
		return orgRefRoleDao.get(id);
	}
	
	@Override
	public List<OrgRefRole> list(Map<String, Object> map){
		return orgRefRoleDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orgRefRoleDao.count(map);
	}
	
	@Override
	public int save(OrgRefRole orgRefRole){
		return orgRefRoleDao.save(orgRefRole);
	}
	
	@Override
	public int update(OrgRefRole orgRefRole){
		return orgRefRoleDao.update(orgRefRole);
	}
	
	@Override
	public int remove(Integer id){
		return orgRefRoleDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return orgRefRoleDao.batchRemove(ids);
	}
	
}
