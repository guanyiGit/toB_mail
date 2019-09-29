/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: attrGroupVO
 * Author:   Administrator
 * Date:     2018/12/29 17:45
 * Description: 属性分组列表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.vo;

import com.entities.AttrGroupDO;

/**
 * 〈一句话功能简述〉<br> 
 * 〈属性分组列表〉
 *
 * @author Administrator
 * @create 2018/12/29
 * @since 1.0.0
 */
public class attrGroupVO extends AttrGroupDO {
    private String cName;

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
}
