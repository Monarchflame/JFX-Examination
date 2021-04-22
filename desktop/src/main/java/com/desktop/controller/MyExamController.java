package com.desktop.controller;

import com.desktop.MainApplication;
import com.desktop.WinMainApp;
import com.desktop.dao.*;
import com.desktop.entity.*;
import com.desktop.invigilation.monitor.MonitorClient;
import com.desktop.page.FormContent;
import com.desktop.ui.*;
import com.desktop.util.AlertMaker;
import com.desktop.util.Constant;
import com.desktop.util.FtpUtils;
import com.desktop.util.ThreadToolUtil;
import com.desktop.view.ExplorerView;
import com.jfoenix.controls.JFXDecorator;
import de.felixroske.jfxsupport.FXMLController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fx.PrimaryStage;
import fx.ui.util.PageUtil;
import fx.ui.util.RegionUtil;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author desktop
 * @date 2020/12/3 16:50
 */
@FXMLController
@Slf4j
public class MyExamController implements Initializable {

    ObservableList<Exam> list = FXCollections.observableArrayList();

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private TableView<Exam> tableView;
    @FXML
    private TableColumn<Exam, String> examNameCol;
    @FXML
    private TableColumn<Exam, LocalDateTime> startTimeCol;
    @FXML
    private TableColumn<Exam, LocalDateTime> endTimeCol;
    @FXML
    private TableColumn<Exam, String> examTimeCol;

    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private SoftwareMapper softwareMapper;
    @Autowired
    private SoftwareConfigMapper softwareConfigMapper;
    @Autowired
    private SelectSoftwareMapper selectSoftwareMapper;
    @Autowired
    private SelectCourseMapper selectCourseMapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        loadData();
    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }

    /**
     * 初始化列
     */
    private void initCol() {
        examNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        examTimeCol.setCellValueFactory(new PropertyValueFactory<>("examTime"));
    }

    /**
     * 加载数据
     */
    private void loadData() {
        list.clear();

        Student student = Constant.student;
        if (student == null) {
            AlertMaker.showSimpleAlert("错误", "考生未登录");
            return;
        }
        // 查选课信息
        SelectCourseExample selectCourseExample = new SelectCourseExample();
        selectCourseExample.or().andStudentIdEqualTo(student.getId());
        List<SelectCourse> selectCourses = selectCourseMapper.selectByExample(selectCourseExample);
        // 查考试信息
        for (SelectCourse selectCourse : selectCourses) {
            String courseNo = selectCourse.getCourseNo();
            ExamExample examExample = new ExamExample();
            examExample.or().andCourseNoEqualTo(courseNo);
            List<Exam> exams = examMapper.selectByExample(examExample);
            // exams长度应为1
            for (Exam exam : exams) {
                // 只把未结束的考试加进去
                if (exam.getEndTime().isAfter(LocalDateTime.now())) {
                    list.add(exam);
                }
            }
        }
//        formatDate(startTimeCol);
//        formatDate(endTimeCol);
        tableView.setItems(list);
    }

    /**
     * 进入考试
     *
     * @param event
     */
    @FXML
    private void enterExam(ActionEvent event) throws Exception {
        ReadOnlyObjectProperty<Exam> property = tableView.getSelectionModel().selectedItemProperty();
        Exam exam = property.get();
        if (exam == null) {
            AlertMaker.showSimpleAlert("提醒", "请选择要进入的考试");
            return;
        }
        LocalDateTime startTime = exam.getStartTime();

        boolean examStarted = startTime.isBefore(LocalDateTime.now());

        if (examStarted) {
            System.out.println(exam.getName() + " " + "开始");
            Constant.exam = exam;
            String directoryPath = "F:\\" + Constant.student.getStudentNo() + "\\";
            // 拉取考题
            boolean result = FtpUtils.sshPull(directoryPath, exam.getTeacherId().toString(), exam.getName());
            if (!result) {
                log.info("未获取到考题");
            }
            // 连接到教师端
            connectToTeacher();
            // 进入考试界面
            enterExamDesktop();
        } else {
            AlertMaker.showSimpleAlert("提示", "考试未开始");
        }
    }

    /**
     * 连接到教师端
     */
    private void connectToTeacher() {
        Thread thread = new Thread(new MonitorClient());
        thread.start();
    }

    // 更新考试状态
