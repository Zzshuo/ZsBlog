package com.zs.blog.util;

import com.zs.blog.holder.RequestHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author zshuo
 * @Date 2018/9/30
 **/
public class RequestUtil {
    public static String getParameters() {
        HttpServletRequest request = RequestHolder.getRequest();
        Enumeration<String> paraNames = request.getParameterNames();
        if (paraNames == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        while (paraNames.hasMoreElements()) {
            String paraName = paraNames.nextElement();
            sb.append("&").append(paraName).append("=").append(request.getParameter(paraName));
        }
        return sb.toString();
    }

    public static String getHeader(String headerName) {
        return RequestHolder.getRequest().getHeader(headerName);
    }

    public static String getReferer() {
        return getHeader("Referer");
    }

    public static String getUa() {
        return getHeader("User-Agent");
    }

    public static String getIp() {
        HttpServletRequest request = RequestHolder.getRequest();
        return IpUtil.getRealIp(request);
    }

    public static String getRequestUrl() {
        HttpServletRequest request = RequestHolder.getRequest();
        return request.getRequestURL().toString();
    }

    public static String getMethod() {
        HttpServletRequest request = RequestHolder.getRequest();
        return request.getMethod();
    }
}
