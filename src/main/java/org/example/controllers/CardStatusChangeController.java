package org.example.controllers;

import org.example.models.CardStatusInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/cardStatusChange")
public class CardStatusChangeController {
    @GetMapping()
    public String cardStatusChange(HttpServletRequest request) throws ServletException, IOException {
        return  new CardStatusInfoService().cardStatusInfoMake(request);}

    @PostMapping()
    public String cardStatusChangePost(HttpServletRequest request) throws ServletException, IOException {
        return  new CardStatusInfoService().cardStatusInfoMake(request);}

}
