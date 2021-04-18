package Controllers;

import Models.Doctor;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorLogin implements Initializable {
    public AnchorPane LogIn, Background1;
    public TextField UsernameLogin, PasswordLogin;
    public Label Error4;
    public Label Error5;
    public Button RegisterButton;
    int returnValue;

    @Override
    public void initialize(URL Location, ResourceBundle resources){

    }

    public void LogInAction(ActionEvent actionEvent) {
        if (UsernameLogin.getText().isEmpty() || PasswordLogin.getText().isEmpty()) {
            Error5.setVisible(false);
            Error4.setVisible(true);
        } else {
            Error4.setVisible(false);
            Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
            firebase.child("Doctor").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    returnValue = 0;
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Doctor doctor = data.getValue(Doctor.class);
                        if (UsernameLogin.getText().equals(doctor.getUsername()) && PasswordLogin.getText().equals(doctor.getPassword())) {
                            returnValue = 1;
                        }
                    }
                    if (returnValue == 1) {
                        Error5.setVisible(false);
                        System.out.println("Found in Database"); // initial code -- to be change with codes for logging in to doctor menu
                    } else if (returnValue == 0) {
                        Error4.setVisible(false);
                        // there is no existing user
                        Error5.setVisible(true);
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
    }

    public void RegisterScreen(ActionEvent actionEvent) {
        new Main().DoctorsRegisterWindow();
        Stage closeStage = (Stage) RegisterButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}