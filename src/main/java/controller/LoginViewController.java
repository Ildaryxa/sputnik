package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import orm.HibernateSessionFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ildar on 26.06.2016.
 */
public class LoginViewController implements Initializable {

    private static Stage stage;
    private BorderPane rootLayout;

    @FXML ComboBox<HibernateSessionFactory.DBMS> schemeCBox;
    @FXML TextField loginTextField;
    @FXML PasswordField passTextField;

    public LoginViewController() {
    }

    public LoginViewController(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("view/login.view.fxml"));
        rootLayout = loader.load();
        stage.setTitle("Автосервис 'Спутник' - Вход");
        stage.setScene(new Scene(rootLayout));
        LoginViewController.stage = stage;
        LoginViewController.stage.setResizable(false);
        LoginViewController.stage.show();
        initMenuBar();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCBox();
    }

    private void initMenuBar() {
        MenuBar menuBar = (MenuBar) rootLayout.getTop();
        menuBar.getMenus().clear();
        Menu about = new Menu("О компании");
        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        Menu reviews = new Menu("Отзывы");
        reviews.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        menuBar.getMenus().addAll(about, reviews);
    }

    private void initCBox() {
        List<HibernateSessionFactory.DBMS> list = new ArrayList<>();
        Collections.addAll(list, HibernateSessionFactory.DBMS.values());
        schemeCBox.setItems(FXCollections.observableArrayList(list));
        schemeCBox.getSelectionModel().select(HibernateSessionFactory.DBMS.MSSQL);
        HibernateSessionFactory.buildSessionFactory(HibernateSessionFactory.DBMS.MSSQL);
        /*
        schemeCBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            HibernateSessionFactory.buildSessionFactory(newValue);
            System.out.println(newValue);
        });*/


    }

    public void exit(ActionEvent actionEvent){
        System.exit(0);
    }

    public void authorization(ActionEvent actionEvent){

    }

    public void registration(ActionEvent actionEvent) throws IOException {
        new UserInputViewController(stage, UserInputViewController.FormType.Registration);

    }



}
