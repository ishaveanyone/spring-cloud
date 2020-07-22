/**
 * Date: 2020-06-08 15:13
 * Author: xupp
 */

package com.xupp.testanimal.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyTest {

    @GetMapping("/test")
    public String sayTest(){
        return "test";
    }
}
