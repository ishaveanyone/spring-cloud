/**
 * Date: 2020-06-09 16:34
 * Author: xupp
 */

package com.xupp.testanimal.domain;


import com.xupp.testanimal.dao.IAnimalDAO;
import com.xupp.testanimal.model.po.AnimalPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AnimalDomain {
    @Autowired
    IAnimalDAO iAnimalDAO;
    public Page list(Integer pageIndex, Integer pageSize){
        PageRequest pageRequest=PageRequest.of(pageIndex-1,pageSize);
        return iAnimalDAO.findAll(pageRequest);
    }

    public AnimalPO save(
            String title,
            String type,
            String sourceUrl,
            String description
    ){
        AnimalPO animalPO =new AnimalPO();
        animalPO.setName(title);
        animalPO.setType(type);
        animalPO.setDescription(description);
        animalPO.setSourceUrl(sourceUrl);
        return iAnimalDAO.save(animalPO);
    }


    public Object setCover(String refId,String url){
        AnimalPO animalPO =  iAnimalDAO.findById(refId).get();
        animalPO.setIndexUrl(url);
        return iAnimalDAO.save(animalPO);
    }
}
