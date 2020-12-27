package com.desktop.controller;

import com.desktop.MainApplication;
import com.desktop.dao.*;
import com.desktop.entity.Student;
import com.desktop.entity.StudentExample;
import com.desktop.monitor.MonitorTread;
import com.desktop.util.Constant;
import com.desktop.view.MyExamView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    private JFXTextField userNameField;
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
    private void login() {
        String studentNo = userNameField.getText().trim();
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
                Constant.student = student;
                ((Stage) userNameField.getScene().getWindow()).close();
                openExamListStage();
                connectToTeacher();
            } else {
                alert.setContentText("请输入正确的密码！");
                alert.showAndWait();
            }
        }
    }

    private void openExamListStage() {
        MainApplication.showView(MyExamView.class);
    }

    /**
     * 连接到教师端
     */
    private void connectToTeacher() {
        Thread thread = new Thread(new MonitorTread());
        thread.start();
    }
}
