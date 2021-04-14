package Controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MainMenu {
    @FXML
    public AnchorPane ScheduleAppointment;

    public void ScheduleAppointment(ActionEvent actionEvent){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), ScheduleAppointment);
        translateTransition.setByX(310);
        translateTransition.play();
    }
}
