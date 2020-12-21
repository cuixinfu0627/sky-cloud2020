/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.sky.cloud.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cloud.dao.UserDao;
import com.sky.cloud.entity.UserEntity;
import com.sky.cloud.service.UserService;
import com.sky.cloud.view.PageUtils;
import com.sky.cloud.view.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {


	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");

		IPage<UserEntity> page = this.page(
				new Query<UserEntity>().getPage(params),
				new QueryWrapper<UserEntity>().like(StringUtils.isNotBlank(username),
						"username", username)

		);

		return new PageUtils(page);
	}
}
