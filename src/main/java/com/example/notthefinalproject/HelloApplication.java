package com.example.notthefinalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 600);
        stage.setTitle("CompTracker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {


        FileReader.getDataList();
        launch();
    }
}