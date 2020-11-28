package ldh.fx.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ldh.fx.component.LIconPane;
import ldh.fx.component.LPopupButton;
import ldh.fx.component.PopupPos;

/**
 * Created by ldh on 2018/4/19.
 */
public class LPopButtonTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Popup popup = new Popup();
        popup.setAutoHide(true);
        BorderPane borderPane = new BorderPane();
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        LPopupButton button = new LPopupButton(PopupPos.down_east);
        button.setText("show");
        button.setPopupContentPane(new Label("sdfasdfasdfafas"));

        LPopupButton button2 = new LPopupButton(PopupPos.down_east);
        button2.setText("show2");
        LIconPane popPane = new LIconPane();
        popPane.setPrefSize(600, 400);
        button2.setPopupContentPane(popPane);

        Button button3 = new Button("show3");
        LIconPane lp3 = new LIconPane();
//        borderPane.setCenter(lp3);
        button3.setOnAction(e->{
            borderPane.setCenter(lp3);
        });

        LPopupButton button4 = new LPopupButton(PopupPos.down_east);
        button4.setText("show4");
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Label("dsfadfa"), new Label("dfafasdfa"), new Label("sdfasfasdf"));
//            vbox.setPrefSize(600, 400);
        button4.setPopupContentPane(vbox);

        hBox.getChildren().addAll(button, button2, button3, button4);
        borderPane.setTop(hBox);

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
