package xyz.pary.it_one.cup2022final.model;

public class Column {

    private final String title;
    private final String type;

    public Column(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }
}
