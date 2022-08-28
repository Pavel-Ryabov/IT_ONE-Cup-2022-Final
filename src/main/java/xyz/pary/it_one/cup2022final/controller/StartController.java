package xyz.pary.it_one.cup2022final.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.pary.it_one.cup2022final.model.ResultQuery;
import xyz.pary.it_one.cup2022final.service.CheckerService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class StartController {

    private final CheckerService checkerService;
    private int counter = 0;

    @GetMapping("/start")
    public ResponseEntity<Void> start() {
        counter++;
        log.error("start {} - {}", counter, ResultQuery.getResultIdCounter());
//        if (counter == 4)
        checkerService.check();
        log.error("end {} - {}", counter, ResultQuery.getResultIdCounter());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
