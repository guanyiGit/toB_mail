package com.service.impl;

import com.vo.goodsDetailVO;
import com.vo.goodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.dao.GoodsDao;
import com.entities.GoodsDO;
import com.service.GoodsService;



@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public GoodsDO get(Long goodsId){
		return goodsDao.get(goodsId);
	}

	@Override
	public List<Map<String,Object>> findSupplierByOrg(Integer orgId) {
		return goodsDao.findSupplierByOrg(orgId);
	}

	@Override
	public List<Map<String,Object>> findSubjectByOrg(Integer orgId) {
		return goodsDao.findSubjectByOrg(orgId);
	}

	@Override
	public goodsDetailVO findGoodsDetail(long goodsId) {
        goodsDetailVO goodsDetailVO = goodsDao.findGoodsDetail(goodsId);
        goodsDetailVO.setAttrList(goodsDao.findAttrDetail(goodsId));
        List<Map<String, Object>> skuDetail = goodsDao.findSkuDetail(goodsId);
        goodsDetailVO.setSkuDetailList(skuDetail);
        List<Map<String, Object>> goodsPic = goodsDao.findGoodsPic(goodsId);
        goodsDetailVO.setGoodsPicList(goodsPic);
        return goodsDetailVO;
	}

	@Override
	public List<Map<String, Object>> findOrgByPid() {
		return goodsDao.findOrgByPid();
	}

	@Override
	public List<goodsVO> list(Map<String, Object> map){
		return goodsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return goodsDao.count(map);
	}
	
	@Override
	public int save(GoodsDO goods){
		return goodsDao.save(goods);
	}
	
	@Override
	public int update(GoodsDO goods){
		return goodsDao.update(goods);
	}
	
	@Override
	public int remove(Long goodsId){
		return goodsDao.remove(goodsId);
	}
	
	@Override
	public int batchRemove(Long[] goodsIds){
		return goodsDao.batchRemove(goodsIds);
	}
	
}
