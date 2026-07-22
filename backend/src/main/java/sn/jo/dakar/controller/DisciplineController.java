package sn.jo.dakar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.jo.dakar.entity.Athlete;
import sn.jo.dakar.entity.Discipline;
import sn.jo.dakar.service.DisciplineService;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
@CrossOrigin(origins = "*")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping
    public List<Discipline> getAllDisciplines() {
        return disciplineService.getAllDisciplines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discipline> getDisciplineById(@PathVariable Long id) {
        return disciplineService.getDisciplineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Discipline> createDiscipline(@RequestBody Discipline discipline) {
        return new ResponseEntity<>(disciplineService.saveDiscipline(discipline), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discipline> updateDiscipline(@PathVariable Long id, @RequestBody Discipline details) {
        return disciplineService.getDisciplineById(id)
                .map(d -> {
                    details.setId(id);
                    return ResponseEntity.ok(disciplineService.saveDiscipline(details));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Long id) {
        disciplineService.deleteDiscipline(id);
        return ResponseEntity.noContent().build();
    }

    // Consultation des athlètes d'une discipline
    @GetMapping("/{id}/athletes")
    public List<Athlete> getAthletesByDiscipline(@PathVariable Long id) {
        return disciplineService.getAthletesByDiscipline(id);
    }
}