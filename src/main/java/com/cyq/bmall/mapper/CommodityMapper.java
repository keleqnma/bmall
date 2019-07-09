package com.cyq.bmall.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cyq.bmall.model.Category;
import com.cyq.bmall.model.Commodity;
import com.cyq.bmall.model.Shop;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
* @author: cyq
* @date: 7/5/2019
* @description:
*/
@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {

    /**
     * 获取商品列表
     *
     * @param params 参数
     * @return
     */
    List<Commodity> getCommodityList(Map<String, Object> params);

    /**
     * 增加商品
     *
     * @param commodity
     */
    void save(Commodity commodity);

    /**
     * 级联删除
     *
     * @param id 主键
     */
    void deleteById(Long id);

    /**
     * 编辑商品
     *
     * @param commodity 商品
     */
    void update(Commodity commodity);

    /**
     * 通过ID获得商品
     *
     * @param id ID
     * @return 商品
     */
    Commodity getById(Long id);

    /**
     * 查询商店的商品ID
     * @param shopId 角色ID
     * @return 商品ID
     */
    List<Long> getShopCommodityIds(Long shopId);

    /**
     * 查询分類下的商品ID
     * @param categoryId 分類ID
     * @return 商品ID
     */
    List<Long> getCategoryCommodityIds(Long categoryId);

    /**
     * 查询某个商品的分类
     * @param commodityId 商品Id
     * @return 商品ID
     */
    Category getCategoryCommodities(Long commodityId);

    //某个时间段内前十的热销商品
    List<Commodity> bestSellingCommodity(Date dateBegin, Date dateEnd);

    //某个时间段内评价数量前十商品
    List<Commodity> bestEvaluationQuantityCommodity(Date dateBegin, Date dateEnd);

    //某个时间段内好评率前十商品
    List<Commodity> bestEvaluationRatingCommodity(Date dateBegin, Date dateEnd);
}
