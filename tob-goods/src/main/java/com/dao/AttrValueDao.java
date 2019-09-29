package com.dao;

import com.entities.AttrValueDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 15:12:27
 */
@Mapper
public interface AttrValueDao {

	AttrValueDO get(Long avId);
	
	List<AttrValueDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AttrValueDO attrValue);
	
	int update(AttrValueDO attrValue);
	
	int remove(Long av_id);
	
	int batchRemove(Long[] avIds);
}
