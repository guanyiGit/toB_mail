package com.service;

import com.entities.GoodsBrandDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:55:24
 */
public interface GoodsBrandService {
	
	GoodsBrandDO get(Integer brandId);
	
	List<GoodsBrandDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GoodsBrandDO goodsBrand);
	
	int update(GoodsBrandDO goodsBrand);
	
	int remove(Integer brandId);
	
	int batchRemove(Integer[] brandIds);
}
