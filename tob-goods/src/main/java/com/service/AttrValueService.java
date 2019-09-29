package com.service;

import com.entities.AttrValueDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 15:12:27
 */
public interface AttrValueService {
	
	AttrValueDO get(Long avId);
	
	List<AttrValueDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AttrValueDO attrValue);
	
	int update(AttrValueDO attrValue);
	
	int remove(Long avId);
	
	int batchRemove(Long[] avIds);
}
