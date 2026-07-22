package sn.jo.dakar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.jo.dakar.entity.Epreuve;
import sn.jo.dakar.repository.EpreuveRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class EpreuveService {

    @Autowired
    private EpreuveRepository epreuveRepository;

    public List<Epreuve> getAllEpreuves() {
        return epreuveRepository.findAll();
    }

    public Optional<Epreuve> getEpreuveById(Long id) {
        return epreuveRepository.findById(id);
    }

    public Epreuve saveEpreuve(Epreuve epreuve) {
        return epreuveRepository.save(epreuve);
    }

    public void deleteEpreuve(Long id) {
        epreuveRepository.deleteById(id);
    }

    public List<Epreuve> getEpreuvesByDiscipline(Long disciplineId) {
        return epreuveRepository.findByDisciplineId(disciplineId);
    }

    public List<Epreuve> getEpreuvesByDate(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        return epreuveRepository.findByDateEpreuveBetween(start, end);
    }
}