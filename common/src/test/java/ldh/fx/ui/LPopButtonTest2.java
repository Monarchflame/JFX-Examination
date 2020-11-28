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
public class LPopButtonTest2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hbox = new HBox();

        LPopupButton button2 = new LPopupButton(PopupPos.down_east);
        button2.setText("show2");

        Button button3 = new Button("show3");
//        borderPane.setCenter(lp3);
        button3.setOnAction(e->{

        });

        LPopupButton button4 = new LPopupButton(PopupPos.down_east);
        button4.setText("show4");
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Label("dsfadfa"), new Label("dfafasdfa"), new Label("sdfasfasdf"));
//            vbox.setPrefSize(600, 400);
        button4.setPopupContentPane(vbox);

        hbox.getChildren().addAll(button2, button3, button4);


        Scene scene = new Scene(hbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
