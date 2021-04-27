package Controllers;

import Models.Appointments;
import Models.Doctor;
import Models.Schedule;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserAppointments implements Initializable {
    Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
    @FXML
    public AnchorPane ScheduleAppointment,UpcomingAppointments, PreviousAppointments;
    public Button LogOutButton, HomeButton, ScheduleButton, DetailsButton, PaymentsButton, PreQues, PostQues;
    public Button ConfirmDoctor, ConfirmSched, ConfirmAppointment;
    public Label DocName, SchedTime, SchedDate, DoctorApp, SchedApp;
    public ComboBox<String> DoctorsBox, DateBox, TimeBox, AppointmentsBox;
    int count = 0;
    public String FullName;
    User userModel;
    List<Doctor> doctorList = new ArrayList<>();
    List<Appointments> appointmentsList = new ArrayList<>();
    List<Schedule> scheduleList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userModel = UserLogIn.userModel;
        FullName = userModel.getFirstName() + " " + userModel.getLastName();
        firebase.child("Doctor").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Doctor doctor = data.getValue(Doctor.class);
                    doctorList.add(doctor);
                }
                for(int i = 0; i < doctorList.size(); i++) {
                    Doctor doctorModel = doctorList.get(i);
                    DoctorsBox.getItems().add("Dr." + doctorModel.getFirstName() + " " + doctorModel.getLastName());
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
                    Appointments app = data.getValue(Appointments.class);
                    appointmentsList.add(app);
                }
                for(int i = 0; i < appointmentsList.size(); i++) {
                    Appointments appointmentsModel = appointmentsList.get(i);
                    if(FullName.equals(appointmentsModel.getUser())){
                        AppointmentsBox.getItems().add(appointmentsModel.getAppointment());
                    }
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

    public void ConfirmDoctor(ActionEvent actionEvent){
        DocName.setText(DoctorsBox.getValue());
        DateBox.setDisable(false);
        TimeBox.setDisable(false);
        ConfirmSched.setDisable(false);
        DoctorsBox.setDisable(true);
        firebase.child("Schedule").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Schedule schedule = data.getValue(Schedule.class);
                    scheduleList.add(schedule);
                }
                for(int i = 0; i < scheduleList.size(); i++) {
                    Schedule scheduleModel = scheduleList.get(i);
                    if(DoctorsBox.getValue().equals("Dr." + scheduleModel.getName())){
                        DateBox.getItems().add(scheduleModel.getDay());
                        TimeBox.getItems().add(scheduleModel.getTime());
                    }
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void ConfirmSchedule(ActionEvent actionEvent){
        DocName.setText(DoctorsBox.getValue());
        SchedTime.setText(TimeBox.getValue());
        SchedDate.setText(DateBox.getValue());
        ConfirmAppointment.setDisable(false);
    }

    public void ConfirmAppointment(ActionEvent actionEvent){
        WriteAppointment();
        new Main().UserAppointmentsWindow();
        Stage closeStage = (Stage) ConfirmAppointment.getScene().getWindow();
        new Main().CloseButton(closeStage);
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

    public void WriteAppointment(){
        Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
        Appointments model = new Appointments();
        model.setAppointment(DoctorsBox.getValue() + " " + DateBox.getValue() + " " + TimeBox.getValue());
        model.setDoctor(DoctorsBox.getValue());
        model.setUser(FullName);
        model.setTime(TimeBox.getValue());
        model.setDate(DateBox.getValue());
        model.setPreQuest(" ");
        model.setFollowUp(" ");
        model.setPayment(" ");
        model.setPainQ1(" ");
        model.setLink(" ");
        model.setPreQuest(" ");
        model.setStatus("Upcoming");
        firebase.child("Appointments").push().setValue(model);
    }
}