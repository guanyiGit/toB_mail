package com.service;

import com.entities.AttrDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:40:29
 */
public interface AttrService {
	
	AttrDO get(Integer attrId);
	
	List<AttrDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AttrDO attr);
	
	int update(AttrDO attr);
	
	int remove(Integer attrId);
	
	int batchRemove(Integer[] attrIds);

	List<Map<String,Object>> findGoodsCategory();
}
