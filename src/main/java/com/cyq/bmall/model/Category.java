package com.cyq.bmall.model;

/**
 * @author CYQ
 * @date $[DATE]
 * @description
 */
import lombok.Data;

@Data
public class Category implements java.io.Serializable {
    private Long id;
    private String name;
    private Long pid;
    private String pname;
    private String remark;
    private Integer seq;
}
