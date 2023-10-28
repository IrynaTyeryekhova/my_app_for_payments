package org.example.controllers;

import org.example.dbServices.DBCardService;
import org.example.dbServices.DBPaymentService;
import org.example.models.PaymentSendService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequestMapping("/paymentSend")
public class PaymentSendController{
    DBPaymentService paymentsService;
    DBCardService cardService;

    PaymentSendController(DBPaymentService paymentsService, DBCardService cardService) {
        this.paymentsService = paymentsService;
        this.cardService = cardService;
    }

    @PostMapping
    public String paymentSendMakePost(HttpServletRequest request) throws ServletException, IOException {
            return new PaymentSendService().paymentSendMake(request, paymentsService, cardService);
    }
}
