package sn.jo.dakar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "athletes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    private String sexe;

    private LocalDate dateNaissance;

    private String nationalite;

    private Double taille;

    private Double poids;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;
}