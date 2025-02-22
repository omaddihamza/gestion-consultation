package net.me.gestionconsultationbdcc.entities;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Consultation {
    private Long id;
    private Date dateConsultation;
    private String description;
    private Patient patient;
}
