package com.controller;

import com.entities.CardDO;
import com.entities.SupplierDO;
import com.github.pagehelper.PageInfo;
import com.pojo.CardRecordVO;
import com.pojo.CardVO;
import com.pojo.Mycard;
import com.pojo.RecevieInfo;
import com.service.CardService;
import com.utils.BDException;
import com.utils.R;
import com.utils.ReqPage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 购物车
 */


@RestController
@RequestMapping(value = "/biz/card")
public class CardController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CardController.class);

    @Autowired
    private CardService cardService;

    @RequestMapping("/test")
    @ResponseBody
    public R test() {
        return R.ok();
    }

    /**
     * 查列表
     *
     * @param userId  用户id
     * @param reqPage 分页参数
     * @return
     */
    @GetMapping(value = "/{userId}")
    public R list(@PathVariable long userId,
                  @Validated ReqPage reqPage) {
        LOGGER.info("查询购物车列表");
        PageInfo<Mycard> resultPage = null;
        try {
            resultPage = cardService.findListByUserId(userId, reqPage);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            return R.error();
        }

        return R.ok(resultPage);
    }


    /**
     * 查询我的购物车商品数量
     *
     * @param userId
     * @return
     */
    @GetMapping("/account/{userId}")
    public R account(@PathVariable long userId) {
        return R.ok(cardService.account(userId));
    }


    /**
     * 查询购物车列表
     *
     * @param map
     * @return
     */
    @GetMapping("/list")
    public R cardRecordList(@RequestParam Map<String, Object> map) {
        List<CardRecordVO> cardRecordList = cardService.cardRecordList(map);
        return R.ok().put("list", cardRecordList);
    }


    @GetMapping("/checkRecords")
    public R checkRecords(@RequestParam("ids[]") long[] ids) {

        Map<String, Object> map = new HashMap<>();

        map.put("ids", ids);
        List<CardRecordVO> cardRecordList = cardService.cardRecordList(map);
        return R.ok().put("list", cardRecordList);
    }

    /**
     * 添加购物车
     *
     * @param cardDO
     * @return
     */
    @PostMapping
    public R add(@RequestBody CardDO cardDO) {
        if (cardDO != null && cardDO.getGoodsId() != null && cardDO.getPrice() != null
                && cardDO.getSkuId() != null && cardDO.getUserId() != null) {
            LOGGER.info("添加购物车记录！");
            try {
                if (cardService.add(cardDO)) {
                    return R.ok();
                }
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.warn(e.getMessage());
            }
            return R.error();

        }
        return R.error("參數錯誤!");

    }

    /**
     * 购物车批量移除
     *
     * @param cradIds
     * @return
     */
    @RequestMapping("/batch")
    @ResponseBody
    public R batchDel(@RequestParam(value = "cradIds[]") long[] cradIds) {
        try {
            if (cradIds != null && cradIds.length > 0 && cardService.batchDel(cradIds)) {
                return R.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warn(e.getMessage());
        }
        return R.error();
    }

    /**
     * 生成订单
     *
     * @param map
     * @return
     */
    @RequestMapping("/generateOrder")
    @ResponseBody
    public R generateOrder(@RequestBody Map map) {
        //手动封装List<CardRecordVO>
        List<CardRecordVO> recordVOList = new ArrayList<>();
        try {
            JSONArray jsonArray = JSONArray.fromObject(map.get("recordVOList"));
            Iterator iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                CardRecordVO cardRecordVO = new CardRecordVO();
                JSONObject next = (JSONObject) iterator.next();
                JSONObject supplierDOJson = next.getJSONObject("supplierDO");
                SupplierDO supplierDO = (SupplierDO) JSONObject.toBean(supplierDOJson, SupplierDO.class);
                cardRecordVO.setSupplierDO(supplierDO);
                List<CardVO> cardVOList = new ArrayList<>();
                JSONArray cardDOListJsonArray = next.getJSONArray("cardDOList");
                Iterator cdjaInterator = cardDOListJsonArray.iterator();
                while (cdjaInterator.hasNext()) {
                    CardVO cardVO = new CardVO();
                    JSONObject cardRecordJson = (JSONObject) cdjaInterator.next();

                    JSONObject cardDOJson = cardRecordJson.getJSONObject("cardDO");
                    String attrName = (String) cardRecordJson.get("attrName");
                    Integer subId = (Integer) cardRecordJson.get("subId");
                    JSONArray recevieInfosArray = cardRecordJson.getJSONArray("recevieInfos");
                    if (recevieInfosArray.size() == 0) {
                        LOGGER.error("CardController.generateOrder()|未添加收货人！");
                        return R.error("未添加收货人！").put("code", 101);
                    }
                    CardDO cardDO = (CardDO) JSONObject.toBean(cardDOJson, CardDO.class);
//                    List recevieInfos = JSONArray.toList(recevieInfosArray, RecevieInfo.class);
                    List recevieInfos = convertRec(recevieInfosArray);
                    cardVO.setCardDO(cardDO);
                    cardVO.setAttrName(attrName);
                    cardVO.setSubId(subId.longValue());
                    cardVO.setRecevieInfos(recevieInfos);
                    cardVOList.add(cardVO);
                }
                cardRecordVO.setCardDOList(cardVOList);
                recordVOList.add(cardRecordVO);
            }
            boolean res = cardService.generateOrder(recordVOList, map);
            if (res) {
                LOGGER.info("CardController.generateOrder()|生成订单成功！");
                //删除購物車已下單的商品
                boolean b = cardService.deleteCarts(recordVOList);
                if(b) {
                    return R.ok("下單成功！");
                }else {
                    LOGGER.error("刪除購物車已下單的商品失敗！");
                    return R.error("未知的异常，请联系管理员！");
                }
            } else {
                return R.error("下单失败").put("code", 102);
            }
        } catch (BDException e) {
            LOGGER.error(e.getMsg());
            return R.error(e.getMsg()).put("code",e.getCode());
        } catch (ParseException p) {
            return R.error("收货记录日期格式有误！");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return R.error("未知的异常，请联系管理员");
        }
    }

    //将收货人json信息封装到集合
    List<RecevieInfo> convertRec(JSONArray jsonArray) throws ParseException {
        Iterator iterator = jsonArray.iterator();
        List<RecevieInfo> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        while(iterator.hasNext()) {
            RecevieInfo recevieInfo = new RecevieInfo();
            JSONObject recevieInfoJson = (JSONObject)iterator.next();
            Integer receiverId = (Integer) recevieInfoJson.get("receiverId");
            String recNum = (String) recevieInfoJson.get("recNum");
            String expectRecTime = (String) recevieInfoJson.get("expectRecTime");
            String expectInstTime = (String) recevieInfoJson.get("expectInstTime");
            recevieInfo.setExpectInstTime(sdf.parse(expectRecTime));
            recevieInfo.setExpectRecTime(sdf.parse(expectInstTime));
            recevieInfo.setReceiverId(receiverId.longValue());
            recevieInfo.setRecNum(Integer.parseInt(recNum));
            list.add(recevieInfo);
        }
        return list;
    }

//    public static void main(String[] args) throws ParseException {
//        String s = "2018-10-11";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date parse = sdf.parse(s);
//        System.out.println(parse);
//
//    }

    //修改购物车商品数量
    @RequestMapping("/updOrderCount")
    @ResponseBody
    public R updOrderCount(@RequestParam(value = "id") Integer id
            , @RequestParam(value = "count") Integer count) {
        try {
            if (cardService.updOrderCount(id, count) > 0) {
                return R.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.error();
    }


    //直接修改商品数量
    @RequestMapping("/updateCount")
    @ResponseBody
    public R updateCount(@RequestParam(value = "id") Integer id
            , @RequestParam(value = "count") Integer count) {
        try {
            if (cardService.updateCount(id, count) > 0) {
                return R.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.error();
    }

    //根据用户id得到地址集合
    @RequestMapping("/getRecipient")
    @ResponseBody
    public R getRecipient(Integer create_by) {
        try {
            return R.ok().put("list", cardService.getRecipient(create_by));
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    //根据用户id得到地址对象
    @RequestMapping("/getRecipientById")
    @ResponseBody
    public R getRecipientById(Integer id) {
        try {
            return R.ok().put("map", cardService.getRecipientById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }


}
