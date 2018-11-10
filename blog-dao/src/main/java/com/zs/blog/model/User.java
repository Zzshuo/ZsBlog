package com.zs.blog.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class User {
    @Id
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * roleId
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * QQ
     */
    private String qq;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别
     */
    private String gender;

    /**
     * 头像地址
     */
    private String picture;

    /**
     * 注册IP
     */
    @Column(name = "reg_ip")
    private String regIp;

    /**
     * 最近登录IP
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最近登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

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

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取登录密码
     *
     * @return password - 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登录密码
     *
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取roleId
     *
     * @return role_id - roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置roleId
     *
     * @param roleId roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取邮箱地址
     *
     * @return email - 邮箱地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱地址
     *
     * @param email 邮箱地址
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取QQ
     *
     * @return qq - QQ
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置QQ
     *
     * @param qq QQ
     */
    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    /**
     * 获取生日
     *
     * @return birthday - 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取性别
     *
     * @return gender - 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * 获取头像地址
     *
     * @return picture - 头像地址
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置头像地址
     *
     * @param picture 头像地址
     */
    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    /**
     * 获取注册IP
     *
     * @return reg_ip - 注册IP
     */
    public String getRegIp() {
        return regIp;
    }

    /**
     * 设置注册IP
     *
     * @param regIp 注册IP
     */
    public void setRegIp(String regIp) {
        this.regIp = regIp == null ? null : regIp.trim();
    }

    /**
     * 获取最近登录IP
     *
     * @return last_login_ip - 最近登录IP
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 设置最近登录IP
     *
     * @param lastLoginIp 最近登录IP
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    /**
     * 获取最近登录时间
     *
     * @return last_login_time - 最近登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最近登录时间
     *
     * @param lastLoginTime 最近登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取1:正常,2:删除
     *
     * @return state - 1:正常,2:删除
     */
    public Byte getState() {
        return state;
    }

    /**
     * 设置1:正常,2:删除
     *
     * @param state 1:正常,2:删除
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}