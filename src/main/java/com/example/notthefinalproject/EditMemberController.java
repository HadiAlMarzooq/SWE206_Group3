
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

public class EditMemberController implements Initializable {




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


        if(nameField.getText() != "" &&  idField.getText() != ""){

            st.name = nameField.getText();
            st.id = Double.parseDouble(idField.getText());
            st.major = majorField.getText();
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
    Student st;

    EditMemberController(Partecipant partecipant, Student st){this.partecipant = partecipant;this.st = st; }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        message.setOpacity(0);
        majorField.setText(st.major);
        nameField.setText(st.name);
        rankField.setText(partecipant.rank);
        idField.setText(st.id+"");



        parName.setText(st.name);

    }
}
