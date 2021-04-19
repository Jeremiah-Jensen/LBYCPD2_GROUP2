package Controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class UserAppointments implements Initializable {
    @FXML
    public AnchorPane ScheduleAppointment,UpcomingAppointments, PreviousAppointments;
    public Button LogOutButton, HomeButton, ScheduleButton;
    int count = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Switch(ActionEvent actionEvent) {
        double move = 0;
        if(count== 0){
            move = -351;
            count = 1;
            ScheduleButton.setText("Schedule Appointment");
        }
        else{
            move = 351;
            count = 0;
            ScheduleButton.setText("View Previous Appointments");
        }
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), UpcomingAppointments);
        translateTransition.setByY(move);
        translateTransition.play();
        TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(1), ScheduleAppointment);
        translateTransition2.setByY(move);
        translateTransition2.play();
        TranslateTransition translateTransition3 = new TranslateTransition(Duration.seconds(1), PreviousAppointments);
        translateTransition3.setByY(move);
        translateTransition3.play();
    }

    public void MainMenu(ActionEvent actionEvent){
        new Main().MainMenuWindow();
        Stage closeStage = (Stage) HomeButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void LogOut(ActionEvent actionEvent){
        new Main().LoginWindow();
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}
