package org.example.controllers;

import org.example.dbServices.DBCardService;
import org.example.dbServices.DBPaymentService;
import org.example.entities.Payment;
import org.example.models.PaymentCreateService;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@Component
@RequestMapping("/paymentCreate")
public class PaymentCreateController{
    DBCardService cardService;
    DBPaymentService paymentsService;

    PaymentCreateController(DBCardService cardService, DBPaymentService paymentsService) {
        this.cardService = cardService;
        this.paymentsService = paymentsService;
    }

    @PostMapping()
    public String paymentCreateGet(@ModelAttribute("payment") @Valid Payment payment, BindingResult bindingResult, HttpServletRequest request) throws ServletException, IOException {
        if(bindingResult.hasErrors()) return "paymentAddInfo";
        return new PaymentCreateService().paymentAddMake(request, cardService, paymentsService, payment);
    }

}

