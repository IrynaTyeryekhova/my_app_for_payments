package org.example.controllers;

import org.example.dbServices.DBPaymentService;
import org.example.models.PaymentPreparedService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Component
@RequestMapping("/paymentPrepared")
public class PaymentPreparedController{
    DBPaymentService paymentsService;

    PaymentPreparedController(DBPaymentService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping
    public String paymentPreparedGet(HttpServletRequest request) {
        return new PaymentPreparedService().paymentPreparedMake(request, paymentsService);
    }

    @PostMapping
    public String paymentPreparedPost(HttpServletRequest request) {
        return new PaymentPreparedService().paymentPreparedMake(request, paymentsService);
    }
 }

