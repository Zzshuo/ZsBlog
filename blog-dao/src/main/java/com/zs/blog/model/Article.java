package com.zs.blog.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Article {
    @Id
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否原创
     */
    private Byte original;

    /**
     * 类型
     */
    @Column(name = "type_id")
    private Byte typeId;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 文章简介，最多200字
     */
    private String description;

    /**
     * 文章关键字，优化搜索
     */
    private String keywords;

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
     * 文章内容
     */
    private String content;

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
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取是否原创
     *
     * @return original - 是否原创
     */
    public Byte getOriginal() {
        return original;
    }

    /**
     * 设置是否原创
     *
     * @param original 是否原创
     */
    public void setOriginal(Byte original) {
        this.original = original;
    }

    /**
     * 获取类型
     *
     * @return type_id - 类型
     */
    public Byte getTypeId() {
        return typeId;
    }

    /**
     * 设置类型
     *
     * @param typeId 类型
     */
    public void setTypeId(Byte typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取文章简介，最多200字
     *
     * @return description - 文章简介，最多200字
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置文章简介，最多200字
     *
     * @param description 文章简介，最多200字
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取文章关键字，优化搜索
     *
     * @return keywords - 文章关键字，优化搜索
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置文章关键字，优化搜索
     *
     * @param keywords 文章关键字，优化搜索
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
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

    /**
     * 获取文章内容
     *
     * @return content - 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文章内容
     *
     * @param content 文章内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}