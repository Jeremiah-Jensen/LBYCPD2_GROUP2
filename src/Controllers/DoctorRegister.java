package Controllers;

import Models.Doctor;
import com.firebase.client.Firebase;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorRegister implements Initializable {
    public TextField FirstName, LastName, Username, Birthdate, Email, Number, Gender;
    public TextArea Address, Consultation;
    public PasswordField Password, ReenterPassword;
    public Label Error2;
    public Label Error3;
    public Button BackButton, RegisterButton;

    @Override
    public void initialize(URL Location, ResourceBundle resources){

    }

    public void RegisterAction(ActionEvent actionEvent) {
        if(Username.getText().isEmpty() || Password.getText().isEmpty() || FirstName.getText().isEmpty() || FirstName.getText().isEmpty() || LastName.getText().isEmpty() || ReenterPassword.getText().isEmpty() || Birthdate.getText().isEmpty() || Gender.getText().isEmpty() || Email.getText().isEmpty() || Number.getText().isEmpty()) {
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
            new Main().DoctorsLoginWindow();
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
        model.setBirthday(Birthdate.getText());
        model.setContactNumber(Number.getText());
        model.setEmail(Email.getText());
//        model.setAddress(Address.getText().replaceAll("\n", System.getProperty("line.separator")));
        model.setGender(Gender.getText());
//        model.setCondition(Conditions.getText().replaceAll("\n", System.getProperty("line.separator")));
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
        Birthdate.clear();
        Email.clear();
        Gender.clear();
        //Address.setText(" ");
        //Consultation.setText(" ");
    }

    public void Back(ActionEvent actionEvent) {
        new Main().LoginWindow();
        Stage closeStage = (Stage) BackButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}