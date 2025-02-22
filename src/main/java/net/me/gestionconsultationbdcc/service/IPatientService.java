package net.me.gestionconsultationbdcc.service;

import net.me.gestionconsultationbdcc.entities.Patient;

import java.util.List;

public interface IPatientService {
    void addPatient(Patient patient);
    List<Patient> patients();
    Patient getPatient(Long id) ;
    void deletePatient(Patient patient);
    void updatePatient(Patient patient);
    List<Patient> searchPatient(String keyword);
}
