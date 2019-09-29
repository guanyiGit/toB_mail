package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.dao.CategoryDao;
import com.entities.CategoryDO;
import com.service.CategoryService;



@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public CategoryDO get(Integer scId){
		return categoryDao.get(scId);
	}
	
	@Override
	public List<CategoryDO> list(Map<String, Object> map){
		return categoryDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return categoryDao.count(map);
	}
	
	@Override
	public int save(CategoryDO category){
		return categoryDao.save(category);
	}
	
	@Override
	public int update(CategoryDO category){
		return categoryDao.update(category);
	}
	
	@Override
	public int remove(Integer scId){
		return categoryDao.remove(scId);
	}
	
	@Override
	public int batchRemove(Integer[] scIds){
		return categoryDao.batchRemove(scIds);
	}
	/*------------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------------*/
	@Override
	public List<Map> getCategoryList(Integer orgId) {
		return categoryDao.getCategoryList(orgId);
	}

	@Override
	public List<Map> getCategoryType(Integer orgId) {
		return categoryDao.getCategoryType(orgId);
	}

}
