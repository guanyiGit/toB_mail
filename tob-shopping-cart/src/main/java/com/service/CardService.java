package com.service;

import com.entities.CardDO;
import com.github.pagehelper.PageInfo;
import com.pojo.CardRecordVO;
import com.pojo.Mycard;
import com.utils.ReqPage;

import java.util.List;
import java.util.Map;

public interface CardService {

    /**
     * 查列表
     *
     * @param userId  用户id
     * @param reqPage 分页参数
     * @return
     */
    PageInfo<Mycard> findListByUserId(long userId, ReqPage reqPage);

    List<CardRecordVO> cardRecordList(Map<String,Object> map);

    /**
     * 添加购物车
     *
     * @param cardDO
     * @return
     */
    boolean add(CardDO cardDO);

    /**
     * 购物车批量移除
     *
     * @param cradIds
     * @return
     */
    boolean batchDel(long[] cradIds);

    /**
     * 生成订单
     * @param recordVOList
     * @return
     */
    boolean generateOrder(List<CardRecordVO> recordVOList,Map<String,Object> map);


    /**
     * 刪除購物車已下單的商品
     * @param recordVOList
     * @return
     */
    boolean deleteCarts(List<CardRecordVO> recordVOList);


    /**
     * 查询我的购物车数量
     * @param userId
     * @return
     */
    int account(long userId);

    int updOrderCount(Integer id, Integer count);

    List<Map> getRecipient(Integer create_by);

    Map getRecipientById(Integer id);

    //更新购物车数量
    Integer updateCount(Integer id,Integer count);
}
