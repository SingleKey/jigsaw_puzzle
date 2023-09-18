package org.i7606.jigsaw_puzzle.window.play.level;

import cn.hutool.core.util.StrUtil;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.i7606.jigsaw_puzzle.commons.AppConsts;
import org.i7606.jigsaw_puzzle.commons.utils.ConfigUtil;
import org.i7606.jigsaw_puzzle.commons.utils.UrlUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @Author SingleKey
 * @Date 2023/9/17 14:47
 */
public class LevelBuild {
    final int OFFSET1 = 6;
    final int OFFSET2 = 1;
    private final Stage stage;
    private String configPath;
    private String[] answer;
    private String deletion;
    private String name;
    private String reference;
    private HashMap<Group, String> imagesMap = new HashMap<>();
    private HashMap<String, Group> gameMap = new HashMap<>();
    private ArrayList<Group> entitys = new ArrayList<>();
    private String[][] checkerboard;
    // 棋盘空坐标
    private int emptyX = 0;
    private int emptyY = 0;

    // 窗口组件
    private AnchorPane anchorPane;
    // 参考图
    private ImageView referenceImage;

    public LevelBuild(String levelName, Stage stage) {
        this.configPath = "levels/" + levelName;
        this.stage = stage;
        loadResource();
    }

//    @Override
//    protected void finalize() throws Throwable {
//        super.finalize();
//        System.out.println(System.currentTimeMillis());
//    }

    public void build() {
        anchorPane = new AnchorPane();

        // 设置名称和重置按钮等
        Label label = new Label(name);
        Font font = new Font(15);
        label.setFont(font);
        label.setLayoutY(AppConsts.BORDER_START_Y - 30);
        label.setLayoutX(AppConsts.BORDER_START_X);
        anchorPane.getChildren().add(label);

        // 添加边框
        ImageView borderImage = new ImageView(new Image(UrlUtil.getURLStream("images/border.png")));
        borderImage.setFitHeight(AppConsts.BORDER_SIZE);
        borderImage.setFitWidth(AppConsts.BORDER_SIZE);
        borderImage.setLayoutX(AppConsts.BORDER_START_X);
        borderImage.setLayoutY(AppConsts.BORDER_START_Y);

        // 添加提示
        final double REFERENCE_IMAGE_SIZE_OFFSET = 12;
        final double REFERENCE_IMAGE_LOCATION_OFFSET = 6;
        referenceImage = new ImageView(new Image(UrlUtil.getURLStream(configPath + "/" + reference)));
        referenceImage.setFitHeight(AppConsts.BORDER_SIZE - REFERENCE_IMAGE_SIZE_OFFSET);
        referenceImage.setFitWidth(AppConsts.BORDER_SIZE - REFERENCE_IMAGE_SIZE_OFFSET);
        referenceImage.setLayoutX(AppConsts.BORDER_START_X + REFERENCE_IMAGE_LOCATION_OFFSET);
        referenceImage.setLayoutY(AppConsts.BORDER_START_Y + REFERENCE_IMAGE_LOCATION_OFFSET);
        // 设置不可见，需要按住按钮才可见
        referenceImage.setVisible(false);

        anchorPane.getChildren().add(borderImage);

        for (int i = 0; i < entitys.size(); i++) {
            Group group = entitys.get(i);
            if (group.getLayoutX() == 0 || group.getLayoutY() == 0) {
                continue;
            }
            anchorPane.getChildren().add(group);
        }

        // 最后添加，不然会被小方块覆盖
        anchorPane.getChildren().add(referenceImage);

        stage.setScene(new Scene(anchorPane));
    }

