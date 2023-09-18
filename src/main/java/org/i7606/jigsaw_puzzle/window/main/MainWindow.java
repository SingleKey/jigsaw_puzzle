package org.i7606.jigsaw_puzzle.window.main;

import javafx.application.Application;
import javafx.stage.Stage;
import org.i7606.jigsaw_puzzle.window.play.PlayWindow;

/**
 * @Author SingleKey
 * @Date 2023/9/16 16:04
 */
public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        PlayWindow playWindow = new PlayWindow();
        playWindow.show();
    }
}
