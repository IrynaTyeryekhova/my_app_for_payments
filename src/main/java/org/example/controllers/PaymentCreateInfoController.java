package org.example.controllers;

import org.example.dbServices.DBCardService;
import org.example.models.PaymentCreateInfoService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequestMapping("/paymentCreateInfo")
public class PaymentCreateInfoController{
        DBCardService cardService;
        PaymentCreateInfoController (DBCardService cardService){
            this.cardService = cardService;
        }
        @GetMapping
        public String PaymentCreateInfoGet(HttpServletRequest request) throws ServletException, IOException {
            return new PaymentCreateInfoService().paymentAddInfoMake(request, cardService);

        }

    }
