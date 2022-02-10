package com.toolbox.web.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.toolbox.bean.MetaApiResponse;
import com.toolbox.bean.MetaTokenResponse;
import com.toolbox.entity.MetaClient;
import com.toolbox.entity.MetaInfo;
import com.toolbox.service.IMetaClientService;
import com.toolbox.service.IMetaInfoService;
import com.toolbox.util.RestTemplateUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class MetaController {

    private static final String base_url = "https://graph.facebook.com/v12.0";
    @Resource
    private RestTemplateUtils restTemplateUtils;

    @Resource
    private IMetaInfoService metaInfoService;

    @Resource
    private IMetaClientService metaClientService;

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

    @ApiOperation(value = "查询token信息")
    @GetMapping("/queryToken")
    @ResponseBody
    public List<MetaInfo> queryToken() {
        return metaInfoService.list();
    }

    @ApiOperation(value = "查询token信息")
    @GetMapping("/queryClient")
    @ResponseBody
    public MetaClient queryClient(String clientId) {
        QueryWrapper<MetaClient> query = new QueryWrapper<>();
        query.eq("client_id", clientId);
        return metaClientService.getOne(query);
    }

    @ApiOperation(value = "facebook平台的授权回调", notes = "facebook平台的授权回调 ")
    @GetMapping("/fbAccreditCallback")
    public void fbAccreditCallback(HttpServletRequest request, HttpServletResponse response) {
        log.info("------------------------------------------------------>fbAccreditCallback response:{}", response);
        String state = request.getParameter("state");
        String authCode = request.getParameter("code");
        log.info("------------------------------------------------------>fbAuthCallback state:[{}],auth_code:{} ", state, authCode);
        String clientId = "1103354517117699";
        QueryWrapper<MetaClient> query = new QueryWrapper<>();
        query.eq("client_id", clientId);
        MetaClient metaClient = metaClientService.getOne(query);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("redirect_uri", "https://www.mengxi.love/fbAccreditCallback");
        paramMap.put("client_id", metaClient.getClientId());
        paramMap.put("client_secret", metaClient.getClientSecret());
        paramMap.put("code", authCode);
        MetaTokenResponse rst = restTemplateUtils.getJsonHttps(base_url+"/ouath/access_token", paramMap, MetaTokenResponse.class, null);
        log.info("MetaTokenResponse:[{}]", rst);
    }

    @RequestMapping(value = "privacy")
    public String index(HttpServletResponse response) {
        return "/yingsi";
    }

}
