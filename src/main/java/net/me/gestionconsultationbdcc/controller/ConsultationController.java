package net.me.gestionconsultationbdcc.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.me.gestionconsultationbdcc.dao.ConsultationDaoImpl;
import net.me.gestionconsultationbdcc.dao.PatientDaoImpl;
import net.me.gestionconsultationbdcc.entities.Consultation;
import net.me.gestionconsultationbdcc.entities.Patient;
import net.me.gestionconsultationbdcc.service.ConsultationServiceImpl;
import net.me.gestionconsultationbdcc.service.IConsultationService;
import net.me.gestionconsultationbdcc.service.IPatientService;
import net.me.gestionconsultationbdcc.service.PatientServiceImpl;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {
    @FXML
    private TableView tableConsultation;
    @FXML
    private TableColumn columnId;
    @FXML
    private TableColumn columnDate;
    @FXML
    private TableColumn columnPatient;
    @FXML
    private TableColumn columnDescription;
    @FXML
    private DatePicker dateConsultation;
    @FXML
    private TextArea textDescription;
    @FXML
    private ComboBox<Patient> comboPatient;

    private IConsultationService iConsultationService;
    private IPatientService iPatientServic;

    private ObservableList<Patient> patients = FXCollections.observableArrayList();
    private ObservableList<Consultation> consultations = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iPatientServic = new PatientServiceImpl(new PatientDaoImpl());
        iConsultationService = new ConsultationServiceImpl(new ConsultationDaoImpl());
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("dateConsultation"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        loadConsultation();
        tableConsultation.setItems(consultations);
        comboPatient.setItems(patients);
        patients.setAll(iPatientServic.patients());

    }
    public void addConsultation(){
        Consultation consultation = new Consultation();
        consultation.setDescription(textDescription.getText());
        consultation.setDateConsultation(Date.valueOf(dateConsultation.getValue()));
        consultation.setPatient(comboPatient.getSelectionModel().getSelectedItem());
        iConsultationService.addConsultation(consultation);
        loadConsultation();
        videTextFild();
    }

    private void videTextFild() {
        textDescription.setText("");
        dateConsultation.setValue(null);
        comboPatient.getSelectionModel().clearSelection();
    }


    private void loadConsultation(){
        consultations.setAll(iConsultationService.consultationList());
    }


}
