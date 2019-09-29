package com.dao;

import com.entities.GoodsBrandDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:55:24
 */
@Mapper
public interface GoodsBrandDao {

	GoodsBrandDO get(Integer brandId);
	
	List<GoodsBrandDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GoodsBrandDO goodsBrand);
	
	int update(GoodsBrandDO goodsBrand);
	
	int remove(Integer brand_id);
	
	int batchRemove(Integer[] brandIds);
}
