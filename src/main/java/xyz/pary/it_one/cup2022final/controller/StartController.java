package xyz.pary.it_one.cup2022final.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.pary.it_one.cup2022final.service.CheckerService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StartController {

    private final CheckerService checkerService;

    @GetMapping("/start")
    public ResponseEntity<Void> start() {
        checkerService.check();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
