package fx.ui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import fx.ui.util.PageUtil;

/**
 * Created by ldh on 2018/1/29.
 */
public class FormPaneTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = PageUtil.load("/fxml/FormPane1.fxml");
        Scene scene = new Scene(parent, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
