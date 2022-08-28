package xyz.pary.it_one.cup2022final.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ReportColumn {

    private final String title;
    private final String type;
    @JsonInclude(Include.NON_NULL)
    private final String size;

    public ReportColumn(String title, String type, String size) {
        this.title = title;
        this.type = type;
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "ReportColumn{" + "title=" + title + ", type=" + type + ", size=" + size + '}';
    }
}
