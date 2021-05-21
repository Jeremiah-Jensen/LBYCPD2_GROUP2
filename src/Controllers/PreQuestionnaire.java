package Controllers;
import Models.Appointments;
import com.firebase.client.Firebase;
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
    public Button SubmitAnswers;
    public ComboBox<String> PainComboBox = new ComboBox<>();
    public TextArea Reason, Feeling;
    public ToggleGroup TG, TG1, TG2, TG3, TG4, TG5, TG6, TG7, TG8, TG9, TG10;
    public Label Warning, ComboBoxWarning;
    Appointments appointmentsModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentsModel = UserAppointments.appointmentsModel;
        PainComboBox.getItems().addAll("0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    }

    public void SubmitAnswers(ActionEvent actionEvent) {
        YesPainButton.setToggleGroup(TG);
        NoPainButton.setToggleGroup(TG);

        YesButton1.setToggleGroup(TG1);
        NoButton1.setToggleGroup(TG1);

        YesButton2.setToggleGroup(TG2);
        NoButton2.setToggleGroup(TG2);

        YesButton3.setToggleGroup(TG3);
        NoButton3.setToggleGroup(TG3);

        YesButton4.setToggleGroup(TG4);
        NoButton4.setToggleGroup(TG4);

        YesButton5.setToggleGroup(TG5);
        NoButton5.setToggleGroup(TG5);

        YesButton6.setToggleGroup(TG6);
        NoButton6.setToggleGroup(TG6);

        YesButton7.setToggleGroup(TG7);
        NoButton7.setToggleGroup(TG7);

        YesButton8.setToggleGroup(TG8);
        NoButton8.setToggleGroup(TG8);

        YesButton9.setToggleGroup(TG9);
        NoButton9.setToggleGroup(TG9);

        YesButton10.setToggleGroup(TG10);
        NoButton10.setToggleGroup(TG10);

        Write();
    }

    private void Write(){
        System.out.println(appointmentsModel.getId());
        if(appointmentsModel==null){
            throw new NullPointerException("Can't pass null for argument 'pathString' in child()");
        }
        else {

            if(Reason.getText().isEmpty() || PainComboBox.getEditor().getText().isEmpty() || Feeling.getText().isEmpty()){
                Warning.setText("Empty fields.");
            }

            else if(!YesPainButton.isSelected() && !NoPainButton.isSelected() || !YesButton1.isSelected() && !NoButton1.isSelected()
                    || !YesButton2.isSelected() && !NoButton2.isSelected() || !YesButton3.isSelected() && !NoButton3.isSelected() ||
                    !YesButton4.isSelected() && !NoButton4.isSelected()  ||
                    !YesButton5.isSelected() && !NoButton5.isSelected()  ||
                    !YesButton6.isSelected() && !NoButton6.isSelected()
                    || !YesButton7.isSelected() && !NoButton7.isSelected()  ||
                    !YesButton8.isSelected() && !NoButton8.isSelected()
                    || !YesButton9.isSelected() && !NoButton9.isSelected()  ||
                    !YesButton10.isSelected() && !NoButton10.isSelected()){
                Warning.setText("Tick choices.");

            }

            else {
                Firebase firebase=new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
                if(YesPainButton.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("painQ1").setValue("Yes");
                }

                if(NoPainButton.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("painQ1").setValue("No");
                }

                if(YesButton1.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q1").setValue("Yes");
                }

                if(NoButton1.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q1").setValue("No");
                }

                if(YesButton2.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q2").setValue("Yes");
                }

                if(NoButton2.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q2").setValue("No");
                }

                if(YesButton3.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q3").setValue("Yes");
                }

                if(NoButton3.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q3").setValue("No");
                }

                if(YesButton4.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q4").setValue("Yes");
                }

                if(NoButton4.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q4").setValue("No");
                }

                if(YesButton5.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q5").setValue("Yes");
                }

                if(NoButton5.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q5").setValue("No");
                }

                if(YesButton6.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q6").setValue("Yes");
                }

                if(NoButton6.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q6").setValue("No");
                }

                if(YesButton7.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q7").setValue("Yes");
                }

                if(NoButton7.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q7").setValue("No");
                }

                if(YesButton8.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q8").setValue("Yes");
                }

                if(NoButton8.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q8").setValue("No");
                }

                if(YesButton9.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q9").setValue("Yes");
                }

                if(NoButton9.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q9").setValue("No");
                }

                if(YesButton10.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q10").setValue("Yes");
                }

                if(NoButton10.isSelected()){
                    firebase.child("Appointments").child(appointmentsModel.getId()).child("q10").setValue("No");
                }

                firebase.child("Appointments").child(appointmentsModel.getId()).child("feelingQ").setValue(Feeling.getText());
                firebase.child("Appointments").child(appointmentsModel.getId()).child("reasonQ").setValue(Reason.getText());
                firebase.child("Appointments").child(appointmentsModel.getId()).child("painScale").setValue(PainComboBox.getValue());
                firebase.child("Appointments").child(appointmentsModel.getId()).child("status").setValue("Consultation");
                new Main().loadFXML("UserAppointments");
                Stage closeStage = (Stage) SubmitAnswers.getScene().getWindow();
                new Main().CloseButton(closeStage);

            }

        }
    }

    }

