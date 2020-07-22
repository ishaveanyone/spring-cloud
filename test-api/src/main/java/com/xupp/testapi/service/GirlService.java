/**
 * Date: 2020-06-10 15:15
 * Author: xupp
 */

package com.xupp.testapi.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "girl")
public interface GirlService {
    @PostMapping("/girl")
    String save(
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("sourceUrl") String sourceUrl,
            @RequestParam("description") String description
    );
    @GetMapping("/girl/cover")
    String setCover(@RequestParam("refId") String refId, @RequestParam("coverUrl") String coverUrl);

    @GetMapping("/list/page/{pageIndex}/{pageSize}")
    Object list(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);
}
