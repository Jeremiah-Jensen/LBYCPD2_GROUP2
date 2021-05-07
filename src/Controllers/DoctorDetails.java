package Controllers;

import Models.Doctor;
import com.firebase.client.Firebase;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DoctorDetails implements Initializable {

    public Button LogOutButton, AppointmentsButton, PatientsButton, HomeButton;
    public Text FirstName, ContactNumber, Email, Birthday, Gender, Address, Subspecialty;
    public TextField FirstNameEdit, LastNameEdit, BirthdayEdit, GenderEdit, NumberEdit, EmailEdit, AddressEdit, SubspecialtyEdit;
    public ImageView ProfilePhoto;
    public String path = "Default.png";
    Doctor doctorModel;
    Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");

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

    public void ProfilePic(ActionEvent actionEvent) {
        doctorModel = DoctorLogin.doctorModel;
        File file;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Pic");
        file = fileChooser.showOpenDialog(null);
        path = file.getName();
        Image image = new Image(path);
        ProfilePhoto.setImage(image);
        firebase.child("Doctor").child(doctorModel.getId()).child("picture").setValue(path);
    }

    public void EditInformation(ActionEvent actionEvent) {
        doctorModel = DoctorLogin.doctorModel;
        firebase.child("Doctor").child(doctorModel.getId()).child("firstName").setValue(FirstNameEdit.getText());
        firebase.child("Doctor").child(doctorModel.getId()).child("lastName").setValue(LastNameEdit.getText());
        firebase.child("Doctor").child(doctorModel.getId()).child("birthday").setValue(BirthdayEdit.getText());
        firebase.child("Doctor").child(doctorModel.getId()).child("gender").setValue(GenderEdit.getText());
        firebase.child("Doctor").child(doctorModel.getId()).child("contactNumber").setValue(NumberEdit.getText());
        firebase.child("Doctor").child(doctorModel.getId()).child("email").setValue(EmailEdit.getText());
        firebase.child("Doctor").child(doctorModel.getId()).child("address").setValue(AddressEdit.getText());
        firebase.child("Doctor").child(doctorModel.getId()).child("subspecialty").setValue(SubspecialtyEdit.getText());

        String fullName = FirstNameEdit.getText() + " " + LastNameEdit.getText();
        FirstName.setText(fullName);
        ContactNumber.setText(NumberEdit.getText());
        Email.setText(EmailEdit.getText());
        Birthday.setText(BirthdayEdit.getText());
        Gender.setText(GenderEdit.getText());
        Address.setText(AddressEdit.getText());
        Subspecialty.setText(SubspecialtyEdit.getText());

        FirstNameEdit.setText(" ");
        LastNameEdit.setText(" ");
        BirthdayEdit.setText(" ");
        GenderEdit.setText(" ");
        NumberEdit.setText(" ");
        EmailEdit.setText(" ");
        AddressEdit.setText(" ");
        SubspecialtyEdit.setText(" ");
    }

    public void Cancel(ActionEvent actionEvent) {
        FirstNameEdit.setText(" ");
        LastNameEdit.setText(" ");
        BirthdayEdit.setText(" ");
        GenderEdit.setText(" ");
        NumberEdit.setText(" ");
        EmailEdit.setText(" ");
        AddressEdit.setText(" ");
        SubspecialtyEdit.setText(" ");
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
        new Main().loadFXML("LogIn");
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorHome(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorMainMenu");
        Stage closeStage = (Stage) HomeButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}
