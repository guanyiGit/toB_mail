package com.ordermanagement.service;


import com.entities.OrderDO;
import com.ordermanagement.vo.OrderDetail2VO;
import com.pojo.*;

import java.rmi.ServerException;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:44:38
 */
public interface OrderService {
	
	OrderDO get(Long id);

	List<OrderDetail2VO> list(Map<String, Object> map);

	List<Long> getIdList(Map<String,Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderDO order);
	
	int update(OrderDO order);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<DeliveryManager> deliveryManage(Long orgId,Long supId,String input,Integer num, Integer size) throws ServerException;//查询送货管理列表
	int deliveryManageCount(Long orgId,Long supId,String input) throws ServerException;//查询送货管理列表数量

	List<Map<String,String>> selectOrgs(Long supId)throws ServerException;//供应商查询公司

	List<HavesentSingle> selectHavesentSingles(Long supId,String input, Integer num, Integer size) throws ServerException;//查询送货管理已派单列表
	int selectHavesentSinglesCount(Long supId,String input) throws ServerException;//查询送货管理已派单列表总条数

	List<GoodsInfo> selectDeliveryGoods(Long supId, Long deliveryId,Long sendId) throws ServerException;//发货单详情里的商品列表

	List<Photos> selectOrderPhotos(Long deliveryId) throws ServerException;//查询发货单附件照片

	List<DeliveryMan> selectDeliveryMans(Long supId) throws ServerException;//查询送货人

	//订单----------------------------------------------------------------------------------------------------------
	List<FatherOrders> selectAuditApprovalOrders(Long userId,Long orgId,Integer status,String input,Integer month,Integer num,Integer size) throws ServerException;//查询订单审核列表
	List<FatherOrders> selectMyOrders(Long userId,Long orgId,Integer status,String input,Integer month,Integer num,Integer size) throws ServerException;//查询我的订单列表
	Integer selectFatherOrdersCount(Long userId,Long orgId,Integer status,String input,Integer month) throws ServerException;//查询订单审核列表数量
	Integer selectMyOrdersCount(Long userId,Long orgId,Integer status,String input,Integer month) throws ServerException;//查询我的订单列表数量

	List<ReceivingRecords> selectReceivingRecords(Long ogId) throws ServerException;//查询订单详情里每种商品的送货信息
	/**
	 * 查询订单审核审批信息
	 * @param orderId
	 * @return
	 */
	ApprovalReview selectApprovalReview(Long orderId) throws ServerException;
	/**
	 * 审核审批订单
	 * @param approvalReview
	 */
	void auditOrder(ApprovalReview approvalReview) throws ServerException;

	/**
	 * 修改订单状态
	 * @param orderId
	 * @param status
	 * @throws ServerException
	 */
	void updateOrderStatus(Long orderId,Integer status) throws ServerException;

	/**
	 * 查询订单费用
	 * @param orderId
	 * @return
	 */
	Double selectAdditionalFee(Long orderId) throws ServerException;

	/**
	 * 新增加发货单
	 * @param deliveryNote
	 * @throws ServerException
	 */
	void addInvoice(DeliveryNote deliveryNote) throws ServerException;
	//根据机构id得到供应商id
	Map getSupplierByOrgId(Integer orgId);
	//根据机构id得到资产清单列表
	List<Map>getAssetsList(Integer orgId);
	//添加资产清单
	int addAssets(Integer cartId,Integer orgId,Integer userId);
}
