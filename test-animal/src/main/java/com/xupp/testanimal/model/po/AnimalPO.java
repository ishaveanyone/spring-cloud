/**
 * Date: 2020-06-09 16:13
 * Author: xupp
 */

package com.xupp.testanimal.model.po;

import com.xupp.testanimal.model.KeyGenerator;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "animal")
@Data
public class AnimalPO {
    @Id
    private String id= KeyGenerator.UUID();
    //名称
    private String name;
    //描述
    private String description;
    //简介图片地址
    private String indexUrl;
    //漫画来源方式 可能是 图片 url zip 或者doc ppt
    private String type;
    //漫画来源地址 不一定存在
    private String sourceUrl;

}
