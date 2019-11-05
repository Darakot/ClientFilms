package com.geekbrains;

import com.geekbrains.controllers.LoginCtrl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginCtrl parent = new LoginCtrl();

        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(parent, 300, 170));
        primaryStage.show();
    }
}
