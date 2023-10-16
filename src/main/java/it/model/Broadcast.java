package it.model;

import java.util.List;


import it.db.api.Records;
import it.utils.Pair;

public class Broadcast {

    private final int canale;
    private final int idGara;
    private final int visualizzazioni;

    protected static final List<Pair<String, String>> valuesList = List.of(new Pair<>("canale", "INT NOT NULL"),
            new Pair<>("idGara", "int"), new Pair<>("visualizzazioni", "int REFERENCES gare(idGara)"),
            new Pair<>("", "PRIMARY KEY(canale, idGara)"));

    public Broadcast(int canale, int idGara, int visualizzazioni) {
        this.canale = canale;
        this.idGara = idGara;
        this.visualizzazioni = visualizzazioni;
    }

    public int getCanale() {
        return canale;
    }

    public int getIdGara() {
        return idGara;
    }

    public int getVisualizzazioni() {
        return visualizzazioni;
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
                return "broadcast";
            }
            
        };
    }

}
