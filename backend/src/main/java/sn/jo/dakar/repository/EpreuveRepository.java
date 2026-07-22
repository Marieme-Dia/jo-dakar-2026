package sn.jo.dakar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.jo.dakar.entity.Epreuve;
import java.time.LocalDateTime;
import java.util.List;

public interface EpreuveRepository extends JpaRepository<Epreuve, Long> {
    List<Epreuve> findByDisciplineId(Long disciplineId);
    List<Epreuve> findByDateEpreuveBetween(LocalDateTime start, LocalDateTime end);
}