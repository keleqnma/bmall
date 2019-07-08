package com.cyq.bmall.model;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.util.List;

/**
 * @author CYQ
 * @date $[DATE]
 * @description
 */
@Data
public class Shop extends User{
    @TableField(exist = false)
    private List<Long> commodityIds;
    @TableField(exist = false)
    private List<String> commodityNames;


}
