package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.entities.SupplierDO;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 13:52:13
 */
@Mapper
public interface SupplierDao {

	SupplierDO get(Integer supId);
	
	List<SupplierDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SupplierDO supplier);
	
	int update(SupplierDO supplier);
	
	int remove(Integer sup_id);
	
	int batchRemove(Integer[] supIds);
	/*------------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------------*/
	//得到新增数据后的id
	int getId();
	//查询所有供应商总条数orgId,input
	int getSupplierListCount(Map map);
	//查询所有供应商orgId,input,start,size
	List<Map>getSupplierList(Map map);
	//根据供应商id得到供应商
	Map getSupplierById(@Param("id")Integer id);
	//根据id删除供应商
	int delSupplier(@Param("id")Integer id);
	//添加供应商
	int addSupplier(Map map);
	//修改供应商
	int updSupplier(Map map);
	//查询送货人员或者安装人员总数量
	int getDeliveryCount(Map map);
	//查询送货人员或者安装人员集合
	List<Map>getDelivery(Map map);
	//添加送货或者安装人员
	int addDelivery(Map map);
	//修改送货，安装人员状态
	int updDelivery(@Param("id")Integer id,@Param("state")Integer state);
	//查询送货，安装人员对象
	Map getDeliveryDetails(@Param("id")Integer id,@Param("type")Integer type);
	//修改送货，安装人员
	int updDeliverys(Map map);
	//查询所有商品总条数orgId,input
	int getGoodsListCount(Map map);
	//查询所有商品orgId,input,start,size
	List<Map>getGoodsList(Map map);
	//修改订单状态
	int updOrderState(@Param("state")Integer state,@Param("id")Integer id);
	//修改订单表拒绝理由
	int updOrderRefuse(@Param("refuse")String state,@Param("id")Integer id);
	//根据id得到订单对象
	Map getOrder(@Param(value="id")Integer id);
	//新增组织（组织类型为供应商）
	int addOrgInfo(Map map);
	//根据机构id得到供应商id
	Map getSupplierByOrgId(@Param("orgId")Integer orgId);
	//根据id得到订单对象
	Map getOrderById(@Param("id")Integer id);
	//根据订单id得到订单详情集合
	List<Map>getOrderDetailListByOrderId(@Param("id")Integer id);
	//根据商品id和skuid得到商品属性
	List<Map>getGoodsDetailList(@Param("goodsId")Integer goodsId,@Param("skuId")Integer skuId);
	//根据订单详情id得到送货集合
	List<Map>getReceiptList(@Param("ogId")Integer ogId);
}
