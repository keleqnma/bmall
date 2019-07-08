package com.cyq.bmall.model;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
* @author: cyq
* @date: 7/8/2019
* @description:
*/
@Data
public class Order {
    private Long id;
    private Long userId;
    private Date issueTime;
    private String address;
    private String state;

    @TableField(exist = false)
    private List<Long> commodityIds;

    @TableField(exist = false)
    private List<String> commodityNames;
}
