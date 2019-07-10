package com.cyq.bmall.controller;

import com.cyq.bmall.mapper.OrderMapper;
import com.cyq.bmall.mapper.UserMapper;
import com.cyq.bmall.util.AppConst;
import com.cyq.bmall.util.SessionUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dataview")
@Slf4j
public class DataviewController {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Data
    private class OrderPriceResp {
        ArrayList<String> date = new ArrayList<>();
        ArrayList<Long> data = new ArrayList<>();

        public OrderPriceResp() {
//            date = new String[]{"1968/10/4", "1968/10/5", "1968/10/6", "1968/10/7", "1968/10/8", "1968/10/9", "1968/10/10", "1968/10/11", "1968/10/12", "1968/10/13", "1968/10/14", "1968/10/15", "1968/10/16", "1968/10/17", "1968/10/18", "1968/10/19", "1968/10/20", "1968/10/21", "1968/10/22", "1968/10/23", "1968/10/24", "1968/10/25", "1968/10/26", "1968/10/27", "1968/10/28", "1968/10/29", "1968/10/30", "1968/10/31", "1968/11/1", "1968/11/2", "1968/11/3", "1968/11/4", "1968/11/5", "1968/11/6", "1968/11/7", "1968/11/8", "1968/11/9", "1968/11/10", "1968/11/11", "1968/11/12", "1968/11/13", "1968/11/14", "1968/11/15", "1968/11/16", "1968/11/17", "1968/11/18", "1968/11/19", "1968/11/20", "1968/11/21", "1968/11/22", "1968/11/23", "1968/11/24", "1968/11/25", "1968/11/26", "1968/11/27", "1968/11/28", "1968/11/29", "1968/11/30", "1968/12/1", "1968/12/2", "1968/12/3", "1968/12/4", "1968/12/5", "1968/12/6", "1968/12/7", "1968/12/8", "1968/12/9", "1968/12/10", "1968/12/11", "1968/12/12", "1968/12/13", "1968/12/14", "1968/12/15", "1968/12/16", "1968/12/17", "1968/12/18", "1968/12/19", "1968/12/20", "1968/12/21", "1968/12/22", "1968/12/23", "1968/12/24", "1968/12/25", "1968/12/26", "1968/12/27", "1968/12/28", "1968/12/29", "1968/12/30", "1968/12/31", "1969/1/1", "1969/1/2", "1969/1/3", "1969/1/4", "1969/1/5", "1969/1/6", "1969/1/7", "1969/1/8", "1969/1/9", "1969/1/10", "1969/1/11"};
//            data = new int[]{84, 86, 80, 78, 70, 74, 81, 91, 92, 84, 90, 90, 83, 78, 82, 88, 88, 81, 89, 91, 86, 80, 84, 84, 76, 72, 73, 76, 70, 75, 83, 85, 78, 77, 80, 79, 74, 66, 64, 62, 58, 52, 50, 48, 50, 54, 49, 58, 51, 44, 45, 50, 43, 50, 45, 37, 37, 39, 32, 32, 36, 31, 29, 21, 14, 18, 21, 18, 8, 4, 5, 8, 1, -6, -2, 3, 4, -4, -13, -21, -14, -23, -13, -5, 1, -0, -9, -9, -3, -6, -2, -4, -0, -5, 3, 9, 11, 20, 23, 26};
        }

        public void add(String day, long num) {
            date.add(day);
            data.add(num);
        }
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public OrderPriceResp getOrderById(@RequestParam String key, @RequestParam int step,
                                       @RequestParam(defaultValue = "0")  long type,
                                       @RequestParam(defaultValue = "") String startRequest,
                                       @RequestParam(defaultValue = "") String shopperId

    ) {
        Time start,end;

        if(startRequest.isEmpty()){
            String startStr = "2016.1.1";
            String endStr = "2018.12.31";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            ParsePosition pos = new ParsePosition(0);
            start = new Time(sdf.parse(startStr, pos).getTime());
            pos = new ParsePosition(0);
            end = new Time(sdf.parse(endStr, pos).getTime());
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            ParsePosition pos = new ParsePosition(0);
            start = new Time(sdf.parse(startRequest, pos).getTime());
            end = new Time(start.getTime()+86400000);
        }

        OrderPriceResp orderPriceResp = new OrderPriceResp();
        Calendar iter = Calendar.getInstance();
        Calendar nextIter = Calendar.getInstance();
        Map<String, Object> params = new HashMap<>();
        for (iter.setTime(start), nextIter.setTime(start); iter.getTime().before(end); iter.setTime(nextIter.getTime())) {


            if(startRequest.isEmpty()) {
                nextIter.add(Calendar.DATE, step);
            }else{
                nextIter.add(Calendar.HOUR,step);
            }

            Date i = new Date(iter.getTime().getTime());
            Date j = new Date(nextIter.getTime().getTime());
            params.put("dateBegin", i);
            params.put("dateEnd", j);
            Long currUid = SessionUtil.getCurrUid();
            if(currUid!=null) {
                boolean isAdmin = userMapper.getUserRoleIds(currUid).contains(AppConst.USER_TYPE_ADMIN.longValue());
                if (!isAdmin) {//非管理员
                    params.put("userId", currUid);// 查自己有权限的商品
                } else if ( !shopperId.isEmpty()) {//管理员
                    params.put("userId", shopperId);
                }
            }

            long num;
            switch (key) {
                case "price":
                    num = orderMapper.totalAmountOrders(params);
                    break;
                case "count":
                    num = orderMapper.totalQuantityOrders(params);
                    break;
                case "priceByShoper":
                    num = orderMapper.totalQuantityOrders(params);//todo
                    break;
                case "countByShoper":
                    num = orderMapper.totalQuantityOrders(params);//todo
                    break;
                default:
                    num = 0;
            }
            if(startRequest.isEmpty()) {
                SimpleDateFormat outputSdf = new SimpleDateFormat("yyyy/MM/dd");
                orderPriceResp.add(outputSdf.format(iter.getTime()), num);
            }else{
                SimpleDateFormat outputSdf = new SimpleDateFormat("HH:mm:ss");
                orderPriceResp.add(outputSdf.format(iter.getTime()), num);
            }
        }


        return orderPriceResp;
    }

}
