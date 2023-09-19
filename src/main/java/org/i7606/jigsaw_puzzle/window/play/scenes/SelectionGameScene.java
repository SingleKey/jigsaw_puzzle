package org.i7606.jigsaw_puzzle.window.play.scenes;

import cn.hutool.core.util.StrUtil;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.i7606.jigsaw_puzzle.commons.AppConsts;
import org.i7606.jigsaw_puzzle.commons.utils.ConfigUtil;
import org.i7606.jigsaw_puzzle.commons.utils.ImageUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author SingleKey
 * @Date 2023/9/19 15:20
 */
public class SelectionGameScene extends Scene {

    private AnchorPane anchorPane;

    private final int TEXT_IMAGE_WIDTH = 150;
    private final int TEXT_IMAGE_HEIGHT = 37;
    private final int TEXT_IMAGE_LOCATION_X = (AppConsts.WINDOW_WIDTH - TEXT_IMAGE_WIDTH) >> 1;
    private final int TEXT_IMAGE_LOCATION_Y = 50;

    /**
     * 用户选择的关卡
     */
    private String[] levels;
    private ArrayList<String> levelPreImages;
    private String userSelect;
    public SelectionGameScene() {
        super(new AnchorPane());
        anchorPane = (AnchorPane) getRoot();
        init();
    }

    private void init() {
        addTitle();
        loadConfig();
    }

    private void loadConfig() {
        HashMap<String, Object> levelsConfig = ConfigUtil.getLevelsConfig();
        levels = (String[]) levelsConfig.get("levels");
        if (levels == null || levels.length == 0) {
            throw new RuntimeException("请添加关卡资源！");
        }
        levelPreImages = new ArrayList<>();
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        anchorPane.getChildren().add(hBox);
        for (int i = 0; i < levels.length; i++) {
            if (StrUtil.isNotBlank(levels[i])) {
                String levelName = levels[i];
                HashMap<String, Object> config = ConfigUtil.getConfig("levels/" +levelName);
                String preView = (String) config.get("reference");
                String levelCnName = (String) config.get("name");
                if (StrUtil.isBlank(preView)) {
                    throw new RuntimeException("关卡" + levelCnName + "缺失参考图！");
                }
                LevelItem levelItem = new LevelItem(levelName, levelCnName, "levels/" + levelName + "/" + preView);
                levelItem.setOnMouseClicked(mouseEvent -> {
                    userSelect = levelItem.getLevelName();
                });
                hBox.getChildren().add(levelItem);
            }
        }
        hBox.setLayoutY(200);
        hBox.setLayoutX((AppConsts.WINDOW_WIDTH - 350) >> 1);
    }

    private void addTitle() {
        ImageView textImage = ImageUtil.getImageView("images/tips.png");
        textImage.setFitWidth(TEXT_IMAGE_WIDTH);
        textImage.setFitHeight(TEXT_IMAGE_HEIGHT);
        textImage.setLayoutX(TEXT_IMAGE_LOCATION_X);
        textImage.setLayoutY(TEXT_IMAGE_LOCATION_Y);
        anchorPane.getChildren().add(textImage);
    }

}
