package Controllers;

import Models.Appointments;
import Models.Doctor;
import Models.Schedule;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorDetails implements Initializable {

    public Button LogOutButton, AppointmentsButton, PatientsButton, HomeButton, HelpButton;
    public Text FirstName, ContactNumber, Email, Birthday, Gender, Address, Subspecialty, Warning;
    public TextField FirstNameEdit, LastNameEdit, AddressLineEdit, NumberEdit, EmailEdit, AddressEdit, SubspecialtyEdit;
    public ComboBox<String> EditDay, EditMonth, EditYear, GenderEdit;
    public ImageView ProfilePhoto;
    public String path = "Default.png";
    Doctor doctorModel;
    List<Schedule> scheduleList = new ArrayList<>();
    List<Appointments> appointmentsList = new ArrayList<>();
    Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doctorModel = DoctorLogin.doctorModel;
        String fullName = doctorModel.getFirstName() + " " + doctorModel.getLastName();
        FirstName.setText(fullName);
        ContactNumber.setText(doctorModel.getContactNumber());
        Email.setText(doctorModel.getEmail());
        Birthday.setText(doctorModel.getBirthday());
        Gender.setText(doctorModel.getGender());
        Address.setText(doctorModel.getAddress());
        Subspecialty.setText(doctorModel.getSubspecialty());

        FirstNameEdit.setText(doctorModel.getFirstName());
        LastNameEdit.setText(doctorModel.getLastName());
        GenderEdit.getEditor().setText(doctorModel.getGender());
        SubspecialtyEdit.setText(doctorModel.getSubspecialty());
        NumberEdit.setText(doctorModel.getContactNumber());
        String string = doctorModel.getAddress();
        String[] arrString = string.split(", ", 2);
        String birthday = doctorModel.getBirthday();
        String[] arrBirthday = birthday.split(" ", 2);
        EditMonth.getEditor().setText(arrBirthday[0]);
        String dayYear= arrBirthday[1];
        String[] arrBirthdayA = dayYear.split(", ", 2);
        EditDay.getEditor().setText(arrBirthdayA[0]);
        EditYear.getEditor().setText(arrBirthdayA[1]);
        AddressEdit.setText(arrString[0]);
        AddressLineEdit.setText(arrString[1]);
        EmailEdit.setText(doctorModel.getEmail());

        path = doctorModel.getPicture();
        Image image = new Image(path);
        ProfilePhoto.setImage(image);

        for (int i = 0; i < 80; i++){
            int day = 2003 - i;
            String result = String.valueOf(day);
            EditYear.getItems().add(result);
        }
        for (int i = 1; i < 32; i++){
            String result = String.valueOf(i);
            EditDay.getItems().add(result);
        }
        EditMonth.getItems().add("January");
        EditMonth.getItems().add("February");
        EditMonth.getItems().add("March");
        EditMonth.getItems().add("April");
        EditMonth.getItems().add("May");
        EditMonth.getItems().add("June");
        EditMonth.getItems().add("July");
        EditMonth.getItems().add("August");
        EditMonth.getItems().add("September");
        EditMonth.getItems().add("October");
        EditMonth.getItems().add("November");
        EditMonth.getItems().add("December");

        GenderEdit.getItems().add("Male");
        GenderEdit.getItems().add("Female");
        GenderEdit.getItems().add("Non-Binary");
        GenderEdit.getItems().add("Prefer not to say");
    }

    public void ProfilePic(ActionEvent actionEvent) {
        doctorModel = DoctorLogin.doctorModel;
        File file;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Pic");
        file = fileChooser.showOpenDialog(null);
        path = file.getName();
        Image image = new Image(path);
        ProfilePhoto.setImage(image);
        firebase.child("Doctor").child(doctorModel.getId()).child("picture").setValue(path);
    }

    public void EditInformation(ActionEvent actionEvent) {
        doctorModel = DoctorLogin.doctorModel;

        if(EditDay.getEditor().getText().isEmpty() || EditMonth.getEditor().getText().isEmpty() || EditYear.getEditor().getText().isEmpty() || GenderEdit.getEditor().getText().isEmpty() || FirstNameEdit.getText().isEmpty() || LastNameEdit.getText().isEmpty() ||  NumberEdit.getText().isEmpty() || EmailEdit.getText().isEmpty() || AddressEdit.getText().isEmpty() || AddressLineEdit.getText().isEmpty() || SubspecialtyEdit.getText().isEmpty()) {
            Warning.setVisible(true);
            Warning.setText("Missing Details");
        }
        else {
            Warning.setVisible(false);
            firebase.child("Doctor").child(doctorModel.getId()).child("firstName").setValue(FirstNameEdit.getText());
            firebase.child("Doctor").child(doctorModel.getId()).child("lastName").setValue(LastNameEdit.getText());
            firebase.child("Doctor").child(doctorModel.getId()).child("birthday").setValue(EditMonth.getEditor().getText() + " " + EditDay.getEditor().getText() + ", " + EditYear.getEditor().getText());
            firebase.child("Doctor").child(doctorModel.getId()).child("gender").setValue(GenderEdit.getEditor().getText());
            firebase.child("Doctor").child(doctorModel.getId()).child("contactNumber").setValue(NumberEdit.getText());
            firebase.child("Doctor").child(doctorModel.getId()).child("email").setValue(EmailEdit.getText());
            firebase.child("Doctor").child(doctorModel.getId()).child("address").setValue(AddressEdit.getText() + ", " + AddressLineEdit.getText());
            firebase.child("Doctor").child(doctorModel.getId()).child("subspecialty").setValue(SubspecialtyEdit.getText());

            String fullName = FirstNameEdit.getText() + " " + LastNameEdit.getText();
            FirstName.setText(fullName);
            ContactNumber.setText(NumberEdit.getText());
            Email.setText(EmailEdit.getText());
            Birthday.setText(EditMonth.getEditor().getText() + " " + EditDay.getEditor().getText() + ", " + EditYear.getEditor().getText());
            Gender.setText(GenderEdit.getEditor().getText());
            Address.setText(AddressEdit.getText() + ", " + AddressLineEdit.getText());
            Subspecialty.setText(SubspecialtyEdit.getText());
        }

        String fullnameDoctor = doctorModel.getFirstName() + " " + doctorModel.getLastName();
        firebase.child("Schedule").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Schedule schedule = data.getValue(Schedule.class);
                    schedule.setId(data.getKey());
                    scheduleList.add(schedule);
                }
                for (int i = 0; i < scheduleList.size(); i++) {
                    Schedule scheduleModel = scheduleList.get(i);
                    if (fullnameDoctor.equals(scheduleModel.getName())) {
                        firebase.child("Schedule").child(scheduleModel.getId()).child("name").setValue(FirstNameEdit.getText() + " " + LastNameEdit.getText());
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        String fullnameAppointment = "Dr." + doctorModel.getFirstName() + " " + doctorModel.getLastName();
        firebase.child("Appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Appointments appointments = data.getValue(Appointments.class);
                    appointments.setId(data.getKey());
                    appointmentsList.add(appointments);
                }
                for (int i = 0; i < appointmentsList.size(); i++) {
                    Appointments appointmentsModel = appointmentsList.get(i);
                    if (fullnameAppointment.equals(appointmentsModel.getDoctor())) {
                        firebase.child("Appointments").child(appointmentsModel.getId()).child("doctor").setValue("Dr." + FirstNameEdit.getText() + " " + LastNameEdit.getText());
                        String appointment = appointmentsModel.getAppointment();
                        String[] arrAppointment = appointment.split(" ", 6);
                        String temp = arrAppointment[0];
                        arrAppointment[0] = "Dr." + FirstNameEdit.getText();
                        String tempA = arrAppointment[1];
                        arrAppointment[1] = LastNameEdit.getText();
                        String appointmentEdit = arrAppointment[0] + " " + arrAppointment[1] + " " + arrAppointment[2] + " " + arrAppointment[3] + " " + arrAppointment[4] + " " + arrAppointment[5];
                        firebase.child("Appointments").child(appointmentsModel.getId()).child("appointment").setValue(appointmentEdit);
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void Cancel(ActionEvent actionEvent) {
        Warning.setVisible(false);
        FirstNameEdit.setText(doctorModel.getFirstName());
        LastNameEdit.setText(doctorModel.getLastName());
        GenderEdit.getEditor().setText(doctorModel.getGender());
        SubspecialtyEdit.setText(doctorModel.getSubspecialty());
        NumberEdit.setText(doctorModel.getContactNumber());
        String string = doctorModel.getAddress();
        String[] arrString = string.split(", ", 2);
        AddressEdit.setText(arrString[0]);
        AddressLineEdit.setText(arrString[1]);
        EmailEdit.setText(doctorModel.getEmail());
        String birthday = doctorModel.getBirthday();
        String[] arrBirthday = birthday.split(" ", 2);
        EditMonth.getEditor().setText(arrBirthday[0]);
        String dayYear= arrBirthday[1];
        String[] arrBirthdayA = dayYear.split(", ", 2);
        EditDay.getEditor().setText(arrBirthdayA[0]);
        EditYear.getEditor().setText(arrBirthdayA[1]);
    }

    public void DoctorPatients(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorPatients");
        Stage closeStage = (Stage) PatientsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorAppointments(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorAppointments");
        Stage closeStage = (Stage) AppointmentsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void DoctorHelp(ActionEvent actionEvent) {
        new Main().loadFXML("DoctorHelpMenu");
        Stage closeStage = (Stage) HelpButton.getScene().getWindow();
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
}