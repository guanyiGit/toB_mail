package com.service.impl;

import com.dao.CardDao;
import com.entities.*;
import com.github.pagehelper.PageInfo;
import com.pojo.CardRecordVO;
import com.pojo.CardVO;
import com.pojo.Mycard;
import com.pojo.RecevieInfo;
import com.service.CardService;
import com.utils.BDException;
import com.utils.ReqPage;
import com.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CardServiceImpl implements CardService {
    private static Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);
    @Autowired
    private CardDao cardDao;


    @Override
    public PageInfo<Mycard> findListByUserId(long userId, ReqPage reqPage) {
        int totalCount = cardDao.findListByUserIdCount(userId);
        List<Mycard> list = null;
        if (totalCount > 0) {
            list = cardDao.findListByUserId(userId, reqPage);
        }
        PageInfo<Mycard> page = new PageInfo<>(list);
        page.setTotal(totalCount);
        return page;
    }

    @Override
    public boolean add(CardDO cardDO) {
        Map<String, Object> map = new HashMap<>();
        map.put("skuId", cardDO.getSkuId());
        map.put("userId", cardDO.getUserId());
        map.put("remark", "0");

        List<CardDO> list = cardDao.list(map);

        Integer count ;

        //已存在商品添加
        if (list != null && list.size() > 0) {
            CardDO oldCard = list.get(0);
            CardDO bean = new CardDO();
            count = oldCard.getCount();
            if(count == null ){//原数据数量为null
                count = cardDO.getCount() == null ? 0 : cardDO.getCount();
            }else{
                count = cardDO.getCount() == null ? count + 1 : count + cardDO.getCount();
            }
            bean.setCount(count);
            bean.setCradId(oldCard.getCradId());
            count = cardDao.update(bean);
        } else {//新商品直接新增
            cardDO.setAddTime(Calendar.getInstance().getTime());
            cardDO.setCradId(null);
            cardDO.setRemark(0);
            count = cardDO.getCount() == null ? 0 : cardDO.getCount();
            cardDO.setCount(count);
            count = cardDao.save(cardDO);
        }

        if (count == 1) {
            return true;
        }

        return false;
    }

    @Override
    public boolean batchDel(long[] cradIds) {
        if (cardDao.batchRemove(cradIds) == cradIds.length) {
            return true;
        }
        return false;
    }

    @Override
    public List<CardRecordVO> cardRecordList(Map<String, Object> map) {
        List<CardRecordVO> cardRecordList = cardDao.findCardRecordList(map);
        List<CardRecordVO> res = convertList(cardRecordList);
        Map<String, Object> query = new HashMap<>();
        for (CardRecordVO vo : res) {
            List<CardVO> cardDOList = vo.getCardDOList();
            for (CardVO cardVO :
                    cardDOList) {
                Long skuId = cardVO.getCardDO().getSkuId();
                Long goodsId = cardVO.getCardDO().getGoodsId();
                query.put("skuId", skuId);
                query.put("goodsId", goodsId);
                List<String> attrValue = cardDao.getAttrValue(query);
                Integer subIdByGoodsId = cardDao.getSubIdByGoodsId(query);
                String attrStr = StringUtils.convertAttrValue(attrValue);
                cardVO.setAttrName(attrStr);
                cardVO.setSubId(subIdByGoodsId.longValue());
                query.clear();
            }
        }
        return res;
    }

    @Override
    public boolean generateOrder(List<CardRecordVO> recordVOList, Map<String, Object> map) {
        if (recordVOList == null || recordVOList.isEmpty()) {
            logger.error("CardServiceImpl.generateOrder()|recordVOList = {}", recordVOList);
            throw new BDException("未知异常，请联系管理员");
        }
        String userId = map.get("userId").toString();
        String orgId = map.get("orgId").toString();
        Long subId = Long.parseLong(map.get("subId").toString());

        if (recordVOList.size() == 1) {
            //只有一个供应商，不用拆单
            CardRecordVO cardRecordVO = recordVOList.get(0);
            SupplierDO supplierDO = cardRecordVO.getSupplierDO();
            List<CardVO> cardDOList = cardRecordVO.getCardDOList();
            //1.生成订单
            OrderDO orderDO = new OrderDO();
            orderDO.setOrderNo(generateOrderNo());
            orderDO.setOrgId(Long.parseLong(orgId));
            orderDO.setStatus(0);
            orderDO.setMoney(computeOrderMoney(cardRecordVO.getCardDOList()));
            orderDO.setIsuseful(1);     //1 代表有效订单
            orderDO.setParentId(0L);    //没有父订单
            orderDO.setCreateBy(Long.parseLong(userId));
            orderDO.setSupplierId(supplierDO.getSupId());
            orderDO.setCreateTime(new Date());
            orderDO.setSubId(subId);
            cardDao.saveOrder(orderDO);

            for (CardVO c :
                    cardDOList) {
                {
                    //2 生成订单详情
                    OrderDetailDO detailDO = new OrderDetailDO();
                    detailDO.setOrderId(orderDO.getId());
                    detailDO.setOrderNo(orderDO.getOrderNo());
                    detailDO.setGoodsId(c.getCardDO().getGoodsId());
                    detailDO.setPrice(c.getCardDO().getPrice());
                    detailDO.setOrgId(Long.parseLong(orgId));
                    detailDO.setSkuId(c.getCardDO().getSkuId());
                    detailDO.setTotalNum(c.getCardDO().getCount().longValue());
                    detailDO.setReceiveNum(0);
                    detailDO.setStatus(0);
                    detailDO.setCreateTime(new Date());
                    detailDO.setSubjects(subId);
                    cardDao.insertOrderDetail(detailDO);
                    //3 添加商品的收货记录
                    List<RecevieInfo> recevieInfos = c.getRecevieInfos();
                    if (recevieInfos != null || !recevieInfos.isEmpty()) {
                        //添加了收货人
                        List<ReceiptRecordDO> recordDOList = new ArrayList<>();
                        for (RecevieInfo rev : recevieInfos) {
                            ReceiptRecordDO rrd = new ReceiptRecordDO();
                            rrd.setOgId(detailDO.getId());
                            rrd.setCreateTime(new Date());
                            rrd.setNum(rev.getRecNum());
                            rrd.setRecipientId(rev.getReceiverId());
                            rrd.setReceiveNum(0);
                            rrd.setDeliveryTime(rev.getExpectRecTime());
                            rrd.setInstallTime(rev.getExpectInstTime());
                            rrd.setStatus(0);
                            recordDOList.add(rrd);
                        }
                        cardDao.    batchInsertReceiptRecord(recordDOList);
                    } else {
                        logger.error("CardServiceImpl.generateOrder()|未添加收货人！");
                        throw new BDException("未添加收货人!", 101);
                    }
                }
            }

        } else {
            //1、生成父订单
            OrderDO orderDO = new OrderDO();
            orderDO.setOrderNo(generateOrderNo());
            orderDO.setStatus(0);
            orderDO.setOrgId(Long.parseLong(orgId));
            orderDO.setCreateBy(Long.parseLong(userId));
            orderDO.setMoney(computeAllMoney(recordVOList));
            orderDO.setCreateTime(new Date());
            orderDO.setParentId(0L);
            orderDO.setIsuseful(0); //0 表示是父订单
            orderDO.setSubId(subId);
            cardDao.saveOrder(orderDO);
            //2、生成子订单
            //多个供应商，需要拆单
            for (CardRecordVO rv : recordVOList) {
                //2.1 先生成子订单
                OrderDO childOrder = new OrderDO();
                Integer supId = rv.getSupplierDO().getSupId();
                childOrder.setParentId(orderDO.getId());
                childOrder.setSupplierId(supId);
                List<CardVO> cardDOList = rv.getCardDOList();
                //计算该子订单的订单金额
                BigDecimal childOrderMoney = computeOrderMoney(cardDOList);
                childOrder.setMoney(childOrderMoney);
                childOrder.setCreateTime(new Date());
                childOrder.setStatus(0);
                childOrder.setCreateBy(Long.parseLong(userId));
                childOrder.setOrgId(Long.parseLong(orgId));
                childOrder.setOrderNo(generateOrderNo());
                childOrder.setIsuseful(1);      //1 表示是有效订单
                childOrder.setSubId(subId);
                cardDao.saveOrder(childOrder);

                for (CardVO c : cardDOList) {
                    //2.2 再生成订单详情
                    OrderDetailDO detailDO = new OrderDetailDO();
                    detailDO.setOrderId(childOrder.getId());
                    detailDO.setOrderNo(childOrder.getOrderNo());
                    detailDO.setGoodsId(c.getCardDO().getGoodsId());
                    detailDO.setPrice(c.getCardDO().getPrice());
                    detailDO.setOrgId(Long.parseLong(orgId));
                    detailDO.setSkuId(c.getCardDO().getSkuId());
                    detailDO.setTotalNum(c.getCardDO().getCount().longValue());
                    detailDO.setReceiveNum(0);
                    detailDO.setStatus(0);
                    detailDO.setCreateTime(new Date());
                    detailDO.setSubjects(subId);
                    //添加订单详情
                    cardDao.insertOrderDetail(detailDO);
                    //添加商品的收货记录
                    List<RecevieInfo> recevieInfos = c.getRecevieInfos();
                    if (recevieInfos != null && !recevieInfos.isEmpty()) {
                        //添加了收货人
                        List<ReceiptRecordDO> recordDOList = new ArrayList<>();
                        for (RecevieInfo rev :
                                recevieInfos) {
                            ReceiptRecordDO rrd = new ReceiptRecordDO();
                            rrd.setOgId(detailDO.getId());
                            rrd.setCreateTime(new Date());
                            rrd.setNum(rev.getRecNum());
                            rrd.setRecipientId(rev.getReceiverId());
                            rrd.setReceiveNum(0);
                            rrd.setDeliveryTime(rev.getExpectRecTime());
                            rrd.setInstallTime(rev.getExpectInstTime());
                            rrd.setStatus(0);
                            recordDOList.add(rrd);
                        }
                        cardDao.batchInsertReceiptRecord(recordDOList);
                    } else {
                        logger.error("CardServiceImpl.generateOrder()|未添加收货人！");
                        throw new BDException("未添加收货人!", 101);
                    }
                }
            }
        }
        return true;
    }

    /**
     * 刪除購物車已下單的商品
     *
     * @param recordVOList
     * @return
     */
    @Override
    public boolean deleteCarts(List<CardRecordVO> recordVOList) {
        List<Long> idList = new ArrayList<>();
        for (CardRecordVO crv : recordVOList) {
            for (CardVO cv : crv.getCardDOList()) {
                idList.add(cv.getCardDO().getCradId());
            }
        }
        long[] ids = new long[idList.size()];
        for (int j = 0; j < idList.size(); j++) {
            ids[j] = idList.get(j);
        }
        //逻辑删除，更改remark状态为1（已购买）
        Integer rows = cardDao.updateRemarks(ids);
        return rows == ids.length ? true : false;
    }

    @Override
    public int updOrderCount(Integer id, Integer count) {
        return cardDao.updOrderCount(id, count);
    }

    @Override
    public List<Map> getRecipient(Integer create_by) {
        return cardDao.getRecipient(create_by);
    }

    @Override
    public Map getRecipientById(Integer id) {
        return cardDao.getRecipientById(id);
    }

    //计算父订单的订单金额数
    public BigDecimal computeAllMoney(List<CardRecordVO> recordVOList) {
        if (recordVOList == null || recordVOList.isEmpty()) {
            return new BigDecimal(0);
        }
        BigDecimal allMoney = new BigDecimal(0);
        for (CardRecordVO crv : recordVOList) {
            System.out.println(crv);
            List<CardVO> cardDOList = crv.getCardDOList();
            BigDecimal childOrderMoney = computeOrderMoney(cardDOList);
            allMoney = allMoney.add(childOrderMoney);
        }
        return allMoney;
    }

    //计算子订单的订单金额数
    public BigDecimal computeOrderMoney(List<CardVO> cardDOList) {
        BigDecimal totalMoney = new BigDecimal(0);
        for (CardVO cv :
                cardDOList) {
            CardDO cardDO = cv.getCardDO();
            BigDecimal count = new BigDecimal(cardDO.getCount());
            BigDecimal price = cardDO.getPrice();
            BigDecimal multiply = count.multiply(price);
            totalMoney = totalMoney.add(multiply);
        }
        return totalMoney;
    }

    /**
     * 生成订单号规则
     *
     * @return
     */
    public String generateOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String randomOrderNo = sdf.format(new Date());
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            randomOrderNo += random.nextInt(10);
        }
        return randomOrderNo;
    }

    //将商品根据供应商进行归类
    public List<CardRecordVO> convertList(List<CardRecordVO> cardRecordList) {
        List<CardRecordVO> resList = new ArrayList<>();
        Map<Integer, CardRecordVO> resMap = new HashMap<>();
        for (CardRecordVO vo :
                cardRecordList) {
            Integer supId = vo.getSupplierDO().getSupId();
            if (resMap.containsKey(supId)) {
                resMap.get(supId).getCardDOList().addAll(vo.getCardDOList());
            } else {
                resMap.put(supId, vo);
            }
        }
        for (Integer key :
                resMap.keySet()) {
            resList.add(resMap.get(key));
        }
        return resList;
    }

    @Override
    public int account(long userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        Integer count = cardDao.account(map);
        return count == null ? 0 : count;
    }

    @Override
    public Integer updateCount(Integer id, Integer count) {
        return cardDao.updateCount(id,count);
    }
}
