/**
 * Date: 2020-06-10 10:39
 * Author: xupp
 */

package com.xupp.testgirl.model;

import java.util.UUID;

public class KeyGenerator {
    public static String UUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
