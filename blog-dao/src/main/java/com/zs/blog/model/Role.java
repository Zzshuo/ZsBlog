package com.zs.blog.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2019/03/05
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    private Integer id;

    /**
     * 角色
     */
    private String role;

    /**
     * 角色名称
     */
    private String rolename;

    /**
     * 权限
     */
    private String permission;

    /**
     * 1:正常,2:删除
     */
    private Byte state;

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