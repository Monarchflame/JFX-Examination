package fx.ui;

import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import fx.PrimaryStage;
import fx.component.LdhDialog;

/**
 * Created by ldh on 2018/1/13.
 */
public class LdhDialogTest extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        PrimaryStage.STAGE = stage;
        LdhDialog window = new LdhDialog("adas", 400d, 200d, true);
        window.setContentPane(new StackPane());
        window.setWindowMin(false);
        window.setWindowMax(false);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
