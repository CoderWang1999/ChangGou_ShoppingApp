package com.changgou.service.goods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/demo")
@RestController  //@controller,@responseBody
public class DemoController {

    @GetMapping("/test")
    public String demo(){
        return "demo message";
    }
}
