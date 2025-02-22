package net.me.gestionconsultationbdcc.service;

import net.me.gestionconsultationbdcc.dao.IConsultationDao;
import net.me.gestionconsultationbdcc.entities.Consultation;

import java.sql.SQLException;
import java.util.List;

public class ConsultationServiceImpl implements IConsultationService{

    private IConsultationDao iConsultationDao;

    public ConsultationServiceImpl(IConsultationDao iConsultationDao) {
        this.iConsultationDao = iConsultationDao;
    }

    @Override
    public void addConsultation(Consultation consultation) {
        try {
            iConsultationDao.create(consultation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Consultation> consultationList() {
        try {
            return iConsultationDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
