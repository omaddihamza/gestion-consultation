package net.me.gestionconsultationbdcc.dao;

import net.me.gestionconsultationbdcc.entities.Consultation;
import net.me.gestionconsultationbdcc.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDaoImpl implements IConsultationDao{



    @Override
    public void create(Consultation consultation) throws SQLException {
        Connection connection  = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO consultation (date_consultation,description,patient_id) VALUES (?,?,?)");
        ps.setDate(1, consultation.getDateConsultation());
        ps.setString(2, consultation.getDescription());
        ps.setLong(3, consultation.getPatient().getId());
        ps.executeUpdate();
    }

    @Override
    public void delete(Consultation consultation) {

    }

    @Override
    public void update(Consultation consultation) {

    }

    @Override
    public List<Consultation> findAll() throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM consultation");
        ResultSet rs = ps.executeQuery();
        List<Consultation> consultations = new ArrayList<>();
        while (rs.next()){
          Consultation consultation = new Consultation();
          consultation.setId(rs.getLong("id"));
          consultation.setDateConsultation(rs.getDate("date_consultation"));
          consultation.setDescription(rs.getString("description"));
            Long patientId = rs.getLong("patient_id");
            Patient patient = findPatientById(patientId);
            consultation.setPatient(patient);
          consultations.add(consultation);
        }
        return consultations;
    }

    public Patient findPatientById(Long id) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM patient WHERE id = ?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Patient patient = new Patient();
        if (rs.next()) {

           patient.setId(rs.getLong("id"));
           patient.setNom(rs.getString("nom"));
           patient.setPrenom(rs.getString("prenom"));
           patient.setTel(rs.getString("tel"));
        }
        return patient;
    }


    @Override
    public Consultation findById(Long id) {
        return null;
    }
}
