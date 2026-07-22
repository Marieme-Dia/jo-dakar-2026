package sn.jo.dakar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.jo.dakar.entity.Athlete;
import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    List<Athlete> findByDisciplineId(Long disciplineId);
}