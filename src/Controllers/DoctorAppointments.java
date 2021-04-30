package Controllers;

import Models.Doctor;
import Models.Schedule;
import Models.Appointments;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorAppointments implements Initializable {

    public Button LogOutButton, PatientsButton, UserDetailsButton, HomeButton, ConfirmButton, ViewButton;
    public ListView ScheduleList, PreQuesAnswers;
    public TextField day, time, link;
    public ComboBox AppointmentsBox;
    public Text PatientText, ScheduleText;
    public AnchorPane UpcomingAppointments, ConsultationPeriod, Questionnaires;
    Doctor doctorModel;
    List<Schedule> scheduleList = new ArrayList<>();
    List<Appointments> appointmentsList = new ArrayList<>();
    List<Appointments> appointmentsListA = new ArrayList<>();
    List<Appointments> appointmentsListB = new ArrayList<>();
    Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
    int count = 0;
    double move = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doctorModel = DoctorLogin.doctorModel;
        String fullname = doctorModel.getFirstName() + " " + doctorModel.getLastName();
        firebase.child("Schedule").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Schedule schedule = data.getValue(Schedule.class);
                    scheduleList.add(schedule);
                }
                for (int i = 0; i < scheduleList.size(); i++) {
                    Schedule scheduleModel = scheduleList.get(i);
                    if (fullname.equals(scheduleModel.getName())) {
                        ScheduleList.getItems().add(scheduleModel.getDay() + " at " + scheduleModel.getTime() + "\n" + scheduleModel.getLink() + "\n\n");
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        String fullnameDoctor = "Dr." + doctorModel.getFirstName() + " " + doctorModel.getLastName();
        firebase.child("Appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Appointments appointments = data.getValue(Appointments.class);
                    appointmentsList.add(appointments);
                }
                for(int i = 0; i < appointmentsList.size(); i++) {
                    Appointments appointmentModel = appointmentsList.get(i);
                    if(fullnameDoctor.equals(appointmentModel.getDoctor()) && appointmentModel.getStatus().equals("Consultation")) {
                        AppointmentsBox.getItems().add(appointmentModel.getUser());
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void DoctorPatients(ActionEvent actionEvent) {
        new Main().DoctorPatients();
        Stage closeStage = (Stage) PatientsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorDetails(ActionEvent actionEvent) {
        new Main().DoctorDetails();
        Stage closeStage = (Stage) UserDetailsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorLogOut(ActionEvent actionEvent) {
        new Main().LoginWindow();
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorHome(ActionEvent actionEvent) {
        new Main().DoctorMainMenu();
        Stage closeStage = (Stage) HomeButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void ConfirmSchedule(ActionEvent actionEvent) {
        doctorModel = DoctorLogin.doctorModel;
        Schedule schedule = new Schedule();
        String fullname = doctorModel.getFirstName() + " " + doctorModel.getLastName();
        schedule.setName(fullname);
        schedule.setDay(day.getText());
        schedule.setTime(time.getText());
        schedule.setLink(link.getText());
        firebase.child("Schedule").push().setValue(schedule);
        day.setText(null);
        time.setText(null);
        link.setText(null);
    }

    public void ConfirmPatient(ActionEvent actionEvent) {
        String fullnameDoctor = "Dr." + doctorModel.getFirstName() + " " + doctorModel.getLastName();
        firebase.child("Appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Appointments appointments = data.getValue(Appointments.class);
                    appointmentsListA.add(appointments);
                }
                for(int i = 0; i < appointmentsListA.size(); i++) {
                    Appointments appointmentModel = appointmentsListA.get(i);
                    if(appointmentModel.getDoctor().equals(fullnameDoctor) && AppointmentsBox.getValue().equals(appointmentModel.getUser()) && appointmentModel.getStatus().equals("Consultation")) {
                        PatientText.setText(appointmentModel.getUser());
                        ScheduleText.setText(appointmentModel.getChild() + " at " + appointmentModel.getSched());
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void Switch(ActionEvent actionEvent) {
        if(count == 0){
            move = -351;
            count = 1;
            ViewButton.setText("Consultation Period");
        }
        else{
            move = 351;
            count = 0;
            ViewButton.setText("View More");
        }
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), UpcomingAppointments);
        translateTransition.setByY(move);
        translateTransition.play();
        TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(1), ConsultationPeriod);
        translateTransition2.setByY(move);
        translateTransition2.play();
        TranslateTransition translateTransition3 = new TranslateTransition(Duration.seconds(1), Questionnaires);
        translateTransition3.setByY(move);
        translateTransition3.play();

        String fullnameDoctor = "Dr." + doctorModel.getFirstName() + " " + doctorModel.getLastName();
        firebase.child("Appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Appointments appointments = data.getValue(Appointments.class);
                    appointmentsListB.add(appointments);
                }
                for(int i = 0; i < appointmentsListB.size(); i++) {
                    Appointments appointmentModel = appointmentsListB.get(i);
                    if(appointmentModel.getDoctor().equals(fullnameDoctor) && AppointmentsBox.getValue().equals(appointmentModel.getUser()) && appointmentModel.getStatus().equals("Consultation")) {
                        PreQuesAnswers.getItems().add("What is your child feeling?");
                        PreQuesAnswers.getItems().add("     - " + appointmentModel.getFeelingQ());
                        PreQuesAnswers.getItems().add("Is your child feeling pain right now?");
                        PreQuesAnswers.getItems().add("     - " + appointmentModel.getPainQ1());
                        PreQuesAnswers.getItems().add("If yes, rate the pain from a scale of 1-10.");
                        PreQuesAnswers.getItems().add("     - " + appointmentModel.getPainScale());
                        PreQuesAnswers.getItems().add("Why did your child seek consultation?");
                        PreQuesAnswers.getItems().add("     - " + appointmentModel.getReasonQ());
                        PreQuesAnswers.getItems().add("Chest Patin");
                        PreQuesAnswers.getItems().add("     - " + appointmentModel.getQ1());
                        PreQuesAnswers.getItems().add("Abnormal Heart Rate");
                        PreQuesAnswers.getItems().add("     - " + appointmentModel.getQ2());
                        PreQuesAnswers.getItems().add("High Blood Pressure");
                        PreQuesAnswers.getItems().add("     - " + appointmentModel.getQ3());
                        PreQuesAnswers.getItems().add("Frequent Headaches");
                        PreQuesAnswers.getItems().add("     - " + appointmentModel.getQ4());
                        PreQuesAnswers.getItems().add("Frequent Stomach Aches");
                        PreQuesAnswers.getItems().add("     - " + appointmentModel.getQ5());
                        PreQuesAnswers.getItems().add("Vision Problems");
                        PreQuesAnswers.getItems().add("     - " + appointmentModel.getQ6());
                        PreQuesAnswers.getItems().add("Hearing Problems");
                        PreQuesAnswers.getItems().add("     - " + appointmentModel.getQ7());
                        PreQuesAnswers.getItems().add("Injuries");
                        PreQuesAnswers.getItems().add("     - " + appointmentModel.getQ8());
                        PreQuesAnswers.getItems().add("Seizures");
                        PreQuesAnswers.getItems().add("     - " + appointmentModel.getQ9());
                        PreQuesAnswers.getItems().add("Frequent Stepthroat Infections");
                        PreQuesAnswers.getItems().add("     - " + appointmentModel.getQ10());
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void Consultation(ActionEvent actionEvent) {
        new Main().DoctorsAppointmentScreen();
        String name = (String) AppointmentsBox.getValue();
        DoctorAppointmentScreen doctorAppointmentScreen = new DoctorAppointmentScreen();
        doctorAppointmentScreen.setName(name);
    }
}