package sn.jo.dakar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassementPointsDTO {
    private String pays;
    private long orCount;
    private long argentCount;
    private long bronzeCount;
    private long points; // (Or * 7) + (Argent * 4) + (Bronze * 1)
    private long nombreMedailles;
}