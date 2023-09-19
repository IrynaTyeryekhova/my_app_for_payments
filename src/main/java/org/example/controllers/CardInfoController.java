package org.example.controllers;

import org.example.dbServices.DBCardService;
import org.example.dbServices.DBPaymentService;
import org.example.models.CardInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/cardsInfo")
public class CardInfoController {
    private final DBCardService dbCardService;
    private final DBPaymentService dbPaymentService;

    public CardInfoController(DBCardService dbCardService, DBPaymentService dbPaymentService) {
        this.dbCardService = dbCardService;
        this.dbPaymentService = dbPaymentService;
    }

    @GetMapping
    public String cardInfoGet (HttpServletRequest request) throws ServletException, IOException {
        String responce = new CardInfoService().cardInfoMake(request, dbCardService, dbPaymentService);
        return responce;
    }
}
