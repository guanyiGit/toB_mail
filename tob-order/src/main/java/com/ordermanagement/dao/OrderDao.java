package com.ordermanagement.dao;


import com.entities.OrderDO;
import com.entities.OrgInfo;
import com.ordermanagement.vo.OrderDetailVO;
import com.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:44:38
 */
@Mapper
public interface OrderDao {

	OrderDO get(Long id);

	List<Long> getIdList(Map<String,Object> map);

	List<OrderDetailVO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(OrderDO order);

	int update(OrderDO order);

	int remove(Long id);

	int batchRemove(Long[] ids);

	List<OrderGoods> deliveryManage(@Param("orgId") Long orgId, @Param("supId") Long supId, @Param("input") String input, @Param("start") Integer start, @Param("size") Integer size);

	int deliveryManageCount(@Param("orgId") Long orgId, @Param("supId") Long supId, @Param("input") String input);

	List<Map<String, String>> selectOrgs(@Param("supId") Long supId);

	List<HavesentSingle> selectHavesentSingles(@Param("supId") Long supId, @Param("input") String input, @Param("start") Integer start, @Param("size") Integer size);//查询送货管理已派单列表

	int selectHavesentSinglesCount(@Param("supId") Long supId, @Param("input") String input);//查询送货管理已派单列表总条数

	/**
	 * 审核审批订单
	 * @param approvalReview
	 */
	void auditOrder(ApprovalReview approvalReview);
	/**
	 * 审核审批子订单
	 * @param approvalReview
	 */
	void auditOrderchild(ApprovalReview approvalReview);

	/**
	 * 改变父订单状态
	 * @param orderId
	 * @param status
	 */
	void updateFatherOrderStatus(@Param("orderId") Long orderId,@Param("status") Integer status);
	/**
	 * 改变子订单状态
	 * @param orderId
	 * @param status
	 */
	void updateChildOrderStatus(@Param("orderId") Long orderId,@Param("status") Integer status);

	/**
	 * 查询订单附加费用
	 * @param orderId
	 * @return
	 */
	Double selectAdditionalFee(@Param("orderId") Long orderId);

	/**
	 * 新增订单记录
	 * @param deliveryNote
	 */
	void addDeliveryRecord(@Param("deliveryNote") DeliveryNote deliveryNote);
	/**
	 * 新增订单详情
	 * @param deliverysDetail
	 */
	void addDeliveryDetail(DeliverysDetail deliverysDetail);

	/**
	 * 根据订单号查父订单号
	 * @param orderId
	 * @return
	 */
	Long selectParentOrderId(@Param("orderId") Long orderId);

	/**
	 * 修改发货记录表中已下发货单的货物数量
	 * @param ogId
	 * @param recipientId
	 * @param num
	 */
	void updateDeliveryNum(@Param("ogId") Long ogId,@Param("recipientId") Long recipientId,@Param("num") Integer num);

	List<GoodsInfo> selectDeliveryGoods(@Param("supId") Long supId, @Param("deliveryId") Long deliveryId,@Param("sendId") Long sendId);//发货单详情里的商品列表

	List<Photos> selectOrderPhotos(@Param("deliveryId") Long deliveryId);//查询发货单附件照片

	List<DeliveryMan> selectDeliveryMans(@Param("supId") Long supId);//查询送货人

	//订单-----------------------------------------------------------------------------------------------------------------------
	List<OrderGoods> selectMyOrders(@Param("userId") Long userId, @Param("orgId") Long orgId, @Param("status") Integer status, @Param("input") String input, @Param("month") Integer month);//查询我的订单或订单审核列表

	/**
	 * 查所有父订单和非子订单的有效订单
	 *
	 * @param userId
	 * @param orgId
	 * @param status
	 * @param input
	 * @param month
	 * @param start
	 * @param size
	 * @return
	 */
	List<FatherOrders> selectFatherOrders(@Param("userId") Long userId, @Param("orgId") Long orgId, @Param("status") Integer status, @Param("input") String input, @Param("month") Integer month, @Param("start") Integer start, @Param("size") Integer size);

