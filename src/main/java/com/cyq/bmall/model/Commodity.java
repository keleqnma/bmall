package com.cyq.bmall.model;

import java.util.Date;
import lombok.Data;

/**
 * @author CYQ
 * @date $[DATE]
 * @description
 */
@Data
public class Commodity implements java.io.Serializable{
    private Long id;
    private String name;
    private Date createTime;
    private Date modifyTime;
    private double price;
    private String description;
    private String categoryId;
    private String shopId;
    private int exist;
}
