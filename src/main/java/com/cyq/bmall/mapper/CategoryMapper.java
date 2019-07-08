package com.cyq.bmall.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cyq.bmall.model.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* @author: cyq
* @date: 7/8/2019
* @description:
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
     /**
     * * 获取商品分类列表
     ** @param params 参数
     * @return
     */
    List<Category> getCategoryList(Map<String, Object> params);

    /**
     * 增加商品分类
     *
     * @param category
     */
    void save(Category category);

    /**
     * 级联删除
     *
     * @param id 主键
     */
    void deleteById(Long id);

    /**
     * 编辑商品分类
     *
     * @param category 商品分类
     */
    void update(Category category);

    /**
     * 通过ID获得商品分类
     *
     * @param id ID
     * @return 商品分类
     */
    Category getById(Long id);
}
