package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.dao.SkuValueRefDao;
import com.entities.SkuValueRefDO;
import com.service.SkuValueRefService;



@Service
public class SkuValueRefServiceImpl implements SkuValueRefService {
	@Autowired
	private SkuValueRefDao skuValueRefDao;
	
	@Override
	public SkuValueRefDO get(Long svrId){
		return skuValueRefDao.get(svrId);
	}
	
	@Override
	public List<SkuValueRefDO> list(Map<String, Object> map){
		return skuValueRefDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return skuValueRefDao.count(map);
	}
	
	@Override
	public int save(SkuValueRefDO skuValueRef){
		return skuValueRefDao.save(skuValueRef);
	}
	
	@Override
	public int update(SkuValueRefDO skuValueRef){
		return skuValueRefDao.update(skuValueRef);
	}
	
	@Override
	public int remove(Long svrId){
		return skuValueRefDao.remove(svrId);
	}
	
	@Override
	public int batchRemove(Long[] svrIds){
		return skuValueRefDao.batchRemove(svrIds);
	}
	
}
