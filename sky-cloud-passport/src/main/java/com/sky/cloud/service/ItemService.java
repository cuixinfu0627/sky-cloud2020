package com.sky.cloud.service;

import com.sky.cloud.entity.ItemEntity;
import com.sky.cloud.service.impl.ItemServiceFallBackImpl;
import com.sky.cloud.view.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @name: ItemService <tb>
 * @title: open-feign-item  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/12/21 15:27 <tb>
 */
@Component
@FeignClient(value = "${service-url.nacos-item-service}",fallback = ItemServiceFallBackImpl.class)
public interface ItemService {

    @PostMapping("item/decrease")
    public R decrease(@RequestBody ItemEntity item);

}
