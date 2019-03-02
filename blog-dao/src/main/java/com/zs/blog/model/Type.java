package com.zs.blog.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2019/03/02
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    @Id
    private Integer id;

    /**
     * 文章类型名
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
}