package Controllers;

import Models.Appointments;
import Models.User;
import com.firebase.client.Firebase;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PreQuestionnaire implements Initializable {
    @FXML
    public RadioButton NoPainButton, YesPainButton, YesButton1, NoButton1, YesButton2,
            NoButton2, YesButton3, NoButton3, YesButton4, NoButton4,
            YesButton5, NoButton5, YesButton6, NoButton6, YesButton7, NoButton7,
            YesButton8, NoButton8, YesButton9, NoButton9, YesButton10, NoButton10;
    public Button BackButton;
    public ChoiceBox<String> PainScale = new ChoiceBox<>(FXCollections.observableArrayList("1", "2,", "3", "4", "5", "6", "7", "8", "9", "10" ));
    public TextField Reason, Feeling;
    public ToggleGroup TG, TG1, TG2, TG3, TG4, TG5, TG6, TG7, TG8, TG9, TG10;

    Appointments appointmentsModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentsModel = UserAppointments.appointmentsModel;
    }

    public void SubmitAnswers(ActionEvent actionEvent) {
        YesPainButton.setToggleGroup(TG);
        NoPainButton.setToggleGroup(TG);

//        YesButton1.setToggleGroup(TG1);
//        NoButton1.setToggleGroup(TG1);
//
//        YesButton2.setToggleGroup(TG2);
//        NoButton2.setToggleGroup(TG2);
//
//        YesButton3.setToggleGroup(TG3);
//        NoButton3.setToggleGroup(TG3);
//
//        YesButton4.setToggleGroup(TG4);
//        NoButton4.setToggleGroup(TG4);
//
//        YesButton5.setToggleGroup(TG5);
//        NoButton5.setToggleGroup(TG5);
//
//        YesButton6.setToggleGroup(TG6);
//        NoButton6.setToggleGroup(TG6);
//
//        YesButton7.setToggleGroup(TG7);
//        NoButton7.setToggleGroup(TG7);
//
//        YesButton8.setToggleGroup(TG8);
//        NoButton8.setToggleGroup(TG8);
//
//        YesButton9.setToggleGroup(TG9);
//        NoButton9.setToggleGroup(TG9);
//
//        YesButton10.setToggleGroup(TG10);
//        NoButton10.setToggleGroup(TG10);

        Write();
    }

    @FXML
    private void GoBack(ActionEvent actionEvent) {
        Stage closeStage = (Stage) BackButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
        new Main().UserAppointmentsWindow();
        Stage closeStage2 = (Stage) BackButton.getScene().getWindow();
        new Main().CloseButton(closeStage2);
    }

    private void Write(){
        System.out.println(appointmentsModel.getId());
        Firebase firebase=new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
        if(appointmentsModel==null){
            throw new NullPointerException("Can't pass null for argument 'pathString' in child()");
        }
        else {
            if(YesPainButton.isSelected()){
                firebase.child("Appointments").child(appointmentsModel.getId()).child("painQ1").setValue("Yes");
            }
            else if(NoPainButton.isSelected()){
                firebase.child("Appointments").child(appointmentsModel.getId()).child("painQ1").setValue("No");
            }

            firebase.child("Appointments").child(appointmentsModel.getId()).child("status").setValue("Consultation");
        }
    }

}