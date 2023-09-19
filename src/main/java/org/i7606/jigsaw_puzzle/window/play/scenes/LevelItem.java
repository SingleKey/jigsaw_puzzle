package org.i7606.jigsaw_puzzle.window.play.scenes;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.i7606.jigsaw_puzzle.commons.utils.ImageUtil;

/**
 * @Author SingleKey
 * @Date 2023/9/20 0:40
 */
public class LevelItem extends AnchorPane {
    private final int BORDER_SIZE = 100;
    private ImageView imageView;
    private Label nameLabel;
    private String levelName;

    public LevelItem(String levelName, String name, String path) {
        this.levelName = levelName;
        nameLabel = new Label(name);
        nameLabel.setLayoutX(25);
        nameLabel.setLayoutY(0);

        ImageView border = ImageUtil.getImageView("images/border.png");
        border.setFitWidth(BORDER_SIZE);
        border.setFitHeight(BORDER_SIZE);
        border.setLayoutY(30);

        imageView = ImageUtil.getImageView(path);
        imageView.setFitWidth(96);
        imageView.setFitHeight(96);
        imageView.setLayoutY(32);
        imageView.setLayoutX(2);

        getChildren().addAll(nameLabel, border, imageView);

    }

    public String getLevelName() {
        return levelName;
    }
}
