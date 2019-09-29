package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.dao.AttrValueDao;
import com.entities.AttrValueDO;
import com.service.AttrValueService;



@Service
public class AttrValueServiceImpl implements AttrValueService {
	@Autowired
	private AttrValueDao attrValueDao;
	
	@Override
	public AttrValueDO get(Long avId){
		return attrValueDao.get(avId);
	}
	
	@Override
	public List<AttrValueDO> list(Map<String, Object> map){
		return attrValueDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return attrValueDao.count(map);
	}
	
	@Override
	public int save(AttrValueDO attrValue){
		return attrValueDao.save(attrValue);
	}
	
	@Override
	public int update(AttrValueDO attrValue){
		return attrValueDao.update(attrValue);
	}
	
	@Override
	public int remove(Long avId){
		return attrValueDao.remove(avId);
	}
	
	@Override
	public int batchRemove(Long[] avIds){
		return attrValueDao.batchRemove(avIds);
	}
	
}
