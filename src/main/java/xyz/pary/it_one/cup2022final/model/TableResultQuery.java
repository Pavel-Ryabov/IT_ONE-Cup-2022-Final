package xyz.pary.it_one.cup2022final.model;

import org.springframework.http.HttpStatus;

public class TableResultQuery<T> extends ResultQuery {

    private final T table;

    public TableResultQuery(T table, HttpStatus hs) {
        super(hs);
        this.table = table;
    }

    public T getTable() {
        return table;
    }
}
