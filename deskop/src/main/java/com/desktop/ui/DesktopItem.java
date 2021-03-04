package com.desktop.ui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import fx.PrimaryStage;
import fx.util.DialogUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 桌面图标
 *
 * @author qxt
 * @date 2020/11/27
 */
public class DesktopItem extends StackPane {

    private DesktopToolbar desktopToolbar;
    private Label label;
    private DesktopNodeFactory desktopNodeFactory;

    public DesktopItem() {
        this.getStyleClass().add("desktop-item");
        initUi();
    }

    public DesktopItem(Label label, DesktopNodeFactory desktopNodeFactory) {
        this.getStyleClass().add("desktop-item");
        this.label = label;
        this.desktopNodeFactory = desktopNodeFactory;
        initUi();
    }

    public DesktopItem(String text, DesktopNodeFactory desktopNodeFactory) {
        this(null, text, desktopNodeFactory, text);
    }

    public DesktopItem(Image image, String text, DesktopNodeFactory desktopNodeFactory) {
        this(image, text, desktopNodeFactory, text);
    }

    public DesktopItem(Image image, String text, DesktopNodeFactory desktopNodeFactory, String desc) {
        this.getStyleClass().add("desktop-item");
        ImageView iv = new ImageView(image);
        clipImageView(iv, 50, 50);
        label = new Label(text, iv);
        label.setTooltip(new Tooltip(desc));
        this.desktopNodeFactory = desktopNodeFactory;

        initUi();
    }

    public void setText(String text) {
        getLabel().setText(text);
    }

    public String getText() {
        return StringUtils.isEmpty(getLabel().getText()) ? "" : label.getText();
    }

    public Label getLabel() {
        if (label == null) {
            label = new Label();
        }
        return label;
    }

    public void setDesktopToolbar(DesktopToolbar desktopToolbar) {
        this.desktopToolbar = desktopToolbar;
    }

    private void initUi() {
        label.getStyleClass().add("item-content");
        label.setTooltip(new Tooltip(getText()));
        // 鼠标点击图标调用openDialog方法
        label.setOnMouseClicked(e -> openDialog(e));
        this.getChildren().add(label);
    }

    /**
     * 点击图标调用
     *
     * @param event
     */
    private void openDialog(MouseEvent event) {
        if (event.getClickCount() != 2) {
            return;
        }
        // 打开后任务栏显示图标
        ToolbarButton toolbarButton = new ToolbarButton(new Button(getText()));
        Node newNode = buildNewGraphic(this.getLabel().getGraphic());
        toolbarButton.getButton().setGraphic(newNode);
        toolbarButton.getButton().getGraphic().getStyleClass().add("desktop-button-min-graphic");
        desktopToolbar.addToolbarButton(toolbarButton);
        Object obj = desktopNodeFactory.create();

        if (desktopNodeFactory.isButton(obj)) {
            Button button = (Button) obj;
            button.fire();
            // 可从任务栏关闭页面
            toolbarButton.getButton().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
                desktopToolbar.removeToolbarButton(toolbarButton);
                e.consume();
            });
            // 点击任务栏跳出
            toolbarButton.getButton().setOnAction(e -> button.fire());
        } else if (desktopNodeFactory.isNode(obj)) {
            // 桌面上打开页面
            FxDialog fxDialog = new FxDialog(PrimaryStage.STAGE);
            fxDialog.setTitle(getLabel().getTooltip().getText());
            fxDialog.setContent((Parent) obj, 800, 500);
            fxDialog.setOnCloseRequest(e -> desktopToolbar.removeToolbarButton(toolbarButton));
            toolbarButton.getButton().setOnAction(e -> {
                fxDialog.setIconified(false);
            });
            // 可从任务栏关闭页面
            toolbarButton.getButton().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
                desktopToolbar.removeToolbarButton(toolbarButton);
                fxDialog.close();
                e.consume();
            });
            fxDialog.show();
        } else if (desktopNodeFactory.isStage(obj)) {
            Stage stage = (Stage) obj;
            stage.show();
            stage.requestFocus();
            PrimaryStage.putNewStage(stage, (Void) -> {
                desktopToolbar.removeToolbarButton(toolbarButton);
                return null;
            });
            toolbarButton.getButton().setOnAction(e -> {
                if (stage.isShowing()) {
                    stage.setIconified(false);
                } else {
                    stage.setIconified(true);
                }
            });
            // 可从任务栏关闭页面
            toolbarButton.getButton().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
                stage.close();
                PrimaryStage.closeNewStage(stage);
                desktopToolbar.removeToolbarButton(toolbarButton);
                e.consume();
            });
        } else {
            DialogUtil.modelInfo("error", "desktopNodeFactory create old node and stage", 300, 200);
        }
    }

    private Node buildNewGraphic(Node graphic) {
        if (graphic instanceof ImageView) {
            ImageView imageView = new ImageView();
            imageView.setImage(((ImageView) graphic).getImage());
            double width = 20d, height = 20d;
            imageView.setFitHeight(width);
            imageView.setFitWidth(width);
//            clipImageView(imageView, width, height);
            return imageView;
        } else if (graphic instanceof FontAwesomeIconView) {
            FontAwesomeIconView glyphIcon = (FontAwesomeIconView) graphic;
            FontAwesomeIconView newGlyphIcon = new FontAwesomeIconView();
            newGlyphIcon.setGlyphName(glyphIcon.getGlyphName());
            newGlyphIcon.setGlyphSize(28);
            return newGlyphIcon;
        }
        return graphic;
    }

    private void clipImageView(ImageView imageView, double width, double height) {
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        Rectangle clip = new Rectangle(width, height);
        clip.setArcWidth(15);
        clip.setArcHeight(15);
        imageView.setClip(clip);
    }

}
