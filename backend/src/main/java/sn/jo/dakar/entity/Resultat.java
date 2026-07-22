package sn.jo.dakar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resultats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resultat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rang; // 1 pour Or, 2 pour Argent, 3 pour Bronze...

    private String tempsOuScore; // Ex: "9.81s" ou "85 pts"

    @Enumerated(EnumType.STRING)
    private Medaille medaille; // OR, ARGENT, BRONZE, AUCUNE

    @ManyToOne
    @JoinColumn(name = "athlete_id")
    private Athlete athlete;

    @ManyToOne
    @JoinColumn(name = "epreuve_id")
    private Epreuve epreuve;
}