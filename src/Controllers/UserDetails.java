package Controllers;
import Models.Appointments;
import Models.Children;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import Models.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserDetails implements Initializable {
    @FXML
    public AnchorPane ScheduleAppointment, ManageChildren, EditDetails, AddChildren;
    public Button LogOutButton, HomeButton, AppointmentsButton, PaymentsButton;
    public Label Name, Birthday, Email, Number, Gender, Username, ChildName, ChildConditions, ChildBirthday, ChildPic, Error;
    public TextField ChildFN, ChildLN, ChildCN;
    public ImageView UserImage, ChildImage;
    public ComboBox<String> ChildBox, Day, Month, Year;
    public Text test;
    public String Pic;
    public String path = "Default.png";
    int count = 0;
    List<Children> childrenList;
    User userModel;
    Children childrenModel;
    Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com");

    public UserDetails(){
        firebase.child("Child").addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Children childrenModel=data.getValue(Children.class);
                    childrenModel.setId(data.getKey());
                    childrenList.add(childrenModel);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
            });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        childrenList =new ArrayList<>();
        userModel = UserLogIn.userModel;
        String FullName = userModel.getFirstName() + " " + userModel.getLastName();
        Username.setText(FullName);
        Name.setText(userModel.getUsername());
        Birthday.setText(userModel.getBirthday());
        Gender.setText(userModel.getGender());
        Email.setText(userModel.getEmail());
        Number.setText(userModel.getContactNumber());
        Pic = userModel.getPicture();
        Image image = new Image(Pic);
        UserImage.setImage(image);
        Image child = new Image("Default.png");
        ChildImage.setImage(child);

        for (int j = 0; j < 18; j++){
            int day = 2021 - j;
            String result = String.valueOf(day);
            Year.getItems().add(result);
        }
        for (int k = 1; k < 32; k++){
            String result = String.valueOf(k);
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

        firebase.child("Children").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Children child = data.getValue(Children.class);
                    childrenList.add(child);
                }
                for (int i = 0; i < childrenList.size(); i++) {
                    Children childrenModel = childrenList.get(i);
                    if (userModel.getId().equals(childrenModel.getParentID())) {
                        ChildBox.getItems().add(childrenModel.getFirstname() + " " + childrenModel.getLastname());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                ChildName.setText(childrenModel.getFirstname() + " " + childrenModel.getLastname());
                                ChildBirthday.setText(childrenModel.getBirthday());
                                ChildConditions.setText(childrenModel.getConditions());
                                Image chPic = new Image(childrenModel.getPicture());
                                ChildImage.setImage(chPic);
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


    public  void RegisterChild(ActionEvent actionEvent){
        if(ChildFN.getText().isEmpty() || ChildLN.getText().isEmpty() || ChildCN.getText().isEmpty() || Day.getValue().isEmpty() || Month.getValue().isEmpty() || Year.getValue().isEmpty()){
            Error.setVisible(true);
        }
        else{
            Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
            Children model = new Children();
            model.setParentID(userModel.getId());
            model.setBirthday(Month.getValue() + " " + Day.getValue() + ", " + Year.getValue());
            model.setFirstname(ChildFN.getText());
            model.setLastname(ChildLN.getText());
            model.setConditions(ChildCN.getText());
            model.setPicture(path);
            firebase.child("Child").push().setValue(model);
        }
    }

    public  void ProfilePic(ActionEvent actionEvent){
        File file;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Child Profile Pic");
        file = fileChooser.showOpenDialog(null);
        path = file.getName();
        ChildPic.setText(path);
    }

    public  void SelectChild(ActionEvent actionEvent){
        firebase.child("Child").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Children child = data.getValue(Children.class);
                    childrenList.add(child);
                }
                for (int i = 0; i < childrenList.size(); i++) {
                    Children childrenModel = childrenList.get(i);
                    if (ChildBox.getValue().equals(childrenModel.getFirstname() + " " + childrenModel.getLastname())) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                ChildName.setText(ChildBox.getValue());
                                ChildBirthday.setText(childrenModel.getBirthday());
                                ChildConditions.setText(childrenModel.getConditions());
                                Image chPic = new Image(childrenModel.getPicture());
                                ChildImage.setImage(chPic);
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

    public void AddChild(ActionEvent actionEvent){
        ManageChildren.setVisible(false);
        AddChildren.setVisible(true);
    }

    public void GoBack(ActionEvent actionEvent){
        ManageChildren.setVisible(true);
        AddChildren.setVisible(false);
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

    public void LogOut(ActionEvent actionEvent){
        new Main().LoginWindow();
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    public void UserPayments(ActionEvent actionEvent){
        new Main().UserPaymentsWindow();
        Stage closeStage = (Stage) PaymentsButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}
