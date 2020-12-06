package com.desktop.controller;

import com.desktop.dao.ExamArrangementMapper;
import com.desktop.dao.ExamMapper;
import com.desktop.entity.Exam;
import com.desktop.entity.ExamArrangement;
import com.desktop.entity.ExamArrangementExample;
import com.desktop.entity.Student;
import com.desktop.util.AlertMaker;
import com.desktop.util.StudentUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author qxt
 * @date 2020/12/3 16:50
 */
@FXMLController
@Slf4j
public class MyExamController implements Initializable {

    ObservableList<Exam> list = FXCollections.observableArrayList();

    @FXML
    private StackPane rootPane;
    @FXML
    private TableView<Exam> tableView;
    @FXML
    private TableColumn<Exam, String> examNameCol;
    @FXML
    private TableColumn<Exam, Date> startTimeCol;
    @FXML
    private TableColumn<Exam, Date> endTimeCol;
    @FXML
    private TableColumn<Exam, String> examTimeCol;
    @FXML
    private TableColumn<Exam, Boolean> statusCol;

    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private ExamArrangementMapper examArrangementMapper;

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
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    /**
     * 加载数据
     */
    private void loadData() {
        list.clear();

        Student student = StudentUtil.student;
        if (student == null) {
            AlertMaker.showSimpleAlert("错误", "考生未登录");
            return;
        }
        ExamArrangementExample example = new ExamArrangementExample();
        example.or().andStudentIdEqualTo(student.getId());
        List<ExamArrangement> examArrangements = examArrangementMapper.selectByExample(example);
        if (examArrangements != null) {
            for (ExamArrangement examArrangement : examArrangements) {
                Exam exam = examMapper.selectByPrimaryKey(examArrangement.getExamId());
                list.add(exam);
            }
        }

        tableView.setItems(list);
    }

    /**
     * 进入考试
     *
     * @param event
     */
    @FXML
    private void enterExam(ActionEvent event) {

    }

    /**
     * 进入考试
     *
     * @param event
     */
    @FXML
    private void handleEnterExamButtonKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            enterExam(null);
        }
    }

    @FXML
    private void loadExamInfo() {

    }
}
