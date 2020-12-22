package com.sky.cloud.controller;

import com.sky.cloud.entity.OrderEntity;
import com.sky.cloud.service.OrderFeignService;
import com.sky.cloud.view.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private OrderFeignService orderFeignService;

    @GetMapping("/order/list")
    public R orderList(@RequestParam Map<String, Object> params){

        R result = orderFeignService.list(params);

        return result;
    }

    @GetMapping("/order/info/{id}")
    public R info(@PathVariable("id") Long id){

        R result = orderFeignService.info(id);

        return result;
    }

    @PostMapping("/order/create")
    public R create(@RequestBody OrderEntity order){
        R result = orderFeignService.create(order);;
        return result;
    }

    @PostMapping("/order/openfeign/timeout")
    public R openfeignTimeout(){
        //默认等待1秒钟
        R result = orderFeignService.openfeignTimeout();
        return result;
    }

}
