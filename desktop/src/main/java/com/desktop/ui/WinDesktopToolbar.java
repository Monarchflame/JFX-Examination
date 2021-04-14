package com.desktop.ui;

import com.desktop.util.ThreadToolUtil;
import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.WindowEvent;
import fx.PrimaryStage;
import fx.component.LDialog;
import fx.component.LPopupButton;
import fx.component.PopupPos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * windows桌面任务栏
 * @author desktop
 * @date 2020/11/7
 */
public class WinDesktopToolbar extends DesktopToolbar {

    private LPopupButton windowButton = null;

    public WinDesktopToolbar(DesktopPane desktopPane) {
        super(desktopPane);
    }

    @Override
    protected void initItem() {
        windowButton = new LPopupButton(PopupPos.up_east);
        FontAwesomeIconView windowGraphic = new FontAwesomeIconView();
        windowGraphic.getStyleClass().add("window-graphic");
        windowButton.setGraphic(windowGraphic);
        windowButton.getStyleClass().add("toolbar-item");
        windowButton.setPopupContentPane(new Label("asdfaffdadfasdfasfasdfasfsa"));
        windowButton.getPopup().showingProperty().addListener((l, o, n)->{
            animation(n);
        });

        this.getLeftPane().getChildren().add(0, windowButton);

//        addButton(new Icons525View(), "browser-graphic", leftPane);
        addMessageButton(new MaterialDesignIconView(), "message-graphic", getRightPane());
//        addButton(new MaterialDesignIconView(), "message-graphic", rightPane);
        Label dayLabel = new Label();
        Label timeLabel = new Label();
        dateText(dayLabel, timeLabel);
        VBox dateBox = new VBox();
        dateBox.setAlignment(Pos.CENTER);
        dateBox.getChildren().addAll(timeLabel, dayLabel);
        dateBox.setSpacing(2);
        dateBox.getStyleClass().addAll("toolbar-item", "win-date");
        getRightPane().getChildren().add(dateBox);
    }

    /**
     * 日期与时间
     * @param dayLabel
     * @param timeLabel
     */
    private void dateText(Label dayLabel, Label timeLabel) {
        Runnable runnable = () -> {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String day = dateFormat.format(date);
            dateFormat = new SimpleDateFormat("HH:mm");
            String time = dateFormat.format(date);
            Platform.runLater(() -> {
                dayLabel.setText(day);
                timeLabel.setText(time);
            });
        };
        ThreadToolUtil.scheduleAtFixedRate(runnable, 40, TimeUnit.SECONDS);
    }

    private Button addMessageButton(GlyphIcon glyphIcon, String icon, Pane box) {
        LPopupButton messageButton = new LPopupButton(PopupPos.up_west);
        messageButton.getStyleClass().add("toolbar-item");
        if (glyphIcon != null) {
            glyphIcon.getStyleClass().add(icon);
            messageButton.setGraphic(glyphIcon);
        }
        box.getChildren().add(messageButton);
        messageButton.setPopupContentPane(new Label("asdfasfasfdasfa"));
        messageButton.getPopup().showingProperty().addListener((l, o, n)->{
            animation(n);
        });
        return messageButton;
    }
}