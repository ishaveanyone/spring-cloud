/**
 * Date: 2020-06-09 16:34
 * Author: xupp
 */

package com.xupp.testapi.domain;

import com.xupp.testapi.dao.IComicDAO;
import com.xupp.testapi.model.po.ComicPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ComicDomain {
    @Autowired
    IComicDAO iComicDAO;
    public Page list(Integer pageIndex, Integer pageSize){
        PageRequest pageRequest=PageRequest.of(pageIndex-1,pageSize);
        return iComicDAO.findAll(pageRequest);
    }

    public ComicPO save(
            String title,
            String type,
            String sourceUrl,
            String description
    ){
        ComicPO comicPO=new ComicPO();
        comicPO.setName(title);
        comicPO.setType(type);
        comicPO.setDescription(description);
        comicPO.setSourceUrl(sourceUrl);
        return iComicDAO.save(comicPO);
    }


    public Object setCover(String refId,String url){
        ComicPO comicPO=  iComicDAO.findById(refId).get();
        comicPO.setIndexUrl(url);
        return iComicDAO.save(comicPO);
    }
}
