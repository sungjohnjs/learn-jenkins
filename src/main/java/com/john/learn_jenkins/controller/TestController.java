package com.john.learn_jenkins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "")
@Slf4j
public class TestController {

    @GetMapping("")
    public String getTest() {
        log.info("MASUK TEST CONTROLLER");

        return "Halo Jenkins 06";
    }
}
