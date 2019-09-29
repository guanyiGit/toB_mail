package com.dao;

import com.entities.AttrDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:40:29
 */
@Mapper
public interface AttrDao {

	AttrDO get(Integer attrId);
	
	List<AttrDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AttrDO attr);
	
	int update(AttrDO attr);
	
	int remove(Integer attr_id);
	
	int batchRemove(Integer[] attrIds);

	List<Map<String,Object>> findGoodsCategory();
}
