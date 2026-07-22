package sn.jo.dakar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.jo.dakar.dto.ClassementPointsDTO;
import sn.jo.dakar.dto.DashboardStatsDTO;
import sn.jo.dakar.entity.Medaille;
import sn.jo.dakar.entity.Resultat;
import sn.jo.dakar.repository.AthleteRepository;
import sn.jo.dakar.repository.ResultatRepository;

import java.util.*;

@Service
public class DashboardService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private ResultatRepository resultatRepository;

    public DashboardStatsDTO getDashboardStats() {
        // 1. Total d'athlètes
        long totalAthletes = athleteRepository.count();

        // 2. Nombre de pays participants (distincts)
        long totalPays = athleteRepository.findAll().stream()
                .map(a -> a.getNationalite())
                .filter(Objects::nonNull)
                .map(String::trim)
                .distinct()
                .count();

        // 3. Nombre de médailles attribuées par type
        List<Resultat> allResultats = resultatRepository.findAll();
        long orCount = 0;
        long argentCount = 0;
        long bronzeCount = 0;

        Map<String, Map<String, Long>> statsParPays = new HashMap<>();

        for (Resultat r : allResultats) {
            Medaille m = r.getMedaille();
            if ((m == null || m == Medaille.AUCUNE) && r.getRang() != null) {
                if (r.getRang().equals(1)) m = Medaille.OR;
                else if (r.getRang().equals(2)) m = Medaille.ARGENT;
                else if (r.getRang().equals(3)) m = Medaille.BRONZE;
            }

            if (m == Medaille.OR || m == Medaille.ARGENT || m == Medaille.BRONZE) {
                if (m == Medaille.OR) orCount++;
                else if (m == Medaille.ARGENT) argentCount++;
                else if (m == Medaille.BRONZE) bronzeCount++;

                if (r.getAthlete() != null && r.getAthlete().getNationalite() != null) {
                    String pays = r.getAthlete().getNationalite().trim();
                    statsParPays.putIfAbsent(pays, new HashMap<>(Map.of("OR", 0L, "ARGENT", 0L, "BRONZE", 0L)));
                    Map<String, Long> pMap = statsParPays.get(pays);
                    pMap.put(m.name(), pMap.get(m.name()) + 1);
                }
            }
        }

        Map<String, Long> medaillesAttribuees = Map.of(
                "OR", orCount,
                "ARGENT", argentCount,
                "BRONZE", bronzeCount,
                "TOTAL", orCount + argentCount + bronzeCount
        );

        // 4. Classement des pays par Points (Or: 7 pts, Argent: 4 pts, Bronze: 1 pt)
        List<ClassementPointsDTO> classementPoints = new ArrayList<>();
        statsParPays.forEach((pays, map) -> {
            long o = map.get("OR");
            long a = map.get("ARGENT");
            long b = map.get("BRONZE");
            long totalMedailles = o + a + b;
            long points = (o * 7) + (a * 4) + (b * 1);

            classementPoints.add(new ClassementPointsDTO(pays, o, a, b, points, totalMedailles));
        });

        // Tri par points décroissants
        classementPoints.sort((p1, p2) -> Long.compare(p2.getPoints(), p1.getPoints()));

        return new DashboardStatsDTO(totalAthletes, totalPays, medaillesAttribuees, classementPoints);
    }
}