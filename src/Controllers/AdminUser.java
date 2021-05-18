package Controllers;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminUser implements Initializable {
    @FXML
    public ImageView UserImage, ChildImage;
    public Label Username, Name, Gender, Birthday, Email, Number;
    public Label ChildName, ChildBirthday, ChildConditions;
    public ComboBox<String> ChildBox, UserBox;
    public Button LogOutButton;
    User main;
    List<User> userList = new ArrayList<>();
    List<Children> childrenList = new ArrayList<>();
    Firebase firebase = new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firebase.child("Children").addListenerForSingleValueEvent(new ValueEventListener() {
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
    }

    public void SelectUser(ActionEvent actionEvent) {
        for (int i = 0; i < userList.size(); i++) {
            User userModel = userList.get(i);
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
            }
        }
    }

    public void LogOut(ActionEvent actionEvent){
        new Main().LoginWindow();
        Stage closeStage = (Stage) LogOutButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }
}


