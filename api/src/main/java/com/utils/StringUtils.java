package com.utils;

import java.util.List;

/**
 * @Description
 * @Author: linchong
 * @Date: 2019/1/3 15:22
 * @Version 1.0
 */
public class StringUtils {

    public static String convertAttrValue(List<String> list) {
        if(list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i= 0 ;i < list.size();i++) {
            if(i == list.size()-1) {
                sb.append(list.get(i));
            }else {
                sb.append(list.get(i)+" ");
            }
        }
        return sb.toString();
    }
}
