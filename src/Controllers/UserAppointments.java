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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public Button HelpButton, LogOutButton, HomeButton, ScheduleButton, DetailsButton, PaymentsButton, PreQues, PostQues;
    public Button ConfirmSched, Consult;
    public Label DocName, SubSpecialty,Specialty, Error;
    public ImageView DoctorImage;
    public ComboBox<String> DoctorsBox, ScheduleBox, ChildBox, AppointmentsBox, PreviousBox;
    public ListView<String> AppointmentsListView, PreviousListView, Notes;
    public DatePicker DateBox;
    int count = 0;
    public String FullName;
    String CardNum;
    User userModel;
    List<User> userList = new ArrayList<>();
    List<Children> childrenList = new ArrayList<>();
    List<Doctor> doctorList = new ArrayList<>();
    List<Schedule> scheduleList = new ArrayList<>();
    List<Prescription> prescriptionsList = new ArrayList<>();
    List<Appointments> appointmentsList;


    public UserAppointments(){
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
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        firebase.child("Appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(int i = 0; i < appointmentsList.size(); i++) {
                    Appointments appointmentsModel = appointmentsList.get(i);
                    if(FullName.equals(appointmentsModel.getUser())){
                        if(appointmentsModel.getStatus().equals("Upcoming") || appointmentsModel.getStatus().equals("Consultation")){
                            AppointmentsBox.getItems().add(appointmentsModel.getAppointment());
                            AppointmentsListView.getItems().add(appointmentsModel.getAppointment());
                        }
                        else{
                            PreviousBox.getItems().add(appointmentsModel.getAppointment());
                            PreviousListView.getItems().add(appointmentsModel.getAppointment());
                        }
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
                    schedule.setId(data.getKey());
                    scheduleList.add(schedule);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        firebase.child("Prescription").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Prescription prescription= data.getValue(Prescription.class);
                    prescriptionsList.add(prescription);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        firebase.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User User= data.getValue(User.class);
                    userList.add(User);
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

    public void SelectDate(ActionEvent actionEvent){
        System.out.println(scheduleList.size());
        for(int i = 0; i < scheduleList.size(); i++) {
            Schedule scheduleModel = scheduleList.get(i);
            if (DateBox.getEditor().getText().equals(scheduleModel.getDay())){
                DoctorsBox.getItems().add("Dr." + scheduleModel.getName());
                System.out.println("lol");
            }
        }
        DoctorsBox.setDisable(false);
    }

    public void ConfirmDoctor(ActionEvent actionEvent){
        DocName.setText(DoctorsBox.getValue());
        for(int i = 0; i < doctorList.size(); i++) {
            Doctor doctorModel = doctorList.get(i);
            if(DoctorsBox.getValue().equals("Dr." + doctorModel.getFirstName() + " " + doctorModel.getLastName())){
                Image dcPic = new Image(doctorModel.getPicture());
                DoctorImage.setImage(dcPic);
                SubSpecialty.setText("Subspecialty: " + doctorModel.getSubspecialty());
                Specialty.setText("Specialty: Pediatrician");
            }
        }
        ScheduleBox.setDisable(false);
        ChildBox.setDisable(false);
        ConfirmSched.setDisable(false);
        for(int i = 0; i < scheduleList.size(); i++) {
            Schedule scheduleModel = scheduleList.get(i);
            if(DoctorsBox.getValue().equals("Dr." + scheduleModel.getName()) && scheduleModel.getStatus().equals("Available")){
                if(scheduleModel.getDay().equals(DateBox.getEditor().getText())){
                    ScheduleBox.getItems().add(scheduleModel.getDay() + " " + scheduleModel.getTime());
                }
            }
        }
        for(int i = 0; i < childrenList.size(); i++) {
            Children childrenModel = childrenList.get(i);
            if(userModel.getUsername().equals(childrenModel.getParentID())){
                ChildBox.getItems().add(childrenModel.getFirstname() + " " + childrenModel.getLastname());
            }
        }
    }


    public void SelectAppointment(ActionEvent actionEvent){
        String AppValue = AppointmentsBox.getValue();
        for(int i = 0; i < appointmentsList.size(); i++) {
            Appointments Model = appointmentsList.get(i);
            if(AppValue.equals(Model.getAppointment())){
                appointmentsModel = appointmentsList.get(i);
                System.out.println(appointmentsModel.getId());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(appointmentsModel.getStatus().equals("Upcoming") || appointmentsModel.getStatus().equals("Consultation")){
                            if(appointmentsModel.getStatus().equals("Upcoming")){
                                PreQues.setDisable(false);
                                Consult.setDisable(true);
                                System.out.println("aaa");
                            }
                            else if(appointmentsModel.getStatus().equals("Consultation")) {
                                PreQues.setDisable(true);
                                Consult.setDisable(false);
                            }

                        } }}); } }
    }

    public void LoadDetails(ActionEvent actionEvent) {
        Notes.getItems().clear();
        for (int x = 0; x < appointmentsList.size(); x++) {
            Appointments appointmentsModels =appointmentsList.get(x);
            System.out.println(appointmentsModels.getId());
            System.out.println(appointmentsModels.getAppointment());
            System.out.println(PreviousBox.getValue());
            System.out.println(prescriptionsList.size());
            if(appointmentsModels.getAppointment().equals(PreviousBox.getValue())) {
                for (int i = 0; i < prescriptionsList.size(); i++) {
                    Prescription prescriptionModel = prescriptionsList.get(i);
                    System.out.println("___________________");
                    System.out.println(prescriptionModel.getAppointmentId());
                    System.out.println(appointmentsModels.getId());
                    System.out.println("___________________");
                    if (appointmentsModels.getId().equals(prescriptionModel.getAppointmentId())) {
                        System.out.println("Pass");
                        System.out.println(prescriptionModel.getAppointmentId());
                        System.out.println(appointmentsModels.getId());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Notes.getItems().add("--------------------");
                                System.out.println("javafx erroor");
                                Notes.getItems().add("Date: " + prescriptionModel.getPrescriptionDate());
                                Notes.getItems().add("Medicine: " + prescriptionModel.getMedicine());
                                Notes.getItems().add("Daily Dosage: " + prescriptionModel.getDailyDosage());
                                Notes.getItems().add("Duration: " + prescriptionModel.getDuration());
                                Notes.getItems().add("Special Instruction: " + prescriptionModel.getSpecialInstructions());
                                Notes.getItems().add("--------------------");
                            }
                        });
                    }
                }
            }
        }
    }

    public void ConfirmAppointment(ActionEvent actionEvent){
        if(ScheduleBox.getItems().isEmpty() && ChildBox.getItems().isEmpty()){
            Error.setText("No Available Schedule/No Registered Child Found");
        }
        else if(ScheduleBox.getItems().isEmpty()){
            Error.setText("No Available Schedule");
        }
        else if(ChildBox.getItems().isEmpty()){
            Error.setText("No Registered Child Found");
        }
        else{
            WriteAppointment();
            new Main().loadFXML("UserAppointments");
            Stage closeStage = (Stage) ConfirmSched.getScene().getWindow();
            new Main().CloseButton(closeStage);
        }
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

    public void UserHelpMenu(ActionEvent actionEvent) {
        new Main().loadFXML("UserHelpMenu");
        Stage closeStage = (Stage) HelpButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void PreCheckUpQues(ActionEvent actionEvent){
        new Main().loadFXML("PreQues");
    }

    public void PostCheckUpQues(ActionEvent actionEvent){
        new Main().loadFXML("PostQues");
    }

    public void WriteAppointment(){
        String link = null;
        Appointments model = new Appointments();
        model.setAppointment(DoctorsBox.getValue() + " " + ScheduleBox.getValue() + " " + ChildBox.getValue());
        for(int i = 0; i < scheduleList.size(); i++) {
            Schedule scheduleModel = scheduleList.get(i);
            if(DoctorsBox.getValue().equals("Dr."+scheduleModel.getName())) {
                link = scheduleModel.getLink();
                firebase.child("Schedule").child(scheduleModel.getId()).child("status").setValue("Reserved");
            }
        }
        model.setLink(link);
        model.setDoctor(DoctorsBox.getValue());
        model.setUser(FullName);
        model.setSched(ScheduleBox.getValue());
        model.setChild(ChildBox.getValue());
        model.setPreQuest(" ");
        model.setFollowUp(" ");
        model.setPayment(" ");
        model.setPainQ1(" ");
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