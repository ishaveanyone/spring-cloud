/**
 * Date: 2020-06-15 16:18
 * Author: xupp
 */

package com.xupp.testapi.service;

import com.xupp.constant.Constants;
import com.xupp.testapi.domain.IMaterialUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/"+ Constants.SOURCEFROM.FILE)
public class FileMaterialService {

    @Autowired
    @Qualifier("file")
    private IMaterialUD iMaterialUD;

    @RequestMapping(method = RequestMethod.POST, value = "/upload/file",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object upload(
            @RequestParam String refId,
            @RequestPart(value = "file") MultipartFile file
    ){
        Map<String,Object> maps=new HashMap(){{
            put("refId",refId);
            put("file",file);
        }};
        return  iMaterialUD.upload(maps);
    }

}
