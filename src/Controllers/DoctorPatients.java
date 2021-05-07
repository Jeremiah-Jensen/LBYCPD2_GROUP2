package Controllers;

import Models.Appointments;
import Models.Children;
import Models.Doctor;
import Models.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class DoctorPatients implements Initializable {
    public Button UserDetailsButton, LogOutButton, AppointmentsButton, HomeButton;
    public ListView PatientsList, PrevPatientsList;
    public TextArea PatientsInformation;
    public TextField PatientName;
    List<Children> childrenList = new ArrayList<>();
    List<Appointments> appointmentsList = new ArrayList<>();
    Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
    Doctor doctorModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doctorModel = DoctorLogin.doctorModel;
        String fullnameDoctor = "Dr." + doctorModel.getFirstName() + " " + doctorModel.getLastName();
        firebase.child("Appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Appointments appointments = data.getValue(Appointments.class);
                    appointmentsList.add(appointments);
                }
                for(int i = 0; i < appointmentsList.size(); i++) {
                    Appointments appointments = appointmentsList.get(i);
                    if(fullnameDoctor.equals(appointments.getDoctor()) && appointments.getStatus().equals("Consultation") || appointments.getStatus().equals("Monitor")) {
                        PatientsList.getItems().add(appointments.getChild());
                        PatientsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                    }
                    else if(fullnameDoctor.equals(appointments.getDoctor()) && appointments.getStatus().equals("Discharge")) {
                        PrevPatientsList.getItems().add(appointments.getChild());
                        PrevPatientsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        firebase.child("Child").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Children child = data.getValue(Children.class);
                    childrenList.add(child);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void DoctorDetails(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorDetails");
        Stage closeStage = (Stage) UserDetailsButton.getScene().getWindow();
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

    public void SearchPatient(ActionEvent actionEvent) {
        PatientsInformation.setText(null);
        for(int i = 0; i < childrenList.size(); i++) {
            Children child = childrenList.get(i);
            String fullname = child.getFirstname() + " " + child.getLastname();
            if(PatientsList.getSelectionModel().getSelectedItem().equals(fullname)) {
                PatientsInformation.appendText(fullname + "\n");
                PatientsInformation.appendText(child.getBirthday() + "\n");
                PatientsInformation.appendText(child.getConditions() + "\n");
            }
        }
    }

    public void SearchPrevPatient(ActionEvent actionEvent) {
        PatientsInformation.setText(null);
        for(int i = 0; i < childrenList.size(); i++) {
            Children child = childrenList.get(i);
            String fullname = child.getFirstname() + " " + child.getLastname();
            if(PrevPatientsList.getSelectionModel().getSelectedItem().equals(fullname)) {
                PatientsInformation.appendText(fullname + "\n");
                PatientsInformation.appendText(child.getBirthday() + "\n");
                PatientsInformation.appendText(child.getConditions() + "\n");
            }
        }
    }

    public void DoctorHome(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorMainMenu");
        Stage closeStage = (Stage) HomeButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}
