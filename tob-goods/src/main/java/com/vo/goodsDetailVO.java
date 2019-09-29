/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: goodsDetailVO
 * Author:   Administrator
 * Date:     2019/1/3 16:12
 * Description: 商品基本信息详情VO
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品信息详情VO〉
 *
 * @author Administrator
 * @create 2019/1/3
 * @since 1.0.0
 */
public class goodsDetailVO {
    private String goodsNum;
    private String goodsName;
    private String brandName;
    //商品产地
    private String origin;
    //包装清单
    private String packingList;
    //商品毛重
    private Double weight;
    private String supName;
    private String cName;

    private List<attrDetailVO> attrList; //商品属性

    private List<Map<String,Object>> skuDetailList;


    private List<Map<String,Object>> goodsPicList;//商品图片

    public List<Map<String, Object>> getGoodsPicList() {
        return goodsPicList;
    }

    public void setGoodsPicList(List<Map<String, Object>> goodsPicList) {
        this.goodsPicList = goodsPicList;
    }

    public List<Map<String, Object>> getSkuDetailList() {
        return skuDetailList;
    }

    public void setSkuDetailList(List<Map<String, Object>> skuDetailList) {
        this.skuDetailList = skuDetailList;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPackingList() {
        return packingList;
    }

    public void setPackingList(String packingList) {
        this.packingList = packingList;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public List<attrDetailVO> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<attrDetailVO> attrList) {
        this.attrList = attrList;
    }
}
