package org.i7606.jigsaw_puzzle.window.play;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * @Author SingleKey
 * @Date 2023/9/18 21:59
 */
public class HappyWindow extends Stage {

    private Stage parentStage;

    public HappyWindow(String title, String msg, Stage parentStage) {
        this.parentStage = parentStage;
        initWindow();
        addMsgView(title, msg);
    }

    private void addMsgView(String title, String msg) {
        setTitle(title);

        Label msgLabel = new Label(msg);
        msgLabel.setFont(new Font(20));
        msgLabel.setLayoutX(10);
        msgLabel.setLayoutY(10);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(msgLabel);

        setScene(new Scene(anchorPane));
    }

    private void initWindow() {
        setHeight(100);
        setWidth(300);
        setResizable(false);
        setAlwaysOnTop(true);
        initModality(Modality.WINDOW_MODAL);
        initOwner(parentStage);
    }


}
