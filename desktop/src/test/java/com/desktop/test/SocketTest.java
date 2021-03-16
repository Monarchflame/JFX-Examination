//package ldh.desktop.test;
//
//import java.io.File;
//
//import org.james.component.ButtonBox;
//import org.james.component.FileReceiverGrid;
//import org.james.component.FileSenderGrid;
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//
//
//public class SocketTest extends Application {
//
//    public static Stage primaryStage;
//
//    @Override
//    public void start(Stage primaryStage) {
//        try {
//            this.primaryStage = primaryStage;
//            primaryStage.setFullScreen(false);
//            primaryStage.setResizable(false);
//
//            FileReceiverGrid fileReceiverGrid = new FileReceiverGrid();
//            fileReceiverGrid.initialize();
//            FileSenderGrid fileSenderGrid = new FileSenderGrid();
//            fileSenderGrid.initialize();
//
//            ButtonBox buttonBox = new ButtonBox();
//            buttonBox.initialize();
//
//            BorderPane root = new BorderPane();
//            root.setTop(fileReceiverGrid);
//            root.setBottom(buttonBox);
//
//            buttonBox.getReceiveFileFunc().setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    buttonBox.getReceiveFileFunc().setDisable(true);
//                    buttonBox.getSendFileFunc().setDisable(false);
//                    root.setTop(fileReceiverGrid);
//                }
//            });
//
//            buttonBox.getSendFileFunc().setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    buttonBox.getReceiveFileFunc().setDisable(false);
//                    buttonBox.getSendFileFunc().setDisable(true);
//                    root.setTop(fileSenderGrid);
//                }
//            });
//
//            fileSenderGrid.getSelectFileBtn().setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    FileChooser fileChooser = new FileChooser();
//                    fileChooser.setTitle("打开文件");
//                    File selectedFile = fileChooser.showOpenDialog(primaryStage);
//                    if (selectedFile != null) {
//                        fileSenderGrid.setFile(selectedFile);
//                        fileSenderGrid.getFileNameLabel().setText(selectedFile.getPath());
//                    }
//                }
//            });
//
//            Scene scene = new Scene(root, 800, 400);
//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}