    private void loadResource() {
        HashMap<String, Object> config = ConfigUtil.getConfig(configPath);
        answer = (String[]) config.get("answer");
        deletion = (String) config.get("deletion");
        name = (String) config.get("name");
        reference = (String) config.get("reference");

        // 创建棋盘
        createCheckerboard((Integer) config.get("num"));
        // 加载资源
        loadImages();
        // 布局
        setLayout();
    }
    private void setLayout() {
        // 洗牌
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < answer.length; i++) {
            if ( StrUtil.isBlank(deletion) || ! deletion.equals(answer[i])) {
                names.add(answer[i]);
            } else {
                names.add(null);
            }
        }
        Collections.shuffle(names);
        int n = 0;
        for (int i = 0; i < checkerboard.length; i++) {
            for (int i1 = 0; i1 < checkerboard[i].length; i1++, ++ n) {
                checkerboard[i][i1] = names.get(n);
                // 设置位置
                int currentEntityX = AppConsts.BORDER_START_X + OFFSET1 + ((AppConsts.CARD_SIZE + OFFSET2) * i1);
                int currentEntityY = AppConsts.BORDER_START_Y + OFFSET1 + ((AppConsts.CARD_SIZE + OFFSET2) * i);
                Group group = gameMap.get(checkerboard[i][i1]);
                if (group == null) {
                    emptyX = i1;
                    emptyY = i;
                    continue;
                }
//                System.out.println(currentEntityX + ", " + currentEntityY);
                group.setLayoutX(currentEntityX);
                group.setLayoutY(currentEntityY);
            }
        }
    }

    private void loadImages() {
        String imagePath = null;
        for (int i = 0; i < answer.length; i++) {
            imagePath = configPath + "/" + answer[i];
            InputStream urlStream = UrlUtil.getURLStream(imagePath);
            Image bufferedImage = new Image(urlStream);
            Group group = new Group();
            ImageView imageView = new ImageView(bufferedImage);
            imageView.setFitWidth(AppConsts.CARD_SIZE);
            imageView.setFitHeight(AppConsts.CARD_SIZE);
            imageView.setOnMouseClicked(mouseEvent -> {
                move(group);
                if (isWin()) {
                    System.out.println("你赢了！");
                }
            });
            group.getChildren().add(imageView);
            imagesMap.put(group, answer[i]);
            gameMap.put(answer[i], group);
            entitys.add(group);
        }
    }

    private void createCheckerboard(int num) {
        if (num == 9) {
            checkerboard = new String[3][3];
        }
        if (num == 16) {
            checkerboard = new String[4][4];
        }
    }

    public void move(Group group) {
        String name = imagesMap.get(group);
        int curX = -1;
        int curY = -1;
        for (int i = 0; i < checkerboard.length; i++) {
            for (int i1 = 0; i1 < checkerboard[i].length; i1++) {
                if (name.equals(checkerboard[i][i1])) {
                    curX = i1;
                    curY = i;
                    break;
                }
            }
        }
        if (curX == -1 || curY == -1) {
            System.out.println(curX + "-" + curY);
            return;
        }
        // 判断是否在同一行或者同一列
        if (curX != emptyX && curY != emptyY) {
            return;
        }
        // 判断是否相邻
        if (Math.abs(curX - emptyX) != 1 && Math.abs(curY - emptyY) != 1) {
            return;
        }
        // 动画
        final double OFFSET3 = 24.2;
        double sEntityX = ((AppConsts.BORDER_START_X + OFFSET1 + ((AppConsts.CARD_SIZE + OFFSET2) * curX)) >> 1) + OFFSET3;
        double sEntityY = ((AppConsts.BORDER_START_Y + OFFSET1 + ((AppConsts.CARD_SIZE + OFFSET2) * curY)) >> 1)+ OFFSET3;

        double nEntityX = ((AppConsts.BORDER_START_X + OFFSET1 + ((AppConsts.CARD_SIZE + OFFSET2) * emptyX)) >> 1) + OFFSET3;
        double nEntityY = ((AppConsts.BORDER_START_Y + OFFSET1 + ((AppConsts.CARD_SIZE + OFFSET2) * emptyY)) >> 1) + OFFSET3;

        Path path = new Path();
        path.getElements().add(new MoveTo(sEntityX, sEntityY));
        path.getElements().add(new LineTo(nEntityX, nEntityY));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(group);
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.millis(200));
        pathTransition.play();

        // 交换
        checkerboard[emptyY][emptyX] = checkerboard[curY][curX];
        checkerboard[curY][curX] = null;
        emptyX = curX;
        emptyY = curY;

        group.setLayoutX(nEntityX);
        group.setLayoutY(nEntityY);
    }

    /**
     * 判断是否胜利
     */
    public boolean isWin() {
        int n = 0;
        for (int i = 0; i < checkerboard.length; i++) {
            for (int i1 = 0; i1 < checkerboard.length; i1++, ++ n) {
                if (checkerboard[i][i1] == null) {
                    if (deletion.equals(answer[n])) {
                        continue;
                    } else {
                        return false;
                    }
                }
                if (! answer[n].equals(checkerboard[i][i1])) {
                    return false;
                }
            }
        }
        return true;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public ImageView getReferenceImage() {
        return referenceImage;
    }
}
