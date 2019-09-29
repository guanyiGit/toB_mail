package com.service.impl;

import com.entities.SupplierDO;
import com.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.SupplierDao;
import com.service.SupplierService;



@Service
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	private SupplierDao supplierDao;
	
	@Override
	public SupplierDO get(Integer supId){
		return supplierDao.get(supId);
	}
	
	@Override
	public List<SupplierDO> list(Map<String, Object> map){
		return supplierDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return supplierDao.count(map);
	}
	
	@Override
	public int save(SupplierDO supplier){
		return supplierDao.save(supplier);
	}
	
	@Override
	public int update(SupplierDO supplier){
		return supplierDao.update(supplier);
	}
	
	@Override
	public int remove(Integer supId){
		return supplierDao.remove(supId);
	}
	
	@Override
	public int batchRemove(Integer[] supIds){
		return supplierDao.batchRemove(supIds);
	}
	/*------------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------------*/
	@Override
	public Map getSupplierList(String input,Integer userId,Integer start,Integer size) {
		try {
			Map<String,Object>map=new HashMap<>();
			if(input!=null){map.put("input",input);}
			if(userId!=null){map.put("userId",userId);}
			if(start!=null&&size!=null){
				int i=supplierDao.getSupplierListCount(map);
				PageInfo pageInfo=new PageInfo();
				pageInfo.setTotalCount(i);
				pageInfo.setPageSize(size);
				pageInfo.setPageNum(start);
				map.put("start",pageInfo.getTotalStart());
				map.put("size",pageInfo.getPageSize());
				map.put("pageInfo",pageInfo);
			}
			List<Map>list=supplierDao.getSupplierList(map);
			map.put("list",list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Map getSupplierById(Integer id) {
		return supplierDao.getSupplierById(id);
	}

	@Override
	public int delSupplier(Integer id) {
		return supplierDao.delSupplier(id);
	}

	@Override
	public int addSupplier(Map map) {
		try {
			if(supplierDao.addOrgInfo(map)>0){
				map.put("org_id",supplierDao.getId());
				return supplierDao.addSupplier(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	@Override
	public int updSupplier(Map map) {
		return supplierDao.updSupplier(map);
	}

	@Override
	public Map getDelivery(String input, Integer supplier_id, Integer type, Integer start, Integer size) {
		try {
			Map<String,Object>map=new HashMap<>();
			if(input!=null){map.put("input",input);}
			if(supplier_id!=null){map.put("supplier_id",supplier_id);}
			if(type!=null){map.put("type",type);}

			if(start!=null&&size!=null){
				int i=supplierDao.getDeliveryCount(map);
				PageInfo pageInfo=new PageInfo();
				pageInfo.setTotalCount(i);
				pageInfo.setPageSize(size);
				pageInfo.setPageNum(start);
				map.put("start",pageInfo.getTotalStart());
				map.put("size",pageInfo.getPageSize());
				map.put("pageInfo",pageInfo);
			}
			List<Map>list=supplierDao.getDelivery(map);
			map.put("list",list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int addDelivery(Map map) {
		return supplierDao.addDelivery(map);
	}

	@Override
	public int updDelivery(Integer id, Integer state) {
		return supplierDao.updDelivery(id,state);
	}

	@Override
	public Map getDeliveryDetails(Integer id, Integer type) {
		return supplierDao.getDeliveryDetails(id,type);
	}

	@Override
	public int updDeliverys(Map map) {
		return supplierDao.updDeliverys(map);
	}

	@Override
	public Map getGoodsList(String input, Integer sup_id, Integer start, Integer size) {
		try {
			Map<String,Object>map=new HashMap<>();
			if(input!=null&&!input.equals("")){map.put("input",input);}
			if(sup_id!=null){map.put("sup_id",sup_id);}
			if(start!=null&&size!=null){
				int i=supplierDao.getGoodsListCount(map);
				PageInfo pageInfo=new PageInfo();
				pageInfo.setTotalCount(i);
				pageInfo.setPageSize(size);
				pageInfo.setPageNum(start);
				map.put("start",pageInfo.getTotalStart());
				map.put("size",pageInfo.getPageSize());
				map.put("pageInfo",pageInfo);
			}
			List<Map>list=supplierDao.getGoodsList(map);
			map.put("list",list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updOrderState(Integer state, Integer id) {
		return supplierDao.updOrderState(state,id);
	}

	@Override
	public int refuseOrder(String refuse, Integer id) {
		try {
			if(supplierDao.updOrderState(5,id)>0){
				return supplierDao.updOrderRefuse(refuse,id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	@Override
	public Map getOrder(Integer id) {
		return supplierDao.getOrder(id);
	}

	@Override
	public Map getSupplierByOrgId(Integer orgId) {
		return supplierDao.getSupplierByOrgId(orgId);
	}

	@Override
	public Map getOrderDetail(Integer id) {
		try {
			Map map=supplierDao.getOrderById(id);//得到订单对象
			List<Map>orderDetailList=supplierDao.getOrderDetailListByOrderId(id);//得到订单详情集合
			int goodsId[];
			for (int i=0;i<orderDetailList.size();i++){
				int ogId=((Long)((orderDetailList.get(i)).get("id"))).intValue();
				List<Map>receiptList=supplierDao.getReceiptList(ogId);//得到收获地址
				orderDetailList.get(i).put("receiptList",receiptList);
				int goods_id=((Long)((orderDetailList.get(i)).get("goods_id"))).intValue();
				int sku_id=((Long)((orderDetailList.get(i)).get("sku_id"))).intValue();
				List<Map>skuList=supplierDao.getGoodsDetailList(goods_id,sku_id);
				orderDetailList.get(i).put("skuList",skuList);
			}
			map.put("list",orderDetailList);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
