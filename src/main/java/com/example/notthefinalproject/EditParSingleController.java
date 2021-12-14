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

public class EditParSingleController implements Initializable {

    boolean validId = true;
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
    TextField idField;

    @FXML
    TextField majorField;




    @FXML
    protected void done(){
        validId = true;
        validRank = true;
        for(char c : rankField.getText().strip().toCharArray()){

            if(!Character.isDigit(c) && !(c == '-')){
                validRank = false;
            }
        }

        for(char c : idField.getText().strip().toCharArray()){
            if(!Character.isDigit(c)){
                validId =false;
            }
        }
        if(idField.getText().strip().length() != 9){
            validId =false;
        }
        if(nameField.getText() != "" &&  idField.getText() != "" && validId && validRank && rankField.getText() != "" && majorField.getText() != ""){




            ((SinglePartecipant) partecipant).partecipant.name = nameField.getText();
            ((SinglePartecipant) partecipant).partecipant.id = Double.parseDouble(idField.getText());
            ((SinglePartecipant) partecipant).partecipant.major = majorField.getText();
            partecipant.rank = rankField.getText();
            message.setText("Saved!");
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



    Partecipant partecipant;

    EditParSingleController(Partecipant partecipant){this.partecipant = partecipant;}


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Student p = ((SinglePartecipant)(partecipant)).partecipant;
    message.setOpacity(0);
    majorField.setText(p.major);
    nameField.setText(p.name);
    String id = p.id.toString();
    id = id.substring(0, id.length()-3);
        id =id.replace(".", "");
    while (id.length()<9){
            id+="0";
    }

    rankField.setText(partecipant.rank);
    idField.setText(id);



        parName.setText(((SinglePartecipant)(partecipant)).partecipant.name);

    }
}
