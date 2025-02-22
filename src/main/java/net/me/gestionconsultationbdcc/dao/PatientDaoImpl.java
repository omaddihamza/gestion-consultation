package net.me.gestionconsultationbdcc.dao;

import net.me.gestionconsultationbdcc.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements IPatientDao{
    @Override
    public void create(Patient patient) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO  patient (nom,prenom,tel) VALUES (?,?,?)");
        ps.setString(1,patient.getNom());
        ps.setString(2,patient.getPrenom());
        ps.setString(3,patient.getTel());
        ps.executeUpdate();
    }

    @Override
    public void delete(Patient patient) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM  patient WHERE id=?");
        ps.setLong(1,patient.getId());
        ps.executeUpdate();
    }

    @Override
    public void update(Patient patient) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("UPDATE patient SET nom=?, prenom=?, tel=? WHERE id=?");
        ps.setString(1, patient.getNom());
        ps.setString(2, patient.getPrenom());
        ps.setString(3, patient.getTel());
        ps.setLong(4, patient.getId());
        ps.executeUpdate();
    }

    @Override
    public List<Patient> findAll() throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM patient");
        ResultSet rs = ps.executeQuery();
        List<Patient> patients = new ArrayList<>();
        while (rs.next()){
            Patient patient = new Patient();
            patient.setId(rs.getLong("id"));
            patient.setNom(rs.getString("nom"));
            patient.setPrenom(rs.getString("prenom"));
            patient.setTel(rs.getString("tel"));
            patients.add(patient);
        }
        return patients;
    }

    @Override
    public Patient findById(Long id) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM patient WHERE id=?");
        ps.setLong(1,id);
        ResultSet rs = ps.executeQuery();
        Patient patient = new Patient();
        if (rs.next()){
            patient.setId(rs.getLong("id"));
            patient.setNom(rs.getString("nom"));
            patient.setPrenom(rs.getString("prenom"));
            patient.setTel(rs.getString("tel"));
        }
        return patient;
    }

    @Override
    public List<Patient> searchPatients(String keyword) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM patient WHERE nom LIKE ? OR prenom LIKE ?");
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();
        List<Patient> patients = new ArrayList<>();
        while (rs.next()){
            Patient patient = new Patient();
            patient.setId(rs.getLong("id"));
            patient.setNom(rs.getString("nom"));
            patient.setPrenom(rs.getString("prenom"));
            patient.setTel(rs.getString("tel"));
            patients.add(patient);
        }
        return patients;
    }
}
