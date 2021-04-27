package Controllers;

import Models.User;
import com.firebase.client.Firebase;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PreQuestionnaire {
    @FXML
    public RadioButton NoPainButton, YesPainButton, YesButton1, NoButton1, YesButton2,
            NoButton2, YesButton3, NoButton3, YesButton4, NoButton4,
            YesButton5, NoButton5, YesButton6, NoButton6, YesButton7, NoButton7,
            YesButton8, NoButton8, YesButton9, NoButton9, YesButton10, NoButton10;

    @FXML
    public Button BackButton;
    @FXML
    public ChoiceBox<String> PainScale = new ChoiceBox<>(FXCollections.observableArrayList("1", "2,", "3", "4", "5", "6", "7", "8", "9", "10" ));

    @FXML
    public TextField Reason, Feeling;

    @FXML
    public ToggleGroup TG, TG1, TG2, TG3, TG4, TG5, TG6, TG7, TG8, TG9, TG10;

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

    @FXML
    private void GoBack(ActionEvent actionEvent) {
        Stage closeStage = (Stage) BackButton.getScene().getWindow();
        new Main().CloseButton(closeStage);
    }

    private void Write(){
        User model = new User();
        if(YesPainButton.isSelected()){
            Firebase firebase=new Firebase("https://lbycpd2-grp2-default-rtdb.firebaseio.com/");
                    firebase.child("User").child(model.getId()).child("User").setValue("Yes");
        }

    }

}
