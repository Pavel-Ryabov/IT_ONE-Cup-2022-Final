package xyz.pary.it_one.cup2022final.model;

import org.springframework.http.HttpStatus;

public class TableResultQuery<T> extends ResultQuery {

    private final String table;

    public TableResultQuery(String table, HttpStatus hs) {
        super(hs);
        this.table = table;
    }

    public String getTable() {
        return table;
    }
}
