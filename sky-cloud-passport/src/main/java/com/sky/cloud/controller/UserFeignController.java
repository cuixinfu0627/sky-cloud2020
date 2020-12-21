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
 * 经销商用户
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

    /**
     * http://127.0.0.1:8002/order/list
     */
    @GetMapping("/order/list")
    public R orderList(@RequestParam Map<String, Object> params){

        R result = orderFeignService.list(params);

        return result;
    }

    /**
     * http://127.0.0.1:8002/order/info/1001
     */
    @GetMapping("/order/info/{id}")
    public R info(@PathVariable("id") Long id){

        R result = orderFeignService.info(id);

        return result;
    }

    /**http://127.0.0.1:8002/order/create
     * {
     *     "payment": 790000,
     *     "orderNum": "1235649864",
     *     "itemId": "1001",
     *     "itemTitle": "40g海婷海带丝（25包一箱）卖9.9",
     *     "userId": 1,
     *     "username": "孙行者",
     *     "mobile": "15010089114",
     *     "address": "花果山水帘洞齐天大圣美猴王孙悟空",
     *     "message": "充数下班侧",
     *     "status": 1
     * }
     */
    @PostMapping("/order/create")
    public R info(@RequestBody OrderEntity order){
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
