package Controllers;

import Models.Appointments;
import Models.Doctor;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorAppointmentScreen implements Initializable {

    public static String name;
    public Text PatientText, DateText, TimeText;
    public TextField LinkTextField;
    Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
    List<Appointments> appointmentsList = new ArrayList<>();
    Doctor doctorModel;

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
                    Appointments appointmentModel = appointmentsList.get(i);
                    if(appointmentModel.getDoctor().equals(fullnameDoctor) && appointmentModel.getUser().equals(DoctorAppointmentScreen.getName()) && appointmentModel.getStatus().equals("Consultation")) {
                        DateText.setText(appointmentModel.getDate());
                        TimeText.setText(appointmentModel.getTime());
                        LinkTextField.setText(appointmentModel.getLink());
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
