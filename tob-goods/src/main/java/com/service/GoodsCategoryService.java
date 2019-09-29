package com.service;

import com.entities.GoodsCategoryDO;
import com.pojo.CategoryResult;
import com.vo.goodsCategoryVO;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:51:17
 */
public interface GoodsCategoryService {
	
	GoodsCategoryDO get(Integer cid);
	
	List<goodsCategoryVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GoodsCategoryDO goodsCategory);
	
	int update(GoodsCategoryDO goodsCategory);
	
	int remove(Integer cid);
	
	int batchRemove(Integer[] cids);

	//List<Tree<GoodsCategoryDO>> initGoodsCategory(Map<String,Object> map);

	List<?> findGoodsCateGory(Integer parentId);

	CategoryResult findAll();

}
