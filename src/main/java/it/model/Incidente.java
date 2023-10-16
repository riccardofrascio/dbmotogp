package it.model;

import java.util.List;

import it.db.api.Records;
import it.utils.Pair;

public class Incidente {
    
    private final int idPilota;
    private final int idGara;
    private final String descrizione;
    private final String tipologia;
    protected static final List<Pair<String, String>> valuesList = List.of(new Pair<>("idPilota", "int REFERENCES tabelloneGare(idPilota)"), 
            new Pair<>("idGara", "int REFERENCES tabelloneGare(idGara)"),
            new Pair<>("descrizione", "CHAR(40)"),
            new Pair<>("tipologia", "CHAR(40)"),
            new Pair<>("", "PRIMARY KEY(idGara, idPilota, descrizione)"));

    public Incidente(int idPilota, int idGara, String descrizione, String tipologia) {
        this.idPilota = idPilota;
        this.idGara = idGara;
        this.descrizione = descrizione;
        this.tipologia = tipologia;
    }

    public int getIdPilota() {
        return idPilota;
    }
    public int getIdGara() {
        return idGara;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public String getTipologia() {
        return tipologia;
    }

    public static Records getRecords() {
        return new Records() {

            @Override
            public List<Pair<String, String>> getValues() {
                return valuesList;    
            }

            @Override
            public String getTableName() {
                return "incidenti";
            }
            
        };
    }
    
}
