package net.me.gestionconsultationbdcc.entities;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class Patient {
    private Long id;
    private String nom;
    private  String prenom;
    private String tel;
    private List<Consultation> consultations;

    @Override
    public String toString() {
        return  nom + ' ' +
                prenom;
    }
}
