package it.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.db.api.ConnectionProvider;
import it.db.impl.AbstractTable;
import it.db.impl.TableBroadcast;
import it.db.impl.TableClassifica;
import it.db.impl.TableContratti;
import it.db.impl.TableGare;
import it.db.impl.TableIncidenti;
import it.db.impl.TableMoto;
import it.db.impl.TablePiloti;
import it.db.impl.TablePiste;
import it.db.impl.TableSponsor;
import it.db.impl.TableSponsorizzazioni;
import it.db.impl.TableStaff;
import it.db.impl.TableTabelloneGare;
import it.db.impl.TableTeam;
import it.model.Broadcast;
import it.model.Classifica;
import it.model.Contratto;
import it.model.Gara;
import it.model.Incidente;
import it.model.Moto;
import it.model.Pilota;
import it.model.Pista;
import it.model.Sponsor;
import it.model.Sponsorizzazione;
import it.model.Staff;
import it.model.TabelloneGara;
import it.model.Team;
import it.utils.Utils;  //import per inserire righe nel database con date

public class ModelController {

    final static String username = "root";
    final static String password = "";
    final static String dbName = "dbmotogp";
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);

    private final TablePiste tablePiste;
    private final TableGare tableGare;
    private final TablePiloti tablePiloti;
    private final TableBroadcast tableBroadcast;
    private final TableTabelloneGare tableTabelloneGare;
    private final TableIncidenti tableIncidenti;
    private final TableClassifica tableClassifica;
    private final TableMoto tableMoto;
    private final TableTeam tableTeam;
    private final TableStaff tableStaff;
    private final TableContratti tableContratti;
    private final TableSponsor tableSponsor;
    private final TableSponsorizzazioni tableSponsorizzazioni;
    
    public ModelController() {
        tablePiste = new TablePiste(connectionProvider.getMySQLConnection());
        tableGare = new TableGare(connectionProvider.getMySQLConnection());
        tablePiloti = new TablePiloti(connectionProvider.getMySQLConnection());
        tableBroadcast = new TableBroadcast(connectionProvider.getMySQLConnection());
        tableTabelloneGare = new TableTabelloneGare(connectionProvider.getMySQLConnection());
        tableIncidenti = new TableIncidenti(connectionProvider.getMySQLConnection());
        tableClassifica = new TableClassifica(connectionProvider.getMySQLConnection());
        tableMoto = new TableMoto(connectionProvider.getMySQLConnection());
        tableTeam = new TableTeam(connectionProvider.getMySQLConnection());
        tableStaff = new TableStaff(connectionProvider.getMySQLConnection());
        tableContratti = new TableContratti(connectionProvider.getMySQLConnection());
        tableSponsor = new TableSponsor(connectionProvider.getMySQLConnection());
        tableSponsorizzazioni = new TableSponsorizzazioni(connectionProvider.getMySQLConnection());
        
        if(tablesCount() == 0) {
            createAllTables();
            insertValues();
        }
    }

    private int tablesCount() {
        final String query = "SELECT COUNT(*) AS numeroTabelle" +
                                " FROM INFORMATION_SCHEMA.TABLES" +
                                " WHERE table_schema = '"+dbName+"'";
        try (final PreparedStatement statement = connectionProvider.getMySQLConnection().prepareStatement(query)) {
            final ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet.getInt("numeroTabelle");
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    public <T extends AbstractTable<V, K>, V, K> List<K> findAllPrimaryKey(T table) {
        return table.findAllPrimaryKey();
    }

    private void insertValues() {
        insertPista(2, "Spagna", "Barcellona", "Via la rupa", 2000, 11, 12);
        insertSponsor(1, "Monster", "USA", "Los Angeles");
        insertGara(0, Utils.buildDate(11, 10, 1998).get(), "Sole", 0);
        insertBroadcast(200, 0, 1000000);
        insertContratto(0, 2021,2022, 0);
        insertTeam(0, "Ducati", 0);
        insertSponsorizzazione(0, 0, Utils.buildDate(15, 5, 2019).get(), Utils.buildDate(15, 5, 2023).get(), 160000.00);
        insertPilota(0,"Francesco", "Bagnaia", Utils.buildDate(14,1,1997).get(), "Italiano",0);
        insertIncidente(0, 0, "High-side", "perdita di grip");
        insertTabelloneGara(0, 0, 1, 25);
        insertStaff(0, "MNCMTN0305H294K", "Mattia", "Mancuso", Utils.buildDate(15, 5, 2023).get(), "Ingegnere", 0);
        insertClassifica(0, 2021, 265);
        insertPilota(1,"Marc", "Marquez", Utils.buildDate(17,2,1993).get(), "Spagnolo",0);
        insertTeam(1, "Aprilia", 1);
        insertTeam(2, "Yamaha ufficile", 2);
        insertIncidente(1, 1," Scivolata", "perdita di grip");
        insertPilota(2, "Enea", "Bastianini", Utils.buildDate(30, 12, 1997).get(), "Italiano",0);
        insertPilota(3, "Fabio", "Quartararo", Utils.buildDate(20, 4, 1999).get(), "Francese",0);
        insertPilota(4, "Valentino", "Rossi", Utils.buildDate(16, 2,1979).get(), "Italiano",0);
        insertContratto(4, 2019, 2021, 2);
        insertContratto(2, 2021, 2023, 0);
        insertContratto(3, 2019, 2021,2);
        insertContratto(0, 2022, 2023,2);
        insertPilota(5, "Pol", "Espargarò", Utils.buildDate(10, 6,1991).get(), "Spagnolo",0);
        insertPilota(6, "Marco", "Bezzecchi", Utils.buildDate(12, 11,1998).get(), "Italiano",0);
        insertPilota(7, "Johann", "Zarco", Utils.buildDate(16, 7,1990).get(), "Francese",0);
        insertPilota(8, "Jorge", "Martin", Utils.buildDate(29, 2,1998).get(), "Spagnolo", 0);
        insertPilota(9, "Jack", "Miller", Utils.buildDate(18, 1,1995).get(), "Australiano", 0);
        insertPilota(10, "Alex", "Rins", Utils.buildDate(8,12,1995).get(), "Spagnolo", 0);
        insertPilota(11, "Maverick", "Vinales", Utils.buildDate(12, 1,1995).get(), "Spagnolo", 0);   
        insertPista(3, "Portogallo", "Portimao", "Rue Algarve", 4592, 15, 12);
        insertPista(4, "Argentina", "Rio Hondo", "Temas de rio Hondo", 4800, 14, 18);
        insertPista(5, "Francia", "Le Mans", "Heures du Mans", 4200, 12, 15);
        insertPista(6, "Germania", "Chemnitz", "Strasse Sachsen", 3700, 9, 10);
        insertPista(7, "Paesi bassi", "Assen", "Hertenkamp", 4500, 9, 9);
        insertPista(8, "Giappone", "Motegi", "Natl rte 123", 4000, 12, 12);
        insertPista(9, "Thailandia", "Buriram", "E-san Alley", 4554, 9, 12);
        insertPista(10, "Malesia", "Sepang", "Jalan Pekeling", 5500, 15, 20);
    }

    public void insertTeam(int idTeam, String nome, int idMoto) {
        tableTeam.insertValue(new Team(idTeam, nome, idMoto));
    }

    public void insertContratto(int idPilota, int annoInizio, int annoFine, int idTeam) {
        tableContratti.insertValue(new Contratto(idPilota, annoInizio, annoFine, idTeam));
    }

    public void insertSponsorizzazione(int idSponsor, int idTeam, Date dataInizio, Date dataFine, Double pagamento) {
        tableSponsorizzazioni.insertValue(new Sponsorizzazione(idSponsor, idTeam, dataInizio, dataFine, pagamento));
    }

    public void insertStaff(int idStaff, String codiceFiscale, String nome, String cognome, Date dataNascita, String ruolo, int idTeam) {
        tableStaff.insertValue(new Staff(idStaff, codiceFiscale, nome, cognome, dataNascita, ruolo, idTeam));
    }

    public void insertClassifica(int idPilota, int anno, int punteggioFinale) {
        tableClassifica.insertValue(new Classifica(idPilota, anno, punteggioFinale));
    }

    public void insertMoto(int idMoto, String casaProduttrice, int cilindrata, int peso) {
        tableMoto.insertValue(new Moto(idMoto, casaProduttrice, cilindrata, peso));
    }


    public void insertTabelloneGara(int idGara, int idPilota, int posizione, int punti) {
        tableTabelloneGare.insertValue(new TabelloneGara(idGara, idPilota, posizione, punti));
    }

    public void insertIncidente(int idPilota, int idGara, String descrizione, String tipologia) {
        tableIncidenti.insertValue(new Incidente(idPilota, idGara, descrizione, tipologia));
    }

    public void insertPista(int id, String stato, String citta, String indirizzo, int lunghezza, int curveSx, int curveDx) {
        tablePiste.insertValue(new Pista(id, stato, citta, indirizzo, id, id, id));
    }

    public void insertGara(int idGara, Date data, String condizioniMeteo, int idPista) {
        tableGare.insertValue(new Gara(idGara, data, condizioniMeteo, idPista));
    }

    public void insertPilota(int idPilota, String nome, String cognome, Date dataNascita, String nazionalita ,int punteggioAttuale) {
        tablePiloti.insertValue(new Pilota(idPilota, nome, cognome, dataNascita, nazionalita, punteggioAttuale));
    }

    public void insertBroadcast(int canale, int idGara, int visualizzazioni) {
        tableBroadcast.insertValue(new Broadcast(canale, idGara, visualizzazioni));
    }

    public void insertSponsor(int id, String nome, String Stato, String citta) {
        tableSponsor.insertValue(new Sponsor(id, nome, Stato, citta));
    }



    public TablePiste getTablePiste() {
        return tablePiste;
    }

    public TableGare getTableGare() {
        return tableGare;
    }

    public TablePiloti getTablePiloti() {
        return tablePiloti;
    }

    public TableBroadcast getTableBroadcast() {
        return tableBroadcast;
    }

    public TableTabelloneGare getTabelloneGare() {
        return tableTabelloneGare;
    }

    public TableIncidenti getTableIncidenti() {
        return tableIncidenti;
    }

    public TableClassifica getTableClassifica() {
        return tableClassifica;
    }

    public TableMoto getTableMoto() {
        return tableMoto;
    }

    public TableTeam getTableTeam() {
        return tableTeam;
    }

    public TableStaff getTableStaff() {
        return tableStaff;
    }

    public TableContratti getTableContratti() {
        return tableContratti;
    }

    public TableSponsor getTableSponsor() {
        return tableSponsor;
    }

    public TableSponsorizzazioni getTableSponsorizzazioni() {
        return tableSponsorizzazioni;
    }

    public <V,K> List<String> getListAttributes(AbstractTable<V,K> table) {
        List<String> list = new ArrayList<>();
        var values = table.getRecords().getValues();
        for (int i = 0; i < values.size(); i++) {
            if(values.get(i).getX().length()>0) {
                list.add(values.get(i).getX());
            }
        }
        return list;
    }

    private void createAllTables() {
        tablePiste.createTable();
        tableGare.createTable();
        tableBroadcast.createTable();
        tablePiloti.createTable();
        tableClassifica.createTable();
        tableTabelloneGare.createTable();
        tableIncidenti.createTable();
        tableMoto.createTable();
        tableTeam.createTable();
        tableContratti.createTable();
        tableStaff.createTable();
        tableSponsor.createTable();
        tableSponsorizzazioni.createTable();
    }

    /*private void deleteAllTables() {
        tablePiste.dropTable();
        tableGare.dropTable();
        tableBroadcast.dropTable();
        tablePiloti.dropTable();
        tableClassifica.dropTable();
        tableTabelloneGare.dropTable();
        tableIncidenti.dropTable();
        tableMoto.dropTable();
        tableTeam.dropTable();
        tableContratti.dropTable();
        tableStaff.dropTable();
        tableSponsor.dropTable();
        tableSponsorizzazioni.dropTable();
    }*/

    
}
