package com.desktop.ui;

import de.jensd.fx.glyphs.GlyphIcon;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import java.util.HashSet;
import java.util.Set;

/**
 * @author qxt
 * @date 2020/12/11
 */
public class DesktopToolbar extends HBox {

    private HBox leftPane = new HBox();
    private HBox contentPane = new HBox();
    private HBox rightPane = new HBox();
    private DesktopPane desktopPane;
    private Set<String> toolbarButtonSet = new HashSet<>();

    public DesktopToolbar(DesktopPane desktopPane) {
        this.desktopPane = desktopPane;
        this.getStyleClass().add("desktop-toolbar");

        rightPane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        rightPane.setAlignment(Pos.CENTER);
        leftPane.setAlignment(Pos.CENTER);

        leftPane.getStyleClass().add("left-pane");
        rightPane.getStyleClass().add("right-pane");
        contentPane.getStyleClass().add("content-pane");

        HBox.setHgrow(contentPane, Priority.ALWAYS);
        this.getChildren().addAll(leftPane, contentPane, rightPane);
        initItem();
    }

    public DesktopPane getDesktopPane() {
        return desktopPane;
    }

    public Pane getLeftPane() {
        return leftPane;
    }

    public Pane getRightPane() {
        return rightPane;
    }

    public Pane getContentPane() {
        return contentPane;
    }

    protected void initItem() {
    }

    protected Button addButton(GlyphIcon glyphIcon, String icon, Pane box) {
        Button button = new Button();
        button.getStyleClass().add("toolbar-item");
        if (glyphIcon != null) {
            glyphIcon.getStyleClass().add(icon);
            button.setGraphic(glyphIcon);
        }
        box.getChildren().add(button);
        return button;
    }

    protected void animation(boolean animation) {
        if (animation) {
            getDesktopPane().animation();
        } else {
            getDesktopPane().animationRestore();
        }
    }

    /**
     * 向任务栏中添加按键
     *
     * @param toolbarButton
     */
    public void addToolbarButton(ToolbarButton toolbarButton) {
        toolbarButtonSet.forEach(s -> System.out.println(s));
        if (toolbarButtonSet.add(toolbarButton.getButton().getText())) {
            contentPane.getChildren().add(toolbarButton);
        }
    }

    /**
     * 向任务栏中移除按键
     *
     * @param toolbarButton
     */
    public void removeToolbarButton(ToolbarButton toolbarButton) {
        contentPane.getChildren().remove(toolbarButton);
        boolean remove = toolbarButtonSet.remove(toolbarButton.getButton().getText());
        System.out.println("移除成功");
        toolbarButtonSet.forEach(s -> System.out.println(s));
    }
}
