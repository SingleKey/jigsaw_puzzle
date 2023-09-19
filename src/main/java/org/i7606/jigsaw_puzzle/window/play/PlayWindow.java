package org.i7606.jigsaw_puzzle.window.play;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.i7606.jigsaw_puzzle.commons.AppConsts;
import org.i7606.jigsaw_puzzle.commons.utils.ImageUtil;
import org.i7606.jigsaw_puzzle.commons.utils.UrlUtil;
import org.i7606.jigsaw_puzzle.window.play.level.LevelBuild;
import org.i7606.jigsaw_puzzle.window.play.scenes.SelectionGameScene;

import java.io.InputStream;

/**
 * @Author SingleKey
 * @Date 2023/9/16 16:10
 */
public class PlayWindow extends Stage {

    /**
     * 图片按钮大小
     */
    private final int IMAGE_BUTTON_SIZE = 25;

    private LevelBuild factory;
    private AnchorPane anchorPane;
    private Scene scene;
    private Scene gameSelectionScene;

    public PlayWindow() {
        initWindow();
        scene = new SelectionGameScene();
        setScene(scene);
//        initHomeUi();
    }
    private void initWindow() {
        setTitle("拼图画板");
        setResizable(false);
        setHeight(AppConsts.WINDOW_HEIGHT);
        setWidth(AppConsts.WINDOW_WIDTH);
        getIcons().add(new Image(UrlUtil.getURLStream("images/icon.png")));
    }

    private void initHomeUi() {
        anchorPane = new AnchorPane();
        scene = new Scene(anchorPane);
        setScene(scene);

        ImageView homeIconView = ImageUtil.getImageView("images/home_icon.png");
        homeIconView.setFitHeight(AppConsts.HOME_ICON_SIZE);
        homeIconView.setFitWidth(AppConsts.HOME_ICON_SIZE);
        homeIconView.setLayoutX(AppConsts.HOME_ICON_LOCATION_X);
        homeIconView.setLayoutY(AppConsts.HOME_ICON_LOCATION_Y);

        final int LOCATION_Y_OFFSET = 60;
        ImageView playButton = ImageUtil.getHomeButton("images/play.png");
        playButton.setLayoutX(AppConsts.HOME_BUTTON_LOCATION_X);
        playButton.setLayoutY(AppConsts.HOME_BUTTON_LOCATION_Y);
        playButton.setOnMouseClicked(mouseEvent -> {
            // 还有选择关卡步骤，做个弹窗选择关卡...
            initGameUI();
        });

        ImageView closeButton = ImageUtil.getHomeButton("images/close.png");
        closeButton.setLayoutX(AppConsts.HOME_BUTTON_LOCATION_X);
        closeButton.setLayoutY(AppConsts.HOME_BUTTON_LOCATION_Y + LOCATION_Y_OFFSET);
        closeButton.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });

        anchorPane.getChildren().addAll(homeIconView, playButton, closeButton);
    }



    private void initGameUI() {
//        tring levelNamme = "level-001";
//        String levelNamme = "level-002";
        String levelNamme = "level-test";
        factory = new LevelBuild(levelNamme, this);
        factory.build();

        AnchorPane anchorPane = factory.getAnchorPane();

//        HBox optionButtons = new HBox();
//        optionButtons.setSpacing(10);
//        optionButtons.set

        // 添加操作按钮
        InputStream urlCloseGameStream = UrlUtil.getURLStream("images/close_game.png");
        Image closeGameImage = new Image(urlCloseGameStream);
        ImageView closeGameImageButton = new ImageView(closeGameImage);
        closeGameImageButton.setFitHeight(IMAGE_BUTTON_SIZE - 3);
        closeGameImageButton.setFitWidth(IMAGE_BUTTON_SIZE - 3);
        closeGameImageButton.setLayoutX(10);
        closeGameImageButton.setLayoutY(10);
        closeGameImageButton.setOnMouseClicked(mouseEvent -> {
            setScene(scene);
            factory = null;
            System.gc();
        });

        // 添加操作按钮
        InputStream urlResetStream = UrlUtil.getURLStream("images/reset.png");
        Image image = new Image(urlResetStream);
        ImageView resetButton = new ImageView(image);
        resetButton.setFitHeight(IMAGE_BUTTON_SIZE - 3);
        resetButton.setFitWidth(IMAGE_BUTTON_SIZE - 3);
        resetButton.setLayoutX(AppConsts.BORDER_START_X + 230);
        resetButton.setLayoutY(AppConsts.BORDER_START_Y - 30);
        resetButton.setOnMouseClicked(mouseEvent -> {
            initGameUI();
            // 自动调用时是根据当前内存使用情况决定，所以需要手动调用，JDK20
            System.gc();
        });

        // 读取提示图片，将提示图片显示和隐藏
        InputStream urlPromptStream = UrlUtil.getURLStream("images/prompt.png");
        Image promptImage = new Image(urlPromptStream);
        ImageView promptButton = new ImageView(promptImage);
        promptButton.setFitHeight(IMAGE_BUTTON_SIZE);
        promptButton.setFitWidth(IMAGE_BUTTON_SIZE);
        promptButton.setLayoutX(AppConsts.BORDER_START_X + 270);
        promptButton.setLayoutY(AppConsts.BORDER_START_Y - 31.5);
        promptButton.setOnMousePressed(mouseEvent -> {
            factory.getReferenceImage().setVisible(true);
        });
        promptButton.setOnMouseReleased(mouseEvent -> {
            factory.getReferenceImage().setVisible(false);
        });

        anchorPane.getChildren()
                .addAll(closeGameImageButton, resetButton, promptButton);
    }

}
