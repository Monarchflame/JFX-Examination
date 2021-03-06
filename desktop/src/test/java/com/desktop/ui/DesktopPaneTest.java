package com.desktop.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by ldh on 2018/1/15.
 */
public class DesktopPaneTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        DesktopPane desktopPane = new DesktopPane();
        Scene scene = new Scene(desktopPane, 800, 500);
        scene.getStylesheets().add("/css/deskpane.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("demo");
        primaryStage.show();
    }
}
