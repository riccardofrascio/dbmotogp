package it.model;

import java.util.List;

import it.db.api.Records;
import it.utils.Pair;

public class Contratto {

    private final int idPilota;
    private final int annoInizio;
    private final int annoFine;
    private final int idTeam;
    protected static final List<Pair<String, String>> valuesList = List.of(new Pair<>("idPilota", "int"),
            new Pair<>("annoInizio", "int NOT NULL"), new Pair<>("annoFine", "int"),
            new Pair<>("idTeam", "int REFERENCES team(idTeam)"), 
            new Pair<>("", "PRIMARY KEY(idPilota, annoInizio)"));

    public Contratto(int idPilota, int annoInizio, int annoFine, int idTeam) {
        this.idPilota = idPilota;
        this.annoInizio = annoInizio;
        this.annoFine = annoFine;
        this.idTeam = idTeam;
    }

    public int getIdPilota() {
        return idPilota;
    }
    public int getAnnoInizio() {
        return annoInizio;
    }
    public int getAnnoFine() {
        return annoFine;
    }
    public int getIdTeam() {
        return idTeam;
    }
    
    public static Records getRecords() {
        return new Records() {

            @Override
            public List<Pair<String, String>> getValues() {
                return valuesList;    
            }

            @Override
            public String getTableName() {
                return "contratti";
            }
            
        };
    }
}
