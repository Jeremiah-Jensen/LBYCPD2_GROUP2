package Controllers;

import Models.*;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
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
    public static Appointments appointmentsModel;
    Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
    @FXML
    public AnchorPane ScheduleAppointment,UpcomingAppointments, PreviousAppointments;
    public Button LogOutButton, HomeButton, ScheduleButton, DetailsButton, PaymentsButton, PreQues, PostQues;
    public Button ConfirmDoctor, ConfirmSched, ConfirmAppointment, Consult;
    public Label DocName, SchedTime, SchedDate;
    public ComboBox<String> DoctorsBox, ScheduleBox, ChildBox, AppointmentsBox;
    public ListView<String> AppointmentsListView;
    int count = 0;
    public String FullName;
    User userModel;
    List<Children> childrenList = new ArrayList<>();
    List<Doctor> doctorList = new ArrayList<>();
    List<Schedule> scheduleList = new ArrayList<>();
    List<Appointments> appointmentsList;

    public UserAppointments(){
        Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com");
        firebase.child("Appointments").addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Appointments appointmentsModel=data.getValue(Appointments.class);
                    appointmentsModel.setId(data.getKey());
                    appointmentsList.add(appointmentsModel);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentsList = new ArrayList<>();
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
                for(int i = 0; i < appointmentsList.size()/2; i++) {
                    Appointments appointmentsModel = appointmentsList.get(i);
                    if(FullName.equals(appointmentsModel.getUser())){
                        AppointmentsBox.getItems().add(appointmentsModel.getAppointment());
                        AppointmentsListView.getItems().add(appointmentsModel.getAppointment());
                    }
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        firebase.child("Child").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Children child = data.getValue(Children.class);
                    childrenList.add(child);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        firebase.child("Schedule").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Schedule schedule = data.getValue(Schedule.class);
                    scheduleList.add(schedule);
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
        ScheduleBox.setDisable(false);
        ChildBox.setDisable(false);
        ConfirmSched.setDisable(false);
        for(int i = 0; i < scheduleList.size(); i++) {
            Schedule scheduleModel = scheduleList.get(i);
            if(DoctorsBox.getValue().equals("Dr." + scheduleModel.getName())){
                ScheduleBox.getItems().add(scheduleModel.getDay() + " " + scheduleModel.getTime());
            }
        }
        for(int i = 0; i < childrenList.size(); i++) {
            Children childrenModel = childrenList.get(i);
            if(userModel.getId().equals(childrenModel.getParentID())){
                ChildBox.getItems().add(childrenModel.getFirstname() + " " + childrenModel.getLastname());
            }
        }
    }


    public void SelectAppointment(ActionEvent actionEvent){
        String AppValue = AppointmentsBox.getValue();
        for(int i = 0; i < appointmentsList.size()/2; i++) {
            Appointments Model = appointmentsList.get(i);
            if(AppValue.equals(Model.getAppointment())){
                appointmentsModel = appointmentsList.get(i);
                System.out.println(appointmentsModel.getId());
                System.out.println(appointmentsModel.getChild());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(appointmentsModel.getStatus().equals("Upcoming")){
                            PreQues.setDisable(false);
                            Consult.setDisable(true);
                            System.out.println("aaa");
                        }
                        else if(appointmentsModel.getStatus().equals("Consultation")){
                            PreQues.setDisable(true);
                            Consult.setDisable(false);
                        } }}); } }
    }

    public void ConfirmAppointment(ActionEvent actionEvent){
        WriteAppointment();
        new Main().loadFXML("UserAppointments");
        Stage closeStage = (Stage) ConfirmAppointment.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void AppointmentScreen(ActionEvent actionEvent) {
        new Main().loadFXML("UserAppointmentScreen");
    }

    public void MainMenu(ActionEvent actionEvent){
        new Main().loadFXML("MainMenu");
        Stage closeStage = (Stage) HomeButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void LogOut(ActionEvent actionEvent){
        new Main().LoginWindow();
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void UserDetails(ActionEvent actionEvent){
       new Main().loadFXML("UserDetails");
        Stage closeStage = (Stage) DetailsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void UserPayments(ActionEvent actionEvent){
       new Main().loadFXML("UserPayments");
        Stage closeStage = (Stage) PaymentsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void PreCheckUpQues(ActionEvent actionEvent){
        new Main().loadFXML("PreQues");
    }

    public void PostCheckUpQues(ActionEvent actionEvent){
        new Main().loadFXML("PostQues");
    }

    public void WriteAppointment(){
        Appointments model = new Appointments();
        model.setAppointment(DoctorsBox.getValue() + " " + ScheduleBox.getValue() + " " + ChildBox.getValue());
        model.setDoctor(DoctorsBox.getValue());
        model.setUser(FullName);
        model.setSched(ScheduleBox.getValue());
        model.setChild(ChildBox.getValue());
        model.setPreQuest(" ");
        model.setFollowUp(" ");
        model.setPayment(" ");
        model.setPainQ1(" ");
        model.setLink(" ");
        model.setPreQuest(" ");
        model.setFeelingQ(" ");
        model.setReasonQ(" ");
        model.setPainScale(" ");
        model.setQ1(" ");
        model.setQ2(" ");
        model.setQ3(" ");
        model.setQ4(" ");
        model.setQ5(" ");
        model.setQ6(" ");
        model.setQ7(" ");
        model.setQ8(" ");
        model.setQ9(" ");
        model.setQ10(" ");
        model.setSideEffectsQ(" ");
        model.setPainScaleQ2(" ");
        model.setPainQ2(" ");
        model.setFeelingQ2(" ");
        model.setStatus("Upcoming");
        firebase.child("Appointments").push().setValue(model);
    }
}