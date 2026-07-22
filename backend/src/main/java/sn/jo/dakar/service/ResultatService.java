package sn.jo.dakar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.jo.dakar.entity.Medaille;
import sn.jo.dakar.entity.Resultat;
import sn.jo.dakar.repository.ResultatRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResultatService {

    @Autowired
    private ResultatRepository resultatRepository;

    // Saisie d'un résultat avec attribution automatique de la médaille selon le rang
    public Resultat saveResultat(Resultat resultat) {
        if (resultat.getRang() != null) {
            switch (resultat.getRang()) {
                case 1 -> resultat.setMedaille(Medaille.OR);
                case 2 -> resultat.setMedaille(Medaille.ARGENT);
                case 3 -> resultat.setMedaille(Medaille.BRONZE);
                default -> resultat.setMedaille(Medaille.AUCUNE);
            }
        } else {
            resultat.setMedaille(Medaille.AUCUNE);
        }
        return resultatRepository.save(resultat);
    }

    // Récupérer le podium (Top 3) d'une épreuve
    public List<Resultat> getPodiumByEpreuve(Long epreuveId) {
        return resultatRepository.findByEpreuveIdOrderByRangAsc(epreuveId).stream()
                .filter(r -> r.getRang() != null && r.getRang() <= 3)
                .collect(Collectors.toList());
    }

    // Générer le tableau des médailles par pays / nationalité (Classement Olympique Officiel)
    public List<Map<String, Object>> getTableauDesMedailles() {
        List<Resultat> allResultats = resultatRepository.findAll();
        Map<String, Map<String, Integer>> stats = new HashMap<>();

        for (Resultat r : allResultats) {
            if (r.getAthlete() == null || r.getAthlete().getNationalite() == null) {
                continue;
            }

            String pays = r.getAthlete().getNationalite().trim();

            stats.putIfAbsent(
                    pays,
                    new HashMap<>(Map.of("or", 0, "argent", 0, "bronze", 0, "total", 0))
            );

            Map<String, Integer> paysStats = stats.get(pays);
            Medaille medaille = r.getMedaille();

            // Si la médaille n'est pas renseignée ou vaut AUCUNE, on la déduit du rang (1, 2, 3)
            if ((medaille == null || medaille == Medaille.AUCUNE) && r.getRang() != null) {
                if (r.getRang().equals(1)) {
                    medaille = Medaille.OR;
                } else if (r.getRang().equals(2)) {
                    medaille = Medaille.ARGENT;
                } else if (r.getRang().equals(3)) {
                    medaille = Medaille.BRONZE;
                }
            }

            // Incrémentation des médailles et du total uniquement pour un podium (Or, Argent, Bronze)
            if (medaille == Medaille.OR) {
                paysStats.put("or", paysStats.get("or") + 1);
                paysStats.put("total", paysStats.get("total") + 1);
            } else if (medaille == Medaille.ARGENT) {
                paysStats.put("argent", paysStats.get("argent") + 1);
                paysStats.put("total", paysStats.get("total") + 1);
            } else if (medaille == Medaille.BRONZE) {
                paysStats.put("bronze", paysStats.get("bronze") + 1);
                paysStats.put("total", paysStats.get("total") + 1);
            }
        }

        List<Map<String, Object>> result = new ArrayList<>();

        stats.forEach((pays, map) -> {
            Map<String, Object> ligne = new HashMap<>();
            ligne.put("pays", pays);
            ligne.put("or", map.get("or"));
            ligne.put("argent", map.get("argent"));
            ligne.put("bronze", map.get("bronze"));
            ligne.put("total", map.get("total"));
            result.add(ligne);
        });

        // Tri officiel du Comité Olympique (Or > Argent > Bronze)
        result.sort((a, b) -> {
            int compOr = Integer.compare((Integer) b.get("or"), (Integer) a.get("or"));
            if (compOr != 0) return compOr;

            int compArgent = Integer.compare((Integer) b.get("argent"), (Integer) a.get("argent"));
            if (compArgent != 0) return compArgent;

            return Integer.compare((Integer) b.get("bronze"), (Integer) a.get("bronze"));
        });

        return result;
    }
}