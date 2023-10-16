package it.db.api;

import java.util.List;

import it.utils.Pair;

public interface Records {
    
    public List<Pair<String, String>> getValues();

    public String getTableName();
}
