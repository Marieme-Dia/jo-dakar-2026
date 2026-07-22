package sn.jo.dakar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsDTO {
    private long totalAthletes;
    private long totalPaysParticipants;
    private Map<String, Long> medaillesAttribuees; // Ex: {"OR": 5, "ARGENT": 5, "BRONZE": 5}
    private List<ClassementPointsDTO> classementParPoints;
}