package com.cyq.bmall.service.impl;

import com.cyq.bmall.mapper.CategoryMapper;
import com.cyq.bmall.mapper.RoleMapper;
import com.cyq.bmall.mapper.UserMapper;
import com.cyq.bmall.model.Menu;
import com.cyq.bmall.model.Category;
import com.cyq.bmall.service.CategoryService;
import com.cyq.bmall.util.AppConst;
import com.cyq.bmall.util.SessionUtil;
import com.cyq.bmall.util.id.IdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Menu> menus() {

        List<Menu> menuList = new ArrayList<>();

        Map<String, Object> params = new HashMap<>();
        params.put("type", AppConst.CATEGORY_TYPE_TOP);// 非底层商品分类
        Long currUid = SessionUtil.getCurrUid();
        params.put("userId", currUid);// 只查自己有权限的商品分类,管理员可查看全部商品分类，商家只可查看自己商品所在的商品分类

        List<Category> categoryList = categoryMapper.getCategoryList(params);

        assembleMenu(menuList, categoryList);
        return menuList;
    }

    private void assembleMenu(List<Menu> menuList, List<Category> categoryList) {
        for (Category r : categoryList) {
            if (r.getPid() == null) {
                Menu menu = new Menu();
                BeanUtils.copyProperties(r, menu);
                menu.setText(r.getName());
                List<Menu> children = new ArrayList<>();
                for (Category c1 : categoryList) {
                    if (Objects.equals(c1.getPid(), r.getId())) {
                        Menu child = new Menu();
                        BeanUtils.copyProperties(c1, child);
                        child.setText(c1.getName());
                        children.add(child);
                    }
                }
                menu.setChildren(children);
                menuList.add(menu);
            }
        }
    }

    @Override
    public List<Menu> allMenus() {
        List<Menu> menuList = new ArrayList<Menu>();

        Map<String, Object> params = new HashMap<>();
        List<Category> categoryList = categoryMapper.getCategoryList(params);
        assembleMenu(menuList, categoryList);
        return menuList;
    }

    @Override
    public List<Category> treeList() {

        Map<String, Object> params = new HashMap<>();
        Long currUid = SessionUtil.getCurrUid();

        if (currUid != null) {
            params.put("userId", currUid);//
        }

        List<Category> categoryList = categoryMapper.getCategoryList(params);

        Map<Long, Category> map = new HashMap<>();
        categoryList.forEach(category -> map.put(category.getId(), category));
        categoryList.forEach(category -> category.setPname(category.getPid() != null ? map.get(category.getPid()).getName() : null));

        List<Category> list = new ArrayList<>();

        categoryList = new CopyOnWriteArrayList<>(categoryList);
        for (Category category : categoryList) {
            if (category.getPid() == null) {
                list.add(category);
                categoryList.remove(category);
                treeSort(categoryList, list, category);
            }
        }


        return list;
    }


    private void treeSort(List<Category> categoryList, List<Category> list, Category parent) {

        for (Category category : categoryList) {
            if (Objects.equals(parent.getId(), category.getPid())) {
                list.add(category);
                categoryList.remove(category);
                treeSort(categoryList, list, category);
            }
        }
    }



    @Override
    public void add(Category category) {
        category.setId(IdUtil.generateId());
        categoryMapper.save(category);
    }

    /**
     * 删除
     *
     * @param id id
     */
    @Override
    public void delete(Long id) {
        categoryMapper.deleteById(id);
    }


    @Override
    public void edit(Category category) {
        categoryMapper.update(category);
    }


    @Override
    public Category get(Long id) {

        return categoryMapper.getById(id);
    }

    @Override
    public List<Category> getCategoryList(Map<String, Object> params) {
        return categoryMapper.getCategoryList(params);
    }

}
