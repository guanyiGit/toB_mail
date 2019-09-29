package com.service;

import com.entities.AttrGroupDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 15:07:00
 */
public interface AttrGroupService {
	
	AttrGroupDO get(Integer groupId);
	
	List<AttrGroupDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AttrGroupDO attrGroup);
	
	int update(AttrGroupDO attrGroup);
	
	int remove(Integer groupId);
	
	int batchRemove(Integer[] groupIds);

	List<Map<String,Object>> findAttrGroupByCate(Integer cId);
}
