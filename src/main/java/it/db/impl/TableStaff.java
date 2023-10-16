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
import it.model.Staff;
import it.utils.Utils;

public class TableStaff extends AbstractTable<Staff, Integer> {

    public TableStaff(Connection connection) {
        super(Staff.getRecords(), connection);
    }

    @Override
    public boolean insertValue(Staff value) {
        final String query = "INSERT INTO " + Staff.getRecords().getTableName() 
        + " (idStaff, codiceFiscale, nome, cognome, dataNascita, ruolo, idTeam) VALUES (?,?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, value.getIdStaff());
            statement.setString(2, value.getCodiceFiscale());
            statement.setString(3, value.getNome());
            statement.setString(4, value.getCognome());
            statement.setDate(5, Utils.dateToSqlDate(value.getDataNascita()));
            statement.setString(6, value.getRuolo());
            statement.setInt(7, value.getIdTeam());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Staff> readFromResultSet(ResultSet resultSet) {
        final List<Staff> staffList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final int idStaff = resultSet.getInt("idStaff");
                final String codiceFiscale = resultSet.getString("codiceFiscale");
                final String nome = resultSet.getString("nome");
                final String cognome = resultSet.getString("cognome");
                final Date dataNascita = Utils.sqlDateToDate(resultSet.getDate("dataNascita"));
                final String ruolo = resultSet.getString("ruolo");
                final int idTeam = resultSet.getInt("idTeam");
                final Staff staff = new Staff(idStaff, codiceFiscale, nome, cognome, dataNascita, ruolo, idTeam);
                staffList.add(staff);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return staffList;
    }

    @Override
    public Records getRecords() {
        return Staff.getRecords();
    }

    @Override
    public List<Integer> findAllPrimaryKey() {
                return this.findAll().stream().map(value -> value.getIdStaff()).toList();

    }
}
