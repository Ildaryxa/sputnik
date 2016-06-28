import controller.LoginViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.tableFirmAutoEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import orm.HibernateSessionFactory;

import java.io.IOException;

/**
 * Created by ildar on 23.05.2016.
 */
public class Start extends Application{

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        new LoginViewController(primaryStage);
/*
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Автосервис 'Спутник'");

        initRootLayout();

        //showAdministration();
        HibernateSessionFactory.buildSessionFactory();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()){
            System.out.println("Все прошло успешно!");

            tableFirmAutoEntity firm = new tableFirmAutoEntity();
            firm.setFirm("Корола");
            session.save(firm);

        }


        //showAuthorization();
*/
    }

    private void showAuthorization() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Start.class.getResource("main/java/View/Authorization.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Спутник: авторизация пользователя");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(anchorPane);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showAdministration() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("main/java/View/Administration.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            rootLayout.setCenter(anchorPane);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() throws Exception {
        //HibernateSessionFactory.getSessionFactory().close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
