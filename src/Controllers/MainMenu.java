package Controllers;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    @FXML
    public AnchorPane ScheduleAppointment;

    @FXML
    private AnchorPane rootPane;

    public void ScheduleAppointment(ActionEvent actionEvent){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), ScheduleAppointment);
        translateTransition.setByX(310);
        translateTransition.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}