package fx.ui;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import fx.component.DialogModel;
import fx.component.LDialogBase;

/**
 * Created by ldh on 2018/1/31.
 */
public class LDialogTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        LDialogBase lWindow = new LDialogBase();
        lWindow.initDialogModel(primaryStage, DialogModel.Application_model);
        lWindow.buildResizable();
        lWindow.setContentPane(new Label("sadfasdfasdfas"));
//        Scene scene = new Scene(lWindow, 1200, 600);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        lWindow.getScene().getStylesheets().add(LdhWindowTest2.class.getResource("/component/LDialog.css").toExternalForm());
        lWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
