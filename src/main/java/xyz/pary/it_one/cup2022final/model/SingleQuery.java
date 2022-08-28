package xyz.pary.it_one.cup2022final.model;

public class SingleQuery<T> extends Query<T> {

    public static final String ADD_RESULT = "/api/single-query/add-new-query-result";
    public static final String ADD = "/api/single-query/add-new-query?resultId=%s";
    public static final String MODIFY_RESULT = "/api/single-query/add-modify-result";
    public static final String MODIFY = "/api/single-query/modify-single-query?resultId=%s";
    public static final String DELETE_RESULT = "/api/single-query/add-delete-result";
    public static final String DELETE = "/api/single-query/delete-single-query-by-id/%s?resultId=%s";
    public static final String EXECUTE_RESULT = "/api/single-query/add-execute-result";
    public static final String EXECUTE = "/api/single-query/execute-single-query-by-id/%s?resultId=%s";
    public static final String GET_BY_ID_RESULT = "/api/single-query/add-get-single-query-by-id-result";
    public static final String GET_BY_ID = "/api/single-query/get-single-query-by-id/%s?resultId=%s";

    public SingleQuery(T queryId, String query) {
        super(queryId, query);
    }

}
