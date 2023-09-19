package org.i7606.jigsaw_puzzle.window.main;

import javafx.application.Application;
import javafx.stage.Stage;
import org.i7606.jigsaw_puzzle.window.play.PlayWindow;

import java.util.Arrays;

/**
 * @Author SingleKey
 * @Date 2023/9/16 16:04
 */
public class MainWindow extends Application {

    @Override
    public void start(Stage stage) {
        PlayWindow playWindow = new PlayWindow();
        playWindow.show();
    }

    /**
     * TODO：该main方法是给Graalvm打包使用，使用jar打包请勿使用该类作为启动类
     * @param args
     */
    public static void main(String[] args) {
        launch(MainWindow.class);
    }
}
