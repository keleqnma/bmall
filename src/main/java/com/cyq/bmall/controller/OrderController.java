package com.cyq.bmall.controller;

import com.cyq.bmall.jwt.JsonWebTokenUtility;
import com.cyq.bmall.model.Order;
import com.cyq.bmall.model.Resource;
import com.cyq.bmall.model.RestResp;
import com.cyq.bmall.service.OrderService;
import com.cyq.bmall.vo.DataTable;
import com.cyq.bmall.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CYQ
 * @date $[DATE]
 * @description
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    private JsonWebTokenUtility tokenService = new JsonWebTokenUtility();
    
    /**
     * 修改订单
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Order edit(@RequestBody Order order) {
        orderService.edit(order);
        return order;
    }

    @RequestMapping(value = "/tables", method = RequestMethod.POST)
    public DataTable<Order> tables(@RequestBody OrderVO orderVO) {
        return orderService.tables(orderVO);
    }

    @RequestMapping(value = "/tables/{categoryId}", method = RequestMethod.POST)
    public DataTable<Order> categoryTables(@RequestBody OrderVO orderVO,@PathVariable("categoryId") Long categoryId) {
        return orderService.categoryTables(orderVO,categoryId);
    }

    /**
     * 订单详情
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public Order getOrderById(@PathVariable("orderId") Long orderId) {
        Order order = orderService.get(orderId);
        return order;
    }

    /**
     * 删除订单
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE)
    public RestResp delete(@PathVariable("orderId") Long orderId) {
       orderService.delete(orderId);
        return RestResp.ok("删除成功");
    }

    /**
     * 批量删除订单
     *
     * @param ids ('0','1','2')
     * @return
     */
    @RequestMapping(value = "/batchDelete", method = RequestMethod.DELETE)
    public void batchDelete(String ids) {
        if (ids != null && ids.length() > 0) {
            for (String id : ids.split(",")) {
                this.delete(Long.valueOf(id));
            }
        }
    }

    /**
     * 编辑订单状态
     *
     * @param order
     * @return
     */
    @RequestMapping(value="/editState", method = RequestMethod.PUT)
    public RestResp editState(@RequestBody Order order) {
        orderService.editState(order);
        return RestResp.ok("编辑成功");
    }

    @RequestMapping(value = "/allOrder", method = RequestMethod.GET)
    public List<Order> orderList() {
        return orderService.orderList();
    }
    
}
