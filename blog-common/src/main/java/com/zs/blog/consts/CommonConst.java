package com.zs.blog.consts;

/**
 * 程序中公用的常量类
 *
 * @author zshuo
 * @date 2018/10/8
 **/
public class CommonConst {

    /**
     * jwt 盐值
     */
    public static final String JWT_SECRET = "zsblog.top_salt";
    /**
     * jwt 签发者
     */
    public static final String JWT_ISSUER = "service";

    /**
     * username
     */
    public static final String USERNAME = "username";
    public static final String SHIRO_SALT = "zshuo_shiro_salt";
    public static final String ALI_OSS_IMG_ARTICLE_PATH = "img/article/";
    public static final String ALI_OSS_IMG_COVER_PATH = "img/cover/";

    /**
     * ali oss 存储目录
     */
    public static final String ALI_OSS_IMG_PATH = "img/";
    /**
     * ali oss 签名有效期(s)
     */
    public static final Integer ALI_OSS_EXPIRE = 300;
    /**
     * ali oss 文件大小(M)
     */
    public static final Integer ALI_OSS_MAX_SIZE = 10;
    /**
     * ali oss 回调地址
     */
    public static final String ALI_OSS_CALLBACK = "http://admin.zshuo.top/admin/oss/callback";
}
