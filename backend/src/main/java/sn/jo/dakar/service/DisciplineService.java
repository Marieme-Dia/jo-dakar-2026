package sn.jo.dakar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.jo.dakar.entity.Athlete;
import sn.jo.dakar.entity.Discipline;
import sn.jo.dakar.repository.AthleteRepository;
import sn.jo.dakar.repository.DisciplineRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }

    public Optional<Discipline> getDisciplineById(Long id) {
        return disciplineRepository.findById(id);
    }

    public Discipline saveDiscipline(Discipline discipline) {
        return disciplineRepository.save(discipline);
    }

    public void deleteDiscipline(Long id) {
        disciplineRepository.deleteById(id);
    }

    // Récupérer tous les athlètes d'une discipline spécifique
    public List<Athlete> getAthletesByDiscipline(Long disciplineId) {
        return athleteRepository.findByDisciplineId(disciplineId);
    }
}