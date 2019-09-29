package com.ordermanagement.service.impl;

import com.entities.OrderDO;
import com.entities.OrgInfo;
import com.ordermanagement.dao.OrderDao;
import com.ordermanagement.service.OrderService;
import com.ordermanagement.vo.OrderDetail2VO;
import com.ordermanagement.vo.OrderDetailVO;
import com.pojo.*;
import com.utils.orgtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.rmi.ServerException;
import java.util.*;

import static com.utils.StringUtils.convertAttrValue;


@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public OrderDO get(Long id){
		return orderDao.get(id);
	}

	@Override
	public List<OrderDetail2VO> list(Map<String, Object> map){
		List<OrderDetailVO> orderList = orderDao.list(map);
		//查询每个单品的属性
		Map<String,Object> query  = new HashMap<>();
		for (OrderDetailVO vo:
				orderList) {
			Long skuId = vo.getSkuId();
			Long goodsId = vo.getGoodsId();
			query.put("skuId",skuId);
			query.put("goodsId",goodsId);
			List<String> attrValue = orderDao.getAttrValue(query);
			String attrStr = convertAttrValue(attrValue);
			vo.setGoodsAttr(attrStr);
			query.clear();
		}
		//将同一订单的数据合并成一条数据
		List<OrderDetail2VO> resList = convertList(orderList);
		return resList;
	}
	public List<OrderDetail2VO> convertList(List<OrderDetailVO> orderList) {
		List<OrderDetail2VO> res = new ArrayList<>();
		Map<String,List<OrderDetailVO>> map = new HashMap<>();
		for (OrderDetailVO vo:
				orderList) {
			String orderNo = vo.getOrderNo();
			if(map.containsKey(orderNo)) {
				List<OrderDetailVO> orderDetailVOS = map.get(orderNo);
				orderDetailVOS.add(vo);
			}else {
				//第一次添加
				List<OrderDetailVO> list = new ArrayList<>();
				list.add(vo);
				map.put(vo.getOrderNo(),list);
			}
		}
		for (String key : map.keySet()) {
			OrderDetail2VO vo = new OrderDetail2VO();
			List<OrderDetailVO> orderDetailVOS = map.get(key);
			OrderDetailVO examVo = orderDetailVOS.get(0);     //取第一个的作为公共的，正常来说集合对象的某些属性都是一样
			vo.setCreateTime(examVo.getCreateTime());
			vo.setOrderId(examVo.getOrderId());
			vo.setOrgName(examVo.getOrgName());
			vo.setName(examVo.getName());
			//vo.setOStatus(examVo.getoStatus());
			vo.setOrderNo(key);
			vo.setOrderList(map.get(key));
			BigDecimal bd = new BigDecimal("0");
			//计算该订单的总金额
			for (OrderDetailVO orderVo: orderDetailVOS) {
				BigDecimal price = orderVo.getPrice();
				BigDecimal totalNum = BigDecimal.valueOf(orderVo.getTotalNum());
				BigDecimal multiply = price.multiply(totalNum);
				bd = bd.add(multiply);
			}
			vo.setTotalMoney(bd);
			res.add(vo);
		}
		return res;
	}
	@Override
	public List<Long> getIdList(Map<String, Object> map) {
		return orderDao.getIdList(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderDao.count(map);
	}
	
	@Override
	public int save(OrderDO order){
		return orderDao.save(order);
	}
	
	@Override
	public int update(OrderDO order){
		return orderDao.update(order);
	}
	
	@Override
	public int remove(Long id){
		return orderDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return orderDao.batchRemove(ids);
	}

	/**
	 * 查询送货管理列表
	 * @param orgId
	 * @return
	 */
	@Override
	public List<DeliveryManager> deliveryManage(Long orgId,Long supId,String input,Integer num, Integer size) throws ServerException {
		int start = 0;
		if (num!=null){
			if (num>1){
				start=(num-1)*size;
			}
		}
		List<OrderGoods> list=null;
		List<DeliveryManager> list1 = new ArrayList<>();
		try {
			list=orderDao.deliveryManage(orgId,supId,input,start,size);
			for (int i=0;i<list.size();i++){
				OrderGoods orderGoods = list.get(i);
				boolean found =true;
				if (list1.size()==0){
					DeliveryManager deliveryManager = new DeliveryManager();
					deliveryManager.setOrderId(orderGoods.getOrderId());
					deliveryManager.setOrderNo(orderGoods.getOrderNo());
					deliveryManager.setCreateTime(orderGoods.getCreateTime());
					deliveryManager.setRecipientAddress(orderGoods.getRecipientAddress());
					deliveryManager.setRecipientId(orderGoods.getRecipientId());
					deliveryManager.setRecipientName(orderGoods.getRecipientName());
					deliveryManager.setRecipientPhone(orderGoods.getRecipientPhone());
					GoodsInfo goodsInfo = new GoodsInfo();
					goodsInfo.setGoId(orderGoods.getGoId());
					goodsInfo.setGoodsName(orderGoods.getGoodsName());
					goodsInfo.setGoodsNo(orderGoods.getGoodsNo());
					goodsInfo.setGoodsNum(orderGoods.getGoodsNum());
					goodsInfo.setReceiveNum(orderGoods.getReceiveNum());
					goodsInfo.setSkuId(orderGoods.getSkuId());
					List<GoodsInfo> goodsInfoList = new ArrayList<>();
					goodsInfoList.add(goodsInfo);
					deliveryManager.setGoodsInfos(goodsInfoList);
					list1.add(deliveryManager);
					continue;
				}
				for (int j=0;j<list1.size()&&found;j++){
					if (orderGoods.getOrderId()==list1.get(j).getOrderId() && orderGoods.getRecipientId()==list1.get(j).getRecipientId()){
						GoodsInfo goodsInfo = new GoodsInfo();
						goodsInfo.setGoId(orderGoods.getGoId());
						goodsInfo.setGoodsName(orderGoods.getGoodsName());
						goodsInfo.setGoodsNo(orderGoods.getGoodsNo());
						goodsInfo.setGoodsNum(orderGoods.getGoodsNum());
						goodsInfo.setReceiveNum(orderGoods.getReceiveNum());
						goodsInfo.setSkuId(orderGoods.getSkuId());
						List<GoodsInfo> goodsInfoList = new ArrayList<>();
						if (list1.get(j).getGoodsInfos()==null){
							goodsInfoList.add(goodsInfo);
							list1.get(j).setGoodsInfos(goodsInfoList);
						}else {
							goodsInfoList=list1.get(j).getGoodsInfos();
							goodsInfoList.add(goodsInfo);
							list1.get(j).setGoodsInfos(goodsInfoList);
						}
						found=false;
						break;
					}else if (j==list1.size()-1){
						DeliveryManager deliveryManager = new DeliveryManager();
						deliveryManager.setOrderId(orderGoods.getOrderId());
						deliveryManager.setOrderNo(orderGoods.getOrderNo());
						deliveryManager.setCreateTime(orderGoods.getCreateTime());
						deliveryManager.setRecipientAddress(orderGoods.getRecipientAddress());
						deliveryManager.setRecipientId(orderGoods.getRecipientId());
						deliveryManager.setRecipientName(orderGoods.getRecipientName());
						deliveryManager.setRecipientPhone(orderGoods.getRecipientPhone());
						GoodsInfo goodsInfo = new GoodsInfo();
						goodsInfo.setGoId(orderGoods.getGoId());
						goodsInfo.setGoodsName(orderGoods.getGoodsName());
						goodsInfo.setGoodsNo(orderGoods.getGoodsNo());
						goodsInfo.setGoodsNum(orderGoods.getGoodsNum());
						goodsInfo.setReceiveNum(orderGoods.getReceiveNum());
						goodsInfo.setSkuId(orderGoods.getSkuId());
						List<GoodsInfo> goodsInfoList = new ArrayList<>();
						if (deliveryManager.getGoodsInfos()==null){
							goodsInfoList.add(goodsInfo);
							deliveryManager.setGoodsInfos(goodsInfoList);
						}else {
							goodsInfoList=list1.get(j).getGoodsInfos();
							goodsInfoList.add(goodsInfo);
							deliveryManager.setGoodsInfos(goodsInfoList);
						}
						list1.add(deliveryManager);
						found=false;
						break;
					}
				}
			}
		} catch (Exception e) {
			String log = "查询送货管理列表失败!";
			throw new ServerException(log,e);
		}
		return list1;
	}

	@Override
	public int deliveryManageCount(Long orgId, Long supId, String input) throws ServerException {
		int count=0;
		try {
			count=orderDao.deliveryManageCount(orgId,supId,input);
		} catch (Exception e) {
			String log = "查询送货管理列表失败总数失败!";
			throw new ServerException(log,e);
		}
		return count;
	}

	/**
	 * 供应商查询公司
	 * @param supId
	 * @return
	 * @throws ServerException
	 */
	@Override
	public List<Map<String, String>> selectOrgs(Long supId) throws ServerException {
		List<Map<String, String>> list=null;
		try {
			list=orderDao.selectOrgs(supId);
		} catch (Exception e) {
			String log = "供应商查询公司失败!";
			throw new ServerException(log,e);
		}
		return list;
	}

	@Override
	public List<HavesentSingle> selectHavesentSingles(Long supId,String input, Integer num, Integer size) throws ServerException{
		int start = 0;
		if (num!=null){
			if (num>1){
				start=(num-1)*size;
			}
		}
		List<HavesentSingle> list = null;
		try {
			list=orderDao.selectHavesentSingles(supId,input,start,size);
		} catch (Exception e) {
			String log = "供应商查询已派单列表失败!";
			throw new ServerException(log,e);
		}
		return list;
	}



	@Override
	public int selectHavesentSinglesCount(Long supId,String input) throws ServerException{
		int count=0;
		try {
			count=orderDao.selectHavesentSinglesCount(supId,input);
		} catch (Exception e) {
			String log = "供应商查询已派单列表总数失败!";
			throw new ServerException(log,e);
		}
		return count;
	}

	@Override
	public List<GoodsInfo> selectDeliveryGoods(Long supId, Long deliveryId,Long sendId) throws ServerException{
		List<GoodsInfo> list = null;
		try {
			list=orderDao.selectDeliveryGoods(supId,deliveryId,sendId);
		} catch (Exception e) {
			String log = "发货单详情商品信息列表失败!";
			throw new ServerException(log,e);
		}
		return list;
	}

	@Override
	public List<Photos> selectOrderPhotos(Long deliveryId) throws ServerException {
		List<Photos> list = null;
		try {
			list=orderDao.selectOrderPhotos(deliveryId);
		} catch (Exception e) {
			String log = "查询发货单附件照片失败!";
			throw new ServerException(log,e);
		}
		return list;
	}

	@Override
	public List<DeliveryMan> selectDeliveryMans(Long supId) throws ServerException {
		List<DeliveryMan> list = null;
		try {
			list=orderDao.selectDeliveryMans(supId);
		} catch (Exception e) {
			String log = "查询送货人失败!";
			throw new ServerException(log,e);
		}
		return list;
	}
	//订单---------------------------------------------------------------------------------------------------------------

	/**
	 * 查询订单审核审批列表
	 * @param userId
	 * @param orgId
	 * @param status
	 * @param input
	 * @param month
	 * @return
	 * @throws ServerException
	 */
	@Override
	public List<FatherOrders> selectAuditApprovalOrders(Long userId, Long orgId, Integer status, String input, Integer month,Integer num,Integer size) throws ServerException {
//		List<OrderGoods> list=null;
		int start = 0;
		if (size==null){
			size=2;
		}
		if (num!=null){
			if (num>1){
				start=(num-1)*size;
			}
		}
		List<FatherOrders> list1 = new ArrayList<>();
		try {
//			list=orderDao.selectMyOrders(userId,orgId,status,input,month);
//			for (int i=0;i<list.size();i++){
//				OrderGoods orderGoods = list.get(i);
//				boolean found =true;
//				for (int j=0;j<=list1.size()&&found;j++){
//					if (orderGoods.getParentId()==0){
//						if (list1.size()>0 && orderGoods.getOrderId()==list1.get(j).getOrderId() && orderGoods.getSkuId()!=null){//没有子级订单并且已经添加了订单信息
//							GoodsInfo goodsInfo = new GoodsInfo();
//							goodsInfo.setGoId(orderGoods.getGoId());
//							goodsInfo.setGoodsName(orderGoods.getGoodsName());
//							goodsInfo.setGoodsNo(orderGoods.getGoodsNo());
//							goodsInfo.setGoodsNum(orderGoods.getGoodsNum());
//							goodsInfo.setReceiveNum(orderGoods.getReceiveNum());
//							goodsInfo.setSkuId(orderGoods.getSkuId());
//							goodsInfo.setImgurl(orderGoods.getImgurl());
//							goodsInfo.setPrice(orderGoods.getPrice());
//							goodsInfo.setPurchaseNum(orderGoods.getPurchaseNum());
//							BigDecimal totalPrice=list1.get(j).getTotalAmount().add(orderGoods.getTotalPrice());
//							List<GoodsInfo> goodsInfoList = new ArrayList<>();
//							if (list1.get(j).getGoodss()==null){
//								goodsInfoList.add(goodsInfo);
//								list1.get(j).setGoodss(goodsInfoList);
//							}else {
//								goodsInfoList=list1.get(j).getGoodss();
//								goodsInfoList.add(goodsInfo);
//								list1.get(j).setGoodss(goodsInfoList);
//							}
//							list1.get(j).setTotalAmount(totalPrice);
//							found=false;
//							break;
//						}else if (orderGoods.getSkuId()==null){//父级订单
//							if (list1.size()>0 && orderGoods.getOrderId()==list1.get(j).getOrderId()){
//							}else if (list1.size()==0 || j==list1.size()-1){
//								FatherOrders fatherOrders = new FatherOrders();
//								fatherOrders.setOrderId(orderGoods.getOrderId());
//								fatherOrders.setOrderNo(orderGoods.getOrderNo());
//								fatherOrders.setBuyerId(orderGoods.getBuyerId());
//								fatherOrders.setBuyer(orderGoods.getBuyer());
//								fatherOrders.setCreateTime(orderGoods.getCreateTime());
//								fatherOrders.setOrgId(orderGoods.getOrgId());
//								fatherOrders.setCompany(orderGoods.getCompany());
//								list1.add(fatherOrders);
//								found=false;
//								break;
//							}
//						}else if (j==list1.size()-1 || list1.size()==0){//没有子级订单且没添加过的订单
//							FatherOrders fatherOrders = new FatherOrders();
//							fatherOrders.setOrderId(orderGoods.getOrderId());
//							fatherOrders.setOrderNo(orderGoods.getOrderNo());
//							fatherOrders.setBuyerId(orderGoods.getBuyerId());
//							fatherOrders.setBuyer(orderGoods.getBuyer());
//							fatherOrders.setCreateTime(orderGoods.getCreateTime());
//							fatherOrders.setOrgId(orderGoods.getOrgId());
//							fatherOrders.setCompany(orderGoods.getCompany());
//							GoodsInfo goodsInfo = new GoodsInfo();
//							goodsInfo.setGoId(orderGoods.getGoId());
//							goodsInfo.setGoodsName(orderGoods.getGoodsName());
//							goodsInfo.setGoodsNo(orderGoods.getGoodsNo());
//							goodsInfo.setGoodsNum(orderGoods.getGoodsNum());
//							goodsInfo.setReceiveNum(orderGoods.getReceiveNum());
//							goodsInfo.setSkuId(orderGoods.getSkuId());
//							goodsInfo.setImgurl(orderGoods.getImgurl());
//							goodsInfo.setPrice(orderGoods.getPrice());
//							goodsInfo.setPurchaseNum(orderGoods.getPurchaseNum());
//							BigDecimal totalPrice=fatherOrders.getTotalAmount().add(orderGoods.getTotalPrice());
//							List<GoodsInfo> goodsInfoListt = new ArrayList<>();
//							if (fatherOrders.getGoodss()==null){
//								goodsInfoListt.add(goodsInfo);
//								fatherOrders.setGoodss(goodsInfoListt);
//							}else {
//								goodsInfoListt=fatherOrders.getGoodss();
//								goodsInfoListt.add(goodsInfo);
//								fatherOrders.setGoodss(goodsInfoListt);
//							}
//							fatherOrders.setTotalAmount(totalPrice);
//							list1.add(fatherOrders);
//							found=false;
//							break;
//						}
//					}else {
//						if (list1.size()>0 && orderGoods.getParentId()==list1.get(j).getOrderId()){
//							List<ChildOrders> list2=list1.get(j).getChildOrders();
//							boolean fou=true;
//							for (int k=0;k<list2.size()&&fou;k++){
//								if (list2.size()<0 && list2.get(k).getOrderId()==orderGoods.getOrderId()){
//									GoodsInfo goodsInfo = new GoodsInfo();
//									goodsInfo.setGoId(orderGoods.getGoId());
//									goodsInfo.setGoodsName(orderGoods.getGoodsName());
//									goodsInfo.setGoodsNo(orderGoods.getGoodsNo());
//									goodsInfo.setGoodsNum(orderGoods.getGoodsNum());
//									goodsInfo.setReceiveNum(orderGoods.getReceiveNum());
//									goodsInfo.setSkuId(orderGoods.getSkuId());
//									goodsInfo.setImgurl(orderGoods.getImgurl());
//									goodsInfo.setPrice(orderGoods.getPrice());
//									goodsInfo.setPurchaseNum(orderGoods.getPurchaseNum());
//									List<GoodsInfo> goodss = new ArrayList<>();
//									if (list2.get(k).getGoodss()==null){
//										goodss.add(goodsInfo);
//										list2.get(k).setGoodss(goodss);
//									}else {
//										goodss=list2.get(k).getGoodss();
//										goodss.add(goodsInfo);
//										list2.get(k).setGoodss(goodss);
//									}
//									BigDecimal totalPrice=list1.get(j).getTotalAmount().add(orderGoods.getTotalPrice());
//									list1.get(j).setTotalAmount(totalPrice);
//									fou=false;
//									break;
//								}else if (list2.size()==0 || k==list2.size()-1){
//									ChildOrders childOrders = new ChildOrders();
//									childOrders.setOrderId(orderGoods.getOrderId());
//									childOrders.setOrderNo(orderGoods.getOrderNo());
//									childOrders.setOrgId(orderGoods.getOrgId());
//									childOrders.setCompany(orderGoods.getCompany());
//									GoodsInfo goodsInfo = new GoodsInfo();
//									goodsInfo.setGoId(orderGoods.getGoId());
//									goodsInfo.setGoodsName(orderGoods.getGoodsName());
//									goodsInfo.setGoodsNo(orderGoods.getGoodsNo());
//									goodsInfo.setGoodsNum(orderGoods.getGoodsNum());
//									goodsInfo.setReceiveNum(orderGoods.getReceiveNum());
//									goodsInfo.setSkuId(orderGoods.getSkuId());
//									goodsInfo.setImgurl(orderGoods.getImgurl());
//									goodsInfo.setPrice(orderGoods.getPrice());
//									goodsInfo.setPurchaseNum(orderGoods.getPurchaseNum());
//									List<GoodsInfo> goodss = new ArrayList<>();
//									if (list2.get(k).getGoodss()==null){
//										goodss.add(goodsInfo);
//										list2.get(k).setGoodss(goodss);
//									}else {
//										goodss=list2.get(k).getGoodss();
//										goodss.add(goodsInfo);
//										list2.get(k).setGoodss(goodss);
//									}
//									BigDecimal totalPrice=list1.get(j).getTotalAmount().add(orderGoods.getTotalPrice());
//									list1.get(j).setTotalAmount(totalPrice);
//									fou=false;
//									break;
//								}
//							}
//						}else if (j==list1.size()-1 || list1.size()==0){
//							//添加3级信息
//							FatherOrders fatherOrders = new FatherOrders();
//							fatherOrders.setOrderId(orderGoods.getOrderId());
//							fatherOrders.setOrderNo(orderGoods.getOrderNo());
//							fatherOrders.setBuyerId(orderGoods.getBuyerId());
//							fatherOrders.setBuyer(orderGoods.getBuyer());
//							fatherOrders.setCreateTime(orderGoods.getCreateTime());
//							fatherOrders.setOrgId(orderGoods.getOrgId());
//							fatherOrders.setCompany(orderGoods.getCompany());
//							ChildOrders childOrders = new ChildOrders();
//							childOrders.setOrderId(orderGoods.getOrderId());
//							childOrders.setOrderNo(orderGoods.getOrderNo());
//							childOrders.setOrgId(orderGoods.getOrgId());
//							childOrders.setCompany(orderGoods.getCompany());
//							GoodsInfo goodsInfo = new GoodsInfo();
//							goodsInfo.setGoId(orderGoods.getGoId());
//							goodsInfo.setGoodsName(orderGoods.getGoodsName());
//							goodsInfo.setGoodsNo(orderGoods.getGoodsNo());
//							goodsInfo.setGoodsNum(orderGoods.getGoodsNum());
//							goodsInfo.setReceiveNum(orderGoods.getReceiveNum());
//							goodsInfo.setSkuId(orderGoods.getSkuId());
//							goodsInfo.setImgurl(orderGoods.getImgurl());
//							goodsInfo.setPrice(orderGoods.getPrice());
//							goodsInfo.setPurchaseNum(orderGoods.getPurchaseNum());
//							List<GoodsInfo> goodss = new ArrayList<>();
//							if (childOrders.getGoodss()==null){
//								goodss.add(goodsInfo);
//								childOrders.setGoodss(goodss);
//							}else {
//								goodss=childOrders.getGoodss();
//								goodss.add(goodsInfo);
//								childOrders.setGoodss(goodss);
//							}
//							fatherOrders.setTotalAmount(orderGoods.getTotalPrice());
//							List<ChildOrders> childOrderss = new ArrayList<>();
//							if (fatherOrders.getChildOrders()==null){
//								childOrderss.add(childOrders);
//								fatherOrders.setChildOrders(childOrderss);
//							}else {
//								childOrderss=fatherOrders.getChildOrders();
//								childOrderss.add(childOrders);
//								fatherOrders.setChildOrders(childOrderss);
//							}
//							list1.add(fatherOrders);
//							found=false;
//							break;
//						}
//					}
//				}
//			}


			list1=orderDao.selectFatherOrders(userId,orgId,status,input,month,start,size);
			for (int i=0;i<list1.size();i++){
				if (list1.get(i)!=null && list1.get(i).getIsUseful()!=null){
					if (list1.get(i).getIsUseful()==1){
						List<GoodsInfo> goodss = new ArrayList<>();
						goodss=orderDao.selectOrderGoodsInfo(list1.get(i).getOrderId(),list1.get(i).getOrgId());
						for (int j=0;j<goodss.size();j++){
							if (goodss.get(j).getPriceTotal()!=null){
								BigDecimal totalPrice=new BigDecimal("0.00");
								if (list1.get(i).getTotalAmount()!=null){
									totalPrice=list1.get(i).getTotalAmount();
								}
                                totalPrice=totalPrice.add(goodss.get(j).getPriceTotal());
								list1.get(i).setTotalAmount(totalPrice);
							}
						}
						list1.get(i).setGoodss(goodss);
					}else if (list1.get(i).getIsUseful()==0){
						List<ChildOrders> childOrders = new ArrayList<>();
						childOrders=orderDao.selectChildOrders(list1.get(i).getOrderId());
						for (int k=0;k<childOrders.size();k++){
							List<GoodsInfo> goodsss = new ArrayList<>();
							goodsss=orderDao.selectOrderGoodsInfo(childOrders.get(k).getOrderId(),childOrders.get(k).getOrgId());
							for (int j=0;j<goodsss.size();j++){
								if (goodsss.get(j).getPriceTotal()!=null){
									BigDecimal totalPrice=new BigDecimal("0.00");
									if (list1.get(i).getTotalAmount()!=null){
										totalPrice=list1.get(i).getTotalAmount();
									}
                                    totalPrice=totalPrice.add(goodsss.get(j).getPriceTotal());
									list1.get(i).setTotalAmount(totalPrice);
								}
							}
							childOrders.get(k).setGoodss(goodsss);
						}
						list1.get(i).setChildOrders(childOrders);
					}
				}
			}
		} catch (Exception e) {
			String log = "查询我的订单或订单审核审批列表失败!";
			throw new ServerException(log,e);
		}
		return list1;
	}

	/**
	 * 查询我的订单列表
	 * @param userId
	 * @param orgId
	 * @param status
	 * @param input
	 * @param month
	 * @return
	 * @throws ServerException
	 */
	@Override
	public List<FatherOrders> selectMyOrders(Long userId, Long orgId, Integer status, String input, Integer month,Integer num,Integer size) throws ServerException {
		int start = 0;
		if (size==null){
			size=2;
		}
		if (num!=null){
			if (num>1){
				start=(num-1)*size;
			}
		}
		List<FatherOrders> list1 = new ArrayList<>();
		try {
			list1=orderDao.selectEffectiveOrders(userId,orgId,status,input,month,start,size);
			for (int i=0;i<list1.size();i++){
				if (list1.get(i)!=null){
						List<GoodsInfo> goodss = new ArrayList<>();
						goodss=orderDao.selectOrderGoodsInfo(list1.get(i).getOrderId(),list1.get(i).getOrgId());
						for (int j=0;j<goodss.size();j++){
							if (goodss.get(j).getPriceTotal()!=null){
								BigDecimal totalPrice=new BigDecimal("0.00");
								if (list1.get(i).getTotalAmount()!=null){
									totalPrice=list1.get(i).getTotalAmount();
								}
                                totalPrice=totalPrice.add(goodss.get(j).getPriceTotal());
								list1.get(i).setTotalAmount(totalPrice);
							}
						}
						list1.get(i).setGoodss(goodss);
				}
			}
		} catch (Exception e) {
			String log = "查询我的订单或订单审核审批列表失败!";
			throw new ServerException(log,e);
		}
		return list1;
	}

	@Override
	public Integer selectFatherOrdersCount(Long userId, Long orgId, Integer status, String input, Integer month) throws ServerException {
		Integer count=0;
		try {
			count=orderDao.selectFatherOrdersCount(userId,orgId,status,input,month);
		} catch (Exception e) {
			String log = "查询订单审核审批列表数量失败!";
			throw new ServerException(log,e);
		}
		return count;
	}

	@Override
	public Integer selectMyOrdersCount(Long userId, Long orgId, Integer status, String input, Integer month) throws ServerException {
		Integer count=0;
		try {
			count=orderDao.selectEffectiveOrdersCount(userId,orgId,status,input,month);
		} catch (Exception e) {
			String log = "查询我的订单列表数量失败!";
			throw new ServerException(log,e);
		}
		return count;
	}
	@Override
	public List<ReceivingRecords> selectReceivingRecords(Long ogId) throws ServerException {
		List<ReceivingRecords> list = null;
		try {
			list=orderDao.selectReceivingRecords(ogId);
		} catch (Exception e) {
			String log = "查询送货记录失败!";
			throw new ServerException(log,e);
		}
		return list;
	}

	/**
	 * 查询订单审核审批信息
	 * @param orderId
	 * @return
	 */
	@Override
	public ApprovalReview selectApprovalReview(Long orderId) throws ServerException{
		ApprovalReview approvalReview = new ApprovalReview();
		try {
			approvalReview=orderDao.selectApprovalReview(orderId);
		} catch (Exception e) {
			String log = "查询订单审核审批信息失败!";
			throw new ServerException(log,e);
		}
		return approvalReview;
	}

	/**
	 * 审核审批订单
	 * @param approvalReview
	 */
	@Transactional
	@Override
	public void auditOrder(ApprovalReview approvalReview) throws ServerException {
		if (approvalReview==null){
			String log = "订单审核审批参数未空!";
			throw new ServerException(log);
		}
		if (approvalReview.getType()==0){//审核
			approvalReview.setAuditTime(new Date());
		}
		if (approvalReview.getType()==1){//审批
			approvalReview.setApprovaTime(new Date());
		}
		try {
			orderDao.auditOrder(approvalReview);
			orderDao.auditOrderchild(approvalReview);
		} catch (Exception e) {
			String log = "订单审核审批失败!";
			throw new ServerException(log,e);
		}
	}

	/**
	 * 修改订单状态
	 * @param orderId
	 * @param status
	 * @throws ServerException
	 */
	@Transactional
	@Override
	public void updateOrderStatus(Long orderId, Integer status) throws ServerException {
		try {
			orderDao.updateFatherOrderStatus(orderId,status);
			orderDao.updateChildOrderStatus(orderId,status);
		} catch (Exception e) {
			String log = "修改订单状态失败!";
			throw new ServerException(log,e);
		}
	}

    @Override
    public Double selectAdditionalFee(Long orderId) throws ServerException{
	    Double mony=null;
        try {
            mony=orderDao.selectAdditionalFee(orderId);
        } catch (Exception e) {
            String log = "查询附加费用失败!";
            throw new ServerException(log,e);
        }
        return mony;
    }

	/**
	 * 新增加发货单
	 * @param deliveryNote
	 * @throws ServerException
	 */
	@Transactional
	@Override
	public void addInvoice(DeliveryNote deliveryNote) throws ServerException {
		try {
			deliveryNote.setCreateTime(new Date());
			deliveryNote.setStatus(0);
			orderDao.addDeliveryRecord(deliveryNote);
			List<DeliverysDetail> deliverysDetails = deliveryNote.getDeliverysDetails();
			for (int i=0;i<deliverysDetails.size();i++){
				deliverysDetails.get(i).setCreateTime(new Date());
				deliverysDetails.get(i).setDeliveryId(deliveryNote.getId());
				deliverysDetails.get(i).setRecipientId(deliveryNote.getRecipientId());
				deliverysDetails.get(i).setSendId(deliveryNote.getSendId());
				orderDao.updateDeliveryNum(deliverysDetails.get(i).getOgId(),deliverysDetails.get(i).getRecipientId(),deliverysDetails.get(i).getRealNum());
				orderDao.addDeliveryDetail(deliverysDetails.get(i));
				orderDao.updateFatherOrderStatus(deliverysDetails.get(i).getOrderId(),6);
				Long id=orderDao.selectParentOrderId(deliverysDetails.get(i).getOrderId());
				orderDao.updateFatherOrderStatus(id,6);
			}
		} catch (Exception e) {
			String log = "新增发货单失败!";
			throw new ServerException(log,e);
		}
	}
	@Override
	public Map getSupplierByOrgId(Integer orgId) {
		return orderDao.getSupplierByOrgId(orgId);
	}

	@Override
	public List<Map>   getAssetsList(Integer orgId) {

		try {
			List<OrgInfo>orgInfoList=orderDao.getOrgInfoList() ;
			List<OrgInfo>ogrInfoLists=orgtills.getOgrInfoList(orgInfoList,orgId,new ArrayList<OrgInfo>());
			List<Integer>orgIdList=new ArrayList<>();
			for (int i=0;i<ogrInfoLists.size();i++){
				orgIdList.add(ogrInfoLists.get(i).getId());
			}
			return orderDao.getAssetsList(orgIdList);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int addAssets(Integer orderId, Integer orgId, Integer userId) {
		try {
			List<Map>list=orderDao.getOrderDetailListByOrderId(orderId);//商品集合
			List<Map>assetsList=new ArrayList<Map>();//清单集合
			int assetsId=orderDao.getAssetsIdMax();///得到清单最大id
			for (int i=0;i<list.size();i++){
				int count=Integer.parseInt(list.get(i).get("total_num").toString());
				for (int ii=0;ii<count;ii++){
					Map map=new HashMap<String,Object>();//清单对象
					map.put("assets_name",list.get(i).get("goods_name"));
					map.put("assets_category","");
					map.put("assets_model","*");
					map.put("unit","*");
					map.put("amount",1);
					map.put("price",list.get(i).get("price"));
					map.put("sum_price",list.get(i).get("price"));
					map.put("org_id",orgId);
					map.put("t_code",orgtills.getTCode("qd",assetsId));
					map.put("buyer_id",userId);
					assetsId++;
					assetsList.add(map);
				}
			}
			return orderDao.addAssets(assetsList);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
