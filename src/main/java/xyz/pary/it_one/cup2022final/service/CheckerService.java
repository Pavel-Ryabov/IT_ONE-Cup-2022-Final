package xyz.pary.it_one.cup2022final.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import xyz.pary.it_one.cup2022final.model.Column;
import xyz.pary.it_one.cup2022final.model.Report;
import xyz.pary.it_one.cup2022final.model.ReportColumn;
import xyz.pary.it_one.cup2022final.model.ReportResultQuery;
import xyz.pary.it_one.cup2022final.model.ReportTable;
import xyz.pary.it_one.cup2022final.model.ResultQuery;
import xyz.pary.it_one.cup2022final.model.SingleQuery;
import xyz.pary.it_one.cup2022final.model.SingleQueryResultQuery;
import xyz.pary.it_one.cup2022final.model.TableResultQuery;
import xyz.pary.it_one.cup2022final.model.Table;
import xyz.pary.it_one.cup2022final.model.TableQueriesResultQuery;
import xyz.pary.it_one.cup2022final.model.TableQuery;
import xyz.pary.it_one.cup2022final.model.TableQueryResultQuery;

@Service
@Slf4j
public class CheckerService {

    private final RestTemplate client;
    private final ObjectMapper om;
    @Value("${rs.endpoint}")
    private String ep;

    private int tableLimit = 0;
    private int tableQueryLimit = 0;
    private int singleQueryLimit = 0;
    private int reportLimit = 0;

    public CheckerService() {
        this.client = new RestTemplate();
        this.client.setInterceptors(Collections.singletonList(new JsonHeaderRequestInterceptor()));
        this.client.setErrorHandler(new NoOpResponseErrorHandler());
        this.om = new ObjectMapper();
    }

    public void check() {
        tableLimit = 26;
        tableQueryLimit = 18;
        singleQueryLimit = 18;
        reportLimit = 30;
        checkTable();
        log.error("checkTable end {}", ResultQuery.getResultIdCounter());
        checkTableQuery();
        log.error("checkTableQuery end {}", ResultQuery.getResultIdCounter());
        checkSingleQuery();
        log.error("checkSingleQuery end {}", ResultQuery.getResultIdCounter());
        checkReport();
        log.error("checkReport end {}", ResultQuery.getResultIdCounter());
    }

    private void checkTable() {
        List<Column> columns = new ArrayList<>(3);
        columns.add(new Column("id", "int4"));
        columns.add(new Column("name", "varchar"));
        columns.add(new Column("bool", "boolean"));
        List<Column> columns2 = new ArrayList<>(3);
        columns2.add(new Column("id", "int4"));
        columns2.add(new Column("name", "varchar"));
        columns2.add(new Column("bool", "abcde"));

        Table t3 = new Table("CustomerЛАЛА", "id", columns, columns.size());
        boolean ct3r = createTable(t3, HttpStatus.CREATED);
        if (!ct3r) {
            int tn = 1;
            while (tableLimit >= 1) {
                Table t = new Table("CustomerЛАЛА" + (tn++), "id", columns, columns.size());
                if (createTable(t, HttpStatus.CREATED)) {
                    break;
                }
            }
        }

        while (tableLimit >= 1 && !getTable(t3.getTableName(), "{\"tableName\":\"CustomerЛАЛА\",\"primaryKey\":\"id\",\"columnsAmount\":3,\"columnInfos\":[{\"title\":\"ID\",\"type\":\"INTEGER\"},{\"title\":\"NAME\",\"type\":\"CHARACTER VARYING\"},{\"title\":\"BOOL\",\"type\":\"BOOLEAN\"}]}", HttpStatus.OK)) {
        }

        if (tableLimit == 0) {
            return;
        }

        dropTable(t3.getTableName(), HttpStatus.CREATED);
        while (tableLimit >= 1 && !dropTable(t3.getTableName(), HttpStatus.NOT_ACCEPTABLE)) {
        }

        while (tableLimit >= 1 && !getTable(t3.getTableName(), null, HttpStatus.OK)) {
        }

        Table t6 = new Table("table2", "id", columns2, columns2.size() - 1);
        while (tableLimit >= 1 && !createTable(t6, HttpStatus.NOT_ACCEPTABLE)) {
        }

        if (tableLimit == 0) {
            return;
        }
        Table t1 = new Table("Artists0", "id", columns, columns.size());
        boolean ct1r = createTable(t1, HttpStatus.CREATED);
        if (!ct1r) {
            int tn = 1;
            while (tableLimit >= 1) {
                Table t = new Table("Artists" + (tn++), "id", columns, columns.size());
                if (createTable(t, HttpStatus.CREATED)) {
                    break;
                }
            }
        }

        while (tableLimit >= 1 && !createTable(t1, HttpStatus.NOT_ACCEPTABLE)) {
        }

        Table t5 = new Table("table2", "abc", columns2, columns2.size());
        while (tableLimit >= 1 && !createTable(t5, HttpStatus.NOT_ACCEPTABLE)) {
        }

        Table t4 = new Table("table2", "id", columns2, columns2.size());
        while (tableLimit >= 1 && !createTable(t4, HttpStatus.NOT_ACCEPTABLE)) {
        }
    }

