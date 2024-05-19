package com.coelho.brasileiro.expensetrack.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/info")
public class VersionController {

    @Value("${version}")
    private String version;

    @GetMapping("/version")
    public String getVersion() {
        return version;
    }
}
