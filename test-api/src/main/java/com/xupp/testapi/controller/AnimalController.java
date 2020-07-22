/**
 * Date: 2020-06-10 15:18
 * Author: xupp
 */

package com.xupp.testapi.controller;

import com.xupp.constant.Constants;
import com.xupp.testapi.service.AnimalService;
import com.xupp.testapi.service.ComicService;
import com.xupp.testapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    @Value("${web.static.url}")
    private String staticUrl;
    @Value("${web.static.imgdir}")
    private String imgdir;


    @Autowired
    private AnimalService animalService;

    @Autowired
    private StorageService storageService;
    @PostMapping("/animal")
    public Object save(
            @RequestParam String name,
            @RequestParam String type,
            @RequestParam String sourceUrl,
            @RequestParam String description
            ){
      return animalService.save(name,type,sourceUrl,description);
    }

    /**
     * 获取漫画的列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/animals/{pageIndex}/{pageSize}")
    public Object list(@PathVariable Integer pageIndex,@PathVariable Integer pageSize){
        return animalService.list(pageIndex,pageSize);
    }

    /**
     * 设置漫画的封面
     * @param refId
     * @param
     * @return
     */
    @PostMapping("/animal/cover/{refId}")
    public Object setCover(
            @PathVariable String refId,
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request
    ){
        //将文件拷贝到对应的服务位置然后设置 地址
        String httpPath =staticUrl+"/"+refId+"/cover";
        //下载的文件夹
        String downPath=imgdir+"/"+refId+"/cover";
        if(!new File(downPath).exists()){
            new File(downPath).mkdirs();
        }
        String oriFileName=file.getOriginalFilename();
        String newName=String.valueOf(System.currentTimeMillis())+"."+oriFileName.substring(oriFileName.lastIndexOf('.')+1);
        File destFile=new File(downPath+"/"+newName);
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return animalService.setCover(refId,httpPath+"/"+newName);
    }

    /**
     * 通过互联网的方式进行发布
     * @param url
     * @param title
     * @return
     */
    @PostMapping("/deploy/url")
    public Object deployByUrl(
            @RequestParam String url,
            @RequestParam String description,
            @RequestParam String title
    ){
        String refId=animalService.save(
                title,
                Constants.SOURCEFROM.URL,
                url,
                description
                );
        storageService.uploadUrl(refId,url);
        return refId;
    }

    /**
     * 通过打包文件进行发布
     */
    @PostMapping("/deploy/zip")
    public Object deployByZip(
            @RequestPart(value = "file") MultipartFile file,
            @RequestParam String description,
            @RequestParam String title
    ){
        String refId=animalService.save(
                title,
                Constants.SOURCEFROM.ZIP,
                "",
                description
        );
        storageService.uploadZip(refId,file);
        return refId;
    }

    /**
     * 通过上传文件进行发布
     */


    /**
     * 通过doc ppt 等方式进行发布
     */



}
