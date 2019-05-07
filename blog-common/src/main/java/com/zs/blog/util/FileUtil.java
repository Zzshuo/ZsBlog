package com.zs.blog.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;

/**
 * @author zshuo
 * @date 2019-05-07
 **/
public class FileUtil {
    private static final String[] PICTURE_SUFFIXS = {".jpg", ".jpeg", ".png", ".gif", ".bmp", ".svg"};

    /**
     * 判断是否是图片
     *
     * @param suffix
     * @return
     */
    public static boolean isPicture(String suffix) {
        return !StringUtils.isEmpty(suffix) && Arrays.asList(PICTURE_SUFFIXS).contains(suffix.toLowerCase());
    }

    /**
     * 获取文件后缀名
     *
     * @param file
     * @return
     */
    public static String getSuffix(File file) {
        return getSuffix(file.getName());
    }

    /**
     * 获取文件后缀名
     *
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName) {
        int index = fileName.lastIndexOf(".");
        index = -1 == index ? fileName.length() : index;
        return fileName.substring(index);
    }


    /**
     * 检查文件大小
     *
     * @return
     */
    public static boolean checkFileSize(MultipartFile file, int size, String unit) {
        return checkFileSize(file.getSize(), size, unit);
    }

    /**
     * 检查文件大小
     *
     * @return
     */
    public static boolean checkFileSize(File file, int size, String unit) {
        return checkFileSize(file.length(), size, unit);
    }

    /**
     * 检查文件大小
     *
     * @return
     */
    public static boolean checkFileSize(long len, int size, String unit) {
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }
}
