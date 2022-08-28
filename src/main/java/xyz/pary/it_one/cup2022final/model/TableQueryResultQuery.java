package xyz.pary.it_one.cup2022final.model;

import org.springframework.http.HttpStatus;

public class TableQueryResultQuery<T> extends ResultQuery {

    private final T tableQuery;

    public TableQueryResultQuery(T tableQuery, HttpStatus hs) {
        super(hs);
        this.tableQuery = tableQuery;
    }

    public T getTableQuery() {
        return tableQuery;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
