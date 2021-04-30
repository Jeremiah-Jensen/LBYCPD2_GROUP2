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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class DoctorDetails implements Initializable {

    public Button LogOutButton, AppointmentsButton, PatientsButton, HomeButton;
    public Text FirstName, ContactNumber, Email, Birthday, Gender, Address, Subspecialty;
    public ImageView ProfilePhoto;
    public String path = "Default.png";
    Doctor doctorModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doctorModel = DoctorLogin.doctorModel;
        String fullName = doctorModel.getFirstName() + " " + doctorModel.getLastName();
        FirstName.setText(fullName);
        ContactNumber.setText(doctorModel.getContactNumber());
        Email.setText(doctorModel.getEmail());
        Birthday.setText(doctorModel.getBirthday());
        Gender.setText(doctorModel.getGender());
        Address.setText(doctorModel.getAddress());
        Subspecialty.setText(doctorModel.getSubspecialty());
        path = doctorModel.getPicture();
        Image image = new Image(path);
        ProfilePhoto.setImage(image);
    }

    public void DoctorPatients(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorPatients");
        Stage closeStage = (Stage) PatientsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorAppointments(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorAppointments");
        Stage closeStage = (Stage) AppointmentsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);

    }

    public void DoctorLogOut(ActionEvent actionEvent) {
        new Main().LoginWindow();
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorHome(ActionEvent actionEvent) {
    new Main().loadFXML("DoctorMainMenu");
        Stage closeStage = (Stage) HomeButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}
