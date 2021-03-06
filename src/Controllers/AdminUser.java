package Controllers;

import Models.Appointments;
import Models.Children;
import Models.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminUser implements Initializable {
    @FXML
    public AnchorPane ManageChildren, Appointments;
    public ImageView UserImage, ChildImage;
    public Label Username, Name, Gender, Birthday, Email, Number;
    public Label ChildName, ChildBirthday, ChildConditions;
    public ComboBox<String> ChildBox, UserBox;
    public ListView<String> AppointmentsList;
    public Button LogOutButton, DoctorButton;
    User userModel;
    List<User> userList = new ArrayList<>();
    List<Children> childrenList = new ArrayList<>();
    List<Appointments> appointmentsList = new ArrayList<>();
    Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        firebase.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User user = data.getValue(User.class);
                    userList.add(user);
                }
                for (int i = 0; i < userList.size(); i++) {
                    User userModel = userList.get(i);
                    UserBox.getItems().add(userModel.getFirstName() + " " + userModel.getLastName() + "-" + userModel.getUsername());
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
                    Models.Appointments app = data.getValue(Appointments.class);
                    appointmentsList.add(app);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void SelectUser(ActionEvent actionEvent) {
        for (int i = 0; i < userList.size(); i++) {
            userModel = userList.get(i);
            if (UserBox.getValue().equals(userModel.getFirstName() + " " + userModel.getLastName() + "-" + userModel.getUsername())) {
                String FullName = userModel.getFirstName() + " " + userModel.getLastName();
                Username.setText(userModel.getUsername());
                Name.setText(FullName);
                Birthday.setText(userModel.getBirthday());
                Gender.setText(userModel.getGender());
                Email.setText(userModel.getEmail());
                Number.setText(userModel.getContactNumber());
                Image image = new Image(userModel.getPicture());
                UserImage.setImage(image);
                ChildBox.setValue(" ");
                ChildName.setText(" ");
                ChildBirthday.setText(" ");
                ChildConditions.setText(" ");
                Image chPic = new Image("Default.png");
                ChildImage.setImage(chPic);
                ChildName.setText(" ");
                ChildBox.getItems().clear();
                AppointmentsList.getItems().clear();
                for (int j = 0; j < childrenList.size(); j++) {
                    Children childrenModel = childrenList.get(j);
                    if (childrenModel.getParentID().equals(userModel.getUsername())) {
                        if (userModel.getUsername().equals(childrenModel.getParentID())) {
                            ChildBox.getItems().add(childrenModel.getFirstname() + " " + childrenModel.getLastname());
                        }
                    }
                }
                for (int x = 0; x < appointmentsList.size(); x++) {
                    Appointments appointmentsModel = appointmentsList.get(x);
                    if (FullName.equals(appointmentsModel.getUser())) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                AppointmentsList.getItems().add(appointmentsModel.getAppointment()+"  | STATUS: " + appointmentsModel.getStatus() + "  |  PRICE: 500PHP");
                            }
                        });

                    }
                }
            }
        }
    }

    public void SelectChild(ActionEvent actionEvent) {
        for(int j = 0; j < childrenList.size(); j++){
            Children childrenModel = childrenList.get(j);
                if (ChildBox.getValue().equals(childrenModel.getFirstname() + " " + childrenModel.getLastname())) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ChildBox.setValue(childrenModel.getFirstname() + " " + childrenModel.getLastname());
                            ChildName.setText(childrenModel.getFirstname() + " " + childrenModel.getLastname());
                            ChildBirthday.setText(childrenModel.getBirthday());
                            ChildConditions.setText(childrenModel.getConditions());
                            Image chPic = new Image(childrenModel.getPicture());
                            ChildImage.setImage(chPic);
                            ChildName.setText(childrenModel.getFirstname() + " " + childrenModel.getLastname());
                        }
                    });
                }
        }

    }

    public void Children(ActionEvent actionEvent){
        Appointments.setVisible(false);
        ManageChildren.setVisible(true);
    }

    public void Appointment(ActionEvent actionEvent){
        Appointments.setVisible(true);
        ManageChildren.setVisible(false);
    }

    public void Doctor(ActionEvent actionEvent){
        new Main().loadFXML("AdminDoctor");
        Stage closeStage = (Stage) DoctorButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void LogOut(ActionEvent actionEvent){
        new Main().LoginWindow();
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}


