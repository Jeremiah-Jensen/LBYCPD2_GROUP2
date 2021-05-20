package Controllers;

import Models.Appointments;
import Models.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserPayments implements Initializable {
    @FXML
    public Button LogOutButton, AppointmentsButton, DetailsButton, HomeButton, LoadCreditButton;
    public TextField NameTextField, CardNumTextField;
    public PasswordField CVVPasswordField;
    public ListView<String> TransactionHistory;
    public AnchorPane InputCard, CardDetails, History;
    public Label NameLabel, CardNumLabel, ExpirydateLabel, NetworkLabel, CVVLabel;
    public Label NameLabel2, CardNumLabel2, ExpirydateLabel2, NetworkLabel2;
    public ComboBox<String> Month, Year, Network;
    Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com");
    List<Appointments> appointmentsList = new ArrayList<>();
    User userModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userModel = UserLogIn.userModel;
        NameLabel2.setText(userModel.getName());
        CardNumLabel2.setText(userModel.getCardnumber());
        ExpirydateLabel2.setText(userModel.getExpirydate());
        NetworkLabel2.setText(userModel.getNetwork());

        Month.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12");
        Year.getItems().addAll("21","22","23","24","25","26","27","28","29","30","31","32");
        Network.getItems().addAll("Visa", "Mastercard","JCB");
        firebase.child("Appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Appointments app = data.getValue(Appointments.class);
                    appointmentsList.add(app);
                }
                System.out.println(appointmentsList.size());
                for(int i = 0; i < appointmentsList.size(); i++) {
                    Appointments appointmentsModel = appointmentsList.get(i);
                    String FullName = userModel.getFirstName() + " " + userModel.getLastName();
                    if(FullName.equals(appointmentsModel.getUser())){
                        if(appointmentsModel.getStatus().equals("Upcoming") || appointmentsModel.getStatus().equals("Consultation")){

                        }
                        else{
                            TransactionHistory.getItems().add("500 PHP - " + appointmentsModel.getAppointment());
                        }
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

    public void AddCardDetails(ActionEvent actionEvent) {
        Firebase firebase=new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are all credentials correct?");
        alert.setContentText("Exit this dialog box to continue editing\nClick ok to confirm");
        Optional<ButtonType> result = alert.showAndWait();

        if(CardNumTextField.getText().length() == 16 && CVVPasswordField.getText().length() == 3 && !Month.getValue().isEmpty() && !Year.getValue().isEmpty() && !Network.getValue().isEmpty()){
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
                    UserLogIn.userModel = userModel;
                    NameLabel2.setText(userModel.getName());
                    CardNumLabel2.setText(userModel.getCardnumber());
                    ExpirydateLabel2.setText(userModel.getExpirydate());
                    NetworkLabel2.setText(userModel.getNetwork());
                }

            }
        }

        else if(CardNumTextField.getText().equals(userModel.getCardnumber()) && CVVPasswordField.getText().equals(userModel.getCvv())){
            LoadCreditButton.setDisable(false);
        }

        else if (!CardNumTextField.getText().equals(userModel.getCardnumber()) && !CVVPasswordField.getText().equals(userModel.getCvv())){
            CardNumLabel.setText("Incorrect digits");
            CVVLabel.setText("Incorrect digits");
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
            if (Month.getValue().isEmpty() || Year.getValue().isEmpty() || (Month.getValue().isEmpty() && Year.getValue().isEmpty())) {
                ExpirydateLabel.setText("Enter Expiry Date");
            }

            if (Network.getValue().isEmpty()) {
                NetworkLabel.setText("Empty field");
            }

        }

    }


}