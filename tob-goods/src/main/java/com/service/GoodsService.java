package com.service;

import com.entities.GoodsDO;
import com.vo.goodsDetailVO;
import com.vo.goodsVO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 15:01:05
 */
public interface GoodsService {
	
	GoodsDO get(Long goodsId);
	
	List<goodsVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GoodsDO goods);
	
	int update(GoodsDO goods);
	
	int remove(Long goodsId);
	
	int batchRemove(Long[] goodsIds);

	goodsDetailVO findGoodsDetail(long goodsId);

	List<Map<String,Object>> findSupplierByOrg(Integer orgId);
	List<Map<String,Object>> findSubjectByOrg(Integer orgId);
	List<Map<String,Object>> findOrgByPid();
}
