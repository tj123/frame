package com.github.tj123.frame.web.common;


import javax.servlet.http.HttpServletRequest;

public class ControllerUtil {


    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取上下文基础地址
     */
    public static String getBasePath(HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(request.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort()).append(request.getContextPath());
        return buffer.toString();
    }


    /**
     * 获取当前请求的端口
     */
    public static int getServerPort(HttpServletRequest request) {
        return request.getServerPort();
    }

    /**
     * 获取上下文基础地址
     */
    public static String getBasePath(HttpServletRequest request, boolean requireServerPort) {
        StringBuffer basePathBuffer = new StringBuffer();
        basePathBuffer.append(request.getScheme());
        basePathBuffer.append("://");
        basePathBuffer.append(request.getServerName());
        if (requireServerPort) {
            basePathBuffer.append(":");
            basePathBuffer.append(request.getServerPort());
        }
        basePathBuffer.append(request.getContextPath());
        basePathBuffer.append("/");
        return basePathBuffer.toString();
    }

    /**
     * 获取访问的地址
     */
    public static String getVisitUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        if (url != null && !"".equals(url.trim())) {
            if (getServerPort(request) == 80) {
                if (url.indexOf(":80") == -1) {
                    url = url.replaceAll(getBasePath(request, false), "");
                } else {
                    url = url.replaceAll(getBasePath(request, true), "");
                }
            } else if (getServerPort(request) == 443) {
                if (url.indexOf(":443") == -1) {
                    url = url.replaceAll(getBasePath(request, false), "");
                } else {
                    url = url.replaceAll(getBasePath(request, true), "");
                }
            } else {
                url = url.replaceAll(getBasePath(request, true), "");
            }
            return "/" + url.toLowerCase();
        } else {
            return "/";
        }
    }


    /**
     * 判断请求是否为 ajax 请求
     */
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }


}
