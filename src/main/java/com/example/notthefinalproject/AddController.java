package com.example.notthefinalproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddController implements Initializable {



    @FXML
    Stage stage;
    @FXML
    AnchorPane pane;
    @FXML
    Label compNameLabel;

    @FXML
    DatePicker compDatePicker;


    @FXML
    TextField nameTextField;
    @FXML
    TextField dateTextField;
    @FXML
    TextField urlTextField;


    @FXML
    CheckBox isTeam;
    @FXML
    Button doneButton;

    @FXML
    Label error;

    @FXML
    protected void done(){


        if(nameTextField.getText() != "" &&  urlTextField.getText() != ""){
            System.out.println(isTeam.isSelected());
           FileReader.competitions.add(new Competition(nameTextField.getText(), compDatePicker.getValue(), urlTextField.getText(), !isTeam.isSelected()));

            error.setOpacity(1);
            error.setTextFill(Color.GREEN);
            error.setText("Saved!");


        }else {
            error.setOpacity(1);
        }

    }


    Competition compToEdit;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        error.setOpacity(0);
        error.setTextFill(Color.RED);


    }
}
