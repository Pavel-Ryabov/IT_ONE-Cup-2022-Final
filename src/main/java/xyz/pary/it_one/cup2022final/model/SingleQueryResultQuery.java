package xyz.pary.it_one.cup2022final.model;

import org.springframework.http.HttpStatus;

public class SingleQueryResultQuery extends ResultQuery {

    private final int queryId;
    private final String query;

    public SingleQueryResultQuery(int queryId, String query, HttpStatus hs) {
        super(hs);
        this.queryId = queryId;
        this.query = query;
    }

    public int getQueryId() {
        return queryId;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
