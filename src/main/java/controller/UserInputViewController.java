package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.tableUsersEntity;
import orm.DataBase;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by ildar on 27.06.2016.
 */
public class UserInputViewController implements Initializable{

    DataBase db;
    List<String> loginName;

    @FXML TextField nameField;
    @FXML TextField surnameField;
    @FXML TextField loginField;
    @FXML PasswordField passwordField;
    @FXML CheckBox isAdmin;
    @FXML CheckBox isLocked;
    @FXML DatePicker dataRegistration;

    public enum FormType{
        RegistrationAdmin,  //регистрация из админки
        UpdateAdmin, //изменение из админки
        Update, //изменить
        Registration // регистрация
    }
    private static Stage stage;
    private AnchorPane rootLayout;

    public UserInputViewController() {
    }

    public UserInputViewController(Stage primaryStage, FormType frmType) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("view/user_input.view.fxml"));
        rootLayout = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        stage.setResizable(false);
        stage.setScene(new Scene(rootLayout));
        Pane pane = (Pane) rootLayout.lookup("#PanelAdmin");
        Button button = (Button) rootLayout.lookup("#BtnRegUp");
        UserInputViewController.stage = stage;
        UserInputViewController.stage.show();
        switch (frmType){
            case RegistrationAdmin:
                stage.setTitle("'Спутник' - Регистрация");
                button.setText("Зарегистрировать");
                break;
            case UpdateAdmin:
                stage.setTitle("'Спутник' - Изменение");
                button.setText("Изменить данные");
                break;
            case Update:
                stage.setTitle("'Спутник' - Изменение");
                button.setText("Изменить данные");
                pane.setVisible(false);
                stage.setHeight(stage.getHeight()- pane.getHeight());
               break;
            case Registration:
                stage.setTitle("'Спутник' - Регистрация");
                button.setText("Зарегистрировать");
                stage.setHeight(stage.getHeight()- pane.getHeight());
                pane.setVisible(false);
                break;
        }
    }

    public void exit(ActionEvent actionEvent){
        stage.close();
    }

    public void regestration(ActionEvent actionEvent){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = DataBase.INSTANCE;
        ObservableList<tableUsersEntity> users = db.getUsersData();

        loginField.textProperty().addListener((observable, oldValue, newValue) -> {
            loginTextChange(newValue);
        });
    }

    private void loginTextChange(String newValue){

    }


}
