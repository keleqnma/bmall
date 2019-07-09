package com.cyq.bmall.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cyq.bmall.model.Commodity;
import com.cyq.bmall.model.Order;
import com.cyq.bmall.model.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;
import java.util.Map;


@Mapper
public interface OrderMapper extends BaseMapper<Order>{
    /**
     * 查询订单
     * @param params 查询参数
     * @return
     */
    List<Order> findOrder(Map<String, Object> params);

    /**
     * 获得订单的商品id
     * @param id 用户ID
     * @return 角色ID
     */
    List<Long> getOrderCommodityIds(Long id);

    /**
     * 获得订单的商品名字
     * @param id 用户ID
     * @return 角色名称
     */
    List<String> getOrderCommodityNames(Long id);
    /**
     * ID查询订单
     * @param id ID
     * @return 订单
     */
    Order getById(Long id);

    /**
     * 更新订单
     * @param order 订单
     */
    void update(Order order);

    /**
     * 删除订单
     * @param id 订单ID
     */
    void deleteById(Long id);

    //某个时间段内所有订单的总金额
    long totalAmountOrders(Map<String, Object> params);

    //某个时间段内所有订单的总数量
    long totalQuantityOrders(Map<String, Object> params);

}
