package Controllers;

import Models.Doctor;
import com.firebase.client.Firebase;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

    public Button LogOutButton, AppointmentsButton, PatientsButton, HomeButton, HelpButton;
    public Text FirstName, ContactNumber, Email, Birthday, Gender, Address, Subspecialty, Warning;
    public TextField FirstNameEdit, LastNameEdit, AddressLineEdit, NumberEdit, EmailEdit, AddressEdit, SubspecialtyEdit;
    public ComboBox<String> EditDay, EditMonth, EditYear, GenderEdit;
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

        for (int i = 0; i < 80; i++){
            int day = 2003 - i;
            String result = String.valueOf(day);
            EditYear.getItems().add(result);
        }
        for (int i = 1; i < 32; i++){
            String result = String.valueOf(i);
            EditDay.getItems().add(result);
        }
        EditMonth.getItems().add("January");
        EditMonth.getItems().add("February");
        EditMonth.getItems().add("March");
        EditMonth.getItems().add("April");
        EditMonth.getItems().add("May");
        EditMonth.getItems().add("June");
        EditMonth.getItems().add("July");
        EditMonth.getItems().add("August");
        EditMonth.getItems().add("September");
        EditMonth.getItems().add("October");
        EditMonth.getItems().add("November");
        EditMonth.getItems().add("December");

        GenderEdit.getItems().add("Male");
        GenderEdit.getItems().add("Female");
        GenderEdit.getItems().add("Non-Binary");
        GenderEdit.getItems().add("Prefer not to say");
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

        if(FirstNameEdit.getText().isEmpty() || LastNameEdit.getText().isEmpty() ||  NumberEdit.getText().isEmpty() || EmailEdit.getText().isEmpty() || AddressEdit.getText().isEmpty() || AddressLineEdit.getText().isEmpty() || SubspecialtyEdit.getText().isEmpty()) {
            Warning.setVisible(true);
            Warning.setText("Missing Details");
        }
        else {
            Warning.setVisible(false);
            firebase.child("Doctor").child(doctorModel.getId()).child("firstName").setValue(FirstNameEdit.getText());
            firebase.child("Doctor").child(doctorModel.getId()).child("lastName").setValue(LastNameEdit.getText());
            firebase.child("Doctor").child(doctorModel.getId()).child("birthday").setValue(EditMonth.getValue() + " " + EditDay.getValue() + ", " + EditYear.getValue());
            firebase.child("Doctor").child(doctorModel.getId()).child("gender").setValue(GenderEdit.getValue());
            firebase.child("Doctor").child(doctorModel.getId()).child("contactNumber").setValue(NumberEdit.getText());
            firebase.child("Doctor").child(doctorModel.getId()).child("email").setValue(EmailEdit.getText());
            firebase.child("Doctor").child(doctorModel.getId()).child("address").setValue(AddressEdit.getText() + ", " + AddressLineEdit.getText());
            firebase.child("Doctor").child(doctorModel.getId()).child("subspecialty").setValue(SubspecialtyEdit.getText());

            String fullName = FirstNameEdit.getText() + " " + LastNameEdit.getText();
            FirstName.setText(fullName);
            ContactNumber.setText(NumberEdit.getText());
            Email.setText(EmailEdit.getText());
            Birthday.setText(EditMonth.getValue() + " " + EditDay.getValue() + ", " + EditYear.getValue());
            Gender.setText((String) GenderEdit.getValue());
            Address.setText(AddressEdit.getText() + ", " + AddressLineEdit.getText());
            Subspecialty.setText(SubspecialtyEdit.getText());
        }

        FirstNameEdit.setText(null);
        LastNameEdit.setText(null);
        EditMonth.setValue(" ");
        EditMonth.setPromptText("Month");
        EditDay.setValue(" ");
        EditDay.setPromptText("Day");
        EditYear.setValue(" ");
        EditYear.setPromptText("Year");
        GenderEdit.setValue(" ");
        GenderEdit.setPromptText("Gender");
        NumberEdit.setText(null);
        EmailEdit.setText(null);
        AddressEdit.setText(null);
        AddressLineEdit.setText(null);
        SubspecialtyEdit.setText(null);
    }

    public void Cancel(ActionEvent actionEvent) {
        FirstNameEdit.setText(null);
        LastNameEdit.setText(null);
        EditMonth.setValue(" ");
        EditMonth.setPromptText("Month");
        EditDay.setValue(" ");
        EditDay.setPromptText("Day");
        EditYear.setValue(" ");
        EditYear.setPromptText("Year");
        GenderEdit.setValue(" ");
        GenderEdit.setPromptText("Gender");
        NumberEdit.setText(null);
        EmailEdit.setText(null);
        AddressEdit.setText(null);
        AddressLineEdit.setText(null);
        SubspecialtyEdit.setText(null);
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

    public void DoctorHome(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorMainMenu");
        Stage closeStage = (Stage) HomeButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}