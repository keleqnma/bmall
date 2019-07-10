package com.cyq.bmall.service.impl;

import com.cyq.bmall.mapper.OrderMapper;
import com.cyq.bmall.mapper.ResourceMapper;
import com.cyq.bmall.mapper.UserMapper;
import com.cyq.bmall.model.Order;
import com.cyq.bmall.model.Resource;
import com.cyq.bmall.service.OrderService;
import com.cyq.bmall.util.AppConst;
import com.cyq.bmall.util.SessionUtil;
import com.cyq.bmall.vo.DataTable;
import com.cyq.bmall.vo.OrderVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.velocity.runtime.parser.node.MathUtils.isInteger;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Order get(Long id) {
        Order order = orderMapper.getById(id);
        if (order != null) {
            order.setCommodityIds(orderMapper.getOrderCommodityIds(id));
        }
        return order;
    }

    @Override
    public void edit(Order order) {
            orderMapper.update(order);
    }

    @Override
    public void delete(Long id) {
        orderMapper.deleteById(id);
    }

    @Override
    public List<String> commodityList(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", id);
        params.put("type", AppConst.RESOURCE_TYPE_METHOD);
        List<Resource> resources = resourceMapper.getResourceList(params);
        return resources.stream().map(resource -> resource.getUrl() + "-" + resource.getMethod()).collect(Collectors.toList());
    }

    @Override
    public List<Order> orderList(){
        List<Order> orders = orderMapper.findOrder(new HashMap<>());
        return orders;
    }

    @Override
    public void editState(Order order) {
        Assert.notNull(order, "order can not be null");
        Assert.hasText(order.getState(), "password mush have value");

        orderMapper.update(order);
    }

    @Override
    public List<String> getOrderCommodityNames(Long id) {
        return orderMapper.getOrderCommodityNames(id);
    }

    @Override
    public DataTable<Order> tables(OrderVO orderVO) {
        Map<String, Object> params = new HashMap<>();
        Long currUid = SessionUtil.getCurrUid();
        if (currUid != null && !userMapper.getUserRoleIds(currUid).contains(AppConst.USER_TYPE_ADMIN.longValue() ) ) {
            params.put("shopId", currUid);// 查自己有权限的订单
        }

        PageHelper.offsetPage(orderVO.getStart(), orderVO.getLength());
        List<Order> orders = orderMapper.findOrder(params);

        for(Order order:orders){
            int nowstate=0 ;

            try {
                nowstate = Integer.parseInt(order.getState());
            }catch (Exception e){

            }
            int uw=0;
            switch (nowstate){
                case 0:
                    order.setState("已下单");
                    break;
                case 1:
                    order.setState("已发货");
                    break;
                case 2:
                    order.setState("已完成");
                    break;
                case 3:
                    order.setState("已取消");
                    break;

            }
        }

        DataTable<Order> tables = new DataTable<>();
        tables.setRecordsTotal(((Page) orders).getTotal());
        tables.setRecordsFiltered(tables.getRecordsTotal());
        tables.setDraw(orderVO.getDraw());
        tables.setData(orders);
        return tables;
    }

    @Override
    public DataTable<Order> categoryTables(OrderVO orderVO,Long categoryId) {
        Map<String, Object> params = new HashMap<>();
        params.put("categoryId",categoryId);

        Long currUid = SessionUtil.getCurrUid();
        params.put("shopId", currUid);
        if (currUid != null && !userMapper.getUserRoleIds(currUid).contains(AppConst.USER_TYPE_ADMIN.longValue() ) ) {
            params.put("shopId", currUid);// 查自己有权限的订单
        }

        PageHelper.offsetPage(orderVO.getStart(), orderVO.getLength());
        List<Order> orders = orderMapper.findOrder(params);

        for(Order order:orders){
            int nowstate=Integer.parseInt(order.getState());
            int uw=0;
            switch (nowstate){
                case 0:
                    order.setState("已下单");
                    break;
                case 1:
                    order.setState("已发货");
                    break;
                case 2:
                    order.setState("已完成");
                    break;
                case 3:
                    order.setState("已取消");
                    break;

            }
        }

        DataTable<Order> tables = new DataTable<>();
        tables.setRecordsTotal(((Page) orders).getTotal());
        tables.setRecordsFiltered(tables.getRecordsTotal());
        tables.setDraw(orderVO.getDraw());
        tables.setData(orders);
        return tables;
    }

}
