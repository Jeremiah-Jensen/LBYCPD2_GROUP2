package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMainMenu implements Initializable {
    @FXML
    public Button LogOutButton, AppointmentsButton, DetailsButton, PaymentsButton;

    public void LogOut(ActionEvent actionEvent){
        new Main().LoginWindow();
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
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

    public void UserPayments(ActionEvent actionEvent){
        new Main().UserPaymentsWindow();
        Stage closeStage = (Stage) PaymentsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}