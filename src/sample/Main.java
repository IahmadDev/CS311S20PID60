package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Schedule Maker");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        //Icon Added
        Image anotherIcon = new Image("file:icon.png");
        primaryStage.getIcons().add(anotherIcon);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
