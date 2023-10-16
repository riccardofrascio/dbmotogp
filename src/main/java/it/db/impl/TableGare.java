package it.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.db.api.Records;
import it.model.Gara;
import it.utils.Utils;

public class TableGare extends AbstractTable<Gara, Integer>{

    public TableGare(Connection connection) {
        super(Gara.getRecords(), connection);
    }

    @Override
    public boolean insertValue(Gara value) {
        final String query = "INSERT INTO " + Gara.getRecords().getTableName() 
        + " (idGara, data, condizioniMeteo, idPista) VALUES (?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, value.getIdGara());
            statement.setDate(2, Utils.dateToSqlDate(value.getData()));
            statement.setString(3, value.getCondizioniMeteo());
            statement.setInt(4, value.getIdPista());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Gara> readFromResultSet(ResultSet resultSet) {
        final List<Gara> gareList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final int idGara = resultSet.getInt("idGara");
                final Date data = Utils.sqlDateToDate(resultSet.getDate("data"));
                final String condizioniMeteo = resultSet.getString("condizioniMeteo");
                final int idPista = resultSet.getInt("idPista");

                final Gara gara = new Gara(idGara, data, condizioniMeteo, idPista);
                gareList.add(gara);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return gareList;
    }

    @Override
    public Records getRecords() {
        return Gara.getRecords();
    }

    @Override
    public List<Integer> findAllPrimaryKey() {
        return this.findAll().stream().map(value -> value.getIdGara()).toList();

    }
}
