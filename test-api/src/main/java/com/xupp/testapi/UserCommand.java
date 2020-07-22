/**
 * Date: 2020-06-09 22:40
 * Author: xupp
 */

package com.xupp.testapi;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

public class UserCommand extends HystrixCommand<String> {
    private org.springframework.web.client.RestTemplate restTemplate;
    private Long id;
    public UserCommand (Setter setter,RestTemplate restTemplate,Long id){
        super(setter);
        this.restTemplate=restTemplate;
        this.id=id;
    }
    @Override
    protected String run() throws Exception {
        return null;
    }
}
