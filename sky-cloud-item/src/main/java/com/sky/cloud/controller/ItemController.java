package com.sky.cloud.controller;

import com.sky.cloud.entity.ItemEntity;
import com.sky.cloud.view.PageUtils;
import com.sky.cloud.view.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sky.cloud.service.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 商品表
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-12-19 15:36:10
 */
@RestController
@RequestMapping("theme/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = itemService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		ItemEntity item = itemService.getById(id);

        return R.ok().put("item", item);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ItemEntity item){
		itemService.save(item);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ItemEntity item){
		itemService.updateById(item);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		itemService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
