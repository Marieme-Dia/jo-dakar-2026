package sn.jo.dakar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.jo.dakar.entity.Athlete;
import sn.jo.dakar.service.AthleteService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/athletes")
@CrossOrigin(origins = "*") // Permettra à Angular de communiquer facilement avec le backend
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    // GET /api/athletes (Tous les athlètes)
    @GetMapping
    public List<Athlete> getAllAthletes() {
        return athleteService.getAllAthletes();
    }

    // GET /api/athletes/{id} (Un athlète par ID)
    @GetMapping("/{id}")
    public ResponseEntity<Athlete> getAthleteById(@PathVariable Long id) {
        return athleteService.getAthleteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/athletes (Créer un athlète)
    @PostMapping
    public ResponseEntity<Athlete> createAthlete(@RequestBody Athlete athlete) {
        Athlete savedAthlete = athleteService.saveAthlete(athlete);
        return new ResponseEntity<>(savedAthlete, HttpStatus.CREATED);
    }

    // PUT /api/athletes/{id} (Modification complète)
    @PutMapping("/{id}")
    public ResponseEntity<Athlete> updateAthlete(@PathVariable Long id, @RequestBody Athlete athleteDetails) {
        return athleteService.getAthleteById(id)
                .map(existingAthlete -> {
                    athleteDetails.setId(id);
                    Athlete updated = athleteService.saveAthlete(athleteDetails);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // PATCH /api/athletes/{id} (Modification partielle)
    @PatchMapping("/{id}")
    public ResponseEntity<Athlete> updateAthletePartial(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        try {
            Athlete updatedAthlete = athleteService.updateAthletePartial(id, updates);
            return ResponseEntity.ok(updatedAthlete);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/athletes/{id} (Suppression)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAthlete(@PathVariable Long id) {
        athleteService.deleteAthlete(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/athletes/recherche (Recherche multicritère)
    @GetMapping("/recherche")
    public List<Athlete> searchAthletes(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String nationalite,
            @RequestParam(required = false) String sexe,
            @RequestParam(required = false) Long disciplineId) {
        return athleteService.searchAthletes(nom, nationalite, sexe, disciplineId);
    }
}