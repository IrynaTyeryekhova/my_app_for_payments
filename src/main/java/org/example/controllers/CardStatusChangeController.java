package org.example.controllers;

import org.example.dbServices.DBCardService;
import org.example.models.CardStatusInfoService;
import org.example.models.CardStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/cardStatusChange")
public class CardStatusChangeController {
    DBCardService cardService;
    @Autowired
    CardStatusChangeController(DBCardService cardService) {
        this.cardService = cardService;
    }
    @GetMapping()
    public String cardStatusChange(HttpServletRequest request) throws ServletException, IOException {
        return new CardStatusInfoService().cardStatusInfoMake(request);}

    @PostMapping()
    public String cardStatusChangePost(HttpServletRequest request) throws ServletException, IOException {
        return new CardStatusInfoService().cardStatusInfoMake(request);}

    @PatchMapping()
    public String cardStatusChangePatch(HttpServletRequest request) throws ServletException, IOException {
        return new CardStatusService().cardStatusMake(request, cardService);}

}