    private void checkTableQuery() {
        TableQuery<Integer> tq6 = new TableQuery<>("Artists", 4, "select * from Artists".repeat(6));
        boolean ctq6r = createTableQuery(tq6, HttpStatus.NOT_ACCEPTABLE);
        if (!ctq6r) {
            int tqn = 100;
            while (tableQueryLimit >= 1) {
                TableQuery<Integer> tq = new TableQuery<>("Artists", tqn++, "select * from Artists".repeat(6));
                if (createTableQuery(tq, HttpStatus.NOT_ACCEPTABLE)) {
                    break;
                }
            }
        }

        TableQuery<Integer> tq7 = new TableQuery<>("Artistsssss".repeat(5), 5, "select * from Artists");
        boolean ctq7r = createTableQuery(tq7, HttpStatus.NOT_ACCEPTABLE);
        if (!ctq7r) {
            int tqn = 130;
            while (tableQueryLimit >= 1) {
                TableQuery<Integer> tq = new TableQuery<>("Artistsssss".repeat(5), tqn++, "select * from Artists");
                if (createTableQuery(tq, HttpStatus.NOT_ACCEPTABLE)) {
                    break;
                }
            }
        }

        TableQuery<Integer> tq4 = new TableQuery<>("Arti", 3, "select * from Artists");
        boolean ctq4r = createTableQuery(tq4, HttpStatus.NOT_ACCEPTABLE);
        if (!ctq4r) {
            int tqn = 160;
            while (tableQueryLimit >= 1) {
                TableQuery<Integer> tq = new TableQuery<>("Arti", tqn++, "select * from Artists");
                if (createTableQuery(tq, HttpStatus.NOT_ACCEPTABLE)) {
                    break;
                }
            }
        }

        TableQuery<Integer> mtq2 = new TableQuery<>("Artist", 1, "modified");
        while (tableQueryLimit >= 1 && !modifyTableQuery(mtq2, HttpStatus.NOT_ACCEPTABLE)) {
        }

        if (tableQueryLimit == 0) {
            return;
        }

        List<Column> columnsArtists = new ArrayList<>(3);
        columnsArtists.add(new Column("id", "int4"));
        columnsArtists.add(new Column("name", "varchar"));
        columnsArtists.add(new Column("bool", "boolean"));
        Table artists = new Table("Artists", "id", columnsArtists, columnsArtists.size());
        createTable(artists, HttpStatus.CREATED);
        List<Column> columnsCustomer = new ArrayList<>(3);
        columnsCustomer.add(new Column("id", "int4"));
        columnsCustomer.add(new Column("name", "varchar(20)"));
        Table customer = new Table("Customer", "id", columnsCustomer, columnsCustomer.size());
        createTable(customer, HttpStatus.CREATED);

        TableQuery<Integer> tq1 = new TableQuery<>("Artists", 1, "раз dva");
        boolean ctq1r = createTableQuery(tq1, HttpStatus.CREATED);
        if (!ctq1r) {
            int tqn = 100;
            while (tableQueryLimit >= 1) {
                TableQuery<Integer> tq = new TableQuery<>("Artists", tqn++, "раз dva");
                if (createTableQuery(tq, HttpStatus.CREATED)) {
                    break;
                }
            }
        }

        while (tableQueryLimit >= 1 && !executeTableQuery(tq1.getQueryId(), HttpStatus.NOT_ACCEPTABLE)) {
        }

        TableQuery<Integer> mtq1 = new TableQuery<>("Artists", tq1.getQueryId(), "drop  table  Artists;drop  table  Customer ;"
                + "create table C2 (id int4, CONSTRAINT PK_C2 PRIMARY KEY (id));");
        while (tableQueryLimit >= 1 && !modifyTableQuery(mtq1, HttpStatus.OK)) {
        }

        while (tableQueryLimit >= 1 && !getTableQuery(tq1.getQueryId(), " {\"queryId\":1,\"query\":\"drop  table  Artists;drop  table  Customer ;create table C2 (id int4, CONSTRAINT PK_C2 PRIMARY KEY (id));\",\"tableName\":\"Artists\"}", HttpStatus.OK)) {
        }

        if (tableQueryLimit == 0) {
            return;
        }

        executeTableQuery(tq1.getQueryId(), HttpStatus.CREATED);

        while (tableLimit >= 1 && !getTable("Artists", null, HttpStatus.OK)) {
            if (tableLimit == 0) {
                ReportColumn rc = new ReportColumn("ID", "INT4", null);
                List<ReportColumn> rcs = new ArrayList<>(1);
                rcs.add(rc);
                ReportTable rt = new ReportTable("Artists", rcs);
                List<ReportTable> rts = new ArrayList<>(1);
                rts.add(rt);
                int rId = 0;
                while (reportLimit >= 1) {
                    Report r = new Report(++rId, 1, rts);
                    if (createReport(r, HttpStatus.NOT_ACCEPTABLE)) {
                        break;
                    }
                }
                int tqId = 3000;
                while (tableQueryLimit >= 1) {
                    TableQuery<Integer> tq = new TableQuery<>("Artists", ++tqId, "раз dva");
                    if (createTableQuery(tq, HttpStatus.NOT_ACCEPTABLE)) {
                        break;
                    }
                }
            }
        }

        while (tableQueryLimit >= 1 && !getTableQuery(tq1.getQueryId(), null, HttpStatus.INTERNAL_SERVER_ERROR)) {
        }

        while (tableQueryLimit >= 1 && !getTableQueries("Artists", "[]", HttpStatus.OK)) {
        }

        if (tableQueryLimit == 0) {
            return;
        }

        while (tableQueryLimit >= 1 && !deleteTableQuery(5, HttpStatus.NOT_ACCEPTABLE)) {
        }

        if (tableQueryLimit >= 3) {
            TableQuery<Integer> tq9 = new TableQuery<>("C2", 9, "alter table C2 rename to C3;ALTER TABLE C3 ADD f1 int4; ALTER TABLE C3 ADD f2 int4;");
            createTableQuery(tq9, HttpStatus.CREATED);
            executeTableQuery(tq9.getQueryId(), HttpStatus.CREATED);
            while (tableQueryLimit >= 1 && !getTableQueries("C3", "[{\"queryId\":9,\"query\":\"alter table C2 rename to C3;ALTER TABLE C3 ADD f1 int4; ALTER TABLE C3 ADD f2 int4;\",\"tableName\":\"C3\"}]", HttpStatus.OK)) {
            }//"[]", HttpStatus.OK);// 

            while (tableLimit >= 1 && !getTable("C3", "{\"tableName\":\"C3\",\"primaryKey\":\"id\",\"columnsAmount\":3,\"columnInfos\":[{\"title\":\"ID\",\"type\":\"INTEGER\"},{\"title\":\"F1\",\"type\":\"INTEGER\"},{\"title\":\"F2\",\"type\":\"INTEGER\"}]}", HttpStatus.OK)) {
                if (tableLimit == 0) {
                    ReportColumn rc = new ReportColumn("ID", "INT4", null);
                    List<ReportColumn> rcs = new ArrayList<>(1);
                    rcs.add(rc);
                    ReportTable rt = new ReportTable("C3", rcs);
                    List<ReportTable> rts = new ArrayList<>(1);
                    rts.add(rt);
                    int rId = 0;
                    while (reportLimit >= 1) {
                        Report r = new Report(++rId, 1, rts);
                        if (createReport(r, HttpStatus.CREATED)) {
                            break;
                        }
                    }
                    int tqId = 1250;
                    while (tableQueryLimit >= 1) {
                        TableQuery<Integer> tq = new TableQuery<>("C3", ++tqId, "раз dva");
                        if (createTableQuery(tq, HttpStatus.CREATED)) {
                            break;
                        }
                    }
                }
            }
        }
    }

