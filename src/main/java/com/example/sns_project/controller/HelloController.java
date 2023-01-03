package com.example.sns_project.controller;

import com.example.sns_project.service.AlgorithmService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
@RequiredArgsConstructor
public class HelloController {
    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok().body("정대환");
    }

    private final AlgorithmService algorithmService;

    @ApiOperation(value = "code Test")
    @GetMapping("/hello/{num}")
    public ResponseEntity<Integer> calc(@PathVariable int num){
        return ResponseEntity.ok().body(algorithmService.sumOfDigit(num));
    }

}
