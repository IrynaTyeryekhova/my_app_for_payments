package org.example.controllers;

import org.example.dbServices.DBPaymentService;
import org.example.models.PaymentMenuService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Component
@RequestMapping("/paymentMenu")
public class PaymentMenuController {

    DBPaymentService paymentsService;

    PaymentMenuController(DBPaymentService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping
    public String paymentMenuGet(HttpServletRequest req) {
       return new PaymentMenuService().paymentMenuMake(req, paymentsService);
    }
}
