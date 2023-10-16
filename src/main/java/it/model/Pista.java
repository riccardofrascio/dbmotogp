package it.model;

import java.util.List;
import java.util.Objects;

import it.db.api.Records;
import it.utils.Pair;


public class Pista {
    
    private final int idPista;
    private final String stato;
    private final String citta;
    private final String indirizzo;
    private final int lunghezza;
    private final int curveSx;
    private final int curveDx;
    protected static final List<Pair<String, String>> valuesList = List.of(new Pair<>("idPista", "INT NOT NULL PRIMARY KEY"), 
            new Pair<>("stato", "CHAR(40)"), new Pair<>("citta", "CHAR(40)"), 
            new Pair<>("indirizzo", "CHAR(40)"), new Pair<>("lunghezza", "INT"), 
            new Pair<>("curveSx", "INT"), new Pair<>("curveDx", "INT"));
    

    public Pista(final int idPista, final String stato, final String citta, final String indirizzo,
            final int lunghezza, final int curveSx, final int curveDx) {
        
        this.idPista = idPista;
        this.stato = Objects.requireNonNull(stato);
        this.citta = Objects.requireNonNull(citta);
        this.indirizzo = Objects.requireNonNull(indirizzo);
        this.lunghezza = Objects.requireNonNull(lunghezza);
        this.curveSx = Objects.requireNonNull(curveSx);
        this.curveDx = Objects.requireNonNull(curveDx);
        /*valuesList.add(new Pair<String,String>("id", "INT"));
        valuesList.add(new Pair<String,String>("stato", "CHAR(40)"));
        valuesList.add(new Pair<String,String>("citta", "CHAR(40)"));
        valuesList.add(new Pair<String,String>("Indirizzo", "CHAR(40)"));
        valuesList.add(new Pair<String,String>("Lunghezza", "INT"));
        valuesList.add(new Pair<String,String>("CurveSx", "INT"));
        valuesList.add(new Pair<String,String>("CurveDx", "INT"));*/
    }

    public int getIdPista() {
        return idPista;
    }

    public String getStato() {
        return stato;
    }

    public String getCitta() {
        return citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public int getLunghezza() {
        return lunghezza;
    }

    public int getCurveSx() {
        return curveSx;
    }

    public int getCurveDx() {
        return curveDx;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("(").append(idPista)
                .append(", ").append(stato).append(", ").append(citta)
                .append(", ").append(indirizzo).append(", ").append(lunghezza)
                .append(", ").append(curveSx).append(", ").append(curveDx)
                .append(")").toString();
    }


    public static Records getRecords() {
        return new Records() {

            @Override
            public List<Pair<String, String>> getValues() {
                return valuesList;
            }

            @Override
            public String getTableName() {
                return "piste";
            }
            
        };
    }
}
