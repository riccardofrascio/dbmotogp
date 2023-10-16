package it.model;

import java.util.List;

import it.db.api.Records;
import it.utils.Pair;

public class Classifica {

    private final int idPilota;
    private final int anno;
    private final int punteggio;
    protected static final List<Pair<String, String>> valuesList = List.of(new Pair<>("idPilota", "int REFERENCES piloti(idPilota)"), 
            new Pair<>("anno", "int"),
            new Pair<>("punteggio", "int"),
            new Pair<>("", "PRIMARY KEY(idPilota, anno)"));


    public Classifica(int idPilota, int anno, int punteggio) {
        this.idPilota = idPilota;
        this.anno = anno;
        this.punteggio = punteggio;
    }

    public int getIdPilota() {
        return idPilota;
    }


    public int getAnno() {
        return anno;
    }


    public int getPunteggio() {
        return punteggio;
    }


    public static List<Pair<String, String>> getValueslist() {
        return valuesList;
    }

    public static Records getRecords() {
        return new Records() {

            @Override
            public List<Pair<String, String>> getValues() {
                return valuesList;    
            }

            @Override
            public String getTableName() {
                return "classifica";
            }
            
        };
    }
    
}
