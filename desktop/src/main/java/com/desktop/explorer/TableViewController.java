package com.desktop.explorer;

import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Fahim on 4/27/2017.
 */
@FXMLController
public class TableViewController implements Initializable {

    @FXML
    private TableView<FileInfo> tableview;
    @FXML
    private TableColumn<FileInfo, ImageView> image;
    @FXML
    private TableColumn<FileInfo, String> date;
    @FXML
    private TableColumn<FileInfo, String> name;
    @FXML
    private TableColumn<FileInfo, String> size;
    private Desktop desktop;
    public ObservableList<FileInfo> list;
    public static FileExplorerFx fileExplorerFx;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileExplorerFx = new ClassTableView();
        fileExplorerFx.setValues(tableview, image, date, name, size);
        if (FileExplorerFx.CurrDirFile == null) {
            FileExplorerFx.CurrDirFile = new File("./");
            FileExplorerFx.CurrDirStr = FileExplorerFx.CurrDirFile.getAbsolutePath();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        File[] fl;
        ObservableList<FileInfo> list;
        if (FileExplorerFx.CurrDirFile == null) {
            FileExplorerFx.CurrDirFile = new File("./");
        }

        fl = FileExplorerFx.CurrDirFile.listFiles();
        // donot delete . original
        FileInfo st[] = new FileInfo[fl.length];
        for (int i = 0; i < fl.length; i++) {
            String s1 = null;
            String s2 = null;
            String s3 = null;
            ImageView img = null;
            try {
                if (fileExplorerFx.IsDrive(fl[i])) {
                    img = new ImageView(fileExplorerFx.getIconImageFX(fl[i]));
                    s1 = fl[i].getAbsolutePath();
                } else {
                    img = new ImageView(fileExplorerFx.getIconImageFX(fl[i]));
                    s1 = fl[i].getName();
                }
                s2 = fileExplorerFx.calculateSize(fl[i]);
                s3 = sdf.format(fl[i].lastModified());
            } catch (Exception x) {
                System.out.println("Exception detected in tableview strings: " + x.getMessage());
            }
            st[i] = new FileInfo(img, s1, s2, s3);
        }

        list = FXCollections.observableArrayList(st);

        //id.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        size.setCellValueFactory(new PropertyValueFactory<>("size"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableview.setItems(list);
    }

    @FXML
    private void handleTableMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            String fileName = tableview.getSelectionModel().getSelectedItem().getName();
            String fileAbsolutelyPath = FileExplorerFx.CurrDirStr + "\\" + fileName;
            File file = new File(fileAbsolutelyPath);
            if (file.isDirectory()) {
                try {
                    FileExplorerFx.CurrDirFile = file;
                    FileExplorerFx.CurrDirStr = FileExplorerFx.CurrDirFile.getAbsolutePath();
                    fileExplorerFx.setLabelTxt();
                    tableview.getItems().clear();
                    fileExplorerFx.CreateTableView();
                } catch (Exception x) {
                    x.printStackTrace();
                }
            } else if (file.isFile()) {
                desktop = Desktop.getDesktop();
                try {
                    desktop.open(file);
                } catch (IOException x) {
                    x.printStackTrace();
                }
            }
        }
    }
    /**
     * 跳转函数
     */
    public void goThere(String dir) {
        if (!"".equals(dir)) {
            try {
                FileExplorerFx.CurrDirFile = new File(dir);
                FileExplorerFx.CurrDirStr = dir;
                fileExplorerFx.setLabelTxt();
                tableview.getItems().clear();
                fileExplorerFx.CreateTableView();
            } catch (Exception x) {
                x.printStackTrace();
            }
        } else {

        }
    }

    public void delete(ActionEvent actionEvent) {
    }

    public void rename(ActionEvent actionEvent) {
        FileInfo selectFile = tableview.getSelectionModel().getSelectedItem();

        TextInputDialog dialog = new TextInputDialog(selectFile.getName());
        dialog.setTitle("重命名");
        dialog.setHeaderText("重命名输入框");
        dialog.setContentText("请输入新的文件名:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String newName = result.get();
            if (!selectFile.getName().equals(newName) && !StringUtils.isEmpty(newName)) {
                String fileAbsolutelyPath = FileExplorerFx.CurrDirStr + "\\" + selectFile.getName();
                File file = new File(fileAbsolutelyPath);
                file.renameTo(new File(FileExplorerFx.CurrDirStr + "\\" + newName));
                //刷新
                goThere(FileExplorerFx.CurrDirStr);
            }
        }
    }
}