package sn.jo.dakar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.jo.dakar.entity.Discipline;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
}