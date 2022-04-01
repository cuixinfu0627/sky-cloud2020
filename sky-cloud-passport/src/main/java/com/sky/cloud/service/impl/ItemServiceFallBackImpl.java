package com.sky.cloud.service.impl;

import com.sky.cloud.entity.ItemEntity;
import com.sky.cloud.service.ItemService;
import com.sky.cloud.view.R;
import org.springframework.stereotype.Component;

/**
 * @name: ItemServiceImpl <tb>
 * @title: 兜底的方法  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/1/8 16:31 <tb>
 */
@Component
public class ItemServiceFallBackImpl implements ItemService {

    @Override
    public R decrease(ItemEntity item) {
        //TODO 记录日志信息，后续自动补偿，根据实际业务情况处理
        return R.error("-----------------------服务返回 decrease ItemServiceFallBackImpl fall back-list");
    }

}
