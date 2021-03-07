package com.desktop.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lombok.Data;

import java.util.Date;

/**
 * @author desktop
 * @date 2020/12/4 16:18
 */
public class TableViewTest extends Application {

    private TableView<Exam> table = new TableView<Exam>();
    private final ObservableList<Exam> data =
            FXCollections.observableArrayList(
                    new Exam("Jacob", new Date(), new Date()),
                    new Exam("Isabella", new Date(), new Date()),
                    new Exam("Ethan", new Date(), new Date())
            );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(850);
        stage.setHeight(500);

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        // 考试 开始时间 结束时间
        TableColumn firstNameCol = new TableColumn("考试");
        firstNameCol.setMinWidth(200);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Exam, String>("name"));

        TableColumn lastNameCol = new TableColumn("开始时间");
        lastNameCol.setMinWidth(300);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Exam, Date>("startTime"));

        TableColumn emailCol = new TableColumn("结束时间");
        emailCol.setMinWidth(300);
        emailCol.setCellValueFactory(new PropertyValueFactory<Exam, Date>("endTime"));

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    @Data
    public static class Exam {
        // 要有getter setter方法
        private final String name;
//        private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        private final Date startTime;
        private final Date endTime;

        private Exam(String name, Date startTime, Date endTime) {
            this.name = name;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}