	Integer selectFatherOrdersCount(@Param("userId") Long userId, @Param("orgId") Long orgId, @Param("status") Integer status, @Param("input") String input, @Param("month") Integer month);

	/**
	 * 查所有有效订单
	 *
	 * @param userId
	 * @param orgId
	 * @param status
	 * @param input
	 * @param month
	 * @param start
	 * @param size
	 * @return
	 */
	List<FatherOrders> selectEffectiveOrders(@Param("userId") Long userId, @Param("orgId") Long orgId, @Param("status") Integer status, @Param("input") String input, @Param("month") Integer month, @Param("start") Integer start, @Param("size") Integer size);

	Integer selectEffectiveOrdersCount(@Param("userId") Long userId, @Param("orgId") Long orgId, @Param("status") Integer status, @Param("input") String input, @Param("month") Integer month);

	/**
	 * 查询子订单
	 *
	 * @param parentId
	 * @return
	 */
	List<ChildOrders> selectChildOrders(@Param("parentId") Long parentId);

	/**
	 * 查订单的商品信息
	 *
	 * @param orderId
	 * @return
	 */
	List<GoodsInfo> selectOrderGoodsInfo(@Param("orderId") Long orderId, @Param("orgId") Long orgId);

	List<ReceivingRecords> selectReceivingRecords(@Param("ogId") Long ogId);//查询订单详情里每种商品的送货信息

	/**
	 * 查询订单审核审批信息
	 *
	 * @param orderId
	 * @return
	 */
	ApprovalReview selectApprovalReview(@Param("orderId") Long orderId);


	//得到订单集合总数量
	int getOrderListCount(Map map);

	//得到订单集合
	List<Map> getOrderList(Map map);

	//得到每个订单的订单详情集合
	List<Map> getOrderList(List list);


	/**
	 * 查询所有订单 （子订单和父订单集合）
	 *
	 * @return
	 */
	List<OrderDO> findOrderDetailsById(@Param("orderId") long orderId, @Param("isOneself") boolean isOneself);

	/**
	 * 查询订单下的送货列表
	 *
	 * @param orderIds
	 * @return
	 */
	List<DeliveryDetailVO> findDeliveryDetailsInOrderIds(@Param("orderIds") List<Long> orderIds);

	/**
	 * 查询订单下的安装列表
	 *
	 * @param orderIds
	 * @return
	 */
	List<InstallDetailVO> findInstallDetailsByOrderId(@Param("orderIds") List<Long> orderIds);

	/**
	 * 查询订单其他费用（统计用）
	 * @param type 费用类型
	//     *             0：订单额外费用
	 *             1：安装额外费用
	 *             2：送货额外费用
	 * @param orderIds 订单id集
	 * @return 费用类型，费用
	 *          0其他费用,
	 *          1安装费,
	 *          2运费,
	 *          3耗材费,
	 *          4高空作业费
	 */
	List<Map<String, Object>> findOtherCost(@Param("type") int type, @Param("orderIds")List<Long> orderIds);

	/**
	 * 查询订单其他费用（查列表）
	 * @param orderIds 订单id集
	 * @return
	 */
	List<OtherCostInfo> findOtherCostInfos(@Param("orderIds")List<Long> orderIds);
	List<String> getAttrValue(Map<String,Object> map);
	//根据机构id得到供应商id
	Map getSupplierByOrgId(@Param("orgId")Integer orgId);
	//得到所有orgId集合
	List<OrgInfo>getOrgInfoList();
	//根据orgId得到资产清单集合
	List<Map>getAssetsList(@Param("orgIdList")List<Integer>orgIdList);
	//添加资产清单
	int addAssets(@Param("list")List<Map>list);
	//根据订单id得到订单详情集合
	List<Map>getOrderDetailListByOrderId(@Param("id")Integer id);
	//根据商品id和skuid得到商品属性
	List<Map>getGoodsDetailList(@Param("goodsId")Integer goodsId,@Param("skuId")Integer skuId);
	//得到最大清单id
	int getAssetsIdMax();
}
