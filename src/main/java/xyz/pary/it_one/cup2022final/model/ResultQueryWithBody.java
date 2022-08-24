package xyz.pary.it_one.cup2022final.model;

import org.springframework.http.HttpStatus;

public class ResultQueryWithBody<T> extends ResultQuery {

    private final T body;

    public ResultQueryWithBody(T body, HttpStatus hs) {
        super(hs);
        this.body = body;
    }

    public T getBody() {
        return body;
    }
}
