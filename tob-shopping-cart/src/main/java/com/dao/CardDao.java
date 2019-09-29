package com.dao;

import com.entities.OrderDO;
import com.entities.OrderDetailDO;
import com.entities.ReceiptRecordDO;
import com.pojo.CardRecordVO;
import com.pojo.Mycard;
import com.entities.CardDO;
import com.utils.ReqPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-27 16:13:41
 */
@Mapper
public interface CardDao {

    List<String> getAttrValue(Map<String,Object> map);

    CardDO get(Long cradId);

    List<CardDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(CardDO card);

    int update(CardDO card);

    int remove(Long crad_id);

    int batchRemove(long[] cradIds);

    Integer getSubIdByGoodsId(Map<String,Object> map);

    Integer batchInsertOrderDetail(List<OrderDetailDO> detailDOList);

    Integer batchInsertReceiptRecord(List<ReceiptRecordDO> recordDOList);

    Integer insertOrderDetail(OrderDetailDO orderDetailDO);

    /**
     * 查列表
     *
     * @param userId  用户id
     * @param reqPage 分页参数
     * @return
     */
    List<Mycard> findListByUserId(@Param("userId") long userId, @Param("reqPage") ReqPage reqPage);

    /**
     * 查列表 条数
     *
     * @param userId  用户id
     * @return
     */
    int findListByUserIdCount(@Param("userId") long userId);

    /**
     * 查询购物车的商品列表
     * by: linchong
     * @param map
     * @return
     */
    List<CardRecordVO> findCardRecordList(Map<String,Object> map);
    

    boolean saveOrder(OrderDO orderDO);
    //修改购物车商品数量
    int updOrderCount(@Param("id")Integer id,@Param("count")Integer count);
    //根据用户id得到地址集合
    List<Map> getRecipient(@Param("create_by")Integer create_by);
    //根据地址id得到地址对象
    Map getRecipientById(@Param("id")Integer id);
    /**
     * 计算我的购物车数量
     * @param map
     * @return
     */
    Integer account(Map<String, Object> map);

    Integer updateRemarks(long[] ids);

    Integer updateCount(@Param("id") Integer id,@Param("count") Integer count);
}
