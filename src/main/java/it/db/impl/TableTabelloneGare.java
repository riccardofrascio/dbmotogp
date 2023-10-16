package it.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import it.db.api.Records;
import it.model.TabelloneGara;
import it.utils.Pair;

public class TableTabelloneGare extends AbstractTable<TabelloneGara, Pair<Integer, Integer>>{

    public TableTabelloneGare(Connection connection) {
        super(TabelloneGara.getRecords(), connection);
    }

    @Override
    public boolean insertValue(TabelloneGara value) {
        final String query = "INSERT INTO " + TabelloneGara.getRecords().getTableName() 
        + " (idGara, idPilota, posizione, punti) VALUES (?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, value.getIdGara());
            statement.setInt(2, value.getIdPilota());
            statement.setInt(3, value.getPosizione());
            statement.setInt(4, value.getPunti());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<TabelloneGara> readFromResultSet(ResultSet resultSet) {
        final List<TabelloneGara> tabelloneGaraList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final int idGara = resultSet.getInt("idGara");
                final int idPilota = resultSet.getInt("idPilota");
                final int posizione = resultSet.getInt("posizione");
                final int punti = resultSet.getInt("punti");
                final TabelloneGara tabelloneGara = new TabelloneGara(idGara, idPilota, posizione, punti);
                tabelloneGaraList.add(tabelloneGara);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return tabelloneGaraList;
    }

    @Override
    public Records getRecords() {
        return TabelloneGara.getRecords();
    }

    @Override
    public List<Pair<Integer, Integer>> findAllPrimaryKey() {
        return this.findAll().stream().map(value -> new Pair<>(value.getIdGara(), value.getIdPilota())).toList();

    }

    public List<Pair<Integer, Integer>> countMaxVittorie() {
        final String query = "SELECT idPilota, COUNT(*) AS conteggio" + 
                " FROM tabellonegare" +
                " WHERE posizione = 1" + 
                " GROUP BY idPilota" + 
                " HAVING COUNT(*) = (" +
                " SELECT MAX(piloti_count) AS max_count" + 
                " FROM (" +
                    " SELECT idPilota, COUNT(*) AS piloti_count" +
                    " FROM tabellonegare" +
                    " WHERE posizione = 1" +
                    " GROUP BY idPilota" +
                " ) AS conteggi" +
                " ); ";

        List<Pair<Integer, Integer>> resultList = new ArrayList<>();
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                final int idPilota = resultSet.getInt("idPilota");
                final int conteggio = resultSet.getInt("conteggio");
                final Pair<Integer, Integer> result = new Pair<>(idPilota, conteggio);
                resultList.add(result);
            }
            
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return resultList;
    }

    public List<Integer> getPosizioniFromIdGara(int id) {
        
        final String query = "SELECT posizione FROM tabellonegare WHERE idGara = ?";
        final List<Integer> listPosizioni = new ArrayList<>();
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final int posizione = resultSet.getInt("posizione");
                listPosizioni.add(posizione);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return listPosizioni;
    }

}
