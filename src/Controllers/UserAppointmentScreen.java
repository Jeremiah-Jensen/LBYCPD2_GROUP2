package Controllers;

import Models.Appointments;
import Models.Prescription;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserAppointmentScreen implements Initializable {
    Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
    Appointments appointmentsModel;
    @FXML
    public Label DoctorName, ChildName, Schedule;
    public ListView Prescription;
    public TextField ZoomLink;
    List<Prescription> prescriptionsList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentsModel = UserAppointments.appointmentsModel;
        DoctorName.setText(appointmentsModel.getDoctor());
        ChildName.setText(appointmentsModel.getChild());
        Schedule.setText(appointmentsModel.getSched());
        ZoomLink.setText(appointmentsModel.getLink());
    }

    public void ShowPrescription(javafx.event.ActionEvent actionEvent) {
        firebase.child("Prescription").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Prescription prescription= data.getValue(Prescription.class);
                    prescriptionsList.add(prescription);

                }
                for(int i = 0; i < prescriptionsList.size(); i++) {
                    Prescription prescriptionModel= prescriptionsList.get(i);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(appointmentsModel.getId().equals(prescriptionModel.getAppointmentId())){
                                        Prescription.getItems().add("--------------------");
                                        System.out.println("javafx erroor");
                                        Prescription.getItems().add("Date: " + prescriptionModel.getPrescriptionDate());
                                        Prescription.getItems().add("Medicine: " + prescriptionModel.getMedicine());
                                        Prescription.getItems().add("Daily Dosage: " + prescriptionModel.getDailyDosage());
                                        Prescription.getItems().add("Duration: " + prescriptionModel.getDuration());
                                        Prescription.getItems().add("Special Instruction: " + prescriptionModel.getSpecialInstructions());
                                        Prescription.getItems().add("--------------------");
                            }

                        }
                    });
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