    private void checkSingleQuery() {
        SingleQuery<Integer> q1 = new SingleQuery<>(5001, "select * from Artists".repeat(6));
        boolean q1r = createSingleQuery(q1, HttpStatus.BAD_REQUEST);
        if (!q1r) {
            int qn = 5500;
            while (singleQueryLimit >= 1) {
                SingleQuery<Integer> q = new SingleQuery<>(qn++, "select * from Artists".repeat(6));
                if (createSingleQuery(q1, HttpStatus.BAD_REQUEST)) {
                    break;
                }
            }
        }
        while (singleQueryLimit >= 1 && !deleteSingleQuery(q1.getQueryId(), HttpStatus.NOT_ACCEPTABLE)) {
        }
        if (singleQueryLimit == 0) {
            return;
        }
        SingleQuery<Integer> q2 = new SingleQuery<>(5002, "раз dva");
        createSingleQuery(q2, HttpStatus.CREATED);
        while (singleQueryLimit >= 1 && !executeSingleQuery(q2.getQueryId(), HttpStatus.NOT_ACCEPTABLE)) {
        }
        q2 = new SingleQuery<>(q2.getQueryId(), "create table SQ (id int, CONSTRAINT PK_SQ PRIMARY KEY (id));");
//        q2 = new SingleQuery<>(q2.getQueryId(), "alter table SQ_TEST rename to SQ;");
        while (singleQueryLimit >= 1 && !modifySingleQuery(q2, HttpStatus.OK)) {
        }
        if (singleQueryLimit == 0) {
            return;
        }
        executeSingleQuery(q2.getQueryId(), HttpStatus.CREATED);
        while (tableLimit >= 1 && !getTable("SQ", "{\"tableName\":\"SQ\",\"primaryKey\":\"id\",\"columnsAmount\":1,\"columnInfos\":[{\"title\":\"ID\",\"type\":\"INTEGER\"}]}", HttpStatus.OK)) {
//        while (tableLimit >= 1 && !getTable("SQ_TEST", "{\"tableName\":\"SQ_TEST\",\"primaryKey\":\"id\",\"columnsAmount\":1,\"columnInfos\":[{\"title\":\"ID\",\"type\":\"INTEGER\"}]}", HttpStatus.OK)) {
            if (tableLimit == 0) {
                ReportColumn rc = new ReportColumn("ID", "INT4", null);
                List<ReportColumn> rcs = new ArrayList<>(1);
                rcs.add(rc);
                ReportTable rt = new ReportTable("SQ", rcs);
                List<ReportTable> rts = new ArrayList<>(1);
                rts.add(rt);
                int rId = 0;
                while (reportLimit >= 1) {
                    Report r = new Report(++rId, 1, rts);
                    if (createReport(r, HttpStatus.CREATED)) {
                        break;
                    }
                }
                int tqId = 5600;
                while (tableQueryLimit >= 1) {
                    TableQuery<Integer> tq = new TableQuery<>("SQ", ++tqId, "раз dva");
                    if (createTableQuery(tq, HttpStatus.CREATED)) {
                        break;
                    }
                }
            }
        }
        boolean renamed = false;
        if (tableQueryLimit >= 1 && singleQueryLimit >= 2) {
            TableQuery<Integer> tq = new TableQuery<>("SQ", 5700, "раз dva");
            createTableQuery(tq, HttpStatus.CREATED);
            SingleQuery<Integer> q3 = new SingleQuery<>(5003, "alter table SQ rename to\nSQ2");
            createSingleQuery(q3, HttpStatus.CREATED);
            executeSingleQuery(q3.getQueryId(), HttpStatus.CREATED);
            renamed = true;
            while (tableQueryLimit >= 1 && !getTableQuery(tq.getQueryId(), "{\"queryId\":5700,\"query\":\"раз dva\",\"tableName\":\"SQ2\"}", HttpStatus.OK)) {
            }
        }
        String tn = !renamed ? "SQ" : "SQ2";
        SingleQuery<Integer> q4 = new SingleQuery<>(5004, "drop  table\t" + tn);
        createSingleQuery(q4, HttpStatus.CREATED);
        executeSingleQuery(q4.getQueryId(), HttpStatus.CREATED);
        while (tableLimit >= 1 && !getTable(tn, null, HttpStatus.OK)) {
            if (tableLimit == 0) {
                ReportColumn rc = new ReportColumn("ID", "INT4", null);
                List<ReportColumn> rcs = new ArrayList<>(1);
                rcs.add(rc);
                ReportTable rt = new ReportTable(tn, rcs);
                List<ReportTable> rts = new ArrayList<>(1);
                rts.add(rt);
                int rId = 0;
                while (reportLimit >= 1) {
                    Report r = new Report(++rId, 1, rts);
                    if (createReport(r, HttpStatus.NOT_ACCEPTABLE)) {
                        break;
                    }
                }
                int tqId = 5700;
                while (tableQueryLimit >= 1) {
                    TableQuery<Integer> tq = new TableQuery<>(tn, ++tqId, "раз dva");
                    if (createTableQuery(tq, HttpStatus.NOT_ACCEPTABLE)) {
                        break;
                    }
                }
            }
        }
        boolean dqr = deleteSingleQuery(q4.getQueryId(), HttpStatus.ACCEPTED);
        if (!dqr) {
            while (singleQueryLimit >= 2) {
                SingleQuery<Integer> q5 = new SingleQuery<>(5005, "раз dva");
                createSingleQuery(q5, HttpStatus.CREATED);
                deleteSingleQuery(q5.getQueryId(), HttpStatus.ACCEPTED);
            }
        }
        while (singleQueryLimit >= 1 && !getSingleQuery(q4, null, HttpStatus.INTERNAL_SERVER_ERROR)) {
        }
        while (singleQueryLimit >= 1 && !getSingleQuery(q2, "{\"queryId\":5002,\"query\":\"create table SQ (id int, CONSTRAINT PK_SQ PRIMARY KEY (id));\"}", HttpStatus.OK)) {
        }
    }

