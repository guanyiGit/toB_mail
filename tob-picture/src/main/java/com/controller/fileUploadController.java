/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: fileUploadController
 * Author:   Administrator
 * Date:     2019/1/16 12:02
 * Description: 图片上传controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.controller;

import com.service.fileUploadService;
import com.utils.FastDfsUtils;
import com.utils.R;
import com.utils.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈图片上传controller〉
 *
 * @author Administrator
 * @create 2019/1/16
 * @since 1.0.0
 */
@Controller
@RequestMapping("/biz/fileManager")
public class fileUploadController {


    @Autowired
    private fileUploadService fileUploadService;
    //    图片上传
    @ResponseBody
    //@RequiresPermissions("biz:manureDisposal:uploadPic")
    @PostMapping("/uploadFileBatch")
    public R uploadPic(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> MultipartFileMap = Murequest.getFileMap();//得到文件map对象
        List<MultipartFile> files = new ArrayList<>();
        for (String key : MultipartFileMap.keySet()) {
            MultipartFile attach = MultipartFileMap.get(key);
            files.add(attach);
        }
        String id = request.getParameter("id");
        if(id == null) {
            return R.error("id不能为空");
        }
        String type = request.getParameter("type");
        if(type == null) {
            return R.error("type不能为空");
        }
        map.put("id",id);
        map.put("type",type);
        map.put("isVideo",0);

        List<UploadResult> uploadResults = FastDfsUtils.uploadFiles(files);

        int result = fileUploadService.saveMediaAndRef(uploadResults,map);
        if(result > 0) {
            return R.ok();
        }
        return R.error("上传图片失败");
    }

    //视频上传
    @ResponseBody
    //@RequiresPermissions("biz:manureDisposal:uploadVideo")
    @PostMapping("/uploadVideo")
    public R uploadVideo(HttpServletRequest request,HttpServletResponse response
            ,@RequestParam("videos") MultipartFile[] videos){
        Map<String,Object> map = new HashMap<>();

        String id = request.getParameter("id");
        if(id == null) {
            return R.error("id不能为空");
        }
        String type = request.getParameter("type");
        if(type == null) {
            return R.error("type不能为空");
        }
        map.put("id",id);
        map.put("type",type);
        map.put("isVideo",1);   //1代表视频

        List<String> videoUrlList = FastDfsUtils.uploadVideos(videos);
        int result = fileUploadService.saveVideoMediaAndRef(videoUrlList,map);
        if(result > 0) {
            return R.ok();
        }
        return R.error("上传视频失败");
    }
}
