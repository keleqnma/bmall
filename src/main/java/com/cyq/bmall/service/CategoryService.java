package com.cyq.bmall.service;

import com.cyq.bmall.model.Menu;
import com.cyq.bmall.model.Category;

import java.util.List;
import java.util.Map;

/**
 * 商品分类Service
 * 
 * @author yangfan
 * 
 */
public interface CategoryService {

	/**
	 * 获得商品分类树(商品分类类型为菜单类型)
	 * 
	 * 通过用户ID判断，他能看到的商品分类
	 * 
	 * @return
	 */
	public List<Menu> menus();

	/**
	 * 获得商品分类树(包括所有商品分类类型)
	 * 
	 * 通过用户ID判断，他能看到的商品分类
	 * 
	 * @return
	 */
	public List<Menu> allMenus();

	/**
	 * 获得商品分类列表
	 * 
	 *
	 * @return
	 */
	public List<Category> treeList();

	/**
	 * 添加商品分类
	 * 
	 * @param category
	 */
	public void add(Category category);

	/**
	 * 删除商品分类
	 * 
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 修改商品分类
	 * 
	 * @param category
	 */
	public void edit(Category category);

	/**
	 * 获得一个商品分类
	 * 
	 * @param id
	 * @return
	 */
	public Category get(Long id);

	/**
	 * 资列表
	 * @param params
	 * @return
	 */
    List<Category> getCategoryList(Map<String, Object> params);


}
