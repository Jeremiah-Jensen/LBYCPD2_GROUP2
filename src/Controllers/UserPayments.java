package Controllers;

import Models.Appointments;
import Models.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserPayments implements Initializable {
    @FXML
    public Button HelpButton, LogOutButton, AppointmentsButton, DetailsButton, HomeButton, Switch;
    public TextField NameTextField, CardNumTextField;
    public Text NameLabel2, CardNumLabel2, ExpirydateLabel2, NetworkLabel2;
    public PasswordField CVVPasswordField;
    public ListView<String> TransactionHistory;
    public AnchorPane InputCard, CardDetails, History;
    public Label NameLabel, CardNumLabel, ExpirydateLabel, NetworkLabel, CVVLabel;
//    public Label NameLabel2, CardNumLabel2, ExpirydateLabel2, NetworkLabel2;
    public ComboBox<String> Month, Year, Network;
    Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com");
    List<Appointments> appointmentsList = new ArrayList<>();
    List<User> usersList = new ArrayList<>();
    User userModel = UserLogIn.userModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String FullName = userModel.getFirstName() + " " + userModel.getLastName();
        Month.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12");
        Year.getItems().addAll("21","22","23","24","25","26","27","28","29","30","31","32");
        Network.getItems().addAll("Visa", "Mastercard","JCB");
        firebase.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User app = data.getValue(User.class);
                    usersList.add(app);
                }
                for(int i = 0; i < usersList.size(); i++) {
                    User RT = usersList.get(i);
                    if(FullName.equals(usersList.get(i).getFirstName() + " " + usersList.get(i).getLastName())){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                NameLabel2.setText(RT.getName());
                                System.out.println(RT.getName());
                                CardNumLabel2.setText(RT.getCardnumber());
                                ExpirydateLabel2.setText(RT.getExpirydate());
                                NetworkLabel2.setText(RT.getNetwork());
                            }
                        });
                    }
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
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if(appointmentsModel.getStatus().equals("Upcoming") || appointmentsModel.getStatus().equals("Consultation")){

                                }
                                else{
                                    TransactionHistory.getItems().add("500 PHP - " + appointmentsModel.getAppointment());
                                }

                            }
                        });

                    }
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void Switch(ActionEvent actionEvent) {
        InputCard.setVisible(true);
        History.setVisible(false);
    }

    public void GoBack(ActionEvent actionEvent) {
        InputCard.setVisible(false);
        History.setVisible(true);
    }


    public void LogOut(ActionEvent actionEvent){
        new Main().LoginWindow();
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void MainMenu(ActionEvent actionEvent){
        new Main().loadFXML("MainMenu");
        Stage closeStage = (Stage) HomeButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void UserAppointments(ActionEvent actionEvent){
        new Main().loadFXML("UserAppointments");
        Stage closeStage = (Stage) AppointmentsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void UserDetails(ActionEvent actionEvent){
        new Main().loadFXML("UserDetails");
        Stage closeStage = (Stage) DetailsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void UserHelpMenu(ActionEvent actionEvent) {
        new Main().loadFXML("UserHelpMenu");
        Stage closeStage = (Stage) HelpButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void AddCardDetails(ActionEvent actionEvent) {
        Firebase firebase=new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are all credentials correct?");
        alert.setContentText("Exit this dialog box to continue editing\nClick ok to confirm");
        Optional<ButtonType> result = alert.showAndWait();

        if(CardNumTextField.getText().length() == 16 && CVVPasswordField.getText().length() == 3 && !Month.getSelectionModel().isSelected(-1) && !Year.getSelectionModel().isSelected(-1) && !Network.getSelectionModel().isSelected(-1)){
            if(result.isPresent()&&result.get()==ButtonType.OK){
                if(userModel==null){
                    throw new NullPointerException("Can't pass null for argument 'pathString' in child()");
                }
                else {
                    firebase.child("User").child(userModel.getId()).child("name").setValue(NameTextField.getText());
                    firebase.child("User").child(userModel.getId()).child("cardnumber").setValue(CardNumTextField.getText());
                    firebase.child("User").child(userModel.getId()).child("cvv").setValue(CVVPasswordField.getText());
                    firebase.child("User").child(userModel.getId()).child("expirydate").setValue(Month.getValue()+"/"+Year.getValue());
                    firebase.child("User").child(userModel.getId()).child("network").setValue(Network.getValue());
                    NameTextField.clear();
                    CardNumTextField.clear();
                    CVVPasswordField.clear();
                    InputCard.setVisible(false);
                    History.setVisible(true);
                    new Main().loadFXML("UserPayments");
                    Stage closeStage = (Stage) Switch.getScene().getWindow();
                    new Main().CloseButton(closeStage);
                }
            }
        }

        else {
            if (NameTextField.getText().isEmpty()) {
                NameLabel.setText("Empty field");
            }

            if (CardNumTextField.getText().length() != 16 || CardNumTextField.getText().isEmpty()) {
                CardNumLabel.setText("Must be 16 digits");
            }

            if (CVVPasswordField.getText().length() != 3 || CVVPasswordField.getText().isEmpty()) {
                CVVLabel.setText("Must be 3 digits");
            }
            if (Month.getSelectionModel().isSelected(-1) || Year.getSelectionModel().isSelected(-1)) {
                ExpirydateLabel.setText("Enter Expiry Date");
            }

            if (Network.getSelectionModel().isSelected(-1)) {
                NetworkLabel.setText("Empty field");
            }

        }
    }
}