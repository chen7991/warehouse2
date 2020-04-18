package com.casic.warehouse2.controller;

import cn.hutool.core.date.DateUtil;
import com.casic.warehouse2.common.AppFileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("file")
public class FileController {

    /**图片上传
     * _picture临时文件后缀
     * @param photofile
     * @return
     */
    @RequestMapping("upFile")
    public Map<String,Object> uploadFile(MultipartFile photofile) {
        //1得到文件名
        String oldName = photofile.getOriginalFilename();
        //2根据文件名生成新的文件名
        String newName= AppFileUtils.createNewFileName(oldName);
        //3得到当前日期的字符串
        String dirName= DateUtil.format(new Date(), "yyyy-MM-dd");
        //4构造文件夹
        File dirFile=new File(AppFileUtils.UPLOADPATH,dirName);
        //5判断当前文件夹是否存在
        if(!dirFile.exists()) {
            dirFile.mkdirs();//创建文件夹
        }
        //6构造文件对象
        File file=new File(dirFile, newName+"_picture");
        //7把photofile里面的图片信息写入file
        try {
            photofile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("path", dirName+"/"+newName+"_picture");
        return map;
    }

    /**
     * 图片下载
     */
    @RequestMapping("PhotoByPath")
    public ResponseEntity<Object> showImageByPath(String path){
        return AppFileUtils.createResponseEntity(path);
    }

}
