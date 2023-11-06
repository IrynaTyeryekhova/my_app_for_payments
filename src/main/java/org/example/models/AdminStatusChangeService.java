package org.example.models;

import org.example.dbServices.DBCardService;
import org.example.dbServices.Statuses;
import org.example.entities.CardAccount;
import org.example.services.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class AdminStatusChangeService {

    public String cardStatusChange(HttpServletRequest request, DBCardService cardService) {
        HttpSession session = request.getSession();
        Service service = new Service();
        String cardNumber =  service.getParameter(session, request,"card");
        CardAccount cardAccount = cardService.getCardInfo(cardNumber);
        if (cardAccount == null) return "redirect:/infoMessage?message=error&adminPage=true";
        else if ((cardAccount.getValidityPeriod()).compareTo(LocalDate.now().toString()) < 0) {
            return "redirect:/infoMessage?message=sendRequestAdminErrValidityPeriod&adminPage=true";
        } else {
            int updateResult = cardService.cardStatusChange(cardAccount.getNumber(), Statuses.UNBLOCK);
            int sendRequest = cardService.requestAdminChange(cardAccount.getNumber(), Statuses.DONE);
            System.out.println(updateResult);
            System.out.println(sendRequest);
            if (updateResult == 1 && sendRequest == 1) return "redirect:/infoMessage?message=status&adminPage=true";
            else return "redirect:/infoMessage?message=sendRequestAdminErr&adminPage=true";
        }
    }
}

