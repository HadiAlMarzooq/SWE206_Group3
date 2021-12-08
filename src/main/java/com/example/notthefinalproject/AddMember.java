package com.example.notthefinalproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class AddMember implements Initializable {




    @FXML
    Label parName;
    @FXML
    Label message;

    @FXML
    Button done;

    @FXML
    TextField nameField;

    @FXML
    TextField idField;

    @FXML
    TextField majorField;




    @FXML
    protected void done(){


        if(nameField.getText() != "" &&  idField.getText() != ""){

            team.addMumber(new Student(
                    nameField.getText(),
                    Double.parseDouble(idField.getText()) ,
                    majorField.getText()
            ));

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



   TeamPartecipant team;


    AddMember(TeamPartecipant team){this.team = team;}


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        message.setOpacity(0);
        parName.setText(team.teamName);

    }
}
