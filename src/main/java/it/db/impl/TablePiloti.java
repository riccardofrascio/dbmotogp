package it.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import it.db.api.Records;
import it.model.Pilota;
import it.utils.Utils;

public class TablePiloti extends AbstractTable<Pilota, Integer>{

    public TablePiloti(Connection connection) {
        super(Pilota.getRecords(), connection);
    }

    @Override
    public boolean insertValue(Pilota value) {
        final String query = "INSERT INTO " + Pilota.getRecords().getTableName() 
        + " (idPilota, nome, cognome, dataNascita, nazionalita, punteggioAttuale) VALUES (?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, value.getIdPilota());
            statement.setString(2, value.getNome());
            statement.setString(3, value.getCognome());
            statement.setDate(4, Utils.dateToSqlDate(value.getDataNascita()));
            statement.setString(5, value.getNazionalita());
            statement.setInt(6, value.getPunteggioAttuale());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Pilota> readFromResultSet(ResultSet resultSet) {
        final List<Pilota> pilotiList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final int idPilota = resultSet.getInt("idPilota");
                final String nome = resultSet.getString("nome");
                final String cognome = resultSet.getString("cognome");
                final Date dataNascita = Utils.sqlDateToDate(resultSet.getDate("dataNascita"));
                final String nazionalita = resultSet.getString("nazionalita");
                final int punteggioAttuale = resultSet.getInt("punteggioAttuale");

                final Pilota piloti = new Pilota(idPilota, nome, cognome, dataNascita, nazionalita, punteggioAttuale);
                pilotiList.add(piloti);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return pilotiList;
    }

    @Override
    public Records getRecords() {
        return Pilota.getRecords();
    }

    @Override
    public List<Integer> findAllPrimaryKey() {
        return this.findAll().stream().map(value ->value.getIdPilota()).toList();
    }

    public Optional<Pilota> findByPrimaryKey(final int id) {
        
        final String query = "SELECT * FROM piloti WHERE idPilota = ?";
        
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            
            statement.setInt(1, id);

            final ResultSet resultSet = statement.executeQuery();

            return readFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void updatePunteggio(final int id) {
        final String query = "UPDATE piloti" +
                                " SET piloti.punteggioAttuale = (SELECT SUM(tabellonegare.punti)" +
                                                            " from tabellonegare, gare" +
                                                            " WHERE idPilota = " + id +
                                                            " AND tabellonegare.idgara = gare.idgara" + 
                                                            " AND YEAR(gare.data) > 2022) " +
                                " WHERE idPilota = " +id + ";";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {

            statement.executeUpdate(query);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
