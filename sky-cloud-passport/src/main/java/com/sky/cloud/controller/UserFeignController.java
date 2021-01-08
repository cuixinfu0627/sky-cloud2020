package com.sky.cloud.controller;

import com.sky.cloud.entity.OrderEntity;
import com.sky.cloud.service.OrderService;
import com.sky.cloud.view.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


/**
 * 使用 openfeign 调用
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-12-19 15:36:10
 */
@RestController
@RequestMapping("user-feign")
public class UserFeignController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/list")
    public R orderList(@RequestParam Map<String, Object> params){

        R result = orderService.list(params);
        return result;
    }

    @GetMapping("/order/info/{id}")
    public R info(@PathVariable("id") Long id){

        R result = orderService.info(id);

        return result;
    }

    @PostMapping("/order/create")
    public R create(@RequestBody OrderEntity order){
        R result = orderService.create(order);;
        return result;
    }

    @PostMapping("/order/openfeign/timeout")
    public R openfeignTimeout(){
        //默认等待1秒钟
        R result = orderService.openfeignTimeout();
        return result;
    }

}
