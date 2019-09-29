/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: fileUploadServiceImpl
 * Author:   Administrator
 * Date:     2019/1/16 12:06
 * Description: 图片上传
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.service.impl;

import com.dao.MediaInfoDao;
import com.jayway.jsonpath.Criteria;
import com.pojo.TMediaInfo;
import com.pojo.TMediaRef;
import com.pojo.TMediaRefExample;
import com.service.fileUploadService;
import com.utils.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈图片上传〉
 *
 * @author Administrator
 * @create 2019/1/16
 * @since 1.0.0
 */
@Service
public class fileUploadServiceImpl implements fileUploadService {

    @Autowired
    private MediaInfoDao mediaInfoDao;


    @Transactional
    @Override
    public int saveMediaAndRef(List<UploadResult> list, Map<String,Object> map) {
        //1.已知条件
        Integer id = Integer.parseInt(map.get("id").toString());
        Integer type = Integer.parseInt(map.get("type").toString());
        Integer isVideo = Integer.parseInt(map.get("isVideo").toString());


        List<TMediaInfo> mediaList =new ArrayList<>();
        for (UploadResult result:
                list) {
            TMediaInfo mediaInfo = new TMediaInfo();
            mediaInfo.setUrl(result.getUrl());
            mediaInfo.setThumbnailUrl(result.getThumbnailUrl());
            mediaInfo.setCreateDate(new Date());
            mediaList.add(mediaInfo);
        }
        //插入媒体表
        int rows1 = mediaInfoDao.insertMediaBatch(mediaList);
        int rows2 = 0;
        if(rows1 > 0) {
            //插入媒体关联表
            List<TMediaRef>  refList =new ArrayList<>();
            for (TMediaInfo media :
                    mediaList) {
                TMediaRef ref =new TMediaRef();
                ref.setMediaId(media.getId());
                ref.setRefId(id);
                ref.setCreateDate(new Date());
                ref.setIsVideo(isVideo);
                ref.setType(type);
                ref.setCreateDate(new Date());
                refList.add(ref);
            }
            rows2 = mediaInfoDao.insertMediaRefBatch(refList);
        }
        return rows2;
    }

    /**
     * 保存视频及关联
     *
     * @param videoUrlList
     * @param map
     * @return
     */
    @Override
    public int saveVideoMediaAndRef(List<String> videoUrlList, Map<String, Object> map) {
        //1.已知条件
        Integer id = Integer.parseInt(map.get("id").toString());
        Integer type = Integer.parseInt(map.get("type").toString());
        Integer isVideo = Integer.parseInt(map.get("isVideo").toString());

        List<TMediaInfo> mediaList =new ArrayList<>();
        for (String videoUrl:
                videoUrlList) {
            TMediaInfo mediaInfo = new TMediaInfo();
            mediaInfo.setUrl(videoUrl);
            mediaInfo.setCreateDate(new Date());
            mediaList.add(mediaInfo);
        }
        int row1 = mediaInfoDao.insertMediaBatch(mediaList);
        int row2 = 0;
        if(row1 > 0) {
            List<TMediaRef>  refList =new ArrayList<>();
            for (TMediaInfo mediaInfo: mediaList) {
                TMediaRef mediaRef = new TMediaRef();
                mediaRef.setIsVideo(isVideo);
                mediaRef.setType(type);
                mediaRef.setRefId(id);
                mediaRef.setMediaId(mediaInfo.getId());
                mediaRef.setCreateDate(new Date());
                refList.add(mediaRef);
            }
            row2 = mediaInfoDao.insertMediaRefBatch(refList);
        }
        return row2;
    }
}
