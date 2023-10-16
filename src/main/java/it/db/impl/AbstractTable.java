package it.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import it.db.api.Records;
import it.db.api.Table;

public abstract class AbstractTable<V, K> implements Table<V, K>{

    protected final Records records;
    protected final Connection connection; 


    public AbstractTable(final Records records, final Connection connection) {
        this.records = records;
        this.connection = connection;
    }

    @Override
    public String getTableName() {
        return records.getTableName();
    }

    @Override
    public boolean createTable() {
        try (final Statement statement = this.connection.createStatement()) {
            StringBuilder query = new StringBuilder().append("CREATE TABLE ")
                    .append(getTableName()).append(" (");
            var values = records.getValues();
            for (int i = 0; i < values.size(); i++) {
                query.append(values.get(i).getX()).append(" ").append(values.get(i).getY());
                if(i != (values.size()-1)) {
                    query.append(", ");
                }
            }
            query.append(")");
            statement.executeUpdate(query.toString());
            return true;
        } catch (final SQLException e) {
            return false;
        }
    }

    @Override
    public boolean dropTable() {
        try (final Statement statement = this.connection.createStatement()) {
            statement.executeUpdate("DROP TABLE " + this.getTableName());
            return true;
        } catch (final SQLException e) {
            return false;
        }
    }

    @Override
    public List<V> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + records.getTableName());
            return readFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
}
