package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.dao.SkuDao;
import com.entities.SkuDO;
import com.service.SkuService;



@Service
public class SkuServiceImpl implements SkuService {
	@Autowired
	private SkuDao skuDao;
	
	@Override
	public SkuDO get(Long skuId){
		return skuDao.get(skuId);
	}
	
	@Override
	public List<SkuDO> list(Map<String, Object> map){
		return skuDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return skuDao.count(map);
	}
	
	@Override
	public int save(SkuDO sku){
		return skuDao.save(sku);
	}
	
	@Override
	public int update(SkuDO sku){
		return skuDao.update(sku);
	}
	
	@Override
	public int remove(Long skuId){
		return skuDao.remove(skuId);
	}
	
	@Override
	public int batchRemove(Long[] skuIds){
		return skuDao.batchRemove(skuIds);
	}
	
}
