package Controllers;

import com.firebase.client.Firebase;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    public static Stage mainStage;

    public void start(Stage primaryStage) throws Exception {
        this.mainStage = primaryStage;
        LoginWindow();
    }

    public void LoginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/LogIn.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFXML(String file) {
        String fxml = "../FXML Files/" + file + ".fxml";
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    mainStage = new Stage();
                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void CloseButton(Stage stage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage.close();
            }
        });
    }

    public static void main (String[]args){
        launch(args);
    }

}