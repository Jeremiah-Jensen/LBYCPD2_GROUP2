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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorLogin implements Initializable {
    public TextField UsernameLogin, PasswordLogin;
    public Text LogInError;
    public Button RegisterButton, LoginButton;
    int returnValue;
    List<Doctor> doctorList;
    public static Doctor doctorModel;

    @Override
    public void initialize(URL Location, ResourceBundle resources) {
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

    private void doctorList() {
        doctorList = new ArrayList<>();
    }

    public void LogInAction(ActionEvent actionEvent) {
        if (UsernameLogin.getText().isEmpty() || PasswordLogin.getText().isEmpty()) {
            LogInError.setText("Please enter both fields");
        } else {
            for (int i = 0; i < doctorList.size(); i++) {
                Doctor model = doctorList.get(i);
                if (!UsernameLogin.getText().equals(model.getUsername())) {
                    LogInError.setText("User not found");
                } else {
                    if (UsernameLogin.getText().equals(doctorList.get(i).getUsername()) && !PasswordLogin.getText().equals(doctorList.get(i).getPassword())) {
                        LogInError.setText("Wrong password");
                        i = doctorList.size();
                    } else if (UsernameLogin.getText().equals(doctorList.get(i).getUsername()) && PasswordLogin.getText().equals(doctorList.get(i).getPassword())) {
                        doctorModel = doctorList.get(i);
                        LogInError.setVisible(false);
                        new Main().loadFXML("DoctorMainMenu");
                        Stage closeStage = (Stage) LoginButton.getScene().getWindow();
                        new Main().CloseButton(closeStage);
                    }
                }
            }
        }
    }

    public void RegisterScreen (ActionEvent actionEvent){
        new Main().loadFXML("DoctorRegister");
        Stage closeStage = (Stage) RegisterButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}
