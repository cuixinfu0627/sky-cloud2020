package com.sky.cloud.service.impl;

import com.sky.cloud.dao.ItemDao;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cloud.entity.ItemEntity;
import com.sky.cloud.service.ItemService;
import com.sky.cloud.view.PageUtils;
import com.sky.cloud.view.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("itemService")
public class ItemServiceImpl extends ServiceImpl<ItemDao, ItemEntity> implements ItemService {

    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ItemEntity> page = this.page(
                new Query<ItemEntity>().getPage(params),
                new QueryWrapper<ItemEntity>()
        );

        return new PageUtils(page);
    }

}