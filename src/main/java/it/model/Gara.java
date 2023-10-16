package it.model;

import java.util.Date;
import java.util.List;

import it.db.api.Records;
import it.utils.Pair;

public class Gara {
    
    private final int idGara;
    private final Date data;
    private final String condizioniMeteo;
    private final int idPista;
    protected static final List<Pair<String, String>> valuesList = List.of(new Pair<>("idGara", "INT NOT NULL PRIMARY KEY"), 
            new Pair<>("data", "DATE"),
            new Pair<>("condizioniMeteo", "CHAR(40)"), new Pair<>("idPista", "int REFERENCES piste(idPista)"));
    
    public Gara(int idGara, Date data, String condizioniMeteo, int idPista) {
        this.idGara = idGara;
        this.data = data;
        this.condizioniMeteo = condizioniMeteo;
        this.idPista = idPista;
    }

    public int getIdGara() {
        return idGara;
    }

    public Date getData() {
        return data;
    }

    public String getCondizioniMeteo() {
        return condizioniMeteo;
    }

    public int getIdPista() {
        return idPista;
    }

    public static Records getRecords() {
        return new Records() {

            @Override
            public List<Pair<String, String>> getValues() {
                return valuesList;
            }

            @Override
            public String getTableName() {
                return "gare";
            }
            
        };
    }

}
