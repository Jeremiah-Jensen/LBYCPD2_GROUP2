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

    public Button ConsultationButton, LogOutButton, PatientsButton, UserDetailsButton, HomeButton, ConfirmButton, ViewButton, HelpButton;
    public ListView ScheduleList, PreQuesAnswers, PostQuesAnswers;
    public TextField time, link;
    public ComboBox<String> AppointmentsBox, Status;
    public Text PatientText, ScheduleText, Warning;
    public DatePicker date;
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
                        ScheduleList.getItems().add(scheduleModel.getDay() + " at " + scheduleModel.getTime() + "\n" + scheduleModel.getLink() + "\n" + scheduleModel.getStatus() + "\n\n");
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Status.getItems().add("Available");

        String fullnameDoctor = "Dr." + doctorModel.getFirstName() + " " + doctorModel.getLastName();
        firebase.child("Appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Appointments appointments = data.getValue(Appointments.class);
                    appointmentsList.add(appointments);
                }
                for (int i = 0; i < appointmentsList.size(); i++) {
                    Appointments appointmentModel = appointmentsList.get(i);
                    if (fullnameDoctor.equals(appointmentModel.getDoctor()) && appointmentModel.getStatus().equals("Consultation") || appointmentModel.getStatus().equals("Monitor")) {
                        AppointmentsBox.getItems().add(appointmentModel.getChild());
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void DoctorHelp(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorHelpMenu");
        Stage closeStage = (Stage) HelpButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorPatients(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorPatients");
        Stage closeStage = (Stage) PatientsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorDetails(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorDetails");
        Stage closeStage = (Stage) UserDetailsButton.getScene().getWindow();
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

    public void ConfirmSchedule(ActionEvent actionEvent) {
        doctorModel = DoctorLogin.doctorModel;
        if (date.getEditor().getText().isEmpty() || time.getText().isEmpty() || link.getText().isEmpty() || Status.getSelectionModel().isSelected(-1)) {
            Warning.setVisible(true);
            Warning.setText("Missing Details");
        } else {
            Warning.setVisible(false);
            Schedule schedule = new Schedule();
            String fullname = doctorModel.getFirstName() + " " + doctorModel.getLastName();
            schedule.setName(fullname);
            schedule.setDay(date.getEditor().getText());
            schedule.setTime(time.getText());
            schedule.setLink(link.getText());
            schedule.setStatus(Status.getValue());
            ScheduleList.getItems().add(date.getValue() + " at " + time.getText() + "\n" + link.getText() + "\n" + Status.getValue() + "\n\n");
            firebase.child("Schedule").push().setValue(schedule);
            date.setValue(null);
            date.setPromptText("Date");
            Status.setValue(null);
            Status.setPromptText("Status");
            time.setText(null);
            link.setText(null);
        }
    }

    public void ConfirmPatient(ActionEvent actionEvent) {
        PreQuesAnswers.getItems().clear();
        PostQuesAnswers.getItems().clear();
        ConsultationButton.setDisable(false);
        ViewButton.setDisable(false);
        String fullnameDoctor = "Dr." + doctorModel.getFirstName() + " " + doctorModel.getLastName();
        for (int i = 0; i < appointmentsList.size(); i++) {
            Appointments appointmentModel = appointmentsList.get(i);
            String fullname = appointmentModel.getChild();
            if (AppointmentsBox.getSelectionModel().getSelectedItem().equals(fullname)) {
                PatientText.setText(appointmentModel.getChild());
                ScheduleText.setText(appointmentModel.getSched());
                PreQuesAnswers.getItems().add("STATUS: " + appointmentModel.getStatus());
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

                if (appointmentModel.getFeelingQ2().equals(" ") || appointmentModel.getPainQ2().equals(" ") || appointmentModel.getPainScaleQ2().equals(" ") || appointmentModel.getSideEffectsQ().equals(" ")) {
                    PostQuesAnswers.getItems().add("No Answers Available");
                } else {
                    PostQuesAnswers.getItems().add("STATUS: " + appointmentModel.getStatus());
                    PostQuesAnswers.getItems().add("What is your child feeling?");
                    PostQuesAnswers.getItems().add("     - " + appointmentModel.getFeelingQ2());
                    PostQuesAnswers.getItems().add("Is your child feeling pain right now?");
                    PostQuesAnswers.getItems().add("     - " + appointmentModel.getPainQ2());
                    PostQuesAnswers.getItems().add("If yes, rate the pain from a scale of 1-10.");
                    PostQuesAnswers.getItems().add("     - " + appointmentModel.getPainScaleQ2());
                    PostQuesAnswers.getItems().add("What are the side effects after drinking the medicine?");
                    PostQuesAnswers.getItems().add("     - " + appointmentModel.getSideEffectsQ());
                }
            }
        }
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
    }

    public void Consultation(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorAppointmentScreen");
        String name = AppointmentsBox.getSelectionModel().getSelectedItem();
        DoctorAppointmentScreen doctorAppointmentScreen = new DoctorAppointmentScreen();
        doctorAppointmentScreen.setName(name);
    }
}