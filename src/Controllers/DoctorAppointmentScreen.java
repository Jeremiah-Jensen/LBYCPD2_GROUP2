package Controllers;

import Models.Appointments;
import Models.Doctor;
import Models.Prescription;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorAppointmentScreen implements Initializable {

    public static String name;
    public Text PatientText, DateText, TimeText;
    public TextField LinkTextField, PrescriptionDate, Medicine, DailyDosage, Duration, SpecialInstructions;
    public Button EndButton;
    public ComboBox Status;
    Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
    List<Appointments> appointmentsList = new ArrayList<>();
    List<Appointments> appointmentsListA = new ArrayList<>();
    Doctor doctorModel;
    Appointments appointmentModel;

    public void setName(String name) {
        this.name = name;
    }

    public static String getName() {
        return name;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PatientText.setText(DoctorAppointmentScreen.getName());
        doctorModel = DoctorLogin.doctorModel;
        String fullnameDoctor = "Dr." + doctorModel.getFirstName() + " " + doctorModel.getLastName();
        firebase.child("Appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Appointments appointments = data.getValue(Appointments.class);
                    appointmentsList.add(appointments);
                }
                for(int i = 0; i < appointmentsList.size(); i++) {
                    appointmentModel = appointmentsList.get(i);
                    if(appointmentModel.getDoctor().equals(fullnameDoctor) && appointmentModel.getChild().equals(DoctorAppointmentScreen.getName()) && appointmentModel.getStatus().equals("Consultation")) {
                        DateText.setText(appointmentModel.getSched());
                        LinkTextField.setText(appointmentModel.getLink());
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Status.getItems().add("Monitor");
        Status.getItems().add("Discharge");
    }

    public void ConfirmPrescription(ActionEvent actionEvent) {
        Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
        Prescription prescription = new Prescription();
        prescription.setName(DoctorAppointmentScreen.getName());
        prescription.setPrescriptionDate(PrescriptionDate.getText());
        prescription.setMedicine(Medicine.getText());
        prescription.setDailyDosage(DailyDosage.getText());
        prescription.setDuration(Duration.getText());
        prescription.setAppointmentId(appointmentModel.getId());
        prescription.setSpecialInstructions(SpecialInstructions.getText());
        firebase.child("Prescription").push().setValue(prescription);
    }

    public void EndConsultation(ActionEvent actionEvent) {
        doctorModel = DoctorLogin.doctorModel;
        String fullnameDoctor = "Dr." + doctorModel.getFirstName() + " " + doctorModel.getLastName();
        firebase.child("Appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Appointments appointments = data.getValue(Appointments.class);
                    appointments.setId(data.getKey());
                    appointmentsListA.add(appointments);
                }
                for(int i = 0; i < appointmentsListA.size(); i++) {
                    Appointments appointmentModel = appointmentsListA.get(i);
                    if(appointmentModel.getChild().equals(DoctorAppointmentScreen.getName())) {
                        System.out.println(appointmentModel.getId());
                        System.out.println(appointmentModel.getChild());
                        firebase.child("Appointments").child(appointmentModel.getId()).child("status").setValue(Status.getValue());
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Stage closeStage = (Stage) EndButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}