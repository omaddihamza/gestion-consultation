package net.me.gestionconsultationbdcc.service;

import net.me.gestionconsultationbdcc.dao.IPatientDao;
import net.me.gestionconsultationbdcc.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public class PatientServiceImpl implements IPatientService {

    private IPatientDao patientDao;

    public PatientServiceImpl(IPatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public void addPatient(Patient patient)  {
        try {
            patientDao.create(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> patients() {
        try {
            return patientDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Patient getPatient(Long id){
        try {
            return patientDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePatient(Patient patient){
        try {
            patientDao.delete(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePatient(Patient patient){
        try {
            patientDao.update(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> searchPatient(String keyword) {
        try {
            return patientDao.searchPatients(keyword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
