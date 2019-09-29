package com.service;

import com.entities.SupplierDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 13:52:13
 */
public interface SupplierService {
	
	SupplierDO get(Integer supId);
	
	List<SupplierDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SupplierDO supplier);
	
	int update(SupplierDO supplier);
	
	int remove(Integer supId);
	
	int batchRemove(Integer[] supIds);
	/*------------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------------*/
	//查询所有供应商
	Map getSupplierList(String input,Integer userId,Integer start,Integer size);
	//根据id查询供应商
	Map getSupplierById(Integer id);
	//根据id删除
	int delSupplier(Integer id);
	//添加供应商
	int addSupplier(Map map);
	//添加供应商
	int updSupplier(Map map);
	//查询送货人员或者安装人员
	Map getDelivery(String input,Integer supplier_id,Integer type,Integer start,Integer size);
	//添加送货员或者安装员
	int addDelivery(Map map);
	//修改送货，安装人员状态
	int updDelivery(Integer id,Integer state);
	//查看送货，安装人员对象
	Map getDeliveryDetails(Integer id,Integer type);
	//修改送货，安装人员状态
	int updDeliverys(Map map);
	//查询所有商品
	Map getGoodsList(String input,Integer sup_id,Integer start,Integer size);
	//修改得到状态
	int updOrderState(Integer state,Integer id);
	//拒绝订单
	int refuseOrder(String refuse,Integer id);
	//根据id得到订单对象
	Map getOrder(Integer id);
	//根据机构id得到供应商id
	Map getSupplierByOrgId(Integer orgId);
	//根据订单id得到订单详情列表
	Map getOrderDetail(Integer id);
}
