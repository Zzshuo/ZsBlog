package com.zs.blog.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标签名
     */
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}