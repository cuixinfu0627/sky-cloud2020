package com.sky.cloud.controller;

import com.sky.cloud.entity.OrderEntity;
import com.sky.cloud.service.UserService;
import com.sky.cloud.view.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @name: UserFeignSeataController <tb>
 * @title: 测试分布式事务seata配合feign调用  <tb>
 * @author: cuixinfu@51play.com <tb>
 */
@RestController
@RequestMapping("user-seata")
public class UserSeataController {

    @Autowired
    private UserService userService;

    /**
     * @title: 创建订单 <tb>
     * @params: order
     *{
     *     "payment": 790000,
     *     "orderNum": "1235649864",
     *     "itemId": "1001",
     *     "itemTitle": "40g海婷海带丝（25包一箱）卖9.9",
     *     "userId": 1,
     *     "username": "孙行者",
     *     "mobile": "15010089114",
     *     "address": "花果山水帘洞齐天大圣美猴王孙悟空",
     *     "message": "充数下班侧",
     *     "status": 1,
     *     "item":
     *     {
     *       "itemId": "2000",
     *       "num": 1,
     *       "picPath": "http://img.testfc-dev.zhxf.ltd/sky/goods/goods.jpg",
     *       "price": 11000,
     *       "title": "new8- 三星 W999 黑色 电信3G手机 双卡双待双通",
     *       "totalFee": 11000
     *     }
     * }  <tb
     * @author: cuixinfu@51play.com <tb>
     * @date: 2021/4/27 16:04<tb>
     */
    @PostMapping("/createOrder")
    public R create(@RequestBody OrderEntity order){
        R result = userService.createOrder(order);;
        return result;
    }

}
