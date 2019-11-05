package com.geekbrains.controllers;

import com.geekbrains.model.ClientService;
import com.geekbrains.domain.User;
import javafx.event.ActionEvent;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationCtrl extends AnchorPane {

    private ClientService clientREST = new ClientService();

    public RegistrationCtrl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/registration.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField nicknameField;

    @FXML
    private Button registration;

    @FXML
    void initialize() {
    }

    @FXML
    void registrationUsr(ActionEvent event) {
        int response = clientREST.createUser(firstNameField.getText(),
                lastNameField.getText(),
                nicknameField.getText(),
                passwordField.getText());
        User newUser = clientREST.getUser(nicknameField.getText());
        if (response == 201) {
            WindowUserCtrl windowReg = new WindowUserCtrl(newUser);
            Pane UsrWinRoot = new Pane();
            UsrWinRoot.getChildren().add(windowReg);
            Scene WinUsr = new Scene(UsrWinRoot, 440, 670);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(WinUsr);
            stage.show();
        } else {
            System.out.println("Это хреново");
        }
    }
}

