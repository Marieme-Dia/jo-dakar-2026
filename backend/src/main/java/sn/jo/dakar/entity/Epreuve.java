package sn.jo.dakar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "epreuves")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Epreuve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private LocalDateTime dateEpreuve;

    private String lieu;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;
}