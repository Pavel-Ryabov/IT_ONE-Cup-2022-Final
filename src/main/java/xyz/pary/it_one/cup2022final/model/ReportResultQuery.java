package xyz.pary.it_one.cup2022final.model;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;

public class ReportResultQuery extends ResultQuery {

    private final JsonNode getReport;

    public ReportResultQuery(JsonNode getReport, HttpStatus hs) {
        super(hs);
        this.getReport = getReport;
    }

    public JsonNode getGetReport() {
        return getReport;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
