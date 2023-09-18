package org.i7606.jigsaw_puzzle.window.play;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.i7606.jigsaw_puzzle.commons.AppConsts;
import org.i7606.jigsaw_puzzle.commons.utils.UrlUtil;
import org.i7606.jigsaw_puzzle.window.play.level.LevelBuild;

import java.io.InputStream;

/**
 * @Author SingleKey
 * @Date 2023/9/16 16:10
 */
public class PlayWindow extends Stage {

    private LevelBuild factory;

    public PlayWindow() {
        initWindow();
        manage();
        setUi();
    }

    private void setUi() {
        AnchorPane anchorPane = factory.getAnchorPane();
        // 添加操作按钮
        InputStream urlResetStream = UrlUtil.getURLStream("images/reset.png");
        Image image = new Image(urlResetStream);
        ImageView resetButton = new ImageView(image);
        resetButton.setFitHeight(30);
        resetButton.setFitWidth(30);
        resetButton.setLayoutX(AppConsts.BORDER_START_X + 200);
        resetButton.setLayoutY(AppConsts.BORDER_START_Y - 35);
        resetButton.setOnMouseClicked(mouseEvent -> {
            manage();
            setUi();
            // 自动调用时是根据当前内存使用情况决定，所以需要手动调用，JDK20
            System.gc();
        });

        // 读取提示图片，将提示图片显示和隐藏
        InputStream urlPromptStream = UrlUtil.getURLStream("images/prompt.png");
        Image promptImage = new Image(urlPromptStream);
        ImageView promptButton = new ImageView(promptImage);
        promptButton.setFitHeight(30);
        promptButton.setFitWidth(30);
        promptButton.setLayoutX(AppConsts.BORDER_START_X + 260);
        promptButton.setLayoutY(AppConsts.BORDER_START_Y - 35);
        promptButton.setOnMouseClicked(mouseEvent -> {

        });

        anchorPane.getChildren().add(resetButton);
        anchorPane.getChildren().add(promptButton);
    }

    private void initWindow() {
        setTitle("拼图画板");
        setResizable(false);
        setHeight(AppConsts.WINDOW_HEIGHT);
        setWidth(AppConsts.WINDOW_WIDTH);
    }

    private void manage() {
        factory = new LevelBuild("level-001", this);
        factory.build();
    }

}
