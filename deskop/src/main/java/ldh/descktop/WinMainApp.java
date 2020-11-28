package ldh.descktop;

import com.jfoenix.controls.JFXDecorator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.StageStyle;
import ldh.descktop.page.AnimationPane;
import ldh.descktop.page.FormContent;
import ldh.descktop.ui.*;
import ldh.fx.ui.util.PageUtil;
import ldh.fx.ui.util.RegionUtil;

import java.io.IOException;

/**
 * @author qixiaotian
 * @date 2020/11/7
 */
public class WinMainApp extends AbstractMainApp {

    @Override
    public void start() throws Exception {
        // 加载Windows图标
        Image image = new Image(WinMainApp.class.getResource("/images/win10.png").toExternalForm());
        //
        DesktopNodeFactory webViewNodeFactory = () -> {
            WebView webView = new WebView();
            webView.getEngine().load("http://www.baidu.com");
            return webView;
        };

        DesktopPane desktopPane = new DesktopPane();
        desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel("Home后台", new FontAwesomeIconView(), "home-graphic"), () -> PageUtil.load("/fxml/Home.fxml")));
        desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel("CVS浏览器", new FontAwesomeIconView(), "cvs-graphic"), () -> PageUtil.load("/fxml/Cvs.fxml")));
        desktopPane.getChildren().add(new DesktopItem(image, "百度搜索", webViewNodeFactory));
        desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel("Form表单样式", new FontAwesomeIconView(), "form-graphic"), () -> new FormContent()));
        desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel("动画面板", new FontAwesomeIconView(), "plan-pane-graphic"), () -> new AnimationPane()));
        desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel("测试", new FontAwesomeIconView(), "plan-pane-graphic"), () -> buildPane()));

        desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel("打开酷狗", new FontAwesomeIconView(), "plan-pane-graphic"), () -> buildButton()));

        DesktopToolbar toolbar = new WinDesktopToolbar(desktopPane);
        WinDesktop desktop = new WinDesktop(desktopPane, toolbar);

        JFXDecorator jfxDecorator = new JFXDecorator(stage, desktop);
        Scene scene = new Scene(jfxDecorator, 1200, 700);
        scene.getStylesheets().add(this.getClass().getResource("/css/win10.css").toExternalForm());
        scene.getStylesheets().add(this.getClass().getResource("/css/Common.css").toExternalForm());
//        scene.getStylesheets().add("/component/LDialog.css");
        scene.getStylesheets().add("/css/fxdialog.css");
//        scene.getStylesheets().add("/component/LxDialog.css");
        stage.setScene(scene);
        stage.setTitle("Win10桌面");

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setMaximized(true);
        stage.show();
    }

    private Pane buildPane() {
        VBox vbox = new VBox();
        vbox.getChildren().add(new Label("tste"));
        vbox.getChildren().add(new TextField("tste"));
        vbox.getChildren().add(new Label("tste"));
        return vbox;
    }

    private Button buildButton(){
        Button btn = new Button();
        btn.setText("打开酷狗");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Process proc = Runtime.getRuntime().exec("D:/Program Files (x86)/kugou/KuGou.exe");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return btn;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
