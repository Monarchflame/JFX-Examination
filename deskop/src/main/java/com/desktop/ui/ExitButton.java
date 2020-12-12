package com.desktop.ui;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;

import java.util.List;
import java.util.Optional;

/**
 * @author qxt
 * @date 2020/12/12 21:44
 */
public class ExitButton extends Button {

    public ExitButton(String name) {
        this.setText(name);
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION); // 创建一个确认对话框
                alert.setHeaderText("提示"); // 设置对话框的头部文本
                // 设置对话框的内容文本
                alert.setContentText("确定退出考试吗？");
                // 显示对话框，并等待按钮返回
                Optional<ButtonType> buttonType = alert.showAndWait();
                // 判断返回的按钮类型是确定还是取消，再据此分别进一步处理
                if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) { // 单击了确定按钮OK_DONE
                    System.exit(0);
                }
            }
        });
    }
}
