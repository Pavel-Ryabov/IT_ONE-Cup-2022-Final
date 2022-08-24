package xyz.pary.it_one.cup2022final.model;

import java.util.List;

public class Table {

    public static final String ADD_RESULT = "/api/table/add-create-table-result";
    public static final String ADD = "/api/table/create-table?resultId=%s";
    public static final String GET_BY_NAME_RESULT = "/api/table/add-get-table-by-name-result";
    public static final String GET_BY_NAME = "/api/table/get-table-by-name/%s?resultId=%s";
    public static final String DELETE_RESULT = "/api/table/add-drop-table-result";
    public static final String DELETE = "/api/table/drop-table/%s?resultId=%s";

    private final String tableName;
    private final String primaryKey;
    private final List<Column> columnInfos;

    private int columnsAmount;

    public Table(String tableName, String primaryKey, List<Column> columnInfos, int columnsAmount) {
        this.tableName = tableName;
        this.primaryKey = primaryKey;
        this.columnInfos = columnInfos;
        this.columnsAmount = columnsAmount;
    }

    public String getTableName() {
        return tableName;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public List<Column> getColumnInfos() {
        return columnInfos;
    }

    public int getColumnsAmount() {
        return columnsAmount;
    }

    public void setColumnsAmount(int columnsAmount) {
        this.columnsAmount = columnsAmount;
    }

}
