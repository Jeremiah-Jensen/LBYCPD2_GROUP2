package Controllers;

import Models.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import javafx.event.ActionEvent;

import javax.naming.Name;
import javax.print.Doc;
import java.awt.*;
import java.util.ResourceBundle;


public class UserLogIn implements Initializable {
    @FXML
    public AnchorPane LogIn, Register, UserInfo, Background1, Background2, Background3, Background4;
    public TextField UsernameLogin, PasswordLogin, FirstName, LastName, Username, Birthdate, Email, Number, Gender;
    public TextArea Address, Conditions;
    public PasswordField Password, ReenterPassword;
    public Label Error1;
    public Label Error2;
    public Label Error3;
    public Label Error4;
    public Label Error5;
    public Button Next, LoginButton, DoctorLoginButton;
    int count = 0;
    int returnValue;

    @Override
    public void initialize(URL Location, ResourceBundle resources){

    }

    public void LogInAction(ActionEvent actionEvent) {
        if(UsernameLogin.getText().isEmpty() || PasswordLogin.getText().isEmpty()) {
            Error5.setVisible(false);
            Error4.setVisible(true);
        }
        else {
            Error4.setVisible(false);
            Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
            firebase.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    returnValue = 0;
                    for(DataSnapshot data: dataSnapshot.getChildren()) {
                        User user = data.getValue(User.class);
                        if(UsernameLogin.getText().equals(user.getUsername()) && PasswordLogin.getText().equals(user.getPassword())) {
                            returnValue = 1;
                        }
                    }
                    if(returnValue == 1) {
                        new Main().MainMenuWindow();
                        Stage closeStage = (Stage) LoginButton.getScene().getWindow();
                        new Main().CloseButton(closeStage);
                        Error5.setVisible(false);
                        System.out.println("Found in Database"); // initial code -- to be change with codes for logging in to main
                    }
                    else if(returnValue == 0) {
                        Error4.setVisible(false);
                        // there is no existing user
                        Error5.setVisible(true);
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
    }

    public void RegisterAction(ActionEvent actionEvent){
        if(Birthdate.getText().isEmpty() || Gender.getText().isEmpty() || Email.getText().isEmpty() || Number.getText().isEmpty()) {
            Error2.setVisible(true);
        } else {
            Write();
            Reset();
            Clear();
        }
    }

    public void Next(ActionEvent actionEvent){
        if(FirstName.getText().isEmpty() || LastName.getText().isEmpty() || Username.getText().isEmpty() || Password.getText().isEmpty() || ReenterPassword.getText().isEmpty()) {
            // please enter all fields
            Error1.setVisible(true);
            // passwords do not match
            Error3.setVisible(false);
        }
        else if(!Password.getText().equals(ReenterPassword.getText())) {
            // passwords do not match
            Error3.setVisible(true);
            // please enter all fields
            Error1.setVisible(false);
        }

        else {
            Error1.setVisible(false);
            Error3.setVisible(false);
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), Register);
            translateTransition.setByX(-380);
            translateTransition.play();
            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(1), UserInfo);
            translateTransition2.setByX(-380);
            translateTransition2.play();
            Next.setDisable(true);

            count = 1;
        }
    }

    public void Back(ActionEvent actionEvent){
        if(count == 0) {
            //this code basically moves the anchor panes lang, no need to set false
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), LogIn);
            translateTransition.setByX(-380);
            translateTransition.play();
            //just to pause
            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(1), Register);
            translateTransition2.setByX(380);
            translateTransition2.play();
            count = 0;
        }
        else {
            Reset();
        }
        Clear();
    }

    public void RegisterScreen(ActionEvent actionEvent) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1),LogIn);
        translateTransition.setByX(380);
        translateTransition.play();
        TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(1),Register);
        translateTransition2.setByX(-380);
        translateTransition2.play();
        Next.setDisable(false);
    }

    private void Reset(){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), LogIn);
        translateTransition.setByX(-380);
        translateTransition.play();
        TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(1), Register);
        translateTransition2.setByX(760);
        translateTransition2.play();
        TranslateTransition translateTransition3 = new TranslateTransition(Duration.seconds(1), UserInfo);
        translateTransition3.setByX(380);
        translateTransition3.play();
        count = 0;
    }

    private void Write(){
        Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
        User model = new User();
        model.setUsername(Username.getText());
        model.setPassword(Password.getText());
        model.setFirstName(FirstName.getText());
        model.setLastName(LastName.getText());
        model.setReenterPass(ReenterPassword.getText());
        model.setBirthday(Birthdate.getText());
        model.setContactNumber(Number.getText());
        model.setEmail(Email.getText());
//        model.setAddress(Address.getText().replaceAll("\n", System.getProperty("line.separator")));
        model.setGender(Gender.getText());
//        model.setCondition(Conditions.getText().replaceAll("\n", System.getProperty("line.separator")));
        firebase.child("User").push().setValue(model);
    }

    private void Clear(){
        Error1.setVisible(false);
        Error2.setVisible(false);
        Error3.setVisible(false);
        Error4.setVisible(false);
        FirstName.clear();
        LastName.clear();
        Username.clear();
        Password.clear();
        ReenterPassword.clear();
        Birthdate.clear();
        Email.clear();
        Gender.clear();
        Address.setText(" ");
        Conditions.setText(" ");
    }

    public void DoctorsLogin(ActionEvent actionEvent) {
        new Main().DoctorsLoginWindow();
        Stage closeStage = (Stage) DoctorLoginButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}