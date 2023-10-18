package org.example.controllers;

import org.example.dbServices.DBCardService;
import org.example.entities.Payment;
import org.example.models.PaymentCreateInfoService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Component
@RequestMapping("/paymentCreateInfo")
public class PaymentCreateInfoController{
    DBCardService cardService;
    PaymentCreateInfoController (DBCardService cardService){
        this.cardService = cardService;
    }

    @GetMapping("/new")
    public String PaymentCreateInfoNewGet(@ModelAttribute("payment") Payment payment, HttpServletRequest request){
        return new PaymentCreateInfoService().paymentAddInfoMake(request, cardService);
    }

    @PostMapping("/new")
    public String PaymentCreateInfoNewPost(@ModelAttribute("payment") Payment payment, HttpServletRequest request){
        return new PaymentCreateInfoService().paymentAddInfoMake(request, cardService);
    }

}
