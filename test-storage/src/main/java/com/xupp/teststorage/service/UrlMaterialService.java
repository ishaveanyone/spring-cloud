/**
 * Date: 2020-06-10 15:34
 * Author: xupp
 */

package com.xupp.teststorage.service;

import com.netflix.discovery.converters.Auto;
import com.xupp.constant.Constants;
import com.xupp.teststorage.SorageApplicationContextProvider;
import com.xupp.teststorage.domain.IMaterialUD;
import com.xupp.teststorage.domain.NetURLMaterialDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/"+Constants.SOURCEFROM.URL)
public class UrlMaterialService {
    @Autowired
    @Qualifier(Constants.SOURCEFROM.URL)
    IMaterialUD iMaterialUD;

    @PostMapping("/upload/file")
    public Object upload(
            @RequestParam String refId,
            @RequestParam String url
    ){
       Map<String,Object> maps=new HashMap(){{
            put("url",url);
            put("refId",refId);
       }};
       return  iMaterialUD.upload(maps);
    }
}
