package Controllers;

import Models.User;
import com.firebase.client.Firebase;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserPayments implements Initializable {
    @FXML
    public Button LogOutButton, AppointmentsButton, DetailsButton, HomeButton;
    public TextField NameTextField, CardNumTextField, ExpirydateTextField, BankTextField, NetworkTextField;
    public PasswordField CVVPasswordField;
    public AnchorPane InputCard, CardDetails;
    public Label NameLabel, CardNumLabel, ExpirydateLabel, CVVLabel, BankLabel, NetworkLabel;
    User userModel;
    int count = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userModel = UserLogIn.userModel;
    }

    public void Switch(ActionEvent actionEvent) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), InputCard);
        translateTransition.setByY(-321);
        translateTransition.play();
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(1), CardDetails);
        translateTransition1.setByY(321);
        translateTransition1.play();
    }

    public void LogOut(ActionEvent actionEvent){
        new Main().LoginWindow();
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void MainMenu(ActionEvent actionEvent){
        new Main().MainMenuWindow();
        Stage closeStage = (Stage) HomeButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void UserAppointments(ActionEvent actionEvent){
        new Main().UserAppointmentsWindow();
        Stage closeStage = (Stage) AppointmentsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void UserDetails(ActionEvent actionEvent){
        new Main().UserDetailsWindow();
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

        if(CardNumTextField.getText().length() == 16 && CVVPasswordField.getText().length() == 3 && ExpirydateTextField.getText().length() == 5){
            if(result.isPresent()&&result.get()==ButtonType.OK){
                if(userModel==null){
                    throw new NullPointerException("Can't pass null for argument 'pathString' in child()");
                }
                else {
                    firebase.child("User").child(userModel.getId()).child("name").setValue(NameTextField.getText());
                    firebase.child("User").child(userModel.getId()).child("cardnumber").setValue(CardNumTextField.getText());
                    firebase.child("User").child(userModel.getId()).child("cvv").setValue(CVVPasswordField.getText());
                    firebase.child("User").child(userModel.getId()).child("expirydate").setValue(ExpirydateTextField.getText());
                    firebase.child("User").child(userModel.getId()).child("bank").setValue(BankTextField.getText());
                    firebase.child("User").child(userModel.getId()).child("network").setValue(NetworkTextField.getText());
                }
            }
        }
        if(NameTextField.getText().isEmpty()){
            NameLabel.setText("Empty field");
        }

        if(CardNumTextField.getText().length() != 16 || CardNumTextField.getText().isEmpty()){
            CardNumLabel.setText("Must be 16 digits");
        }

        if(CVVPasswordField.getText().length() != 3 || CVVPasswordField.getText().isEmpty()){
            CVVLabel.setText("Must be 3 digits");
        }
        if(ExpirydateTextField.getText().length() != 5 || ExpirydateTextField.getText().isEmpty()){
           ExpirydateLabel.setText("Must be 5 digits");
        }

        if(BankTextField.getText().isEmpty()){
            BankLabel.setText("Empty field");
        }

        if(NameTextField.getText().isEmpty()){
            NetworkLabel.setText("Empty field");
        }

    }
}