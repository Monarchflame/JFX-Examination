package com.desktop.controller;

import com.desktop.MainApplication;
import com.desktop.WinMainApp;
import com.desktop.dao.*;
import com.desktop.entity.*;
import com.desktop.ui.*;
import com.desktop.util.StudentUtil;
import com.desktop.util.ThreadToolUtil;
import com.desktop.view.MyExamView;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.felixroske.jfxsupport.FXMLController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fx.PrimaryStage;
import fx.ui.util.RegionUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author qxt
 * @date 2020/12/2 21:20
 */
@FXMLController
@Slf4j
public class LoginController implements Initializable {
    @FXML
    private AnchorPane loginPane;
    @FXML
    private JFXTextField userName;
    @FXML
    private JFXPasswordField passwordField;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SoftwareMapper softwareMapper;
    @Autowired
    private SelectSoftwareMapper selectSoftwareMapper;
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private SelectCourseMapper selectCourseMapper;
    @Autowired
    private ExamArrangementMapper examArrangementMapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * 登录
     */
    @FXML
    private void handleLoginButtonAction() {
        String studentNo = userName.getText().trim();
        String password = passwordField.getText().trim();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        String alertInfo = "";
        if (StringUtils.isEmpty(studentNo)) {
            alertInfo = "账号不能为空！";
        } else if (StringUtils.isEmpty(password)) {
            alertInfo = "密码不能为空！";
        }
        if (!StringUtils.isEmpty(alertInfo)) {
            alert.setContentText(alertInfo);
            alert.showAndWait();
            return;
        }

        StudentExample example = new StudentExample();
        example.or().andStudentNoEqualTo(studentNo);

        //验证密码
        List<Student> students = studentMapper.selectByExample(example);
        if (students.size() < 1) {
            alert.setContentText("账号错误！");
            alert.showAndWait();
            return;
        } else {
            Student student = students.get(0);
            // 登录
            if (student != null) {
                StudentUtil.student = student;
                ((Stage) userName.getScene().getWindow()).close();
                openExamWindow();
            } else {
                alert.setContentText("请输入正确的密码！");
                alert.showAndWait();
            }
        }
    }

    public void initExamStage() {
        Stage stage = new Stage();
        // 加载Windows图标
        Image image = new Image(WinMainApp.class.getResource("/images/win10.png").toExternalForm());
        DesktopNodeFactory webViewNodeFactory = () -> {
            WebView webView = new WebView();
            webView.getEngine().load("http://www.baidu.com");
            return webView;
        };

        DesktopPane desktopPane = new DesktopPane();
//        desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel("CVS浏览器", new FontAwesomeIconView(), "cvs-graphic"), () -> PageUtil.load("/fxml/Cvs.fxml")));
//        desktopPane.getChildren().add(new DesktopItem(image, "百度搜索", webViewNodeFactory));
//        desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel("Form表单样式", new FontAwesomeIconView(), "form-graphic"), () -> new FormContent()));

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

    public void addAllButton(Long studentId, DesktopPane desktopPane) {
        // 查询考试
        ExamArrangementExample examArrangementExample = new ExamArrangementExample();
        examArrangementExample.or().andStudentIdEqualTo(studentId);
        List<ExamArrangement> examArrangements = examArrangementMapper.selectByExample(examArrangementExample);
        for (ExamArrangement examArrangement : examArrangements) {
            Long examId = examArrangement.getExamId();
            Exam exam = examMapper.selectByPrimaryKey(examId);
            // 打开我的考试界面
            // 判断时间 (万一提前进去怎么办)
        }

        // 查询白名单配置

        // 查询软件

        // 添加软件按钮
        softwareMapper.selectByExample(new SoftwareExample());
        desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel("打开酷狗", new FontAwesomeIconView(), "plan-pane-graphic"), () -> new ApplicationButton("酷狗", "D:/Program Files (x86)/kugou/KuGou.exe")));

    }

    public void openExamWindow() {
        MainApplication.showView(MyExamView.class);
    }

}
