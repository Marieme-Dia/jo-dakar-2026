package sn.jo.dakar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.jo.dakar.entity.Resultat;

import java.util.List;

public interface ResultatRepository extends JpaRepository<Resultat, Long> {
    List<Resultat> findByEpreuveIdOrderByRangAsc(Long epreuveId);
}