package fx.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by ldh on 2018/1/16.
 */
public class HiddenPaneTest2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent node = FXMLLoader.load(getClass().getResource("/fxml/HiddenPaneTest2.fxml"));
        Scene scene = new Scene(node, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
