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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorLogin implements Initializable {
    public TextField UsernameLogin, PasswordLogin;
    public Label Error4;
    public Label Error5;
    public Button RegisterButton, LoginButton;
    int returnValue;
    List<Doctor> doctorList;
    public static Doctor doctorModel;

    @Override
    public void initialize(URL Location, ResourceBundle resources){
        doctorList();
    }

    public DoctorLogin() {
        Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
        firebase.child("Doctor").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                returnValue = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Doctor doctorModel = data.getValue(Doctor.class);
                    doctorModel.setId(data.getKey());
                    doctorList.add(doctorModel);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void doctorList() {doctorList = new ArrayList<>();}

    public void LogInAction(ActionEvent actionEvent) {
        if (UsernameLogin.getText().isEmpty() || PasswordLogin.getText().isEmpty()) {
            Error5.setVisible(false);
            Error4.setVisible(true);
        } else {
            Error4.setVisible(false);
            for(int i = 0; i < doctorList.size(); i++) {
                Doctor model = doctorList.get(i);
                if(UsernameLogin.getText().equals(model.getUsername()) && PasswordLogin.getText().equals(model.getPassword())) {
                    returnValue = 1;
                    doctorModel = doctorList.get(i);
                }
            }
            if (returnValue == 1) {
            new Main().loadFXML("DoctorMainMenu");
                Stage closeStage = (Stage) LoginButton.getScene().getWindow();
                new Main().CloseButton(closeStage);
                Error5.setVisible(false);
                System.out.println("Found in Database"); // initial code -- to be change with codes for logging in to doctor menu
            } else if (returnValue == 0) {
                Error4.setVisible(false);
                // there is no existing user
                Error5.setVisible(true);
            }
        }
    }

    public void RegisterScreen(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorRegister");
        Stage closeStage = (Stage) RegisterButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}