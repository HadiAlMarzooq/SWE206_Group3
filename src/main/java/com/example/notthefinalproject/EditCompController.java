package com.example.notthefinalproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditCompController implements Initializable {



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
    Button doneButton;

    @FXML
    Label error;

    @FXML
            protected void done(){


        if(nameTextField.getText() != "" &&  urlTextField.getText() != ""){
            compToEdit.compName = nameTextField.getText();
            compToEdit.compUrl = urlTextField.getText();
            compToEdit.compDate = compDatePicker.getValue();
            stage = (Stage) pane.getScene().getWindow();

            error.setOpacity(1);
            error.setTextFill(Color.GREEN);
            error.setText("Saved!");


        }else {
            error.setOpacity(1);
        }

    }


    Competition compToEdit;
    EditCompController(Competition compToEdit){
        this.compToEdit = compToEdit;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
            compDatePicker.setValue(compToEdit.compDate);
            error.setOpacity(0);
            error.setTextFill(Color.RED);
            nameTextField.setText(compToEdit.compName);


            urlTextField.setText(compToEdit.compUrl);

    }
}
