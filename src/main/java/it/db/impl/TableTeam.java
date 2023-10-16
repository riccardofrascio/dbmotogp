package it.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.db.api.Records;
import it.model.Team;

public class TableTeam extends AbstractTable<Team, Integer>{

    public TableTeam(Connection connection) {
        super(Team.getRecords(), connection);
    }

    @Override
    public boolean insertValue(Team value) {
        final String query = "INSERT INTO " + Team.getRecords().getTableName() + " (idTeam, nome, idMoto) VALUES (?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, value.getIdTeam());
            statement.setString(2, value.getNome());
            statement.setInt(3, value.getIdMoto());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Team> readFromResultSet(ResultSet resultSet) {
        final List<Team> teamList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final int idTeam = resultSet.getInt("idTeam");
                final String nome = resultSet.getString("nome");
                final int idMoto = resultSet.getInt("idMoto");
                final Team team = new Team(idTeam, nome, idMoto);
                teamList.add(team);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return teamList;
    }

    @Override
    public Records getRecords() {
        return Team.getRecords();
    }

    @Override
    public List<Integer> findAllPrimaryKey() {
                return this.findAll().stream().map(value -> value.getIdTeam()).toList();
    }

    public Optional<Team> findByPrimaryKey(final int id) {
        
        final String query = "SELECT * FROM team WHERE idTeam = ?";
        
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            
            statement.setInt(1, id);

            final ResultSet resultSet = statement.executeQuery();

            return readFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
}
