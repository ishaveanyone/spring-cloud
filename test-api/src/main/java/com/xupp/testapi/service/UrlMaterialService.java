/**
 * Date: 2020-06-10 15:34
 * Author: xupp
 */

package com.xupp.testapi.service;

import com.xupp.constant.Constants;

import com.xupp.testapi.domain.IMaterialUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
