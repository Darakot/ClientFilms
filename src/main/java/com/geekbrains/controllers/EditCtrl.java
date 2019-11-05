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
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class EditCtrl extends AnchorPane {
    private ClientService clientREST = new ClientService();

    private User userEdit;

    public EditCtrl(User userEdit) {
        this.userEdit = userEdit;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/edit.fxml"));
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
    private Button Edit;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField firstNameField;

    @FXML
    void editUsr(ActionEvent event) {

    }

    @FXML
    void initialize() {
        nicknameField.setText(userEdit.getNickname());
        lastNameField.setText(userEdit.getLastName());
        firstNameField.setText(userEdit.getFirstName());
        passwordField.setText(userEdit.getPassword());
    }


    @FXML
    void editUser(ActionEvent event) {
        int response = clientREST.editUser(firstNameField.getText(), lastNameField.getText(), nicknameField.getText(), passwordField.getText(), userEdit);

        if (response == 200) {
            LoginCtrl winLogin = new LoginCtrl();
            Pane winLoginRoot = new Pane();
            winLoginRoot.getChildren().add(winLogin);
            Scene WinUsr = new Scene(winLoginRoot, 300, 170);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(WinUsr);
            stage.show();
        } else {
            log.info("Это хреново");
            System.out.println("Это хреново");
        }
    }

    @FXML
    void deleteUsr(ActionEvent event) {
        int response = clientREST.deleteUsr(userEdit.getNickname());
        if (response == 200) {
            LoginCtrl winLogin = new LoginCtrl();
            Pane winLoginRoot = new Pane();
            winLoginRoot.getChildren().add(winLogin);
            Scene WinUsr = new Scene(winLoginRoot, 300, 170);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(WinUsr);
            stage.show();
        } else {
            System.out.println("Это хреново");
        }
    }
}
