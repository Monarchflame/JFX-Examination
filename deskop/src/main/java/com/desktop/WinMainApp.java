package com.desktop;

import com.desktop.dao.SoftwareMapper;
import com.desktop.entity.Software;
import com.desktop.entity.SoftwareExample;
import com.desktop.page.FormContent;
import com.desktop.ui.*;
import com.jfoenix.controls.JFXDecorator;
import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fx.ui.util.PageUtil;
import fx.ui.util.RegionUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import com.desktop.util.ThreadToolUtil;
import fx.PrimaryStage;
import fx.util.DialogUtil;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qxt
 * @date 2020/11/25
 */
@Component
public class WinMainApp extends Application {

    private Stage stage;
    private DesktopPane desktopPane;

    @Autowired
    private SoftwareMapper softwareMapper;

    @Override
    public void start(Stage primaryStage) throws Exception {
        PrimaryStage.STAGE = primaryStage;
        this.stage = primaryStage;

        // 加载Windows图标
        Image image = new Image(WinMainApp.class.getResource("/images/win10.png").toExternalForm());
        DesktopNodeFactory webViewNodeFactory = () -> {
            WebView webView = new WebView();
            webView.getEngine().load("http://www.baidu.com");
            return webView;
        };

        desktopPane = new DesktopPane();
        desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel("CVS浏览器", new FontAwesomeIconView(), "cvs-graphic"), () -> PageUtil.load("/fxml/Cvs.fxml")));
        desktopPane.getChildren().add(new DesktopItem(image, "百度搜索", webViewNodeFactory));
        desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel("Form表单样式", new FontAwesomeIconView(), "form-graphic"), () -> new FormContent()));
        addAllButton(desktopPane);

        DesktopToolbar toolbar = new WinDesktopToolbar(desktopPane);
        WinDesktop desktop = new WinDesktop(desktopPane, toolbar);

        JFXDecorator jfxDecorator = new JFXDecorator(stage, desktop);
        Scene scene = new Scene(jfxDecorator, 1200, 700);
        scene.getStylesheets().add(this.getClass().getResource("/css/win10.css").toExternalForm());
        scene.getStylesheets().add(this.getClass().getResource("/css/Common.css").toExternalForm());
        scene.getStylesheets().add("/css/fxdialog.css");
        stage.setScene(scene);
        stage.setTitle("Win10桌面");

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setMaximized(true);
        stage.show();
        stage.setOnCloseRequest(e -> {
            PrimaryStage.closeAllNewStages();
            ThreadToolUtil.close();
        });
    }

    public void addAllButton(DesktopPane desktopPane) {
        List<Software> softwareList = softwareMapper.selectByExample(new SoftwareExample());
        for (Software software : softwareList) {
            desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel(software.getName(), new FontAwesomeIconView(), "plan-pane-graphic"),
                    () -> new ApplicationButton(software.getName(), software.getPath())));
        }
    }
}
