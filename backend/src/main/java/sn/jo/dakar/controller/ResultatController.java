package sn.jo.dakar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.jo.dakar.entity.Resultat;
import sn.jo.dakar.service.ResultatService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resultats")
@CrossOrigin(origins = "*")
public class ResultatController {

    @Autowired
    private ResultatService resultatService;

    @PostMapping
    public ResponseEntity<Resultat> createResultat(@RequestBody Resultat resultat) {
        return new ResponseEntity<>(resultatService.saveResultat(resultat), HttpStatus.CREATED);
    }

    // Récupérer le podium d'une épreuve
    @GetMapping("/podium/{epreuveId}")
    public List<Resultat> getPodium(@PathVariable Long epreuveId) {
        return resultatService.getPodiumByEpreuve(epreuveId);
    }

    // Obtenir le classement par pays (Tableau des médailles)
    @GetMapping("/tableau-medailles")
    public List<Map<String, Object>> getTableauDesMedailles() {
        return resultatService.getTableauDesMedailles();
    }
}