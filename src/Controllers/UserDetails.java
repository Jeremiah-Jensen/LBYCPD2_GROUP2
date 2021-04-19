package Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import Models.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class UserDetails implements Initializable {
    @FXML
    public AnchorPane ScheduleAppointment,UpcomingAppointments;
    public Button LogOutButton, HomeButton, AppointmentsButton;
    public Label Name, Birthday, Email, Number, Gender, Username;
    public Text test;
    String Uname;
    int count = 0;
    User userModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userModel = UserLogIn.userModel;
        String FullName = userModel.getFirstName() + " " + userModel.getLastName();
        Username.setText(FullName);
        Name.setText(userModel.getUsername());
        Birthday.setText(userModel.getBirthday());
        Gender.setText(userModel.getGender());
        Email.setText(userModel.getEmail());
        Number.setText(userModel.getContactNumber());
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
}
