/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: goodsCategoryVO
 * Author:   Administrator
 * Date:     2018/12/29 17:09
 * Description: 商品分类列表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.vo;

import com.entities.GoodsCategoryDO;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品分类列表〉
 *
 * @author Administrator
 * @create 2018/12/29
 * @since 1.0.0
 */
public class goodsCategoryVO extends GoodsCategoryDO {
    private String pName;

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }
}
