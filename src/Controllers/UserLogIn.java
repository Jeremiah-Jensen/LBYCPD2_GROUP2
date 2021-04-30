package Controllers;

import Models.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.ResourceBundle;

//dont touch
public class UserLogIn implements Initializable {
    @FXML
    public AnchorPane LogIn, Register, UserInfo, Background1;
    public TextField UsernameLogin, PasswordLogin, FirstName, LastName, Username, Birthdate, Email, Number, Address1, Address2;
    public PasswordField Password, ReenterPassword;
    public Label Error1,Error2, Error3, Error4, Error5, Pic;
    public Button Next, LoginButton, DoctorLoginButton, RegisterButton, AddPic;
    public ComboBox<String> Day, Month, Year, Gender;
    public String path = "Default.png";
    int count = 0;
    int returnValue;

    List<User> userList;
    public static User userModel;

    public UserLogIn(){
        Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com");
        firebase.child("User").addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User userModel=data.getValue(User.class);
                    userModel.setId(data.getKey());
                    userList.add(userModel);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    @Override
    public void initialize(URL Location, ResourceBundle resources){
        userList();
        for (int i = 0; i < 80; i++){
            int day = 2003 - i;
            String result = String.valueOf(day);
            Year.getItems().add(result);
        }
        for (int i = 1; i < 32; i++){
            String result = String.valueOf(i);
            Day.getItems().add(result);
        }
        Month.getItems().add("January");
        Month.getItems().add("February");
        Month.getItems().add("March");
        Month.getItems().add("April");
        Month.getItems().add("May");
        Month.getItems().add("June");
        Month.getItems().add("July");
        Month.getItems().add("August");
        Month.getItems().add("September");
        Month.getItems().add("October");
        Month.getItems().add("November");
        Month.getItems().add("December");

        Gender.getItems().add("Male");
        Gender.getItems().add("Female");
        Gender.getItems().add("Non-Binary");
        Gender.getItems().add("Prefer not to say");
    }

    private void userList(){
        userList=new ArrayList<>();
    }

    public void LogInAction(ActionEvent actionEvent) {

        if(UsernameLogin.getText().isEmpty() || PasswordLogin.getText().isEmpty()) {
            Error5.setVisible(false);
            Error4.setVisible(true);
        }
        else {
            Error4.setVisible(false);

            for (int i = 0; i <userList.size() ; i++) {
                User model=userList.get(i);
                if (UsernameLogin.getText().equals(model.getUsername()) && PasswordLogin.getText().equals(model.getPassword())) {
                    returnValue = 1;
                    userModel = userList.get(i);
                    System.out.println("Found in Database");
                }
            }

            if (returnValue==1){
                new Main().MainMenuWindow();
                Stage closeStage = (Stage) LoginButton.getScene().getWindow();
                new Main().CloseButton(closeStage);
            }else if (returnValue==0){
                Error4.setVisible(false);
                // there is no existing user
                Error5.setVisible(true);
            }
        }
    }

    public void RegisterAction(ActionEvent actionEvent){
        if(Day.getValue().isEmpty() || Month.getValue().isEmpty() || Year.getValue().isEmpty() || Gender.getValue().isEmpty() || Email.getText().isEmpty() || Number.getText().isEmpty() || Address2.getText().isEmpty() || Address1.getText().isEmpty()) {
            Error2.setVisible(true);
        } else {
            Write();
            Reset();
            new Main().LoginWindow();
            Stage closeStage = (Stage) RegisterButton.getScene().getWindow();
            new Main().CloseButton(closeStage);
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
        UsernameLogin.clear();
        PasswordLogin.clear();
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
        model.setBirthday(Month.getValue() + " " + Day.getValue() + ", " + Year.getValue());
        model.setContactNumber(Number.getText());
        model.setEmail(Email.getText());
        model.setAddress(Address2.getText() + "_" + Address1.getText());
        model.setGender(Gender.getValue());
        model.setPicture(path);
        model.setCredit(" ");
        model.setName(" ");
        model.setCardnumber(" ");
        model.setBank(" ");
        model.setCvv(" ");
        model.setExpirydate(" ");
        model.setNetwork(" ");
        firebase.child("User").push().setValue(model);
    }

    public  void ProfilePic(ActionEvent actionEvent){
        File file;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Pic");
        file = fileChooser.showOpenDialog(null);
        path = file.getName();
        Pic.setText(path);
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
        Email.clear();
        Gender.setValue(" ");
        Day.setValue(" ");
        Month.setValue(" ");
        Gender.setValue(" ");
        Address1.clear();
        Address2.clear();
        Pic.setText("-No Photo Selected-");
    }

    public void DoctorsLogin(ActionEvent actionEvent) {
        new Main().DoctorsLoginWindow();
        Stage closeStage = (Stage) DoctorLoginButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}