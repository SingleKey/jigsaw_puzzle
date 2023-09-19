package org.i7606.jigsaw_puzzle.commons.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.i7606.jigsaw_puzzle.commons.AppConsts;

import java.io.InputStream;

/**
 * @Author SingleKey
 * @Date 2023/9/19 15:59
 */
public class ImageUtil {

    public static ImageView getHomeButton(String imagePath) {
        ImageView imageView = getImageView(imagePath);
        imageView.setFitWidth(AppConsts.HOME_BUTTON_WIDTH);
        imageView.setFitHeight(AppConsts.HOME_BUTTON_HEIGHT);
        return imageView;
    }

    public static ImageView getImageView(String imagePath) {
        InputStream urlStream = UrlUtil.getURLStream(imagePath);
        Image image = new Image(urlStream);
        return new ImageView(image);
    }

}
