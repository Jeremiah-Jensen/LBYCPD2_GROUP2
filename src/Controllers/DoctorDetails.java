package Controllers;

import Models.Doctor;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class DoctorDetails implements Initializable {

    public Button LogOutButton, AppointmentsButton, PatientsButton;
    public Text FirstName, ContactNumber, Email, Birthday, Gender;
    public String username;
    List<Doctor> doctorList = new ArrayList<>();

    public void Username(String username) {
        this.username = username;
    }

    public String returnUsername() {
        return username;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
        firebase.child("Doctor").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Doctor doctor = data.getValue(Doctor.class);
                    doctorList.add(doctor);
                }
                for(int i = 0; i < doctorList.size(); i++) {
                    Doctor doctor = doctorList.get(i);
                    if(doctor.getUsername().equals(returnUsername())) {
                        System.out.println();
                        FirstName.setText(doctor.getFirstName() + " " + doctor.getLastName());
                        ContactNumber.setText(doctor.getContactNumber());
                        Email.setText(doctor.getEmail());
                        Birthday.setText(doctor.getBirthday());
                        Gender.setText(doctor.getGender());
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void DoctorPatients(ActionEvent actionEvent) {
        new Main().DoctorPatients();
        Stage closeStage = (Stage) PatientsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorAppointments(ActionEvent actionEvent) {
        new Main().DoctorAppointment();
        Stage closeStage = (Stage) AppointmentsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);

    }

    public void DoctorLogOut(ActionEvent actionEvent) {
        new Main().LoginWindow();
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

}
