package com.dao;

import com.entities.GoodsDO;

import java.util.List;
import java.util.Map;

import com.vo.attrDetailVO;
import com.vo.goodsDetailVO;
import com.vo.goodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 15:01:05
 */
@Mapper
public interface GoodsDao {

	GoodsDO get(Long goodsId);
	
	List<goodsVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GoodsDO goods);
	
	int update(GoodsDO goods);
	
	int remove(Long goods_id);
	
	int batchRemove(Long[] goodsIds);

	goodsDetailVO findGoodsDetail(long goodsId);

	List<attrDetailVO> findAttrDetail(long goodsId);

	List<Map<String,Object>> findSkuDetail(long goodsId);

	List<Map<String,Object>> findGoodsPic(long goodsId);

	List<Map<String,Object>> findSupplierByOrg(@Param("orgId") Integer orgId);
	List<Map<String,Object>> findSubjectByOrg(Integer orgId);
	List<Map<String,Object>> findOrgByPid();
}
