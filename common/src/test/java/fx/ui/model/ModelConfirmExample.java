package fx.ui.model;

import javafx.application.Application;
import javafx.beans.value.*;
import javafx.concurrent.Worker;
import javafx.event.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.*;

/**
 * Application modal dialog with the following properties:
 *   translucent background
 *   drop-shadowed border
 *   non-rectangular shape
 *   blur effect applied to parent when dialog is showing
 *   configurable message text
 *   configurable yes and no event handlers
 */


/**
 * Demonstrates a modal confirm box in JavaFX.
 * Dialog is rendered upon a blurred background.
 * Dialog is translucent.
 */
public class ModelConfirmExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        final WebView webView = new WebView();

        final ModelDialog dialog = createWebViewPreferenceDialog(primaryStage, webView);

        // show the preference dialog each time a new page is loaded.
        webView.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State state, Worker.State newState) {
                if (newState.equals(Worker.State.SUCCEEDED)) {
                    dialog.show();
                    dialog.toFront();
                }
            }
        });
        webView.getEngine().load("http://docs.oracle.com/javafx/");

        // initialize the stage
        primaryStage.setTitle("Modal Confirm Example");
        primaryStage.setScene(new Scene(webView));
        primaryStage.show();
    }

    private ModelDialog createWebViewPreferenceDialog(final Stage primaryStage, final WebView webView) {
        final EventHandler<ActionEvent> yesEventHandler =
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent actionEvent) {
                        System.out.println("Liked: " + webView.getEngine().getTitle());
                        primaryStage.getScene().getRoot().setEffect(null);
                        Stage dialogStage = getTargetStage(actionEvent);
                        dialogStage.close();
                    }
                };

        final EventHandler<ActionEvent> noEventHandler =
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent actionEvent) {
                        System.out.println("Disliked: " + webView.getEngine().getTitle());
                        primaryStage.getScene().getRoot().setEffect(null);
                        Stage dialogStage = getTargetStage(actionEvent);
                        dialogStage.close();
                    }
                };

        return new ModelDialog(primaryStage, "Will you like this Page?", yesEventHandler, noEventHandler);
    }

    private Stage getTargetStage(ActionEvent actionEvent) {
        Node target = (Node) actionEvent.getTarget();
        return ((Stage) target.getScene().getWindow());
    }
}