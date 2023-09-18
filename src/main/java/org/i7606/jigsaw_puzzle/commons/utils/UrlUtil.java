package org.i7606.jigsaw_puzzle.commons.utils;

import java.io.InputStream;
import java.net.URL;

/**
 * URL工具类
 */
public class UrlUtil {


    /**
     * 根据相对路径获取对应URL（禁止用于加载图片）
     *
     * @param path
     * @return
     */
    public static URL getURL(String path) {
        URL resource = UrlUtil.class.getClassLoader().getResource(path);
        if(resource == null) {
            return null;
        }
        return resource;
    }

    /**
     * 根据相对路径获取对应文件流（用于加载图片）
     *
     * @param path
     * @return
     */
    public static InputStream getURLStream(String path) {
        InputStream resourceAsStream = UrlUtil.class.getClassLoader().getResourceAsStream(path);
        if(resourceAsStream == null) {
            throw new RuntimeException("无法找到该文件:" + path);
        }
        return resourceAsStream;
    }

}
