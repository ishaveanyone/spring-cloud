/**
 * Date: 2020-06-10 14:35
 * Author: xupp
 */

package com.xupp.testapi.service;

import com.xupp.constant.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@FeignClient(name = "storage")
public interface StorageService {
    //url 方式
    @PostMapping(value = "/"+ Constants.SOURCEFROM.URL +"/upload/file")
    String uploadUrl(@RequestParam("refId") String refId,@RequestParam("url") String url);

    //zip方式（默认里面都是可直接预览的图片）
    @PostMapping(
            value = "/"+ Constants.SOURCEFROM.ZIP+"/upload/file",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadZip( @RequestParam(value = "refId") String refId,@RequestPart(value = "file") MultipartFile file);



    //统一处理下载zip
    @GetMapping(value = "/download/file")
    ResponseEntity<byte[]> download(@RequestParam("refId") String refId, @RequestParam String type);

}
