package com.sky.cloud.controller;

import com.sky.cloud.entity.ItemEntity;
import com.sky.cloud.service.ItemService;
import com.sky.cloud.view.PageUtils;
import com.sky.cloud.view.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



/**
 * 商品表
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-12-19 15:36:10
 */
@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = itemService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		ItemEntity item = itemService.getById(id);

        return R.ok().put("item", item);
    }

    /**
     * 保存
     */
    @PostMapping("/create")
    public R create(@RequestBody ItemEntity item){
		itemService.save(item);

        return R.ok();
    }

}
