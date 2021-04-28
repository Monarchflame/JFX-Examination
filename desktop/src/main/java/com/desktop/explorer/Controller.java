package com.desktop.explorer;

import com.desktop.util.Constant;
import com.desktop.util.FtpUtils;
import com.desktop.util.ZipUtils;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//import static com.desktop.explorer.TilesViewController.Fx3;

/**
 * Created by Fahim on 4/27/2017.
 */
@FXMLController
public class Controller implements Initializable {
    @FXML
    private Button btn;
    @FXML
    private Pane secPane;
    @FXML
    private Label label;
    private int count;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        count = 0;

//        FileExplorerFx.CurrDirFile = new File("./");
        FileExplorerFx.CurrDirFile = new File("F://" + Constant.getStudent().getStudentNo());
        FileExplorerFx.CurrDirStr = FileExplorerFx.CurrDirFile.getAbsolutePath();
        FileExplorerFx.lbl = label;
        label.setText(FileExplorerFx.CurrDirStr);
        try {
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/explorer/Scene2.fxml"));
            secPane.getChildren().add(newLoadedPane);
        } catch (NullPointerException x) {
            x.printStackTrace();
        } catch (IOException x) {
            x.printStackTrace();
        }

//        Fx2.CreateTableView();// okhan theke dile error dey :/
    }

    @FXML
    private void loadFxml(ActionEvent event) throws IOException {
        count = (count + 1) % 2;
        Pane newLoadedPane;
        secPane.getChildren().clear();
        if (count == 0) {
            newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/explorer/Scene2.fxml"));
        } else {
            newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/explorer/Scene3.fxml"));
        }
        secPane.getChildren().add(newLoadedPane);
    }

    @FXML
    private void newFolder() {
        System.out.println("新建文件夹");
        File rootFolder = new File(FileExplorerFx.CurrDirStr);
        String newFolderName = "新建文件夹";
        if (rootFolder.list().length == 0) {
            new File(FileExplorerFx.CurrDirStr + "\\" + newFolderName).mkdirs();
            homeList();
            return;
        }
        String[] folderList = rootFolder.list();
        Arrays.sort(folderList);
        Stream<String> folderListStream = Arrays.stream(folderList).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                String pattern = "新建文件夹\\s\\((\\d)\\)";
                return Pattern.matches(pattern, s) || "新建文件夹".equals(s);
            }
        });
        List<String> collect = folderListStream.collect(Collectors.toList());
        if (collect.isEmpty()) {
            new File(FileExplorerFx.CurrDirStr + "\\" + newFolderName).mkdirs();
            homeList();
            return;
        }
        String lastFolderName = collect.get(collect.size() - 1);
        if (lastFolderName.equals(newFolderName)) {
            new File(FileExplorerFx.CurrDirStr + "\\" + newFolderName + " (1)").mkdirs();
            homeList();
            return;
        } else {
            String pattern = "新建文件夹\\s\\((\\d)\\)";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(lastFolderName);
            if (m.find()) {
                int number = Integer.parseInt(m.group(1)) + 1;
                new File(FileExplorerFx.CurrDirStr + "\\" + newFolderName + " (" + number + ")").mkdirs();
                homeList();
            } else {
                System.out.println("NO MATCH");
            }
        }
    }

    /**
     * 回到当前界面
     */
    public void homeList() {
        FileExplorerFx.CurrDirStr = FileExplorerFx.CurrDirFile.getAbsolutePath();
        FileExplorerFx.lbl = label;
        label.setText(FileExplorerFx.CurrDirStr);
        try {
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/explorer/Scene2.fxml"));
            secPane.getChildren().add(newLoadedPane);
        } catch (NullPointerException x) {
            x.printStackTrace();
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    /**
     * 上传答案
     */
    public void uploadAnswer() {
        try {
            // 压缩
            File zipFile = new File("F://" + Constant.getStudent().getStudentNo() + ".zip");
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
            ZipUtils.toZip("F://" + Constant.getStudent().getStudentNo(), fileOutputStream, true);
            // 上传
            FtpUtils.sshPut(zipFile, Constant.getExam().getTeacherId().toString() + "/" + Constant.getExam().getName(), zipFile.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
