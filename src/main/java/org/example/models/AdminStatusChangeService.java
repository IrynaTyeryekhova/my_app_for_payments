package org.example.models;

import org.example.dbServices.DBCardService;
import org.example.dbServices.Statuses;
import org.example.entities.CardAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class AdminStatusChangeService {

    public String cardStatusChange(HttpServletRequest request, DBCardService cardService) {
        HttpSession session = request.getSession();
        CardAccount cardAccount = (CardAccount)session.getAttribute("cardAdminChangeStatus");

        if (cardAccount == null) return "redirect:/infoMessage?message=error";
        else if ((cardAccount.getValidityPeriod()).compareTo(LocalDate.now().toString()) < 0) {
            return "redirect:/infoMessage?message=sendRequestAdminErrValidityPeriod&adminPage=true";
        } else {
            int updateResult = cardService.cardStatusChange(cardAccount.getNumber(), Statuses.UNBLOCK);
            int sendRequest = cardService.requestAdminChange(cardAccount.getId(), Statuses.DONE);

            if (updateResult == 1 && sendRequest == 1) return "redirect:/infoMessage?message=status&adminPage=true";
            else return "redirect:/infoMessage?message=sendRequestAdminErr&adminPage=true";
        }
    }
}

