package xyz.pary.it_one.cup2022final.model;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;

public class TableQueriesResultQuery extends ResultQuery {

    private final JsonNode tableQueries;

    public TableQueriesResultQuery(JsonNode tableQueries, HttpStatus hs) {
        super(hs);
        this.tableQueries = tableQueries;
    }

    public JsonNode getTableQueries() {
        return tableQueries;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
