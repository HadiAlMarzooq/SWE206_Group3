package com.example.notthefinalproject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    final String mailTemplate = """
Dear [Student name/Team name],

Conguratulation on your achievement in [Competition name]. This achievement is deeply appreciated by the unversity and we will announce it in the approbrite medias.\n

In case you have Photos you want to share with the news post, reply to this email with the photos.

Regards and Congrats,
KFUPM News Team
            """;

    Competition selectedComp = null;

    @FXML
    Button addComp;

    public static boolean didChange = false;

    @FXML
    ListView<Competition> compList;


    @FXML
    ListView<Partecipant> partList;

    @FXML
    Label parLabel;

    @FXML
    Button editComp;


    @FXML
    Button editPar;

    @FXML
    Button addPar;
    @FXML
    ListView<Student> memberList;

    @FXML
    Button memberAdd;


    @FXML
    WebView webView;

    @FXML
    WebEngine engine;

    @FXML
    Button memberEdit;

    @FXML
    Label mamberLabel;
    @FXML
    protected void editPar() throws IOException {
        didChange = true;

        if(compList.getFocusModel().getFocusedItem().single){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("edit-par-single.fxml"));
            EditParSingleController controller = new EditParSingleController(partList.getFocusModel().getFocusedItem());
            fxmlLoader.setController(controller);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 590, 500);
            stage.setTitle("CompTracker");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("edit-par-team.fxml"));
            EditTeamController controller = new EditTeamController(partList.getFocusModel().getFocusedItem());
            fxmlLoader.setController(controller);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 590, 500);
            stage.setTitle("CompTracker");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }

    }


    @FXML
    protected void save() throws IOException, InvalidFormatException {
        didChange = false;
        FileReader.writeData();
    }
    @FXML
    protected void addPar() throws IOException, InvalidFormatException {
        didChange = true;
        if(compList.getFocusModel().getFocusedItem().single){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-par-single.fxml"));
            AddParSingleController controller = new AddParSingleController(compList.getFocusModel().getFocusedItem());
            fxmlLoader.setController(controller);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 590, 500);
            stage.setTitle("CompTracker");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-par-team.fxml"));
            AddTeamController controller = new AddTeamController(compList.getFocusModel().getFocusedItem());
            fxmlLoader.setController(controller);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 590, 500);
            stage.setTitle("CompTracker");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
        }

    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }



    @FXML
    protected void editMemeber() throws IOException {
        didChange = true;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("edit-member.fxml"));
        EditMemberController controller = new EditMemberController(partList.getFocusModel().getFocusedItem(), memberList.getFocusModel().getFocusedItem());
        fxmlLoader.setController(controller);
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 590, 500);
        stage.setTitle("CompTracker");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    protected void addComp() throws IOException {
        didChange = true;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-comp.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 590, 500);

        stage.setTitle("CompTracker");

        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                refreshList();
            }
        });


    }
    @FXML
    protected void editComp() throws IOException {

        didChange = true;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("edit-comp.fxml"));
        EditCompController controller = new EditCompController(compList.getFocusModel().getFocusedItem());
        fxmlLoader.setController(controller);
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 590, 500);
        stage.setTitle("CompTracker");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                refreshList();
            }
        });


    }


    @FXML
    Button deleteComp;


    @FXML
    Label compName;
    @FXML
    Label nameLabel;
    @FXML
    Label compDate;
    @FXML
    Label dateLabel;
    @FXML
    Label parName;
    @FXML
    Label parMajor;
    @FXML
    Label parId;
    @FXML
    Label parRank;
    @FXML
    Label nameLabel2;
    @FXML
    Label idLabel;
    @FXML
    Label majorLabel;
    @FXML
    Label rankLabel;


    @FXML
    protected void deleteComp(){
        didChange = true;
        FileReader.competitions.remove(compList.getFocusModel().getFocusedItem());
        compList.getItems().setAll(FileReader.competitions);
    }

    @FXML
    Button deletePar;

    @FXML
    protected void deletePar() throws URISyntaxException, IOException {
        didChange = true;
        compList.getFocusModel().getFocusedItem().partecipants.remove(partList.getFocusModel().getFocusedItem());
        partList.getItems().setAll(compList.getFocusModel().getFocusedItem().partecipants);
        memberList.setOpacity(0);
        memberAdd.setOpacity(0);
        memberEdit.setOpacity(0);
        mamberLabel.setOpacity(0);


    }

    @FXML
    Button deleteMember;

    @FXML
    Button sendEmailPar;

    @FXML
    Label mamberName;

    @FXML
    protected  void sendEmailPar() throws IOException, URISyntaxException {
        Desktop desktop;
        if (Desktop.isDesktopSupported()
                && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
            if(compList.getFocusModel().getFocusedItem().single){

                String body = mailTemplate.replace("[Student name/Team name]", ((SinglePartecipant) partList.getFocusModel().getFocusedItem()).partecipant.name);
                body = body.replace("[Competition name]", compList.getFocusModel().getFocusedItem().compName);
                body = body.replaceAll("\n", "%0A");
                body = body.replaceAll(" ", "%20");
                String stId = ((SinglePartecipant)partList.getFocusModel().getFocusedItem()).partecipant.id.toString();
                stId = stId.substring(0, stId.length()-3);
                while (stId.length()<9){
                    stId+="0";
                }
                stId =stId.replace(".", "");
                String email = stId;

                email = email+"@kfupm.edu.sa";
                String subject = compList.getFocusModel().getFocusedItem().compName;
                URI mailto = new URI("mailto:"+email+ "?subject=Info&body="+body);
                desktop.mail(mailto);
            } else{
                String body = mailTemplate.replace("[Student name/Team name]", ((TeamPartecipant) partList.getFocusModel().getFocusedItem()).teamName);
                body = body.replace("[Competition name]", compList.getFocusModel().getFocusedItem().compName);
                body = body.replaceAll("\n", "%0A");
                body = body.replaceAll(" ", "%20");
                String email = "";
                for(Student st :(((TeamPartecipant)partList.getFocusModel().getFocusedItem()).teamMumbers)){
                    String stId = st.id.toString();
                    stId = stId.substring(0, stId.length()-3);
                    while (stId.length()<9){
                        stId+="0";
                    }
                    stId =stId.replace(".", "");


                    email += stId+ "@kfupm.edu.sa,";
                }
                System.out.println(email);
                email.substring(0, email.length()-2);
                URI mailto = new URI("mailto:"+email+ "?subject=Info&body="+body);
                desktop.mail(mailto);
            }
            }else {
            // TODO fallback to some Runtime.exec(..) voodoo?
            throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
        }

    }


    @FXML
    protected void deleteMember(){
        didChange = true;
      (  (TeamPartecipant) partList.getFocusModel().getFocusedItem()).teamMumbers.remove(memberList.getFocusModel().getFocusedItem());
        memberList.getItems().setAll(((TeamPartecipant) partList.getFocusModel().getFocusedItem()).teamMumbers);

    }


    @FXML
    protected void addMember() throws IOException {
        didChange = true;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-member.fxml"));

        AddMember controller = new AddMember((TeamPartecipant) partList.getFocusModel().getFocusedItem());
        fxmlLoader.setController(controller);
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 590, 500);
        stage.setTitle("CompTracker");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }



    public void  refreshList(){
        this.compList.getItems().setAll(FileReader.competitions);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    mamberName.setOpacity(0);

        parName.setOpacity(0);
        engine = webView.getEngine();
        memberAdd.setOpacity(0);
        memberList.setOpacity(0);
        memberEdit.setOpacity(0);
        mamberLabel.setOpacity(0);
        deletePar.setOpacity(0);
        sendEmailPar.setOpacity(0);

        compName.setOpacity(0);

        addPar.setOpacity(0);
        editPar.setOpacity(0);
        welcomeText.setOpacity(0);
        parLabel.setOpacity(0);
        deleteMember.setOpacity(0);
        partList.setOpacity(0);

        partList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Partecipant>() {
            @Override
            public void changed(ObservableValue<? extends Partecipant> observable, Partecipant oldValue, Partecipant newValue) {

                if (newValue != null) {
                    mamberName.setOpacity(0);
                    if(compList.getFocusModel().getFocusedItem().single){


                        SinglePartecipant p = (SinglePartecipant) partList.getFocusModel().getFocusedItem();


                        String stId = p.partecipant.id.toString();
                        stId = stId.substring(0, stId.length()-3);
                        while (stId.length()<9){
                            stId+="0";
                        }
                        stId =stId.replace(".", "");

                        String text = p.partecipant.name + "\n\n"+ stId+ "\n\n"+ p.partecipant.major+"\n\n"+"#"+p.rank;
                        parName.setText(text);

                        parName.setOpacity(1);

                    }else {
                        TeamPartecipant p = (TeamPartecipant) partList.getFocusModel().getFocusedItem();




                        String text = p.teamName + "\n\n#"+ p.rank;
                        parName.setText(text);

                        parName.setOpacity(1);
                    }
                    if(!(compList.getFocusModel().getFocusedItem().single)){

                        System.out.println(compList.getFocusModel().getFocusedItem().compName);
                        memberList.getItems().setAll(((TeamPartecipant) newValue).teamMumbers);
                        memberList.setOpacity(1);
                        memberEdit.setOpacity(1);
                        memberAdd.setOpacity(1);
                        deleteMember.setOpacity(1);
                        mamberLabel.setOpacity(1);


                    }
                }


            }
        });
        compList.getItems().addAll(FileReader.competitions);
        compList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Competition>() {
            @Override
            public void changed(ObservableValue<? extends Competition> observable, Competition oldValue, Competition newValue) {

                if(newValue != null){
                    mamberName.setOpacity(0);
                    parName.setOpacity(0);
                    String compInfo = compList.getFocusModel().getFocusedItem().compName + "\n\n"+compList.getFocusModel().getFocusedItem().compDate.toString();
                    compName.setText(compInfo);

                    load(newValue.compUrl);
                    selectedComp = newValue;
                    for(Partecipant p : newValue.partecipants){

                        System.out.println(p);
                    }

                    compName.setOpacity(1);
                    deletePar.setOpacity(1);
                    memberList.setOpacity(0);
                    sendEmailPar.setOpacity(1);
                    memberAdd.setOpacity(0);
                    memberEdit.setOpacity(0);
                    mamberLabel.setOpacity(0);
                    deleteMember.setOpacity(0);
                    welcomeText.setText(newValue.single ? "The competition is single participate.": "The competition is team based.");
                    welcomeText.setOpacity(1);
                    parLabel.setOpacity(1);
                    partList.setOpacity(1);
                    addPar.setOpacity(1);
                    editPar.setOpacity(1);
                    partList.getItems().setAll(newValue.partecipants);

                }

            }
        });
        memberList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                Student p =  memberList.getFocusModel().getFocusedItem();


                String stId = p.id.toString();
                stId = stId.substring(0, stId.length()-3);
                while (stId.length()<9){
                    stId+="0";
                }
                stId =stId.replace(".", "");

                String text = p.name + "\n\n"+ stId+ "\n\n"+ p.major;
                mamberName.setText(text);

                mamberName.setOpacity(1);
            }
        });
    }
    public void load(String url){

        System.out.println("loading "+url);

        engine.load(url);
    }

    @FXML
    public void back(){
        engine.getHistory().go(-1);
    }



}