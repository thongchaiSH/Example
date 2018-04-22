package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping(value = { "/index"})
    public String homePage() {
        return "demos/example";
    }
}
