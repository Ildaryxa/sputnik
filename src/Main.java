import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by ildar on 23.05.2016.
 */
public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Автосервис 'Спутник'");

        initRootLayout();

        showAdministration();

        showAuthorization();


    }

    private void showAuthorization() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("View/Authorization.fxml"));
            AnchorPane anchorPane = loader.load();

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
            loader.setLocation(Main.class.getResource("View/Administration.fxml"));
            AnchorPane anchorPane = loader.load();

            rootLayout.setCenter(anchorPane);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("View/RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