    private void checkReport() {
        List<Column> columns = new ArrayList<>(3);
        columns.add(new Column("id", "int4"));
        columns.add(new Column("name", "varchar"));
        columns.add(new Column("bool", "boolean"));
        Table testReportTable1 = new Table("TestReport1", "id", columns, columns.size());
        createTable(testReportTable1, HttpStatus.CREATED);
        Table testReportTable2 = new Table("TestReport2", "id", columns, columns.size());
        createTable(testReportTable2, HttpStatus.CREATED);

        ReportColumn rc1 = new ReportColumn("ID", "INT4", null);
        ReportColumn rc2 = new ReportColumn("name", "varChar", null);
        ReportColumn rc3 = new ReportColumn("bool", "boolean", null);
        List<ReportColumn> rcs1 = new ArrayList<>(3);
        rcs1.add(rc1);
        rcs1.add(rc2);
        rcs1.add(rc3);
        ReportTable rt1 = new ReportTable(testReportTable1.getTableName(), rcs1);
        ReportTable rt2 = new ReportTable(testReportTable2.getTableName(), rcs1);
        List<ReportTable> rts1 = new ArrayList<>(1);
        rts1.add(rt1);
        rts1.add(rt2);
        Report r1 = new Report(101, 2, rts1);
        ReportColumn rc4 = new ReportColumn("ID", "varchar", null);
        ReportColumn rc5 = new ReportColumn("name", "varChar", null);
        ReportColumn rc6 = new ReportColumn("col", "boolean", null);
        List<ReportColumn> rcs2 = new ArrayList<>(3);
        rcs2.add(rc4);
        rcs2.add(rc5);
        rcs2.add(rc6);
        ReportTable rt3 = new ReportTable("abcde", rcs2);
        List<ReportTable> rts2 = new ArrayList<>(1);
        rts2.add(rt1);
        rts2.add(rt3);
        Report r2 = new Report(102, 2, rts2);
        while (reportLimit >= 1 && !createReport(r2, HttpStatus.NOT_ACCEPTABLE)) {
        }
        Report r3 = new Report(103, 1, rts2);
        while (reportLimit >= 1 && !createReport(r3, HttpStatus.NOT_ACCEPTABLE)) {
        }
        if (reportLimit == 0) {
            return;
        }
        boolean r1r = createReport(r1, HttpStatus.CREATED);
        if (!r1r) {
            int rId = 10000;
            while (reportLimit >= 1) {
                Report r = new Report(rId++, 2, rts1);
                if (createReport(r, HttpStatus.CREATED)) {
                    break;
                }
            }
        }
        while (reportLimit >= 1 && !createReport(r1, HttpStatus.NOT_ACCEPTABLE)) {
        }

        TableQuery<Integer> insert = new TableQuery<>("TestReport1", 2000, "insert into TestReport1 values(1, 'n1', true), (2, 'n2', false), (3, 'n3', null);");
        createTableQuery(insert, HttpStatus.CREATED);
        executeTableQuery(2000, HttpStatus.CREATED);

        while (reportLimit >= 1 && !getReport(r1.getReportId(), "{\"reportId\":101,\"tableAmount\":2,\"tables\":[{\"tableName\":\"TestReport1\",\"columns\":[{\"title\":\"ID\",\"type\":\"INT4\",\"size\":\"3\"},{\"title\":\"name\",\"type\":\"varChar\",\"size\":\"3\"},{\"title\":\"bool\",\"type\":\"boolean\",\"size\":\"2\"}]},{\"tableName\":\"TestReport2\",\"columns\":[{\"title\":\"ID\",\"type\":\"INT4\",\"size\":\"0\"},{\"title\":\"name\",\"type\":\"varChar\",\"size\":\"0\"},{\"title\":\"bool\",\"type\":\"boolean\",\"size\":\"0\"}]}]}", HttpStatus.CREATED)) {
        }

        SingleQuery<Integer> alter = new SingleQuery<>(2001, "alter table TestReport1 alter column name rename to renamed; create table tr3(id int4 primary key,num int4)");
        createSingleQuery(alter, HttpStatus.CREATED);
        executeSingleQuery(alter.getQueryId(), HttpStatus.CREATED);

        while (reportLimit >= 1 && !getReport(r1.getReportId(), null, HttpStatus.NOT_ACCEPTABLE)) {
        }

        if (reportLimit == 0) {
            return;
        }
        ReportColumn rc7 = new ReportColumn("id", "int4", null);
        ReportColumn rc8 = new ReportColumn("num", "int4", null);
        List<ReportColumn> rcs3 = new ArrayList<>(2);
        rcs3.add(rc7);
        rcs3.add(rc8);
        ReportTable rt4 = new ReportTable("tr3", rcs3);
        List<ReportTable> rts3 = new ArrayList<>(1);
        rts3.add(rt4);
        Report r4 = new Report(104, 1, rts3);
        boolean r4r = createReport(r4, HttpStatus.CREATED);
        if (!r4r) {
            int rId = 10100;
            while (reportLimit >= 1) {
                Report r = new Report(rId++, 1, rts3);
                if (createReport(r, HttpStatus.CREATED)) {
                    break;
                }
            }
        }
    }

