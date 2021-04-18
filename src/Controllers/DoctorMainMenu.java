package Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class DoctorMainMenu {

    public Button UserDetailsButton, LogOutButton, AppointmentsButton, PatientButton;

    public void DoctorDetails(ActionEvent actionEvent) {
        new Main().DoctorDetails();
        Stage closeStage = (Stage) UserDetailsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorLogOut(ActionEvent actionEvent) {
        new Main().LoginWindow();
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorAppointments(ActionEvent actionEvent) {
        new Main().DoctorAppointment();
        Stage closeStage = (Stage) AppointmentsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorPatient(ActionEvent actionEvent) {
        new Main().DoctorPatients();
        Stage closeStage = (Stage) PatientButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}
