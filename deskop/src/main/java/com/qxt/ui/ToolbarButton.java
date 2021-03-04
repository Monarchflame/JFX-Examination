package com.qxt.ui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.WindowEvent;

/**
 * 任务栏按钮
 * @author qxt
 */
public class ToolbarButton extends StackPane {
    /**
     * 主按钮
     */
    private Button textButton;
    /**
     * 右上角退出按钮
     */
    private Button exitButton = new Button();

    public ToolbarButton(Button textButton) {
        this.textButton = textButton;
        this.getStyleClass().add("toolbar-button-container");
        this.textButton.getStyleClass().add("toolbar-button");
        this.exitButton.getStyleClass().add("toolbar-button-close");
        FontAwesomeIconView icon = new FontAwesomeIconView();
        icon.getStyleClass().add("toolbar-button-close-graphic");
        exitButton.setGraphic(icon);
        this.getChildren().addAll(textButton, exitButton);
        StackPane.setAlignment(exitButton, Pos.TOP_RIGHT);
        exitButton.setVisible(false);
        this.setOnMouseExited(e -> exitButton.setVisible(false));
        this.setOnMouseEntered(e -> exitButton.setVisible(true));
        textButton.setOnMouseExited(e -> exitButton.setVisible(false));
        textButton.setOnMouseEntered(e -> exitButton.setVisible(true));
        exitButton.setOnMouseEntered(e -> {
            exitButton.setVisible(true);
            textButton.fireEvent(new MouseEvent(MouseEvent.MOUSE_ENTERED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
        });
        exitButton.setOnMouseExited(e -> {
            exitButton.setVisible(false);
            textButton.fireEvent(new MouseEvent(MouseEvent.MOUSE_EXITED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
        });
        exitButton.setOnAction(e -> {
            textButton.fireEvent(new WindowEvent(this.getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST));
        });
    }

    public Button getButton() {
        return textButton;
    }
}
