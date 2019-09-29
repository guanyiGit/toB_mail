package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dao.GoodsBrandDao;
import com.entities.GoodsBrandDO;
import com.service.GoodsBrandService;



@Service
public class GoodsBrandServiceImpl implements GoodsBrandService {
	@Autowired
	private GoodsBrandDao goodsBrandDao;
	
	@Override
	public GoodsBrandDO get(Integer brandId){
		return goodsBrandDao.get(brandId);
	}
	
	@Override
	public List<GoodsBrandDO> list(Map<String, Object> map){
		return goodsBrandDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return goodsBrandDao.count(map);
	}
	
	@Override
	public int save(GoodsBrandDO goodsBrand){
		goodsBrand.setCreateTime(new Date());
		goodsBrand.setUpdateTime(new Date());
		return goodsBrandDao.save(goodsBrand);
	}
	
	@Override
	public int update(GoodsBrandDO goodsBrand){
		return goodsBrandDao.update(goodsBrand);
	}
	
	@Override
	public int remove(Integer brandId){
		return goodsBrandDao.remove(brandId);
	}
	
	@Override
	public int batchRemove(Integer[] brandIds){
		return goodsBrandDao.batchRemove(brandIds);
	}
	
}
