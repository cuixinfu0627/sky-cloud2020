package com.sky.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sky.cloud.entity.OrderEntity;
import com.sky.cloud.service.OrderFeignService;
import com.sky.cloud.view.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


/**
 * 使用 openfeign调用,配合hystrix做服务降级
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-12-19 15:36:10
 */
@RestController
@RequestMapping("user-hystrix") 
@DefaultProperties(defaultFallback ="createOrder_Global_Fallback")
public class UserHystrixController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private OrderFeignService orderFeignService;

    @GetMapping("/order/list")
    @HystrixCommand(fallbackMethod = "createOrder_Fallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
            @HystrixProperty(name = "execution.timeout.enabled", value = "false")})
    public R orderList(@RequestParam Map<String, Object> params){

        logger.info("/order/list-开始睡眠3000毫秒");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("/order/list-睡眠结束...");

        R result = orderFeignService.list(params);

        return result;
    }

    @PostMapping("/order/create")
    @HystrixCommand(commandKey="createOrder", groupKey="order", fallbackMethod ="createOrder_Fallback", threadPoolKey ="tpk1", commandProperties={
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="3000")})
    public R create(@RequestBody OrderEntity order){
        R result = orderFeignService.create(order);
        return result;
    }

    public R createOrder_Fallback(@RequestBody OrderEntity order) {
        return R.error("创建订单系统繁忙或请求超时，请稍后重试!!!");
    }

    //服务降级 全局处理
    // hystrix global fallback 全局处理
    @PostMapping("/order/create2")
    @HystrixCommand
    public R create2(@RequestBody OrderEntity order){
        R result = orderFeignService.create(order);
        return result;
    }
    public R createOrder_Global_Fallback(@RequestBody OrderEntity order) {
        return R.error("订单系统繁忙或请求超时，[全局处理]请稍后重试!!!");
    }

    //hystrix 服务熔断
    @PostMapping("/order/circuitBreaker")
    @HystrixCommand(fallbackMethod ="createOrder_Global_Fallback",commandProperties={
    @HystrixProperty(name="circuitBreaker.enabled",value="true"),     //是否开启断路器
    @HystrixProperty(name ="circuitBreaker.requestVolumeThreshold",value="10"),     //请求次数
    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="10000"),    //时间窗口期
    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="60")})    //关败室达到多少后跳闸
    public R orderCircuitBreaker(@RequestBody OrderEntity order){

        R result = orderFeignService.hystrixCircuitBreaker(order);

        return result;
    }

}
