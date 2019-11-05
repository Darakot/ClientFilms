package com.geekbrains.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.geekbrains.model.MovieService;
import com.geekbrains.model.ClientService;
import com.geekbrains.domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class WindowUserCtrl extends AnchorPane {
    private ClientService clientService = new ClientService();

    private MovieService choiceService = new MovieService();

    private User userMainWin;


    public WindowUserCtrl(User userMainWin) {
        this.userMainWin = userMainWin;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/windowUser.fxml"));
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
    private ListView<String> lookList;

    @FXML
    private ListView<String> favoriteList;

    @FXML
    private ListView<String> watchedList;

    @FXML
    private TextField film1;

    @FXML
    private ChoiceBox<?> choiceFilm1;

    @FXML
    private TextField film2;

    @FXML
    private ChoiceBox<?> choiceFilm2;

    @FXML
    private TextField film3;

    @FXML
    private ChoiceBox<?> choiceFilm3;

    @FXML
    private TextField film4;

    @FXML
    private ChoiceBox<?> choiceFilm4;

    @FXML
    private TextField film5;

    @FXML
    private ChoiceBox<?> choiceFilm5;

    @FXML
    private Button getFilms;

    @FXML
    private Button saveList;

    @FXML
    private Label labelNickname;

    @FXML
    private Button EditProfile;

    @FXML
    private Button loguot;

    @FXML
    void initialize() {
        labelNickname.setText(userMainWin.getNickname());
        loadList();
    }

    @FXML
    void openWinUsrEdit(ActionEvent event) {
        EditCtrl windowEdit = new EditCtrl(userMainWin);

        Pane RegRoot = new Pane();
        RegRoot.getChildren().add(windowEdit);
        Scene registration = new Scene(RegRoot, 300, 260);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(registration);
        stage.show();
    }


    @FXML
    void loguotUser(ActionEvent event) {
        LoginCtrl winLogin = new LoginCtrl();
        Pane winLoginRoot = new Pane();
        winLoginRoot.getChildren().add(winLogin);
        Scene WinUsr = new Scene(winLoginRoot, 300, 170);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(WinUsr);
        stage.show();
    }

    private void loadList() {
        String nickname = userMainWin.getNickname();

        List<String> lookListREST = choiceService.getLook(nickname);
        List<String> favoriteListREST = choiceService.getFavorite(nickname);
        List<String> watchedListREST = choiceService.getWatched(nickname);

        ObservableList<String> observableLookList = FXCollections.observableList(lookListREST);
        ObservableList<String> observableFavoriteList = FXCollections.observableList(favoriteListREST);
        ObservableList<String> observableWatchedList = FXCollections.observableList(watchedListREST);

        lookList.setItems(observableLookList);
        favoriteList.setItems(observableFavoriteList);
        watchedList.setItems(observableWatchedList);
    }
}

