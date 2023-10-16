package it.model;


import java.util.List;

import it.db.api.Records;
import it.utils.Pair;

public class TabelloneGara {
    
    private final int idGara;
    private final int idPilota;
    private final int posizione;
    private final int punti;
    protected static final List<Pair<String, String>> valuesList = List.of(new Pair<>("idGara", "int REFERENCES gare(idGara)"),
            new Pair<>("idPilota", "int REFERENCES piloti(idPilota)"), new Pair<>("posizione", "int"),
            new Pair<>("punti", "int"), 
            new Pair<>("", "PRIMARY KEY(idGara, idPilota)"));

    public TabelloneGara(int idGara, int idPilota, int posizione, int punti) {
        this.idGara = idGara;
        this.idPilota = idPilota;
        this.posizione = posizione;
        this.punti = punti;
    }
    
    public int getIdGara() {
        return idGara;
    }
    public int getIdPilota() {
        return idPilota;
    }
    public int getPosizione() {
        return posizione;
    }
    public int getPunti() {
        return punti;
    }

    public static Records getRecords() {
        return new Records() {

            @Override
            public List<Pair<String, String>> getValues() {
                return valuesList;    
            }

            @Override
            public String getTableName() {
                return "tabelloneGare";
            }
            
        };
    }
    
}
