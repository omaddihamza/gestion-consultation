package net.me.gestionconsultationbdcc.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.me.gestionconsultationbdcc.dao.PatientDaoImpl;
import net.me.gestionconsultationbdcc.entities.Patient;
import net.me.gestionconsultationbdcc.service.IPatientService;
import net.me.gestionconsultationbdcc.service.PatientServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML
    private TableColumn<Patient, Long> columnId;
    @FXML
    private TableColumn<Patient, String> columnNom;
    @FXML
    private TableColumn<Patient, String > columnPrenom;
    @FXML
    private TableColumn<Patient, String > columnTel;
    @FXML
    private TableView<Patient> tablePatient;
    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldPrenom;
    @FXML
    private TextField textFieldTel;
    @FXML
    private TextField textFieldSearch;
    @FXML
    private Label lableSucces;
    private IPatientService ipatientService;
    //model
    private ObservableList<Patient> patients = FXCollections.observableArrayList();
private Patient selectPatient;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         ipatientService = new PatientServiceImpl(new PatientDaoImpl());
         columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
         columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         columnTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
         tablePatient.setItems(patients);
         loadPatient();
         textFieldSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
             patients.setAll(ipatientService.searchPatient(newValue));
         });

         tablePatient.getSelectionModel().selectedItemProperty().addListener((observableValue, oldPatient, newPatient) -> {
           if(newPatient!=null){
               textFieldNom.setText(newPatient.getNom());
               textFieldPrenom.setText(newPatient.getPrenom());
               textFieldTel.setText(newPatient.getTel());
               selectPatient=newPatient;
           }
         });
    }

    public void addPatient()  {
       Patient patient = new Patient();
       patient.setNom(textFieldNom.getText());
       patient.setPrenom(textFieldPrenom.getText());
       patient.setTel(textFieldTel.getText());
       ipatientService.addPatient(patient);
       loadPatient();
    }


    public Patient getPatient() {

        return null ;

    }

    public void deletePatient() {
        Patient patient = tablePatient.getSelectionModel().getSelectedItem();
        ipatientService.deletePatient(patient);
        lableSucces.setText("patient supperemer avec success");
        textFieldNom.setText(null);
        textFieldPrenom.setText(null);
        textFieldTel.setText(null);
        loadPatient();
    }

    public void updatePatient() {
        selectPatient.setNom(textFieldNom.getText());
        selectPatient.setPrenom(textFieldPrenom.getText());
        selectPatient.setTel(textFieldTel.getText());
        ipatientService.updatePatient(selectPatient);
        loadPatient();
    }


    private void loadPatient(){
        patients.setAll(ipatientService.patients());
    }
}
