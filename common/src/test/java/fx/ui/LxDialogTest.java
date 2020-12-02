package fx.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import fx.PrimaryStage;
import fx.component.DialogModel;
import fx.component.LxDialog;

/**
 * Created by ldh on 2018/1/13.
 */
public class LxDialogTest extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        PrimaryStage.STAGE = stage;

//        window.setResizable();
//        window.isShowingMaxBtn(false);
//        window.isShowingMinBtn(false);

//        window.show();
        Button b = new Button("show");
        b.setOnAction(e->{
            LxDialog window = new LxDialog(stage, "demo", DialogModel.Application, 400d, 200d);
            window.isShowingMinButton(false);
            Label label = new Label("error");
            label.setStyle("-fx-padding: 10");
            window.setContentPane(label);
            window.getScene().getStylesheets().add("/component/LxDialog.css");
            window.setMovable();
            window.show();
        });
        Scene scene = new Scene(b, 200, 300);
        scene.getStylesheets().add("/component/LxDialog.css");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
