package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dao.AttrGroupDao;
import com.entities.AttrGroupDO;
import com.service.AttrGroupService;



@Service
public class AttrGroupServiceImpl implements AttrGroupService {
	@Autowired
	private AttrGroupDao attrGroupDao;
	
	@Override
	public AttrGroupDO get(Integer groupId){
		return attrGroupDao.get(groupId);
	}
	
	@Override
	public List<AttrGroupDO> list(Map<String, Object> map){
		return attrGroupDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return attrGroupDao.count(map);
	}

	@Override
	public List<Map<String, Object>> findAttrGroupByCate(Integer cId) {
		return attrGroupDao.findAttrGroupByCate(cId);
	}

	@Override
	public int save(AttrGroupDO attrGroup){
		attrGroup.setCreateTime(new Date());
		attrGroup.setUpdateTime(new Date());
		return attrGroupDao.save(attrGroup);
	}
	
	@Override
	public int update(AttrGroupDO attrGroup){
		return attrGroupDao.update(attrGroup);
	}
	
	@Override
	public int remove(Integer groupId){
		return attrGroupDao.remove(groupId);
	}
	
	@Override
	public int batchRemove(Integer[] groupIds){
		return attrGroupDao.batchRemove(groupIds);
	}
	
}
