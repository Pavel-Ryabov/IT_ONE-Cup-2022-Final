package xyz.pary.it_one.cup2022final.model;

import java.util.List;

public class Report<T> {

    public static final String ADD_RESULT = "/api/report/add-create-report-result";
    public static final String ADD = "/api/report/create-report?resultId=%s";
    public static final String GET_BY_ID_RESULT = "/api/report/get-report-by-id-result";
    public static final String GET_BY_ID = "/api/report/get-report-by-id/%s?resultId=%s";

    private final T reportId;
    private final int tableAmount;
    private final List<ReportTable> tables;

    public Report(T reportId, int tableAmount, List<ReportTable> tables) {
        this.reportId = reportId;
        this.tableAmount = tableAmount;
        this.tables = tables;
    }

    public T getReportId() {
        return reportId;
    }

    public int getTableAmount() {
        return tableAmount;
    }

    public List<ReportTable> getTables() {
        return tables;
    }
}
