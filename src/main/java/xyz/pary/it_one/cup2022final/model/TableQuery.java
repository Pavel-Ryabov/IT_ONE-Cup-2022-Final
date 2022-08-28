package xyz.pary.it_one.cup2022final.model;

public class TableQuery<T> extends Query<T> {

    public static final String ADD_RESULT = "/api/table-query/add-new-query-to-table-result";
    public static final String ADD = "/api/table-query/add-new-query-to-table?resultId=%s";
    public static final String GET_BY_TABLE_NAME_RESULT = "/api/table-query/get-all-queries-by-table-name-result";
    public static final String GET_BY_TABLE_NAME = "/api/table-query/get-all-queries-by-table-name/%s?resultId=%s";
    public static final String GET_BY_ID_RESULT = "/api/table-query/get-table-query-by-id-result";
    public static final String GET_BY_ID = "/api/table-query/get-table-query-by-id/%s?resultId=%s";
    public static final String EXECUTE_RESULT = "/api/table-query/execute-table-query-by-id-result";
    public static final String EXECUTE = "/api/table-query/execute-table-query-by-id/%s?resultId=%s";
    public static final String DELETE_RESULT = "/api/table-query/delete-table-query-by-id-result";
    public static final String DELETE = "/api/table-query/delete-table-query-by-id/%s?resultId=%s";
    public static final String MODIFY_RESULT = "/api/table-query/modify-query-in-table-result";
    public static final String MODIFY = "/api/table-query/modify-query-in-table?resultId=%s";

    private final String tableName;

    public TableQuery(String tableName, T queryId, String query) {
        super(queryId, query);
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public String toString() {
        return "TableQuery{" + "tableName=" + tableName + ", " + super.toString() + '}';
    }

}
