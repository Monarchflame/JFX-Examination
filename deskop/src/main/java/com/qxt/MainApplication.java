package com.qxt;

import com.qxt.view.LoginView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.scene.image.Image;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;

/**
 * @author qxt
 * @date 2020/12/2 15:33
 */
@SpringBootApplication
public class MainApplication extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launch(MainApplication.class, LoginView.class, args);
    }

    // 虽然在application.yml中可以设置应用图标，但是首屏启动时的应用图标未改变，建议在此处覆盖默认图标
    @Override
    public List<Image> loadDefaultIcons() {
        return Collections.singletonList(new Image(getClass().getResource("/images/icon.png").toExternalForm()));
    }
}
