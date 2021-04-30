package Controllers;

import Models.Doctor;
import com.firebase.client.Firebase;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DoctorRegister implements Initializable {
    public TextField FirstName, LastName, Username, Email, Number, City, AddressLine, Subspecialty;
    public PasswordField Password, ReenterPassword;
    public Label Error2, Error3, Pic;
    public Button BackButton, RegisterButton;
    public ComboBox Day, Month, Year, Gender;
    public String path = "Default.png";

    @Override
    public void initialize(URL Location, ResourceBundle resources){
        for (int i = 0; i < 80; i++){
            int day = 2003 - i;
            String result = String.valueOf(day);
            Year.getItems().add(result);
        }
        for (int i = 1; i < 32; i++){
            String result = String.valueOf(i);
            Day.getItems().add(result);
        }
        Month.getItems().add("January");
        Month.getItems().add("February");
        Month.getItems().add("March");
        Month.getItems().add("April");
        Month.getItems().add("May");
        Month.getItems().add("June");
        Month.getItems().add("July");
        Month.getItems().add("August");
        Month.getItems().add("September");
        Month.getItems().add("October");
        Month.getItems().add("November");
        Month.getItems().add("December");

        Gender.getItems().add("Male");
        Gender.getItems().add("Female");
        Gender.getItems().add("Non-Binary");
        Gender.getItems().add("Prefer not to say");
    }

    public void RegisterAction(ActionEvent actionEvent) {
        if(Username.getText().isEmpty() || Password.getText().isEmpty() || FirstName.getText().isEmpty() || FirstName.getText().isEmpty() || LastName.getText().isEmpty() || ReenterPassword.getText().isEmpty() ||  Email.getText().isEmpty() || Number.getText().isEmpty()) {
            Error3.setVisible(false);
            Error2.setVisible(true);
        }
        else if(!Password.getText().equals(ReenterPassword.getText())) {
            Error2.setVisible(false);
            Error3.setVisible(true);
        }
        else {
            Write();
            Clear();
        new Main().loadFXML("DoctorLogIn");
            Stage closeStage = (Stage) RegisterButton.getScene().getWindow();
            new Main().CloseButton(closeStage);
        }
    }

    private void Write(){
        Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
        Doctor model = new Doctor();
        model.setUsername(Username.getText());
        model.setPassword(Password.getText());
        model.setFirstName(FirstName.getText());
        model.setLastName(LastName.getText());
        model.setReenterPass(ReenterPassword.getText());
        model.setContactNumber(Number.getText());
        model.setEmail(Email.getText());
        model.setSubspecialty(Subspecialty.getText());
        model.setBirthday(Month.getValue() + " " + Day.getValue() + ", " + Year.getValue());
        model.setAddress(City.getText() + ", " + AddressLine.getText());
        model.setGender((String) Gender.getValue());
        model.setPicture(path);
        firebase.child("Doctor").push().setValue(model);
    }

    private void Clear(){
        Error2.setVisible(false);
        Error3.setVisible(false);
        FirstName.clear();
        LastName.clear();
        Username.clear();
        Password.clear();
        ReenterPassword.clear();
        Email.clear();
        City.clear();
        AddressLine.clear();
        Subspecialty.clear();
        Pic.setText("-No Photo Selected-");
        Gender.setValue(" ");
        Day.setValue(" ");
        Month.setValue(" ");
        Year.setValue(" ");
    }

    public void Back(ActionEvent actionEvent) {
        new Main().LoginWindow();
        Stage closeStage = (Stage) BackButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void ProfilePic(ActionEvent actionEvent) {
        File file;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Pic");
        file = fileChooser.showOpenDialog(null);
        path = file.getName();
        Pic.setText(path);
    }
}