package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class IndexController {
    @GetMapping()
    public String index() { return "index"; }

    @PostMapping()
    public String indexPost() { return "index"; }

    @GetMapping("/indexLogin")
    public String indexLogin() { return "indexLogin"; }

    @PostMapping("/indexLogin")
    public String indexLoginPost() { return "indexLogin"; }

    @GetMapping("/indexRegister")
    public String indexRegister() { return "indexRegister"; }

    @PostMapping("/indexRegister")
    public String indexRegisterPost() { return "indexRegister"; }
}

