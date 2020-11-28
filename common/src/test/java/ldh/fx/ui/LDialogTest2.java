package ldh.fx.ui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.octicons.OctIcon;
import de.jensd.fx.glyphs.weathericons.WeatherIcon;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ldh.fx.component.DialogModel;
import ldh.fx.component.LDialogBase;
import ldh.fx.component.LIconPane;

/**
 * Created by ldh on 2018/1/31.
 */
public class LDialogTest2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

//        lWindow.setStyle("-fx-background-color: grey");
        Button button = new Button("show popup");
        button.setOnAction(e-> {
            LDialogBase lWindow = new LDialogBase();
            lWindow.initDialogModel(primaryStage, DialogModel.Application_model);
            lWindow.buildResizable();
            lWindow.setPrefSize(600, 400);
//            lWindow.getStylesheets().add(LdhWindowTest2.class.getResource("/component/LDialog.css").toExternalForm());
//        lWindow.setContentPane(RegionUtil.createButton("drag", new MaterialDesignIconView(), "sw-bugle-graphic"));
            LIconPane iconPane = new LIconPane(FontAwesomeIcon.class, MaterialDesignIcon.class, OctIcon.class, WeatherIcon.class);
            lWindow.setContentPane(iconPane);
            lWindow.show();
        });
        Scene scene = new Scene(button, 1200, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
