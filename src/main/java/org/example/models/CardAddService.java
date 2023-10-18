package org.example.models;

import org.example.dbServices.DBCardService;
import org.example.entities.CardAccount;
import org.example.services.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CardAddService {
//    public static final Logger LOG=Logger.getLogger(CardAddService.class.getName());
        public String cardAdd(HttpServletRequest request, DBCardService dbCardService, CardAccount cardAccount) {
//            LOG.info("CardAddService is starting");
            HttpSession session = request.getSession();
            String email = new Service().getParameter(session, request, "ClientEMail");

            int insertResult = dbCardService.insertCard(cardAccount, email);
            if (insertResult == 0) return "redirect:/infoMessage?message=insertCardErr";
            return "redirect:/infoMessage?message=card";
        }
    }

