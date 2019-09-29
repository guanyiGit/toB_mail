package com.dao;

import java.util.List;
import java.util.Map;

import com.entities.CategoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:17:17
 */
@Mapper
public interface CategoryDao {

	CategoryDO get(Integer scId);
	
	List<CategoryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CategoryDO category);
	
	int update(CategoryDO category);
	
	int remove(Integer sc_id);
	
	int batchRemove(Integer[] scIds);
	/*------------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------------*/
	//得到供应商分类集合
	List<Map>getCategoryList(@Param("orgId")Integer orgId);
	//得到字典表集合
	List<Map>getCategoryType(@Param("orgId")Integer orgId);
}
