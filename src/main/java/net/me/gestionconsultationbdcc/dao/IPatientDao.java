package net.me.gestionconsultationbdcc.dao;

import net.me.gestionconsultationbdcc.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public interface IPatientDao extends Dao<Patient, Long>{
    List<Patient> searchPatients(String keyword) throws SQLException;
}
