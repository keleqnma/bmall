package com.cyq.bmall.service;

import com.cyq.bmall.model.Order;
import com.cyq.bmall.vo.DataTable;
import com.cyq.bmall.vo.OrderVO;

import java.util.List;

/**
 * 订单Service
 *
 * @author yangfan
 */
public interface OrderService {

    /**
     * 获得订单对象
     *
     * @param id
     * @return
     */
    Order get(Long id);

    void edit(Order order);

    /**
     * 删除订单
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 获得订单能访问的商品
     *
     * @param id 订单ID
     * @return
     */
    List<String> commodityList(Long id);

    /**
     * 获得全部订单
     *
     * @return
     */
    List<Order> orderList();

    /**
     * 编辑订单状态
     *
     * @param order
     */
    void editState(Order order);

    List<String> getOrderCommodityNames(Long id);

    DataTable<Order> tables(OrderVO orderVO);

    DataTable<Order> categoryTables(OrderVO orderVO,Long categoryId);
}
