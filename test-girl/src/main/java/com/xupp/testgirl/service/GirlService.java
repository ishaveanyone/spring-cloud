/**
 * Date: 2020-06-09 16:07
 * Author: xupp
 */

package com.xupp.testgirl.service;

import com.netflix.discovery.converters.Auto;
import com.xupp.testgirl.domain.GirlDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GirlService {

    @Autowired
    private GirlDomain girlDomain;

    /**
     * 获取列表
     */
    @GetMapping("/list/page/{pageIndex}/{pageSize}")
    public Object listGirls(@PathVariable  Integer pageIndex,@PathVariable Integer pageSize){
        return girlDomain.list(pageIndex,pageSize);
    }

    /**
     * 保存一条记录
     */
    @PostMapping("/girl")
    public String save(
            @RequestParam(name = "name")  String name,
            @RequestParam(name = "type")  String type,
            @RequestParam(name = "sourceUrl")  String sourceUrl,
            @RequestParam(name = "description")  String description

    ){
        return girlDomain.save(name,type,sourceUrl,description).getId();
    }

    /**
     *
     * @param refId
     * @param coverUrl
     * @return
     */
    @GetMapping("/girl/cover")
    public Object setCover(
           @RequestParam(name = "refId") String refId,
           @RequestParam("coverUrl") String  coverUrl

    ){
        return girlDomain.setCover(refId,coverUrl);
    }



}
