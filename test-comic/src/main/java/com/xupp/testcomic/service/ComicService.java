/**
 * Date: 2020-06-09 16:07
 * Author: xupp
 */

package com.xupp.testcomic.service;

import com.xupp.testcomic.domain.ComicDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ComicService {

    @Autowired
    private ComicDomain comicDomain;

    /**
     * 获取列表
     */
    @GetMapping("/list/page/{pageIndex}/{pageSize}")
    public Object listComics(@PathVariable  Integer pageIndex,@PathVariable Integer pageSize){
        return comicDomain.list(pageIndex,pageSize);
    }

    /**
     * 保存一条记录
     */
    @PostMapping("/comic")
    public String save(
            @RequestParam(name = "name")  String name,
            @RequestParam(name = "type")  String type,
            @RequestParam(name = "sourceUrl")  String sourceUrl,
            @RequestParam(name = "description")  String description

    ){
        return comicDomain.save(name,type,sourceUrl,description).getId();
    }

    /**
     * 设置漫画的封面图片
     * @param refId
     * @param coverUrl
     * @return
     */
    @GetMapping("/comic/cover")
    public Object setCover(
           @RequestParam(name = "refId") String refId,
           @RequestParam("coverUrl") String  coverUrl

    ){
        return comicDomain.setCover(refId,coverUrl);
    }



}
