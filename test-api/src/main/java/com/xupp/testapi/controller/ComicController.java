/**
 * Date: 2020-06-10 15:18
 * Author: xupp
 */

package com.xupp.testapi.controller;

import com.xupp.constant.Constants;
import com.xupp.testapi.domain.ComicDomain;
import com.xupp.testapi.domain.IMaterialUD;
import com.xupp.testapi.service.ComicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/comic")
public class ComicController {

    @Autowired
    private ComicDomain comicDomain;
    @Value("${web.static.url}")
    private String staticUrl;
    @Value("${web.static.imgdir}")
    private String imgdir;

    @Autowired
    @Qualifier(Constants.SOURCEFROM.ZIP)
    IMaterialUD zipMaterialUD;

    @Autowired
    @Qualifier(Constants.SOURCEFROM.URL)
    IMaterialUD urlMaterialUD;


    @PostMapping("/comic")
    public Object save(
            @RequestParam String name,
            @RequestParam String type,
            @RequestParam String sourceUrl,
            @RequestParam String description
            ){
      return comicDomain.save(name,type,sourceUrl,description);
    }

    /**
     * 获取漫画的列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/comics/{pageIndex}/{pageSize}")
    public Object list(@PathVariable Integer pageIndex,@PathVariable Integer pageSize){
        return comicDomain.list(pageIndex,pageSize);
    }

    /**
     * 设置漫画的封面
     * @param refId
     * @param
     * @return
     */
    @PostMapping("/comic/cover/{refId}")
    public Object setCover(
            @PathVariable String refId,
            @RequestParam("file") MultipartFile file
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
        return comicDomain.setCover(refId,httpPath+"/"+newName);
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
        String refId=comicDomain.save(
                title,
                Constants.SOURCEFROM.URL,
                url,
                description
                ).getId();
        Map<String,Object> maps=new HashMap(){{
            put("url",url);
            put("refId",refId);
        }};
        urlMaterialUD.upload(maps);
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
        String refId=comicDomain.save(
                title,
                Constants.SOURCEFROM.ZIP,
                "",
                description
        ).getId();
        Map<String,Object> maps=new HashMap(){{
            put("refId",refId);
            put("file",file);
        }};
        zipMaterialUD.upload(maps);
        return refId;
    }




}
