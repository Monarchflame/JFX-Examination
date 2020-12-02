package fx.ui.model;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ModelDialog extends Stage {
    private static final Effect parentEffect = new BoxBlur();

    private final String messageText;
    private final EventHandler<ActionEvent> yesEventHandler;
    private final EventHandler<ActionEvent> noEventHandler;

    public ModelDialog(
            Stage parent,
            String messageText,
            EventHandler<ActionEvent> yesEventHandler,
            EventHandler<ActionEvent> noEventHandler) {
        super(StageStyle.TRANSPARENT);

        this.messageText = messageText;
        this.yesEventHandler = yesEventHandler;
        this.noEventHandler = noEventHandler;

        // initialize the dialog
        initOwner(parent);
        initParentEffects(parent);
        initModality(Modality.APPLICATION_MODAL);
        setScene(createScene(createLayout()));
    }

    private StackPane createLayout() {
        StackPane layout = new StackPane();
        layout.getChildren().setAll(
                createGlassPane(),
                createContentPane()
        );

        return layout;
    }

    private Pane createGlassPane() {
        final Pane glassPane = new Pane();
        glassPane.getStyleClass().add(
                "modal-dialog-glass"
        );

        return glassPane;
    }

    private Pane createContentPane() {
        final HBox contentPane = new HBox();
        contentPane.getStyleClass().add(
                "modal-dialog-content"
        );
        contentPane.getChildren().setAll(
                new Label(messageText),
                createYesButton(),
                createNoButton()
        );

        return contentPane;
    }

    private Button createYesButton() {
        final Button yesButton = new Button("Yes");
        yesButton.setDefaultButton(true);
        yesButton.setOnAction(yesEventHandler);

        return yesButton;
    }

    private Button createNoButton() {
        final Button noButton = new Button("No");
        noButton.setOnAction(noEventHandler);

        return noButton;
    }

    private Scene createScene(StackPane layout) {
        Scene scene = new Scene(layout, Color.TRANSPARENT);
        scene.getStylesheets().add(
                getClass().getResource(
                        "/css/modal-dialog.css"
                ).toExternalForm()
        );

        return scene;
    }

    private void initParentEffects(final Stage parent) {
        this.showingProperty().addListener(new ChangeListener<Boolean>() {
            @Override public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasShowing, Boolean isShowing) {
                parent.getScene().getRoot().setEffect(
                        isShowing ? parentEffect : null
                );
            }
        });
    }
}
