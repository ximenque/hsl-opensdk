package com.hsl.sdkdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author gaoh
 * @version 1.0
 * @date 2019/9/28 9:17
 */
@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping(value = "/test")
    public String test(String request) {
        ResponseEntity<Object> objectResponseEntity = restTemplate.postForEntity("http://127.0.0.1:8080/park/GetTrafficNum2.do", null, Object.class);

        Object body = objectResponseEntity.getBody();


        log.debug("body:{}", body);

        assert body != null;
        return body.toString();
    }


}
