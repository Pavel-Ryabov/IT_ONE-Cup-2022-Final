package xyz.pary.it_one.cup2022final.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import xyz.pary.it_one.cup2022final.model.Column;
import xyz.pary.it_one.cup2022final.model.ResultQuery;
import xyz.pary.it_one.cup2022final.model.TableResultQuery;
import xyz.pary.it_one.cup2022final.model.Table;

@Service
@Slf4j
public class CheckerService {

    private final RestTemplate rt;
    @Value("${rs.endpoint}")
    private String ep;

    public CheckerService() {
        this.rt = new RestTemplate();
        this.rt.setInterceptors(Collections.singletonList(new JsonHeaderRequestInterceptor()));
    }

    public void check() {
        checkTable();
    }

    private void checkTable() {
        log.error("ep: " + ep);
        List<Column> columns = new ArrayList<>(3);
        columns.add(new Column("id", "int2"));
        columns.add(new Column("name", "varchar"));
        columns.add(new Column("bool", "boolean"));
        Table t1 = new Table("table1", "id", columns, columns.size());
        createTable(t1, HttpStatus.CREATED);
        createTable(t1, HttpStatus.NOT_ACCEPTABLE);

        Table t3 = new Table("CustomerЛАЛА", "id", columns, columns.size());
        createTable(t3, HttpStatus.CREATED);

        List<Column> columns2 = new ArrayList<>(3);
        columns.add(new Column("id", "int2"));
        columns.add(new Column("name", "varchar"));
        columns.add(new Column("bool", "abcde"));
        Table t4 = new Table("table2", "id", columns2, columns2.size());
        createTable(t4, HttpStatus.NOT_ACCEPTABLE);

        Table t5 = new Table("table2", "abc", columns, columns.size());
        createTable(t5, HttpStatus.NOT_ACCEPTABLE);

        Table t6 = new Table("table2", "id", columns, columns.size() - 1);
        createTable(t6, HttpStatus.NOT_ACCEPTABLE);
        
        dropTable(t3.getTableName(), HttpStatus.CREATED);
        dropTable(t3.getTableName(), HttpStatus.NOT_ACCEPTABLE);
    }

    private void createTable(Table t, HttpStatus es) {
        String resultUrl = ep + Table.ADD_RESULT;
        ResultQuery rq = new ResultQuery(es);
        HttpStatus rst = rt.postForEntity(resultUrl, rq, Void.class).getStatusCode();
        if (rst != HttpStatus.CREATED) {
            log.error("{} - {} - {}", resultUrl, rq, rst);
        }
        String url = ep + String.format(Table.ADD, rq.getResultId());
        HttpStatus st = rt.postForEntity(url, t, Void.class).getStatusCode();
        if (st != es) {
            log.error("{} - {}", url, st);
        }
    }

    private void dropTable(String tableName, HttpStatus es) {
        String resultUrl = ep + Table.DELETE_RESULT;
        ResultQuery rq = new ResultQuery(es);
        HttpStatus rst = rt.postForEntity(resultUrl, rq, Void.class).getStatusCode();
        if (rst != HttpStatus.CREATED) {
            log.error("{} - {} - {}", resultUrl, rq, rst);
        }
        String url = ep + String.format(Table.DELETE, tableName, rq.getResultId());
        HttpStatus st = rt.exchange(url, HttpMethod.DELETE, null, Void.class).getStatusCode();
        if (st != es) {
            log.error("{} - {}", url, st);
        }
    }
    
    private void getTable(String tableName, String eb, HttpStatus es) {
        String resultUrl = ep + Table.GET_BY_NAME_RESULT;
        TableResultQuery<String> rq = new TableResultQuery<>(eb, es);
        HttpStatus rst = rt.postForEntity(resultUrl, rq, Void.class).getStatusCode();
        if (rst != HttpStatus.ACCEPTED) {
            log.error("{} - {} - {}", resultUrl, rq, rst);
        }
        String url = ep + String.format(Table.DELETE, tableName, rq.getResultId());
        HttpStatus st = rt.exchange(url, HttpMethod.DELETE, null, Void.class).getStatusCode();
        if (st != es) {
            log.error("{} - {}", url, st);
        }
    }

    private static class JsonHeaderRequestInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            HttpHeaders headers = request.getHeaders();
            headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            return execution.execute(request, body);
        }
    }
}
