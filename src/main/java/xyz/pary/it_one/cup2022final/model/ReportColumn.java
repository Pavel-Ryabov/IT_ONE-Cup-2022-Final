package xyz.pary.it_one.cup2022final.model;

public class ReportColumn {

    private final String title;
    private final String type;
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
}
