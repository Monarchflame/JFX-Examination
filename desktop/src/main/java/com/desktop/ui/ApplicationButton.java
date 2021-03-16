package com.desktop.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * 桌面按钮，调用本地软件
 * @author desktop
 * @date 2020/12/2 12:00
 */
public class ApplicationButton extends Button {

    public ApplicationButton(String name, String path) {
        this.setText(name);
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Runtime.getRuntime().exec(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