    private boolean createTable(Table t, HttpStatus es) {
        tableLimit--;
        String resultUrl = ep + Table.ADD_RESULT;
        ResultQuery rq = new ResultQuery(es);
        createResult(resultUrl, rq);
        String url = ep + String.format(Table.ADD, rq.getResultId());
        ResponseEntity<String> re = client.postForEntity(url, t, String.class);
        if (re.getStatusCode() != es) {
//            log.error("{} - {} - {} - {} - {}", url, es, re.getStatusCode(), t, re.getBody());
            return false;
        }
        return true;
    }

    private boolean dropTable(String tableName, HttpStatus es) {
        tableLimit--;
        String resultUrl = ep + Table.DELETE_RESULT;
        ResultQuery rq = new ResultQuery(es);
        createResult(resultUrl, rq);
        String url = ep + String.format(Table.DELETE, tableName, rq.getResultId());
        HttpStatus st = client.exchange(url, HttpMethod.DELETE, null, Void.class).getStatusCode();
        if (st != es) {
//            log.error("{} - {} - {}", url, es, st);
            return false;
        }
        return true;
    }

    private boolean getTable(String tableName, String eb, HttpStatus es) {
        tableLimit--;
        String resultUrl = ep + Table.GET_BY_NAME_RESULT;
        JsonNode jn = null;
        if (eb != null) {
            try {
                jn = om.readTree(eb);
            } catch (Exception e) {
            }
        }
        TableResultQuery<JsonNode> rq = new TableResultQuery<>(jn, es);
        createResult(resultUrl, rq);
        String url = ep + String.format(Table.GET_BY_NAME, tableName, rq.getResultId());
        ResponseEntity<String> re = client.exchange(url, HttpMethod.GET, null, String.class);
        try {
            if (re.getStatusCode() != es || (eb == null && re.getBody() != null) || (eb != null && re.getBody() == null)
                    || (eb != null && re.getBody() != null && !Objects.equals(om.readTree(eb), om.readTree(re.getBody())))) {
//                log.error("{} - {} - {} - {} - {}", url, es, re.getStatusCode(), eb, re.getBody());
                return false;
            }
        } catch (Exception e) {
//            log.error("{} - {} - {} - {} - {}", url, es, re.getStatusCode(), eb, re.getBody());
        }
        return true;
    }

