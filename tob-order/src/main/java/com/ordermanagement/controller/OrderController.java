package com.ordermanagement.controller;

import java.rmi.ServerException;
import java.util.List;
import java.util.Map;

import com.entities.OrderDO;
import com.ordermanagement.service.OrderService;
import com.ordermanagement.vo.OrderDetail2VO;
import com.pojo.*;
import com.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:44:38
 */
 
@Controller
@RequestMapping("/biz/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private RestTemplate restTemplate;
	@GetMapping()
	@RequiresPermissions("system:order:order")
	String Order(){
	    return "system/order/order";
	}

	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("system:order:order")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		int orgId=Integer.parseInt(params.get("supplierId").toString());

		//R map=restTemplate.getForObject("http://tob-supplier/com/supplier/getSupplierByOrgId?orgId="+orgId, R.class);
		int supplierId=Integer.parseInt((orderService.getSupplierByOrgId(orgId).get("sup_id")).toString());
		params.put("supplierId",supplierId);
		mallQuery query = new mallQuery(params);
		int total = orderService.count(query);
		List<Long> idList = orderService.getIdList(query);
		if(idList.size()>0){
			query.put("idList",idList);
			List<OrderDetail2VO> orders = orderService.list(query);
			R res = R.ok().put("total", total).put("orders", orders);
			return res;
		}else {
			return R.ok().put("total", total).put("orders", null);
		}

	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:order:add")
	String add(){
	    return "system/order/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:order:edit")
	String edit(@PathVariable("id") Long id,Model model){
		OrderDO order = orderService.get(id);
		model.addAttribute("order", order);
	    return "system/order/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:order:add")
	public R save( OrderDO order){
		if(orderService.save(order)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:order:edit")
	public R update( OrderDO order){
		orderService.update(order);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("system:order:remove")
	public R remove( Long id){
		if(orderService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:order:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		orderService.batchRemove(ids);
		return R.ok();
	}

	/**
	 *查询送货管理列表
	 * @param orgId
	 * @return
	 */
	@GetMapping( "/deliveryManage")
	@ResponseBody
//	@RequiresPermissions("system:order:batchRemove")
	public R deliveryManage(Long orgId,Long supId,String input,Integer num, Integer size){
		if (input == null || "".equals(input)){
			input=null;
		}
		List<DeliveryManager> list=null;
		int count=0;
		try {
			list=orderService.deliveryManage(orgId,supId,input,num,size);
			count=orderService.deliveryManageCount(orgId,supId,input);
		} catch (ServerException e) {
			R.error();
		}
		return R.ok().put("list",list).put("count",count);
	}

	/**
	 * 供应商查询公司
	 * @param supId
	 * @return
	 */
	@GetMapping( "/selectOrgs")
	@ResponseBody
//	@RequiresPermissions("system:order:batchRemove")
	public R selectOrgs(Long supId){
		List<Map<String, String>> list=null;
		try {
			list=orderService.selectOrgs(supId);
		} catch (ServerException e) {
			R.error();
		}
		return R.ok().put("list",list);
	}

	@GetMapping( "/selectHavesentSingleeee")
	@ResponseBody
//	@RequiresPermissions("system:order:batchRemove")
	public R selectHavesentSingles(Long supId,String input, Integer num, Integer size){
		if (input == null || "".equals(input)){
			input=null;
		}
		List<HavesentSingle> list=null;
		int count=0;
		try {
			list=orderService.selectHavesentSingles(supId,input,num,size);
			count=orderService.selectHavesentSinglesCount(supId,input);
		} catch (ServerException e) {
			R.error();
		}
		return R.ok().put("list",list).put("count",count);
	}


	/**
	 * 发货单详情里的商品列表
	 * @param supId
	 * @param deliveryId
	 * @return
	 */
	@GetMapping( "/selectDeliveryGoods")
	@ResponseBody
//	@RequiresPermissions("system:order:batchRemove")
	public R selectDeliveryGoods(Long supId, Long deliveryId,Long sendId){
		List<GoodsInfo> list=null;
		try {
			list=orderService.selectDeliveryGoods(supId,deliveryId,sendId);
		} catch (ServerException e) {
			R.error();
		}
		return R.ok().put("list",list);
	}

	/**
	 * 查询送货单附件照片
	 * @param deliveryId
	 * @return
	 */
	@GetMapping( "/selectOrderPhotos")
	@ResponseBody
//	@RequiresPermissions("system:order:batchRemove")
	public R selectOrderPhotos(Long deliveryId){
		List<Photos> list=null;
		try {
			list= orderService.selectOrderPhotos(deliveryId);
		} catch (ServerException e) {
			R.error();
		}
		return R.ok().put("list",list);
	}

	/**
	 * 新增送货单查送货人员信息
	 * @param supId
	 * @return
	 */
	@GetMapping( "/selectDeliveryMans")
	@ResponseBody
//	@RequiresPermissions("system:order:batchRemove")
	public R selectDeliveryMans(Long supId){
		List<DeliveryMan> list=null;
		try {
			list=orderService.selectDeliveryMans(supId);
		} catch (ServerException e) {
			R.error();
		}
		return R.ok().put("list",list);
	}

	//生成送货单号
	@RequestMapping("/generateDeliveryNo")
	@ResponseBody
	public String generateDeliveryNo() {
		return CodeFactory.generateCode(4, true);
	}



	//订单---------------------------------------------------------------------------------------------------------------

	/**
	 * 查询我的订单或订单审核列表
	 * @param userId
	 * @param orgId
	 * @param status
	 * @param input
	 * @param month
	 * @return
	 */
	@GetMapping( "/selectAuditApprovalOrders")
	@ResponseBody
//	@RequiresPermissions("system:order:batchRemove")
	public R selectAuditApprovalOrders(Long userId, Long orgId, Integer status, String input, Integer month,Integer num,Integer size){
		if (input == null || "".equals(input)){
			input=null;
		}
		List<FatherOrders> list=null;
		Integer count=0;
		try {
			list=orderService.selectAuditApprovalOrders(userId,orgId,status,input,month,num,size);
			count=orderService.selectFatherOrdersCount(userId,orgId,status,input,month);
		} catch (ServerException e) {
			R.error();
		}
		return R.ok().put("list",list).put("count",count);
	}

	/**
	 * 查询我的订单列表
	 * @param userId
	 * @param orgId
	 * @param status
	 * @param input
	 * @param month
	 * @return
	 */
	@GetMapping( "/selectMyOrders")
	@ResponseBody
//	@RequiresPermissions("system:order:batchRemove")
	public R selectMyOrders(Long userId, Long orgId, Integer status, String input, Integer month,Integer num,Integer size){
		if (input == null || "".equals(input)){
			input=null;
		}
		List<FatherOrders> list=null;
		Integer count=0;
		try {
			list=orderService.selectMyOrders(userId,orgId,status,input,month,num,size);
			count=orderService.selectMyOrdersCount(userId,orgId,status,input,month);
		} catch (ServerException e) {
			R.error();
		}
		return R.ok().put("list",list).put("count",count);
	}

	/**
	 * 查送货记录信息
	 * @param ogId
	 * @return
	 */
	@GetMapping( "/selectReceivingRecords")
	@ResponseBody
//	@RequiresPermissions("system:order:batchRemove")
	public R selectReceivingRecords(Long ogId){
		List<ReceivingRecords> list=null;
		try {
			list=orderService.selectReceivingRecords(ogId);
		} catch (ServerException e) {
			R.error();
		}
		return R.ok().put("list",list);
	}

	/**
	 * 查询订单审核审批信息
	 * @param orderId
	 * @return
	 */
	@GetMapping( "/selectApprovalReview")
	@ResponseBody
//	@RequiresPermissions("system:order:batchRemove")
	public R selectApprovalReview(Long orderId){
		ApprovalReview approvalReview=null;
		try {
			approvalReview=orderService.selectApprovalReview(orderId);
		} catch (ServerException e) {
			R.error();
		}
		return R.ok().put("obj",approvalReview);
	}

	/**
	 * 审核审批订单
	 * @param approvalReview
	 * @return
	 */
	@PostMapping( "/auditOrder")
	@ResponseBody
//	@RequiresPermissions("system:order:batchRemove")
	public R auditOrder(@RequestBody ApprovalReview approvalReview){
		try {
			orderService.auditOrder(approvalReview);
		} catch (ServerException e) {
			R.error();
		}
		return R.ok().put("string","提交审核成功");
	}

	/**
	 * 修改订单状态
	 * @param orderId
	 * @param status
	 * @return
	 */
	@GetMapping( "/updateOrderStatus")
	@ResponseBody
//	@RequiresPermissions("system:order:batchRemove")
	public R updateOrderStatus(Long orderId, Integer status){
		try {
			orderService.updateOrderStatus(orderId,status);
		} catch (ServerException e) {
			R.error();
		}
		return R.ok().put("string","修改订单状态成功");
	}

	/**
	 * 查询订单附加费用
	 * @param orderId
	 * @return
	 */
	@GetMapping( "/selectAdditionalFee")
	@ResponseBody
//	@RequiresPermissions("system:order:batchRemove")
	public R selectAdditionalFee(Long orderId){
		Double money=null;
		try {
			money=orderService.selectAdditionalFee(orderId);
		} catch (ServerException e) {
			R.error();
		}
		return R.ok().put("money",money);
	}

	@PostMapping( "/addInvoice")
	@ResponseBody
//	@RequiresPermissions("system:order:batchRemove")
	public R addInvoice(@RequestBody DeliveryNote deliveryNote){
		try {
			orderService.addInvoice(deliveryNote);
		} catch (ServerException e) {
			R.error();
		}
		return R.ok().put("string","新增发货单成功");
	}
	//根据orgId得到资产清单集合
	@RequestMapping( "/getAssetsList")
	@ResponseBody
	public R getAssetsList(Integer orgId){
		try {
			return R.ok(orderService.getAssetsList(orgId));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	//根据orgId得到资产清单集合
	@RequestMapping( "/addAssets")
	@ResponseBody
	public R addAssets(Integer orderId,Integer orgId,Integer userId){
		try {
			return R.ok(orderService.addAssets(orderId,orgId,userId));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
}
