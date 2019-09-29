/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: fileUploadService
 * Author:   Administrator
 * Date:     2019/1/16 12:04
 * Description: 图片上传service
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.service;

import com.utils.UploadResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈图片上传service〉
 *
 * @author Administrator
 * @create 2019/1/16
 * @since 1.0.0
 */
public interface fileUploadService {
    /**
     * 保存图片及关联
     * @param resultList
     * @param map
     * @return
     */
    int saveMediaAndRef(List<UploadResult> resultList, Map<String,Object> map);

    /**
     * 保存视频及关联
     * @param imgPath
     * @param map
     * @return
     */
    int saveVideoMediaAndRef(List<String> imgPath,Map<String,Object> map);
}
