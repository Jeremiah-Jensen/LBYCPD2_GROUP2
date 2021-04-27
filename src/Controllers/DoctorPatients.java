package Controllers;

import Models.Doctor;
import Models.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class DoctorPatients implements Initializable {
    public Button UserDetailsButton, LogOutButton, AppointmentsButton, HomeButton;
    public ListView PatientsList;
    public TextArea PatientsInformation;
    public TextField PatientName;
    List<User> userList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
        firebase.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User user = data.getValue(User.class);
                    userList.add(user);
                }
                for(int i = 0; i < userList.size(); i++) {
                    User userModel = userList.get(i);
                    PatientsList.getItems().add(userModel.getFirstName() + " " + userModel.getLastName() + "\n");
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

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

    public void SearchPatient(ActionEvent actionEvent) {
        PatientsInformation.setText(null);
        for(int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            String fullname = user.getFirstName() + " " + user.getLastName();
            if(PatientName.getText().equals(fullname.toLowerCase())) {
                PatientsInformation.appendText(user.getFirstName() + " " + user.getLastName() + "\n");
                PatientsInformation.appendText(user.getBirthday() + "\n");
                PatientsInformation.appendText(user.getContactNumber() + "\n");
                PatientsInformation.appendText(user.getEmail() + "\n");
                PatientsInformation.appendText(user.getGender() + "\n\n");
            }
        }
    }

    public void DoctorHome(ActionEvent actionEvent) {
        new Main().DoctorMainMenu();
        Stage closeStage = (Stage) HomeButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}
