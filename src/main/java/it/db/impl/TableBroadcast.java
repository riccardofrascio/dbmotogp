package it.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import it.db.api.Records;
import it.model.Broadcast;
import it.utils.Pair;

public class TableBroadcast extends AbstractTable<Broadcast, Pair<Integer, Integer>>{

    public TableBroadcast(Connection connection) {
        super(Broadcast.getRecords(), connection);
    }

    @Override
    public boolean insertValue(Broadcast value) {
        final String query = "INSERT INTO " + Broadcast.getRecords().getTableName() + " (canale, idGara, visualizzazioni) VALUES (?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, value.getCanale());
            statement.setInt(2, value.getIdGara());
            statement.setInt(3, value.getVisualizzazioni());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Broadcast> readFromResultSet(ResultSet resultSet) {
        final List<Broadcast> broadcastList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final int canale = resultSet.getInt("canale");
                final int idGara = resultSet.getInt("idGara");
                final int visualizzazioni = resultSet.getInt("visualizzazioni");

                final Broadcast broadcast = new Broadcast(canale, idGara, visualizzazioni);
                broadcastList.add(broadcast);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return broadcastList;
    }

    @Override
    public Records getRecords() {
        return Broadcast.getRecords();
    }

    @Override
    public List<Pair<Integer, Integer>> findAllPrimaryKey() {
        return this.findAll().stream().map(v -> new Pair<>(v.getCanale(), v.getIdGara())).toList();
    }

}
