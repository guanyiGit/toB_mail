package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dao.AttrDao;
import com.entities.AttrDO;
import com.service.AttrService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Service
public class AttrServiceImpl implements AttrService {
	@Autowired
	private AttrDao attrDao;
	
	@Override
	public AttrDO get(Integer attrId){
		return attrDao.get(attrId);
	}
	
	@Override
	public List<AttrDO> list(Map<String, Object> map){
		return attrDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return attrDao.count(map);
	}
	
	@Override
	public int save(AttrDO attr){
		attr.setCreateTime(new Date());
		attr.setUpdateTime(new Date());
		return attrDao.save(attr);
	}
	
	@Override
	public int update(AttrDO attr){
		return attrDao.update(attr);
	}
	
	@Override
	public int remove(Integer attrId){
		return attrDao.remove(attrId);
	}
	
	@Override
	public int batchRemove(Integer[] attrIds){
		return attrDao.batchRemove(attrIds);
	}

	@Override
	public List<Map<String, Object>> findGoodsCategory() {
		return attrDao.findGoodsCategory();
	}



}
