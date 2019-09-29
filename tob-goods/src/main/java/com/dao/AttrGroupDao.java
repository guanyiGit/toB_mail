package com.dao;

import com.entities.AttrGroupDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 15:07:00
 */
@Mapper
public interface AttrGroupDao {

	AttrGroupDO get(Integer groupId);
	
	List<AttrGroupDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AttrGroupDO attrGroup);
	
	int update(AttrGroupDO attrGroup);
	
	int remove(Integer group_id);
	
	int batchRemove(Integer[] groupIds);

	List<Map<String,Object>> findAttrGroupByCate(Integer cId);
}
