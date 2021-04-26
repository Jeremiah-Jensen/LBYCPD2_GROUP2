package Controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserPayments {
    @FXML
    public Button LogOutButton, AppointmentsButton, DetailsButton, HomeButton;
    public AnchorPane InputCard, CardDetails;
    int count = 0;

    public void Switch(ActionEvent actionEvent) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), InputCard);
        translateTransition.setByY(-321);
        translateTransition.play();
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(1), CardDetails);
        translateTransition1.setByY(321);
        translateTransition1.play();
    }

    public void LogOut(ActionEvent actionEvent){
        new Main().LoginWindow();
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void MainMenu(ActionEvent actionEvent){
        new Main().MainMenuWindow();
        Stage closeStage = (Stage) HomeButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void UserAppointments(ActionEvent actionEvent){
        new Main().UserAppointmentsWindow();
        Stage closeStage = (Stage) AppointmentsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void UserDetails(ActionEvent actionEvent){
        new Main().UserDetailsWindow();
        Stage closeStage = (Stage) DetailsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}
