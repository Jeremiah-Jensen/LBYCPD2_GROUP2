package Controllers;

import Models.Doctor;
import Models.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserAppointments implements Initializable {
    @FXML
    public AnchorPane ScheduleAppointment,UpcomingAppointments, PreviousAppointments;
    public Button LogOutButton, HomeButton, ScheduleButton, DetailsButton, PaymentsButton, PreQues, PostQues;
    public ListView<String> Doctors;;
    int count = 0;
    List<Doctor> doctorList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
        firebase.child("Doctor").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Doctor doctor = data.getValue(Doctor.class);
                    doctorList.add(doctor);
                }
                for(int i = 0; i < doctorList.size(); i++) {
                    Doctor doctorModel = doctorList.get(i);
                    Doctors.getItems().add("Dr. " + doctorModel.getFirstName() + " " + doctorModel.getLastName() + "\n");
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void Switch(ActionEvent actionEvent) {
        double move = 0;
        if(count== 0){
            move = -351;
            count = 1;
            ScheduleButton.setText("Schedule Appointment");
        }
        else{
            move = 351;
            count = 0;
            ScheduleButton.setText("View Previous Appointments");
        }
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), UpcomingAppointments);
        translateTransition.setByY(move);
        translateTransition.play();
        TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(1), ScheduleAppointment);
        translateTransition2.setByY(move);
        translateTransition2.play();
        TranslateTransition translateTransition3 = new TranslateTransition(Duration.seconds(1), PreviousAppointments);
        translateTransition3.setByY(move);
        translateTransition3.play();
    }

    public void MainMenu(ActionEvent actionEvent){
        new Main().MainMenuWindow();
        Stage closeStage = (Stage) HomeButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void LogOut(ActionEvent actionEvent){
        new Main().LoginWindow();
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void UserDetails(ActionEvent actionEvent){
        new Main().UserDetailsWindow();
        Stage closeStage = (Stage) DetailsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }


    public void UserPayments(ActionEvent actionEvent){
        new Main().UserPaymentsWindow();
        Stage closeStage = (Stage) PaymentsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void PreCheckUpQues(ActionEvent actionEvent){
        new Main().PreQuesWindow();
    }

    public void PostCheckUpQues(ActionEvent actionEvent){
        new Main().PostQuesWindow();
    }
}