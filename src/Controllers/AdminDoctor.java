package Controllers;

import Models.Appointments;
import Models.Doctor;
import Models.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminDoctor implements Initializable {
    @FXML
    public AnchorPane ManageChildren, Appointments;
    public ImageView UserImage;
    public Label Username, Name, Gender, Birthday, Email, Number, Subspecialty;
    public ComboBox<String> DoctorBox;
    public ListView<String> AppointmentsList;
    public Button LogOutButton, UserButton;
    Doctor doctorModel;
    List<Doctor> doctorList = new ArrayList<>();
    List<Models.Appointments> appointmentsList = new ArrayList<>();
    Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firebase.child("Doctor").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Doctor doctor = data.getValue(Doctor.class);
                    doctorList.add(doctor);
                }
                for (int i = 0; i < doctorList.size(); i++) {
                    Doctor doctorModel = doctorList.get(i);
                    DoctorBox.getItems().add(doctorModel.getFirstName() + " " + doctorModel.getLastName() + "-" + doctorModel.getUsername());
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        firebase.child("Appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Models.Appointments app = data.getValue(Appointments.class);
                    appointmentsList.add(app);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void SelectDoctor(ActionEvent actionEvent){
        for (int i = 0; i < doctorList.size(); i++) {
            doctorModel = doctorList.get(i);
            if (DoctorBox.getValue().equals(doctorModel.getFirstName() + " " + doctorModel.getLastName() + "-" + doctorModel.getUsername())) {
                AppointmentsList.getItems().clear();
                String FullName = "Dr." + doctorModel.getFirstName() + " " + doctorModel.getLastName();
                Username.setText(doctorModel.getUsername());
                Name.setText(FullName);
                Birthday.setText(doctorModel.getBirthday());
                Gender.setText(doctorModel.getGender());
                Email.setText(doctorModel.getEmail());
                Number.setText(doctorModel.getContactNumber());
                Subspecialty.setText(doctorModel.getSubspecialty());
                Image image = new Image(doctorModel.getPicture());
                UserImage.setImage(image);

                for (int x = 0; x < appointmentsList.size(); x++) {
                    Appointments appointmentsModel = appointmentsList.get(x);
                    if (FullName.equals(appointmentsModel.getDoctor())) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                AppointmentsList.getItems().add(appointmentsModel.getAppointment()+"  | STATUS: " + appointmentsModel.getStatus());
                            }
                        });

                    }
                }
            }}
    }

    public void User(ActionEvent actionEvent){
        new Main().loadFXML("AdminUser");
        Stage closeStage = (Stage) UserButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void LogOut(ActionEvent actionEvent){
        new Main().LoginWindow();
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}
