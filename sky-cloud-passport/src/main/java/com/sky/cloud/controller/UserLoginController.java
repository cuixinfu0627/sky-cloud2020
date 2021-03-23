package com.sky.cloud.controller;

import com.sky.cloud.entity.OrderEntity;
import com.sky.cloud.service.UserService;
import com.sky.cloud.view.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


/**
 * 用户登录：用户登录
 * @author live
 * @email 870459550@qq.com
 * @date 2020-12-19 15:36:10
 */
@RestController
@RequestMapping("")
public class UserLoginController {

    @Autowired
    private UserService userService;

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-order-service}")
    private String invoke_url; //http://order-service


    @PostMapping("/login")
    public R  login(@RequestBody OrderEntity order){
        R result = restTemplate.getForObject(invoke_url + "/order/info/"+order.getId(), R.class);
        return result;
    }

    @PostMapping("/toIndex")
    public R  toIndex(@RequestBody OrderEntity order){
        R result = restTemplate.getForObject(invoke_url + "/order/info/"+order.getId(), R.class);
        return result;
    }

    @PostMapping("/toError")
    public R  toError(@RequestBody OrderEntity order){
        R result = restTemplate.getForObject(invoke_url + "/order/info/"+order.getId(), R.class);
        return result;
    }

}
