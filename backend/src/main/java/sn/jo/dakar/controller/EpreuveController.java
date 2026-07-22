package sn.jo.dakar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.jo.dakar.entity.Epreuve;
import sn.jo.dakar.service.EpreuveService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/epreuves")
@CrossOrigin(origins = "*")
public class EpreuveController {

    @Autowired
    private EpreuveService epreuveService;

    @GetMapping
    public List<Epreuve> getAllEpreuves() {
        return epreuveService.getAllEpreuves();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Epreuve> getEpreuveById(@PathVariable Long id) {
        return epreuveService.getEpreuveById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Epreuve> createEpreuve(@RequestBody Epreuve epreuve) {
        return new ResponseEntity<>(epreuveService.saveEpreuve(epreuve), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Epreuve> updateEpreuve(@PathVariable Long id, @RequestBody Epreuve details) {
        return epreuveService.getEpreuveById(id)
                .map(e -> {
                    details.setId(id);
                    return ResponseEntity.ok(epreuveService.saveEpreuve(details));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpreuve(@PathVariable Long id) {
        epreuveService.deleteEpreuve(id);
        return ResponseEntity.noContent().build();
    }

    // Recherche par discipline
    @GetMapping("/discipline/{disciplineId}")
    public List<Epreuve> getByDiscipline(@PathVariable Long disciplineId) {
        return epreuveService.getEpreuvesByDiscipline(disciplineId);
    }

    // Recherche par date
    @GetMapping("/date")
    public List<Epreuve> getByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return epreuveService.getEpreuvesByDate(date);
    }
}