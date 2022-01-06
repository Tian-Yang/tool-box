package com.toolbox.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("hello %s!", name);
    }

    @RequestMapping(value = "index")
    public String index() {
        log.info("request index...");
        return "/index";
    }

}
