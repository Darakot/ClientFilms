package com.geekbrains.controllers;

import com.geekbrains.model.ClientService;
import com.geekbrains.domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginCtrl extends AnchorPane {
    private ClientService clientREST = new ClientService();

    public LoginCtrl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/login.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField nicknameField;

    @FXML
    private Button signUp;

    @FXML
    private Button SignIn;

    @FXML
    void initialize() {
    }

    @FXML
    void openRegistrationWin(ActionEvent event) {
        RegistrationCtrl windowReg = new RegistrationCtrl();
        Pane RegRoot = new Pane();
        RegRoot.getChildren().add(windowReg);
        Scene registration = new Scene(RegRoot, 300, 260);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(registration);
        stage.show();
    }

    @FXML
    void openWinUsr(ActionEvent event) {
        User user = clientREST.getUser(nicknameField.getText());

        if (user.getPassword().equals(passwordField.getText())) {

            WindowUserCtrl windowReg = new WindowUserCtrl(user);
            Pane UsrWinRoot = new Pane();
            UsrWinRoot.getChildren().add(windowReg);
            Scene WinUsr = new Scene(UsrWinRoot, 440, 670);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(WinUsr);
            stage.show();
        } else {
            System.out.println("Бадабум");
        }
    }

}
