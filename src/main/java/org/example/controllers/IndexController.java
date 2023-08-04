package org.example.controllers;

import org.example.entities.Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String indexLogin(@ModelAttribute("client") Client client) { return "indexLogin"; }

    @PostMapping("/indexLogin")
    public String indexLoginPost(@ModelAttribute("client") Client client) { return "indexLogin"; }

    @GetMapping("/indexRegister")
    public String indexRegister(@ModelAttribute("client") Client client) { return "indexRegister"; }

    @PostMapping("/indexRegister")
    public String indexRegisterPost(@ModelAttribute("client") Client client) { return "indexRegister"; }
}

