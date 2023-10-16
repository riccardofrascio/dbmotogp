package it.model;

import java.util.List;

import it.db.api.Records;
import it.utils.Pair;

public class Moto {

    private final int idMoto;
    private final String casaProduttrice;
    private final int cilindrata;
    private final int peso;
    protected static final List<Pair<String, String>> valuesList = List.of(new Pair<>("idMoto", "INT NOT NULL PRIMARY KEY"),
            new Pair<>("casaProduttrice", "CHAR(40)"), new Pair<>("cilindrata", "int"),
            new Pair<>("peso", "int"));

    public Moto(int idMoto, String casaProduttrice, int cilindrata, int peso) {
        this.idMoto = idMoto;
        this.casaProduttrice = casaProduttrice;
        this.cilindrata = cilindrata;
        this.peso = peso;
    }

    public int getIdMoto() {
        return idMoto;
    }
    public String getCasaProduttrice() {
        return casaProduttrice;
    }
    public int getCilindrata() {
        return cilindrata;
    }
    public int getPeso() {
        return peso;
    }

    public static Records getRecords() {
        return new Records() {

            @Override
            public List<Pair<String, String>> getValues() {
                return valuesList;    
            }

            @Override
            public String getTableName() {
                return "moto";
            }
            
        };
    }
}
