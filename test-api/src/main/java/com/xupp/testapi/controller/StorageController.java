/**
 * Date: 2020-06-10 14:39
 * Author: xupp
 */

package com.xupp.testapi.controller;

import com.xupp.testapi.service.StorageService;
import com.xupp.util.ZipUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Value("${web.static.url}")
    private String staticUrl;
    @Value("${web.static.imgdir}")
    private String imgdir;

    @Autowired
    private StorageService storageService;

    /**
     * 图片下载
     * 预览zip包中的所有的文件
     */
    @GetMapping("/zip/view")
    public List<String> preview(
            @RequestParam  String refId,
            @RequestParam String type) throws IOException {
        String httpPath =staticUrl+"/"+refId+"/content";
        //下载的文件夹
        String downPath=imgdir+"/"+refId+"/content";
        if(!new File(downPath).exists()){
            new File(downPath).mkdirs();
        }else{
            new File(downPath).delete();
        }
        ResponseEntity<byte[]> responseEntity=
                storageService.download(refId,type);
        byte[] bytes= responseEntity.getBody();
        HttpHeaders httpHeaders= responseEntity.getHeaders();
        String fileName=httpHeaders.getContentDisposition().getFilename();
        File file= new File(downPath+"/"+fileName);
        FileUtils.writeByteArrayToFile(file, bytes);
        ZipUtils.unzip(file, new File(downPath));
        //删除文件
        file.delete();
        //从根目录上获取下级所有哦的文件 并且存放到列表中返回
        File[] files= new File(downPath).listFiles();
        List<String> urlList=new ArrayList();
        for (File file1 : files) {
            urlList.add(httpPath+"/"+file1.getName());
        }
        urlList=urlList.stream().sorted((o1,o2)->{
            String name1=o1.substring(o1.lastIndexOf('/')+1);
            String name2=o2.substring(o2.lastIndexOf('/')+1);
            String index1=name1.substring(0,name1.indexOf('_'));
            String index2=name2.substring(0,name2.indexOf('_'));
            return Integer.valueOf(index1).compareTo(Integer.valueOf(index2));
        }).collect(Collectors.toList());
        return urlList;
    }



}