    private boolean createTableQuery(TableQuery<?> tq, HttpStatus es) {
        tableQueryLimit--;
        String resultUrl = ep + TableQuery.ADD_RESULT;
        TableQueryResultQuery<TableQuery> rq = new TableQueryResultQuery<>(tq, es);
        createResult(resultUrl, rq, HttpStatus.CREATED);
        String url = ep + String.format(TableQuery.ADD, rq.getResultId());
        ResponseEntity<String> re = client.postForEntity(url, tq, String.class);
        if (re.getStatusCode() != es) {
//            log.error("{} - {} - {} - {} - {}", url, es, re.getStatusCode(), tq, re.getBody());
            return false;
        }
        return true;
    }

    private boolean modifyTableQuery(TableQuery<?> tq, HttpStatus es) {
        tableQueryLimit--;
        String resultUrl = ep + TableQuery.MODIFY_RESULT;
        ResultQuery rq = new ResultQuery(es);
        createResult(resultUrl, rq);
        String url = ep + String.format(TableQuery.MODIFY, rq.getResultId());
        ResponseEntity<String> re = client.exchange(url, HttpMethod.PUT, new HttpEntity<>(tq), String.class);
        if (re.getStatusCode() != es) {
//            log.error("{} - {} - {} - {} - {}", url, es, re.getStatusCode(), tq, re.getBody());
            return false;
        }
        return true;
    }

