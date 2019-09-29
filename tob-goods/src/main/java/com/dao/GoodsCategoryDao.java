package com.dao;

import com.entities.GoodsCategoryDO;

import java.util.List;
import java.util.Map;

import com.vo.goodsCategoryVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:51:17
 */
@Mapper
public interface GoodsCategoryDao {

	GoodsCategoryDO get(Integer cid);
	
	List<goodsCategoryVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GoodsCategoryDO goodsCategory);
	
	int update(GoodsCategoryDO goodsCategory);
	
	int remove(Integer cid);
	
	int batchRemove(Integer[] cids);

	List<GoodsCategoryDO> getlist(Map<String, Object> map);


}