//    private void updateExamStatus(Exam exam) throws ParseException {
//        // 定时任务，执行一次
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateString = "2020-12-7 17:44:50";
//        Date dateRef = sdf.parse(dateString);
//
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("-------设定要指定任务--------");
//            }
//        }, dateRef);// 设定指定的时间time,此处为2000毫秒
//    }

    /**
     * 进入考试界面
     */
    private void enterExamDesktop() {
        // 方便调试暂且关闭禁用快捷键
//        Thread keyboardHookThread = new Thread(new KeyboardHook());
//        keyboardHookThread.start();

        getStage().close();
        Stage stage = new Stage();

        DesktopPane desktopPane = initDesktopPane();
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
//        stage.setMaximized(true);
        stage.setFullScreen(true);
        // 禁止退出全屏
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setResizable(false);
        stage.show();

        stage.setOnCloseRequest(e -> {
            PrimaryStage.closeAllNewStages();
            ThreadToolUtil.close();
//            keyboardHookThread.stop();
        });
    }

    /**
     * 初始化考试桌面
     *
     * @return
     */
    private DesktopPane initDesktopPane() {
        // 加载Windows图标
        Image image = new Image(WinMainApp.class.getResource("/images/win10.png").toExternalForm());

        DesktopPane desktopPane = new DesktopPane();

        desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel("CVS浏览器", new FontAwesomeIconView(), "cvs-graphic"), () -> PageUtil.load("/fxml/Cvs.fxml")));
        desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel("Form表单样式", new FontAwesomeIconView(), "form-graphic"), () -> new FormContent()));
        // 添加"答案文件夹"按钮
        Button folderButton = new Button();
        folderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                new MainForm();
                MainApplication.showView(ExplorerView.class);
            }
        });
        desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel("答案文件夹", new FontAwesomeIconView(), "plan-pane-graphic"), () -> PageUtil.load("/fxml/explorer/Scene.fxml")));
        // 添加退出按钮
        desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel("退出考试", new FontAwesomeIconView(), "plan-pane-graphic"), () -> new ExitButton("退出考试")));
        // 添加软件白名单中的软件
        addSoftware(desktopPane);

        return desktopPane;
    }

    /**
     * 在考试面板上添加白名单软件
     *
     * @param desktopPane
     */
    private void addSoftware(DesktopPane desktopPane) {
        List<Software> softwareList = new ArrayList<>();

        Exam exam = Constant.exam;
        Long softwareConfigId = exam.getSoftwareConfigId();

        SelectSoftwareExample selectSoftwareExample = new SelectSoftwareExample();
        selectSoftwareExample.or().andSoftwareConfigIdEqualTo(softwareConfigId);
        List<SelectSoftware> selectSoftwareList = selectSoftwareMapper.selectByExample(selectSoftwareExample);

        for (SelectSoftware selectSoftware : selectSoftwareList) {
            Software software = softwareMapper.selectByPrimaryKey(selectSoftware.getSoftwareId());
            softwareList.add(software);
        }

        for (Software software : softwareList) {
            desktopPane.getChildren().add(new DesktopItem(RegionUtil.createLabel(software.getName(), new FontAwesomeIconView(), "plan-pane-graphic"),
                    () -> new ApplicationButton(software.getName(), software.getPath())));
        }
    }

    /**
     * 格式化表格日期列
     *
     * @param tableColumn
     * @param <T>
     */
    public static <T> void formatDate(TableColumn<T, LocalDateTime> tableColumn) {
        tableColumn.setCellFactory(column -> new TableCell<T, LocalDateTime>() {
            private final SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    this.setText(format.format(item));
                }
            }
        });
    }
}
