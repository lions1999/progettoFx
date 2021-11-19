package logic.controller.guicontroller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.controller.applicationcontroller.ViewController;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Chooser extends Application {

  //  public static Stage stage = new Stage();

//    private ViewController view = new ViewController();
//
//    private static void open(Stage stage, CompletableFuture<File> future) {
//        Platform.runLater(() -> {
//            FileChooser fileChooser = new FileChooser();
//            future.complete(fileChooser.showSaveDialog(stage)); // fill future with result
//        });
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//            new Thread(() -> {
//                for (int i = 0; i < 5; i++) {
//                    CompletableFuture<File> future = new CompletableFuture<>();
//                    open(primaryStage, future);
//                    try {
//                        File file = future.get(); // wait for future to be assigned a result and retrieve it
//                        System.out.println(file == null ? "no file chosen" : file.toString());
//                    } catch (InterruptedException | ExecutionException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }).start();
//        primaryStage.setScene(new Scene(new AnchorPane()));
//        primaryStage.showAndWait();
//        primaryStage.setOnHidden(windowEvent -> {
//            System.out.println("HIDDEN");
//        });
//
//        primaryStage.setOnHiding(windowEvent -> {
//            System.out.println("HIDING");
//        });ù
//        while (primaryStage.isShowing()) {
//            System.out.println("Ciao");
//
//        }
        FileChooser chooser = new FileChooser();
        chooser.showOpenDialog(primaryStage);




        //primaryStage.showAndWait();

    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        FileChooser chooser = new FileChooser();
//        chooser.showOpenDialog(stage.getOwner());
//    }
}
