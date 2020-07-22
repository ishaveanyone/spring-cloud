/**
 * Date: 2020-06-10 15:34
 * Author: xupp
 */

package com.xupp.teststorage.service;

import com.xupp.constant.Constants;
import com.xupp.teststorage.domain.IMaterialUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/"+ Constants.SOURCEFROM.ZIP)
public class ZipMaterialService {
    @Autowired
    @Qualifier(Constants.SOURCEFROM.ZIP)
    IMaterialUD iMaterialUD;

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
