package com.zs.blog.model;

import java.util.Date;
import javax.persistence.*;

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
    private Boolean original;

    /**
     * 类型
     */
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 文章封面图片
     */
    private String image;

    /**
     * 1:正常,2:删除,3:草稿
     */
    private Integer state;

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
    public Boolean getOriginal() {
        return original;
    }

    /**
     * 设置是否原创
     *
     * @param original 是否原创
     */
    public void setOriginal(Boolean original) {
        this.original = original;
    }

    /**
     * 获取类型
     *
     * @return type_id - 类型
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置类型
     *
     * @param typeId 类型
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取文章封面图片
     *
     * @return image - 文章封面图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置文章封面图片
     *
     * @param image 文章封面图片
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * 获取1:正常,2:删除,3:草稿
     *
     * @return state - 1:正常,2:删除,3:草稿
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置1:正常,2:删除,3:草稿
     *
     * @param state 1:正常,2:删除,3:草稿
     */
    public void setState(Integer state) {
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