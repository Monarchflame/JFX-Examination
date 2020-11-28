package ldh.descktop.ui;

import com.jfoenix.controls.JFXDecorator;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 桌面页面
 * @author qixiaotian
 * @date 2020/11/28
 */
public class FxDialog extends Stage {

    private Parent content;
    private JFXDecorator jfxDecorator;

    public FxDialog(Stage parentStage) {
        this.initOwner(parentStage);
        this.initStyle(StageStyle.TRANSPARENT);
        this.iconifiedProperty().addListener((ob, n, o)->{
            System.out.println("iconified listener!!");
            if (ob.getValue()) {
                FxDialog.this.hide();
            } else {
                FxDialog.this.show();
            }
        });
    }

    public void setContent(Node parent) {
        jfxDecorator = new JFXDecorator(this, parent);
        Scene scene = new Scene(jfxDecorator);
        scene.setFill(null);
        this.setScene(scene);
    }

    public void setContent(Node parent, int width, int height) {
        jfxDecorator = new JFXDecorator(this, parent);
        Scene scene = new Scene(jfxDecorator, width, height);
        scene.setFill(null);
        this.setScene(scene);
    }
}
