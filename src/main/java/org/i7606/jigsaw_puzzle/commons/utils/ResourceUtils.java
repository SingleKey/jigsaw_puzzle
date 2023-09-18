package org.i7606.jigsaw_puzzle.commons.utils;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.i7606.jigsaw_puzzle.commons.AppConsts;
import org.i7606.jigsaw_puzzle.commons.utils.UrlUtil;


/**
 * @Author SingleKey
 * @Date 2023/9/16 17:57
 */
public class ResourceUtils {

    /**
     * 获取图片
     *
     * @param filePath
     * @param width
     * @param height
     * @return
     */
    public static Rectangle getImage(String filePath, int width, int height) {
        Image image = new Image(UrlUtil.getURLStream(filePath));
        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setFill(new ImagePattern(image));
        return rectangle;
    }

    /**
     * 获取背景图片
     *
     * @param filePath
     * @return
     */
    public static Rectangle getBackground(String filePath) {
        return getImage(filePath, AppConsts.BORDER_WIDTH, AppConsts.BORDER_HEIGHT);
    }

    /**
     * 获取背景图片
     *
     * @return
     */
    public static Rectangle getBackground() {
        Rectangle rectangle = new Rectangle(AppConsts.BORDER_WIDTH, AppConsts.BORDER_HEIGHT);
        rectangle.setFill(Color.valueOf("409eff"));
        return rectangle;
    }


}
