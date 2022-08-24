package xyz.pary.it_one.cup2022final.model;

import java.util.List;

public class ReportTable {

    private final String tableName;
    private final List<ReportColumn> columns;

    public ReportTable(String tableName, List<ReportColumn> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public List<ReportColumn> getColumns() {
        return columns;
    }
}
