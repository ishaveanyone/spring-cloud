/**
 * Date: 2020-06-09 16:34
 * Author: xupp
 */

package com.xupp.testgirl.domain;

import com.netflix.discovery.converters.Auto;
import com.xupp.testgirl.dao.IGirlDAO;
import com.xupp.testgirl.model.po.GirlPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GirlDomain {
    @Autowired
    IGirlDAO iGirlDAO;
    public Page list(Integer pageIndex, Integer pageSize){
        PageRequest pageRequest=PageRequest.of(pageIndex-1,pageSize);
        return iGirlDAO.findAll(pageRequest);
    }

    public GirlPO save(
            String title,
            String type,
            String sourceUrl,
            String description
    ){
        GirlPO girlPO=new GirlPO();
        girlPO.setName(title);
        girlPO.setType(type);
        girlPO.setDescription(description);
        girlPO.setSourceUrl(sourceUrl);
        return iGirlDAO.save(girlPO);
    }


    public Object setCover(String refId,String url){
        GirlPO girlPO=  iGirlDAO.findById(refId).get();
        girlPO.setIndexUrl(url);
        return iGirlDAO.save(girlPO);
    }
}
