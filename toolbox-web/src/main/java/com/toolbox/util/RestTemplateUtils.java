package com.toolbox.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
public class RestTemplateUtils {

    /**
     * http 请求 GET
     *
     * @param url    地址
     * @param params 参数
     * @return String 类型
     */
    public <T> T getHttps(String url, Map<String, Object> params, Class<T> clazz, HttpHeaders headers) {
        Map<String, Object> urlParams = Maps.newHashMap();
        if (params != null) {
            for (String item : params.keySet()) {
                Object value = params.get(item);
                if (value != null) {
                    urlParams.put(item, value);
                }
            }
        }
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        // 设置编码集
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        // error处理
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        log.info("GET请求URL={}, values={}", url, urlParams);
        url = expandURL(url, urlParams.keySet());
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        //String response = restTemplate.getForObject(url, String.class, params);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class, urlParams);
        T apiResponse = JSONObject.parseObject(response.getBody(), clazz);
        return apiResponse;
    }


    private String getParam(String url, String name) {
        String params = url.substring(url.indexOf("?") + 1, url.length());
        Map<String, String> split = Splitter.on("&").withKeyValueSeparator("=").split(params);
        return split.get(name);
    }

    /**
     * http 请求 POST
     *
     * @param url    地址
     * @param params 参数
     * @return String 类型
     */
    public <T> T postHttps(String url, Map<String, Object> params, Class<T> clazz, HttpHeaders headers) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8)); // 设置编码集
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler()); //error处理
        url = expandURL(url, params.keySet());
        log.info("Post请求URL:" + url + "  param" + JSONObject.toJSONString(params));
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class, params);
        T apiResponse = JSONObject.parseObject(response.getBody(), clazz);
        return apiResponse;
    }

    public <T> T getJsonHttps(String url, Map<String, Object> params, Class<T> clazz, HttpHeaders headers) {
        if (headers == null) {
            headers = new HttpHeaders();
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        // 设置编码集
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler()); //error处理
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params, headers);
        url = expandURL(url, params.keySet());
        log.info("Get Json 请求URL:" + url + "  param" + JSONObject.toJSONString(params));
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class, params);
        T apiResponse = JSONObject.parseObject(response.getBody(), clazz);
        return apiResponse;
    }

    /**
     * http 请求 POST JSON
     *
     * @param url    地址
     * @param params 参数
     * @return String 类型
     */
    public <T> T postJsonHttps(String url, Map<String, Object> params, Class<T> clazz, HttpHeaders headers) {
        if (headers == null) {
            headers = new HttpHeaders();
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        //restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8)); // 设置编码集
        //error处理
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        log.info("Post Json 请求URL:" + url + "  param" + JSONObject.toJSONString(params));
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class, params);
        T apiResponse = JSONObject.parseObject(response.getBody(), clazz);
        return apiResponse;
    }

    /**
     * @param url
     * @param params
     * @param clazz
     * @param headers
     * @param <T>
     * @return
     */
    public <T> T postJsonHttpsWithUtf8(String url, Map<String, Object> params, Class<T> clazz, HttpHeaders headers) {
        if (headers == null) {
            headers = new HttpHeaders();
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        // 设置编码集
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        //error处理
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        log.info("Post Json 请求URL:" + url + "  param" + JSONObject.toJSONString(params));
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class, params);
        T apiResponse = JSONObject.parseObject(response.getBody(), clazz);
        return apiResponse;
    }

    /**
     * http 请求 POST JSON
     * 返回resource
     *
     * @param url    地址
     * @param params 参数
     */
    public org.springframework.core.io.Resource postJsonHttpsFile(String url, Map<String, Object> params, HttpHeaders headers) {
        if (headers == null) {
            headers = new HttpHeaders();
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        //error处理
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        log.info("Post Json 请求URL:" + url + "  param" + JSONObject.toJSONString(params));
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params, headers);

        ResponseEntity<org.springframework.core.io.Resource> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, org.springframework.core.io.Resource.class, params);
        if (200 != response.getStatusCodeValue() || Objects.isNull(response.getBody())) {
            return null;
        }
        log.info("Post Json 请求URL:" + url + "下载报表");
        return response.getBody();
    }


    /**
     * http 请求 POST
     *
     * @param url
     * @param entityBuilder
     * @param clazz
     * @param accessToken
     * @param <T>
     * @return
     */
    public <T> T postHttpsFile(String url, MultipartEntityBuilder entityBuilder, Class<T> clazz, String accessToken) {
        // 构造请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Access-Token", accessToken);

        org.apache.http.HttpEntity entity = entityBuilder.build();
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;

        try {
            client = HttpClientBuilder.create().build();
            httpPost.setURI(URI.create(url));

            httpPost.setEntity(entity);

            response = client.execute(httpPost);
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                bufferedReader.close();
                return JSONObject.parseObject(result.toString(), clazz);
            }

        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                client.close();
            } catch (IOException e) {
                log.warn("关闭异常", e);
            }
        }
        return null;
    }

    /**
     * @param url
     * @param keys
     * @return
     * @Title: expandURL
     * @author:
     * @Description: TODO
     * @return: String
     */
    private static String expandURL(String url, Set<?> keys) {
        StringBuilder sb = new StringBuilder(url);
        if (!url.contains("?")) {
            sb.append("?");
        } else {
            sb.append("&");
        }
        for (Object key : keys) {
            sb.append(key).append("=").append("{").append(key).append("}").append("&");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

}