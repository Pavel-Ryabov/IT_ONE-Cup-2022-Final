package xyz.pary.it_one.cup2022final.model;

import org.springframework.http.HttpStatus;

public class ResultQuery {

    private static int resultIdCounter = 0;

    private final int resultId = ++resultIdCounter;
    private final int code;

    public ResultQuery(HttpStatus hs) {
        this.code = hs.value();
    }

    public int getResultId() {
        return resultId;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ResultQuery{" + "resultId=" + resultId + ", code=" + code + '}';
    }

    public static int getResultIdCounter() {
        return resultIdCounter;
    }

}
