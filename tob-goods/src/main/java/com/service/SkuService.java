package com.service;

import com.entities.SkuDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 15:15:43
 */
public interface SkuService {
	
	SkuDO get(Long skuId);
	
	List<SkuDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SkuDO sku);
	
	int update(SkuDO sku);
	
	int remove(Long skuId);
	
	int batchRemove(Long[] skuIds);
}
