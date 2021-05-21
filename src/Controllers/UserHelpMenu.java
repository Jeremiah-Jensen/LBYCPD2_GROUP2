package Controllers;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class UserHelpMenu {

    public Button AppointmentsButton, HomeButton, PaymentsButton, UserDetailsButton, LogOutButton;

    public void UserHome(ActionEvent actionEvent) {
        new Main().loadFXML("MainMenu");
        Stage closeStage = (Stage) HomeButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void UserAppointments(ActionEvent actionEvent) {
        new Main().loadFXML("UserAppointments");
        Stage closeStage = (Stage) AppointmentsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void UserPayments(ActionEvent actionEvent) {
        new Main().loadFXML("UserPayments");
        Stage closeStage = (Stage) PaymentsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void UserDetails(ActionEvent actionEvent) {
        new Main().loadFXML("UserDetails");
        Stage closeStage = (Stage) UserDetailsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void UserLogOut(ActionEvent actionEvent) {
        new Main().loadFXML("LogIn");
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}
