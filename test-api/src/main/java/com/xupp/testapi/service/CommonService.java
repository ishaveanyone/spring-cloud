/**
 * Date: 2020-06-15 11:47
 * Author: xupp
 */

package com.xupp.testapi.service;

import com.xupp.testapi.SorageApplicationContextProvider;
import com.xupp.testapi.domain.IMaterialUD;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@Service
public class CommonService {


    /**
     * 注意下载的文件都是zip 打包的文件
     * @param refId
     * @param type
     * @param request
     * @return
     */

    public ResponseEntity<byte[]> download(
            String refId,
            String type,// 资源来源的方式
            HttpServletRequest request
    ){
        IMaterialUD iMaterialUD=(IMaterialUD)
                SorageApplicationContextProvider.
                        getBean(type);
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity entity = null;
        InputStream in=null;
        try {
            File zipFile= iMaterialUD.download(refId);
            in=new FileInputStream(zipFile);
            byte[] bytes = new byte[in.available()];
            String imageName=zipFile.getName();
            //处理IE下载文件的中文名称乱码的问题
            String header = request.getHeader("User-Agent").toUpperCase();
            if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
                imageName = URLEncoder.encode(imageName, "utf-8");
                imageName = imageName.replace("+", "%20");    //IE下载文件名空格变+号问题
            } else {
                imageName = new String(imageName.getBytes(), "iso-8859-1");
            }
            in.read(bytes);
            headers.add("Content-Disposition", "attachment;filename="+imageName);
            entity = new ResponseEntity(bytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return entity;

    }
}
