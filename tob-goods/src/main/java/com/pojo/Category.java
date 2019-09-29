/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Category
 * Author:   Administrator
 * Date:     2019/1/3 11:17
 * Description: 商品分类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品分类〉
 *
 * @author Administrator
 * @create 2019/1/3
 * @since 1.0.0
 */
public class Category {

    private Integer value;
    private String label;
    private List<?> children;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }
}
