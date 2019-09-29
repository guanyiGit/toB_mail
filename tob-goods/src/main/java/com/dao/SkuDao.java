package com.dao;

import com.entities.SkuDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 15:15:43
 */
@Mapper
public interface SkuDao {

	SkuDO get(Long skuId);
	
	List<SkuDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SkuDO sku);
	
	int update(SkuDO sku);
	
	int remove(Long sku_id);
	
	int batchRemove(Long[] skuIds);
}
