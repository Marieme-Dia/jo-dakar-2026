package sn.jo.dakar.dto;

public class TableauMedailleDTO {
    private String pays;
    private Long or;
    private Long argent;
    private Long bronze;
    private Long total;

    public TableauMedailleDTO(String pays, Long or, Long argent, Long bronze, Long total) {
        this.pays = pays;
        this.or = or != null ? or : 0L;
        this.argent = argent != null ? argent : 0L;
        this.bronze = bronze != null ? bronze : 0L;
        this.total = total != null ? total : 0L;
    }

    public String getPays() { return pays; }
    public Long getOr() { return or; }
    public Long getArgent() { return argent; }
    public Long getBronze() { return bronze; }
    public Long getTotal() { return total; }
}