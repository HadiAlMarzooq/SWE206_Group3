package com.example.notthefinalproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddTeamController implements Initializable {

    boolean validRank = true;


    @FXML
    Label parName;
    @FXML
    Label message;

    @FXML
    Button done;

    @FXML
    TextField nameField;
    @FXML
    TextField rankField;







    @FXML
    protected void done(){

        validRank = true;
        for(char c : rankField.getText().strip().toCharArray()){

            if(!Character.isDigit(c) && !(c == '-')){
                validRank = false;
            }
        }
        if(nameField.getText() != "" && validRank){

            comp.addPartecipant(new TeamPartecipant(nameField.getText(), rankField.getText()));

            message.setText("Saved");
            message.setTextFill(Color.GREEN);
            message.setOpacity(1);
//            compToEdit.compName = nameTextField.getText();
//            compToEdit.compUrl = urlTextField.getText();
//            compToEdit.compDate = compDatePicker.getValue();
//            stage = (Stage) pane.getScene().getWindow();
//
//            error.setOpacity(1);
//            error.setTextFill(Color.GREEN);
//            error.setText("Saved!");


        }else {
            message.setText("Error");
            message.setTextFill(Color.RED);
            message.setOpacity(1);
        }

    }



    Competition comp;


    AddTeamController(Competition comp){this.comp = comp;}


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        message.setOpacity(0);
        parName.setText(comp.compName);

    }
}
