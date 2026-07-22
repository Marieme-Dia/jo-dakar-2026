package sn.jo.dakar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.jo.dakar.entity.Athlete;
import sn.jo.dakar.repository.AthleteRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    // 1. Lister tous les athlètes
    public List<Athlete> getAllAthletes() {
        return athleteRepository.findAll();
    }

    // 2. Obtenir un athlète par son ID
    public Optional<Athlete> getAthleteById(Long id) {
        return athleteRepository.findById(id);
    }

    // 3. Créer ou modifier totalement un athlète
    public Athlete saveAthlete(Athlete athlete) {
        return athleteRepository.save(athlete);
    }

    // 4. Supprimer un athlète
    public void deleteAthlete(Long id) {
        athleteRepository.deleteById(id);
    }

    // 5. Modification partielle (PATCH)
    public Athlete updateAthletePartial(Long id, Map<String, Object> updates) {
        Athlete athlete = athleteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Athlète non trouvé avec l'ID : " + id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "nom": athlete.setNom((String) value); break;
                case "prenom": athlete.setPrenom((String) value); break;
                case "sexe": athlete.setSexe((String) value); break;
                case "nationalite": athlete.setNationalite((String) value); break;
                case "taille": athlete.setTaille(Double.valueOf(value.toString())); break;
                case "poids": athlete.setPoids(Double.valueOf(value.toString())); break;
            }
        });

        return athleteRepository.save(athlete);
    }

    // 6. Recherche multicritère
    public List<Athlete> searchAthletes(String nom, String nationalite, String sexe, Long disciplineId) {
        return athleteRepository.findAll().stream()
                .filter(a -> nom == null || a.getNom().toLowerCase().contains(nom.toLowerCase()) || a.getPrenom().toLowerCase().contains(nom.toLowerCase()))
                .filter(a -> nationalite == null || a.getNationalite().equalsIgnoreCase(nationalite))
                .filter(a -> sexe == null || a.getSexe().equalsIgnoreCase(sexe))
                .filter(a -> disciplineId == null || (a.getDiscipline() != null && a.getDiscipline().getId().equals(disciplineId)))
                .collect(Collectors.toList());
    }
}