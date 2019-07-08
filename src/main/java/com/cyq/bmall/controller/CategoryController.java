package com.cyq.bmall.controller;

import com.cyq.bmall.model.Menu;
import com.cyq.bmall.model.Category;
import com.cyq.bmall.model.RestResp;
import com.cyq.bmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类控制器
 *
 * @author yangfan
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 获得菜单
     * <p>
     * 通过用户ID判断，他能看到的菜单
     *
     * @return
     */
    @RequestMapping(value = "/menus", method = RequestMethod.POST)
    public List<Menu> menus() {
        return categoryService.menus();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Category getById(@PathVariable("id") Long id) {
        return categoryService.get(id);
    }

    /**
     * 获得商品分类树(包括所有商品分类类型)
     * <p>
     * 通过用户ID判断，他能看到的商品分类
     *
     * @return
     */
    @RequestMapping(value = "/allMenus", method = RequestMethod.POST)
    public List<Menu> allMenus() {

        return categoryService.allMenus();
    }

    /**
     * 添加商品分类
     */
    @RequestMapping(method = RequestMethod.POST)
    public RestResp add(@RequestBody Category category) {
        categoryService.add(category);
        return RestResp.ok("添加成功");
    }

    /**
     * 编辑商品分类
     */
    @RequestMapping(method = RequestMethod.PUT)
    public RestResp edit(@RequestBody Category category) {
        categoryService.edit(category);

        return RestResp.ok("编辑成功");
    }

    /**
     * 删除商品分类
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public RestResp delete(@PathVariable("id") Long id) {
        categoryService.delete(id);

        return RestResp.ok("删除成功");
    }


    /**
     * tree型列表
     * @return
     */
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public List<Category> treeList() {
        return categoryService.treeList();
    }
}
