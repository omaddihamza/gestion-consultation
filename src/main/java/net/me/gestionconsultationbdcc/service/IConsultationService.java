package net.me.gestionconsultationbdcc.service;

import net.me.gestionconsultationbdcc.entities.Consultation;

import java.util.List;


public interface IConsultationService {
    void addConsultation(Consultation consultation);
    List<Consultation> consultationList();
}
