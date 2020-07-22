/**
 * Date: 2020-06-09 22:19
 * Author: xupp
 */

package com.xupp.testapi.service;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Service
public class TestServiceApi {
    @Autowired
    private RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "back")
    public String test(){
        return  restTemplate.getForEntity("http://ANIMAL/test",String.class).getBody();
    }
    public String back(){
        return "error";
    }
}
