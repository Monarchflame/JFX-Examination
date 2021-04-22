package com.desktop.explorer;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


/**
 * Created by Fahim on 4/27/2017.
 */
public interface FileExplorer {
    /**this class will contain all the method's name. as other derived classes
     * will be created, they will implement necessary methods accordingly...*/
    Image getIconImageFX(File f);

    String calculateSize(File f);

    boolean IsDrive(File f);
    int FilesHiddensCount(File dir);
    void CreateTreeView(TreeView<File> treeview);
    void CreateTableView(TableView<FileInfo> tableview, TableColumn<FileInfo, ImageView> image, TableColumn<FileInfo, String> date,
                         TableColumn<FileInfo, String> name, TableColumn<FileInfo, String> size);
    void CreateTableView();
    void CreateTilesView();
    void setLabelTxt();
    void Initiate();
    void setValues(TableView<FileInfo> tableview,TableColumn<FileInfo, ImageView> image,TableColumn<FileInfo, String> date,
                   TableColumn<FileInfo, String> name,TableColumn<FileInfo, String> size);
    void CreateTiles();
    /**********************/
    public int NumOfDirectChilds(File f);

}
