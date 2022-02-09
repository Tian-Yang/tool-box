package com.toolbox.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.toolbox.bean.MetaApiResponse;
import com.toolbox.util.RestTemplateUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class MetaController {

    private static final String base_url = "https://graph.facebook.com/v12.0";
    @Resource
    private RestTemplateUtils restTemplateUtils;

    @ApiOperation(value = "获取个号信息")
    @GetMapping("/getMe")
    @ResponseBody
    public MetaApiResponse getMe(@ApiParam(value = "授权码") String token) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("access_token", "EAASsV1Rvk60BANqk4eDpZCOYoQ4ARNhAcIhrhDq5NZA6qEINXdhKHh6j2Tvj88GMRphpqMNt4pcOYMfZBMIHD1tXdgnkqZAmqmZBX8HVDWmcdP0tuULza5nWZBBFXlB0QXLmWVFh8QClZCe5kOvaNoZCDF3Q7igYjDmYayQTg7DgA3tSnBexk4Bz");
        paramMap.put("fields", "businesses{owned_ad_accounts{account_id,account_status,name,business,owner},client_ad_accounts{account_id,account_status,name,business,owner},adspixels,name,owned_businesses},name,permissions,business_users.limit(10){business,email,id,role},id");
        String url = base_url + "/me";
        MetaApiResponse rst = restTemplateUtils.getJsonHttps(url, paramMap, MetaApiResponse.class, null);
        //BM权限 EMPLOYEE、ADMIN
        //EMPLOYE
        return rst;
    }

    @ApiOperation(value = "facebook平台的授权回调", notes = "facebook平台的授权回调 ")
    @GetMapping("/fbAccreditCallback")
    public void fbAccreditCallback(HttpServletRequest request, HttpServletResponse response){
        log.info("------------------------------------------------------>fbAccreditCallback response:{}", response);
        String userId = request.getParameter("user_id");
        String state = request.getParameter("state");
        String authCode = request.getParameter("auth_code");
        log.info("------------------------------------------------------>fbAuthCallback user_id:{},state:[{}],auth_code:{} ", userId, state, authCode);
    }

    @RequestMapping(value = "privacy")
    public String index(HttpServletResponse response) {
        return "/yingsi";
    }

}
