package com.service;

import com.entities.SkuValueRefDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 15:19:29
 */
public interface SkuValueRefService {
	
	SkuValueRefDO get(Long svrId);
	
	List<SkuValueRefDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SkuValueRefDO skuValueRef);
	
	int update(SkuValueRefDO skuValueRef);
	
	int remove(Long svrId);
	
	int batchRemove(Long[] svrIds);
}
