package org.example.models;

import org.example.dbServices.DBCardService;
import org.example.entities.CardAccount;
import org.example.services.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CardBalanceService {
//    public static final Logger LOG=Logger.getLogger(CardBalanceService.class.getName());

        public String cardBalanceMake(HttpServletRequest request, DBCardService cardService) {
//            LOG.info("CardBalanceService is starting");
            HttpSession session = request.getSession();
            Service service = new Service();
            CardAccount cardAccount = (CardAccount) session.getAttribute("cardInfo");
            String newBalance = service.getParameter(session, request, "newSum");
            String passwordCard = service.getParameter(session, request, "passwordCard");
//            int balanceChange;
//            if (action.equals("balanceChange")){
            if (cardAccount.getPassword().equals(passwordCard)) {
                int balanceChange = cardService.cardBalanceChange(cardAccount.getNumber(), Double.valueOf(newBalance));
                if (balanceChange == 0) return "redirect:/infoMessage?message=cardBalanceChangeErr";
                else return "redirect:/infoMessage?message=balance";
            } else return "redirect:/infoMessage?message=cardBalanceChangeErrPassword";
        }
}
