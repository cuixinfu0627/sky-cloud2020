package com.sky.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sky.cloud.dao.PaymentDao;
import com.sky.cloud.service.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cloud.entity.PaymentEntity;
import com.sky.cloud.view.PageUtils;
import com.sky.cloud.view.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("paymentService")
public class PaymentServiceImpl extends ServiceImpl<PaymentDao, PaymentEntity> implements PaymentService {

    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PaymentEntity> page = this.page(
                new Query<PaymentEntity>().getPage(params),
                new QueryWrapper<PaymentEntity>()
        );

        return new PageUtils(page);
    }

}