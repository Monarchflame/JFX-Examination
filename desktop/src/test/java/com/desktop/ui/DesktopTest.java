package com.desktop.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import com.desktop.page.FormContent;
import com.desktop.util.ThreadToolUtil;
import fx.PrimaryStage;

/**
 * Created by ldh on 2018/1/11.
 */
public class DesktopTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        PrimaryStage.STAGE = primaryStage;
        Image image = new Image(DesktopTest.class.getResource("/img/win10.png").toExternalForm());
        DesktopNodeFactory nodeFactory = () -> {WebView webView = new WebView(); webView.getEngine().load("http://www.baidu.com"); return webView;};


        DesktopPane desktopPane = new DesktopPane();
        desktopPane.getStyleClass().add("desktop");
//        desktop.setPadding(new Insets(20));
        desktopPane.getChildren().add(new DesktopItem(image, "Win10-UI官网", nodeFactory));
        desktopPane.getChildren().add(new DesktopItem(image, "Win10-UI官网", nodeFactory));
        desktopPane.getChildren().add(new DesktopItem(image, "Win10-UI官网", nodeFactory));
        desktopPane.getChildren().add(new DesktopItem(image, "Win10-UI官网", nodeFactory));
        desktopPane.getChildren().add(new DesktopItem(image, "Win10-UI官网", nodeFactory));
        desktopPane.getChildren().add(new DesktopItem(image, "Win10-UI官网", nodeFactory));
        desktopPane.getChildren().add(new DesktopItem(image, "Win10-UI官网", nodeFactory));
        desktopPane.getChildren().add(new DesktopItem(image, "Win10-UI官网", nodeFactory));
        desktopPane.getChildren().add(new DesktopItem(image, "Win10-UI官网", nodeFactory));
        desktopPane.getChildren().add(new DesktopItem(image, "Win10-UI官网", nodeFactory));
        desktopPane.getChildren().add(new DesktopItem(image, "Win10-UI官网", nodeFactory));
        desktopPane.getChildren().add(new DesktopItem(image, "Win10-UI官网", nodeFactory));
        desktopPane.getChildren().add(new DesktopItem(image, "Win10-UI官网", nodeFactory));
        desktopPane.getChildren().add(new DesktopItem(image, "Win10-UI官网", nodeFactory));
        desktopPane.getChildren().add(new DesktopItem(image, "Win10-UI官网", nodeFactory));
        desktopPane.getChildren().add(new DesktopItem(image, "Form表单样式", ()->new FormContent()));

        DesktopToolbar toolbar = new WinDesktopToolbar(desktopPane);
        WinDesktop desktop = new WinDesktop(desktopPane, toolbar);

        Scene scene = new Scene(desktop, 1200, 700);
        scene.getStylesheets().add(this.getClass().getResource("/css/win10.css").toExternalForm());
        scene.getStylesheets().add(this.getClass().getResource("/component/LxDialog.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("demo");
        primaryStage.setOnCloseRequest(e->{
            ThreadToolUtil.close();
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
