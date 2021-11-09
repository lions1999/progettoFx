package logic.controller.guicontroller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.controller.applicationcontroller.ViewController;

public class MainGUI extends Application{

    public final ViewController view = new ViewController();
    public final AlertGUI alert = new AlertGUI();
    public static BorderPane border = new BorderPane();

    @Override public void start(Stage primaryStage) {
        Pane pane = view.getPage("Login");
        border.setCenter(pane);
        Scene scene = new Scene(border);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/logic/view/icon/1x/outline_apartment_white_24dp.png"));
        primaryStage.show();
    }

    public void fullScreen(Boolean bool){
        Stage stage = (Stage) border.getScene().getWindow();
        stage.setMaximized(bool);
    }
}
