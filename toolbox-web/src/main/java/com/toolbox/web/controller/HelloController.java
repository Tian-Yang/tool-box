package com.toolbox.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

@Controller
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("hello %s!", name);
    }

    @RequestMapping(value = "index")
    public String index(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("myCookie", "myCookieValue") // key & value
                .httpOnly(true)        // 禁止js读取
                .secure(false)        // 在http下也传输
                .domain("localhost")// 域名
                .path("/")            // path
                .maxAge(Duration.ofHours(1))    // 1个小时候过期
                .sameSite("None")    // 大多数情况也是不发送第三方 Cookie，但是导航到目标网址的 Get 请求除外
                .build();

        // 设置Cookie Header
        response.setHeader("Set-Cookie", cookie.toString());
        log.info("request index...");
        return "/index";
    }

}