    private boolean deleteTableQuery(int id, HttpStatus es) {
        tableQueryLimit--;
        String resultUrl = ep + TableQuery.DELETE_RESULT;
        ResultQuery rq = new ResultQuery(es);
        createResult(resultUrl, rq, HttpStatus.CREATED);
        String url = ep + String.format(TableQuery.DELETE, id, rq.getResultId());
        HttpStatus st = client.exchange(url, HttpMethod.DELETE, null, Void.class).getStatusCode();
        if (st != es) {
//            log.error("{} - {} - {}", url, es, st);
            return false;
        }
        return true;
    }

    private boolean executeTableQuery(int id, HttpStatus es) {
        tableQueryLimit--;
        String resultUrl = ep + TableQuery.EXECUTE_RESULT;
        ResultQuery rq = new ResultQuery(es);
        createResult(resultUrl, rq, HttpStatus.CREATED);
        String url = ep + String.format(TableQuery.EXECUTE, id, rq.getResultId());
        ResponseEntity<Void> re = client.getForEntity(url, Void.class);
        if (re.getStatusCode() != es) {
//            log.error("{} - {} - {}", url, es, re.getStatusCode());
            return false;
        }
        return true;
    }

    private boolean getTableQuery(int queryId, String eb, HttpStatus es) {
        tableQueryLimit--;
        String resultUrl = ep + TableQuery.GET_BY_ID_RESULT;
        JsonNode jn = null;
        if (eb != null) {
            try {
                jn = om.readTree(eb);
            } catch (Exception e) {
            }
        }
        TableQueryResultQuery<JsonNode> rq = new TableQueryResultQuery<>(jn, es);
        createResult(resultUrl, rq, HttpStatus.CREATED);
        String url = ep + String.format(TableQuery.GET_BY_ID, queryId, rq.getResultId());
        ResponseEntity<String> re = client.getForEntity(url, String.class);
        try {
            if (re.getStatusCode() != es || (eb == null && re.getBody() != null) || (eb != null && re.getBody() == null)
                    || (eb != null && re.getBody() != null && !Objects.equals(om.readTree(eb), om.readTree(re.getBody())))) {
//                log.error("{} - {} - {} - {} - {}", url, es, re.getStatusCode(), eb, re.getBody());
                return false;
            }
        } catch (Exception e) {
//            log.error("{} - {} - {} - {} - {}", url, es, re.getStatusCode(), eb, re.getBody());
        }
        return true;
    }

    private boolean getTableQueries(String tableName, String eb, HttpStatus es) {
        tableQueryLimit--;
        String resultUrl = ep + TableQuery.GET_BY_TABLE_NAME_RESULT;
        JsonNode jn = null;
        if (eb != null) {
            try {
                jn = om.readTree(eb);
            } catch (Exception e) {
            }
        }
        TableQueriesResultQuery rq = new TableQueriesResultQuery(jn, es);
        createResult(resultUrl, rq, HttpStatus.CREATED);
        String url = ep + String.format(TableQuery.GET_BY_TABLE_NAME, tableName, rq.getResultId());
        ResponseEntity<String> re = client.getForEntity(url, String.class);
        try {
            if (re.getStatusCode() != es || (eb == null && re.getBody() != null) || (eb != null && re.getBody() == null)
                    || (eb != null && re.getBody() != null && !Objects.equals(om.readTree(eb), om.readTree(re.getBody())))) {
//                log.error("{} - {} - {} - {} - {}", url, es, re.getStatusCode(), eb, re.getBody());
                return false;
            }
        } catch (Exception e) {
//            log.error("{} - {} - {} - {} - {}", url, es, re.getStatusCode(), eb, re.getBody());
        }
        return true;
    }

    private boolean createSingleQuery(SingleQuery<?> sq, HttpStatus es) {
        singleQueryLimit--;
        String resultUrl = ep + SingleQuery.ADD_RESULT;
        ResultQuery rq = new ResultQuery(es);
        createResult(resultUrl, rq, HttpStatus.CREATED);
        String url = ep + String.format(SingleQuery.ADD, rq.getResultId());
        ResponseEntity<String> re = client.postForEntity(url, sq, String.class);
        if (re.getStatusCode() != es) {
//            log.error("{} - {} - {} - {} - {}", url, es, re.getStatusCode(), sq, re.getBody());
            return false;
        }
        return true;
    }

    private boolean modifySingleQuery(SingleQuery<?> sq, HttpStatus es) {
        singleQueryLimit--;
        String resultUrl = ep + SingleQuery.MODIFY_RESULT;
        ResultQuery rq = new ResultQuery(es);
        createResult(resultUrl, rq);
        String url = ep + String.format(SingleQuery.MODIFY, rq.getResultId());
        ResponseEntity<String> re = client.exchange(url, HttpMethod.PUT, new HttpEntity<>(sq), String.class);
        if (re.getStatusCode() != es) {
            log.error("{} - {} - {} - {} - {}", url, es, re.getStatusCode(), sq, re.getBody());
            return false;
        }
        return true;
    }

