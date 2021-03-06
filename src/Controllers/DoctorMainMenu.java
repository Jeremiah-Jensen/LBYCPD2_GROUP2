package Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class DoctorMainMenu {

    public Button UserDetailsButton, LogOutButton, AppointmentsButton, PatientButton, HelpButton, HomeButton;

    public void DoctorHome(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorMainMenu");
        Stage closeStage = (Stage) HomeButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorDetails(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorDetails");
        Stage closeStage = (Stage) UserDetailsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorHelp(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorHelpMenu");
        Stage closeStage = (Stage) HelpButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorLogOut(ActionEvent actionEvent) {
        new Main().loadFXML("LogIn");
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorAppointments(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorAppointments");
        Stage closeStage = (Stage) AppointmentsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorPatient(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorPatients");
        Stage closeStage = (Stage) PatientButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}

