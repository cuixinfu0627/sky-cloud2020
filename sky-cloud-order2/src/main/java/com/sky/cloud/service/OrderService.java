package com.sky.cloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.cloud.entity.OrderEntity;
import com.sky.cloud.view.PageUtils;

import java.util.Map;

/**
 * @name: OrderService <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/12/21 13:51 <tb>
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

