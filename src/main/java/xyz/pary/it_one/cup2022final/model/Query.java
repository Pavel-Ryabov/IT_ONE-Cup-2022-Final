package xyz.pary.it_one.cup2022final.model;

public class Query<T> {

    private final T queryId;
    private final String query;

    public Query(T queryId, String query) {
        this.queryId = queryId;
        this.query = query;
    }

    public T getQueryId() {
        return queryId;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public String toString() {
        return "Query{" + "queryId=" + queryId + ", query=" + query + '}';
    }
}
