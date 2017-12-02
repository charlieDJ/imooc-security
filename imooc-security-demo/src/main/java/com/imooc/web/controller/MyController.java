package com.imooc.web.controller;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/test")
public class MyController {

    @GetMapping("/length")
    public ResponseEntity<String> testMax(@Length(min = 6, message = "字符太短") String username) {
        return new ResponseEntity<String>("字符长度适合", HttpStatus.OK);
    }
}
