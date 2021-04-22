package com.desktop.explorer;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Fahim on 4/27/2017.
 */
@FXMLController
public class TilesViewController implements Initializable {

    @FXML private TilePane tilePane;
    public static FileExplorerFx Fx3;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Fx3 = new ClassTilesView();
        FileExplorerFx.tilePane = tilePane;
        Fx3.CreateTiles();
    }
}
