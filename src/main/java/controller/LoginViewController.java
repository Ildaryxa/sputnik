package controller;

import crypt.HashText;
import crypt.Salt;
import enumType.FormType;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.tableUsersEntity;
import orm.DataBase;
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
    DataBase db;

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
        db = DataBase.INSTANCE;
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
        String salt = Salt.generator();
        System.out.println(salt);
        System.out.println(HashText.getHash("1111",salt));

    }

    public void registration(ActionEvent actionEvent) throws IOException {
        tableUsersEntity user = new tableUsersEntity();
        boolean okClicked = showUserInputView(user);
        if (okClicked){
            /*
            System.out.println(user.getName());
            System.out.println(user.getSurname());
            System.out.println(user.getLogin());
            System.out.println(user.getSalt());
            System.out.println(user.getPasswordHash());
            System.out.println(user.getDataRegistration());
            System.out.println(user.isAdmin());
            System.out.println(user.isLocked());
            */
            db.addUser(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(stage);
            alert.setTitle("Регистрация");
            alert.setHeaderText("Пользователь успешно зарегистрирован");
            alert.showAndWait();
            //new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK).showAndWait();
        }
    }

    private boolean showUserInputView(tableUsersEntity usersEntity){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/user_input.view.fxml"));
            AnchorPane rootLayout = loader.load();

            //stage
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setResizable(false);
            dialogStage.setScene(new Scene(rootLayout));
            dialogStage.setTitle("'Спутник' - Регистрация");

            //create controller
            UserInputViewController controller = loader.getController();
            controller.setDialogStage(dialogStage, FormType.Registration);
            controller.setUser(usersEntity);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        }catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
    }



}
