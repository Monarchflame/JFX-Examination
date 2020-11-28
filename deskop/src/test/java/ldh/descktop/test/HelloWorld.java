package ldh.descktop.test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloWorld extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Hello world ! ");

        Button btn = new Button();
        btn.setText("打开酷狗");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Process proc = Runtime.getRuntime().exec("D:/Program Files (x86)/kugou/KuGou.exe");
                    // D:/Tencent/WeChat/WeChat.exe
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        // 设定窗口无边框
        primaryStage.initStyle(StageStyle.UNDECORATED);
        // 窗口始终显示在其他窗口之上
//        primaryStage.setAlwaysOnTop(true);
        // 窗口最大化
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

        // 不想显示任务栏就在设置里把任务栏关掉。我也没办法了
        // 设置监听器，不断让页面在其他窗口之上，除了白名单应用
    }
}