package com.service;

import com.entities.CategoryDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:17:17
 */
public interface CategoryService {
	
	CategoryDO get(Integer scId);
	
	List<CategoryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CategoryDO category);
	
	int update(CategoryDO category);
	
	int remove(Integer scId);
	
	int batchRemove(Integer[] scIds);
	/*------------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------------*/
	//查询供应商分类集合
	List<Map>getCategoryList(Integer orgId);
	//得到经营范围
	List<Map>getCategoryType(Integer orgId);
}
