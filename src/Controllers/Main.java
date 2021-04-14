package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    Stage loginstage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.loginstage=primaryStage;
        LoginWindow();
//        MainMenuWindow();
    }

    public void LoginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/LogIn.fxml"));
            AnchorPane pane = loader.load();

            Scene scene = new Scene(pane);
            loginstage.setScene(scene);
            loginstage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MainMenuWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/MainMenu.fxml"));
            AnchorPane pane = loader.load();

            Scene scene = new Scene(pane);
            loginstage.setScene(scene);
            loginstage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
