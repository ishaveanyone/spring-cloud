/**
 * Date: 2020-06-09 16:07
 * Author: xupp
 */

package com.xupp.testanimal.service;

import com.xupp.testanimal.domain.AnimalDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnimalService {

    @Autowired
    private AnimalDomain animalDomain;

    /**
     * 获取列表
     */
    @GetMapping("/list/page/{pageIndex}/{pageSize}")
    public Object listAnimals(@PathVariable  Integer pageIndex,@PathVariable Integer pageSize){
        return animalDomain.list(pageIndex,pageSize);
    }

    /**
     * 保存一条记录
     */
    @PostMapping("/animal")
    public String save(
            @RequestParam(name = "name")  String name,
            @RequestParam(name = "type")  String type,
            @RequestParam(name = "sourceUrl")  String sourceUrl,
            @RequestParam(name = "description")  String description

    ){
        return animalDomain.save(name,type,sourceUrl,description).getId();
    }

    /**
     *
     * @param refId
     * @param coverUrl
     * @return
     */
    @GetMapping("/animal/cover")
    public Object setCover(
           @RequestParam(name = "refId") String refId,
           @RequestParam("coverUrl") String  coverUrl

    ){
        return animalDomain.setCover(refId,coverUrl);
    }



}
