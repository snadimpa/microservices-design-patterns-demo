package com.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fb")
public class FallbackController {

    @PostMapping(value = "/command")
    public ResponseEntity<String> commandFallback(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("We are facing a problem. Command Service is not available, Please contact helpdesk");
    }

    @GetMapping(value = "/query")
    public ResponseEntity<String>  queryFallback(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("We are facing a problem. Query Service is not available, Please contact helpdesk");
    }
}
