package Controllers;
import Models.Appointments;
import com.firebase.client.Firebase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class PostQuestionnaire implements Initializable {

    @FXML
    public ComboBox<String> PainScale = new ComboBox<>();
    public TextArea Feeling, SideEffects;
    public Button SubmitAnswers;
    public RadioButton YesButton, NoButton;
    public ToggleGroup TG1;
    public Label Warning;
    Appointments appointmentsModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentsModel = UserAppointments.appointmentsModel;
        PainScale.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    }

    public void SubmitAnswers(ActionEvent actionEvent) {
        YesButton.setToggleGroup(TG1);
        NoButton.setToggleGroup(TG1);
        Write();
    }

    private void Write(){
        System.out.println(appointmentsModel.getId());
        Firebase firebase=new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");

        if(appointmentsModel == null){
            throw new NullPointerException("Can't pass null for argument 'pathString' in child()");
        }

        else {
            if(Feeling.getText().isEmpty() || PainScale.getValue().isEmpty() || SideEffects.getText().isEmpty()){
                Warning.setText("Empty fields.");
            }
            else{
                firebase.child("Appointments").child(appointmentsModel.getId()).child("feelingQ2").setValue(Feeling.getText());
                firebase.child("Appointments").child(appointmentsModel.getId()).child("sideEffectsQ").setValue(SideEffects.getText());
                firebase.child("Appointments").child(appointmentsModel.getId()).child("painScaleQ2").setValue(PainScale.getValue());

                if(YesButton.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("painQ2").setValue("Yes");
                }

                if(NoButton.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("painQ2").setValue("No");
                }
            }
        }
    }

}
