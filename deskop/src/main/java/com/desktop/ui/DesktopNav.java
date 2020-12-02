package com.desktop.ui;

import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import fx.ui.util.RegionUtil;

/**
 * Created by ldh on 2018/1/18.
 */
public class DesktopNav extends HBox {

    public DesktopNav() {
        init();
    }

    public DesktopNav(double spacing) {
        super(spacing);
        init();
    }

    public DesktopNav(Node... children) {
        super(children);
        init();
    }

    public DesktopNav(double spacing, Node... children) {
        super(spacing, children);
        init();
    }

    private void init() {
        this.getStyleClass().add("desktop-nav");
        this.setAlignment(Pos.CENTER);

        this.getChildren().addListener(new ListChangeListener<Node>() {
            @Override
            public void onChanged(Change<? extends Node> c) {
                while (c.next()) {
                    if (c.wasPermutated()) {
                        for (int i = c.getFrom(); i < c.getTo(); ++i) {
                            //permutate
                        }
                    } else if (c.wasUpdated()) {
                        //update item
                    } else {
                        for (Node additem : c.getAddedSubList()) {
                            DesktopNavItem stackPane = (DesktopNavItem) additem;
                            Button button = (Button) stackPane.getChildren().get(0);
                            Popup popup = new Popup();
                            Label label = new Label(button.getText());
                            label.getStyleClass().add("popup-text");
                            popup.getContent().add(label);
                            stackPane.setOnMouseExited(e->{
                                popup.hide();
                                animationButton(button, 1d,1d, 1d);
                                Button preButton = getPreButton(button);
                                Button nextButton = getNextButton(button);
                                animationButton(preButton, 1d,1d, 1d);
                                animationButton(nextButton, 1d,1d, 1d);
                            });
                            stackPane.setOnMouseEntered(e->{
                                animationButton(button, 1.2d, 1.2d, -30d);
                                Button preButton = getPreButton(button);
                                Button nextButton = getNextButton(button);
                                animationButton(preButton ,1.1d,1.1d, -10d);
                                animationButton(nextButton ,1.1d,1.1d, -10d);
                                final Text text = new Text(label.getText());
                                text.getStyleClass().add("popup-text");
                                new Scene(new Group(text));
                                text.applyCss();
                                final double width = text.getLayoutBounds().getWidth();

                                double anchorX = RegionUtil.anchorX(button) + button.getWidth()/2 - width/2 -1;
//                                System.out.println(String.format("bw:%s, lw:%s", button.getWidth(), width));
                                double anchorY = RegionUtil.anchorY(button) - 17;
                                popup.show(button.getScene().getWindow(), anchorX, anchorY);
                            });
                        }
                    }
                }
            }
        });
    }

    private void animationButton(Button button, double scaleX, double scaleY, double transalteY) {
        if (button == null) return;
        button.setScaleX(scaleX);
        button.setScaleY(scaleY);
        button.setTranslateY(transalteY);
    }

    private Button getPreButton(Button button) {
        for (int i=0; i<this.getChildren().size(); i++) {
            DesktopNavItem stackPane = (DesktopNavItem) getChildren().get(i);
            Button button2 = (Button) stackPane.getChildren().get(0);
            if (button2 == button) {
                if (i == 0) {
                    return null;
                }
                DesktopNavItem stackPane2 = (DesktopNavItem) getChildren().get(i-1);
                return (Button) stackPane2.getChildren().get(0);
            }
        }
        return null;
    }

    private Button getNextButton(Button button) {
        for (int i=0; i<this.getChildren().size(); i++) {
            DesktopNavItem stackPane = (DesktopNavItem) getChildren().get(i);
            Button button2 = (Button) stackPane.getChildren().get(0);
            if (button2 == button) {
                if (i == this.getChildren().size()-1) {
                    return null;
                }
                DesktopNavItem stackPane2 = (DesktopNavItem) getChildren().get(i+1);
                return (Button) stackPane2.getChildren().get(0);
            }
        }
        return null;
    }
}