    private boolean deleteSingleQuery(int id, HttpStatus es) {
        singleQueryLimit--;
        String resultUrl = ep + SingleQuery.DELETE_RESULT;
        ResultQuery rq = new ResultQuery(es);
        createResult(resultUrl, rq);
        String url = ep + String.format(SingleQuery.DELETE, id, rq.getResultId());
        HttpStatus st = client.exchange(url, HttpMethod.DELETE, null, Void.class).getStatusCode();
        if (st != es) {
//            log.error("{} - {} - {}", url, es, st);
            return false;
        }
        return true;
    }

    private boolean executeSingleQuery(int id, HttpStatus es) {
        singleQueryLimit--;
        String resultUrl = ep + SingleQuery.EXECUTE_RESULT;
        ResultQuery rq = new ResultQuery(es);
        createResult(resultUrl, rq);
        String url = ep + String.format(SingleQuery.EXECUTE, id, rq.getResultId());
        ResponseEntity<Void> re = client.getForEntity(url, Void.class);
        if (re.getStatusCode() != es) {
//            log.error("{} - {} - {}", url, es, re.getStatusCode());
            return false;
        }
        return true;
    }

    private boolean getSingleQuery(SingleQuery<Integer> sq, String eb, HttpStatus es) {
        singleQueryLimit--;
        String resultUrl = ep + SingleQuery.GET_BY_ID_RESULT;
        SingleQueryResultQuery rq = new SingleQueryResultQuery(sq.getQueryId(), sq.getQuery(), es);
        createResult(resultUrl, rq);
        String url = ep + String.format(SingleQuery.GET_BY_ID, sq.getQueryId(), rq.getResultId());
        ResponseEntity<String> re = client.getForEntity(url, String.class);
        try {
            if (re.getStatusCode() != es || (eb == null && re.getBody() != null) || (eb != null && re.getBody() == null)
                    || (eb != null && re.getBody() != null && !Objects.equals(om.readTree(eb), om.readTree(re.getBody())))) {
//                log.error("{} - {} - {} - {} - {}", url, es, re.getStatusCode(), eb, re.getBody());
                return false;
            }
        } catch (Exception e) {
//            log.error("{} - {} - {} - {} - {}", url, es, re.getStatusCode(), eb, re.getBody());
        }
        return true;
    }

    private boolean createReport(Report r, HttpStatus es) {
        reportLimit--;
        String resultUrl = ep + Report.ADD_RESULT;
        ResultQuery rq = new ResultQuery(es);
        createResult(resultUrl, rq);
        String url = ep + String.format(Report.ADD, rq.getResultId());
        ResponseEntity<Void> re = client.postForEntity(url, r, Void.class);
        if (re.getStatusCode() != es) {
//            log.error("{} - {} - {}", url, es, re.getStatusCode());
            return false;
        }
        return true;
    }

    private boolean getReport(int id, String eb, HttpStatus es) {
        reportLimit--;
        String resultUrl = ep + Report.GET_BY_ID_RESULT;
        JsonNode jn = null;
        if (eb != null) {
            try {
                jn = om.readTree(eb);
            } catch (Exception e) {
            }
        }
        ReportResultQuery rq = new ReportResultQuery(jn, es);
        createResult(resultUrl, rq);
        String url = ep + String.format(Report.GET_BY_ID, id, rq.getResultId());
        ResponseEntity<String> re = client.getForEntity(url, String.class);
        try {
            if (re.getStatusCode() != es || (eb == null && re.getBody() != null) || (eb != null && re.getBody() == null)
                    || (eb != null && re.getBody() != null && !Objects.equals(om.readTree(eb), om.readTree(re.getBody())))) {
//                log.error("{} - {} - {} - {} - {}", url, es, re.getStatusCode(), eb, re.getBody());
                return false;
            }
        } catch (Exception e) {
//            log.error("{} - {} - {} - {} - {}", url, es, re.getStatusCode(), eb, re.getBody());
        }
        return true;
    }

    private void createResult(String url, ResultQuery rq) {
        createResult(url, rq, HttpStatus.ACCEPTED);
    }

    private void createResult(String url, ResultQuery rq, HttpStatus rs) {
        if (!ep.contains("0.0.0.0")) {
            HttpStatus rst = client.postForEntity(url, rq, Void.class).getStatusCode();
            if (rst != rs) {
                log.error("{} - {} - {} - {}", url, rq, rst, rs);
            }
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

    private static class NoOpResponseErrorHandler extends DefaultResponseErrorHandler {

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
        }

    }
}
