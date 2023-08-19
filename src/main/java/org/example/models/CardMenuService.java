package org.example.models;

import org.example.dbServices.DBCardService;
import org.example.entities.CardAccount;
import org.example.services.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CardMenuService {
//    public static final Logger LOG=Logger.getLogger(CardMenuService.class.getName());
    public String cardMenuMake(HttpServletRequest request, DBCardService dbCardService) {
//            LOG.info("CardMenuService/cardMenuMake is starting");
            HttpSession session = request.getSession();
            String parameterSort =  new Service().getParameter(session,request, "actualParamSortCardMenu");
            String response;

            if(parameterSort==null) {
                System.out.println("null");
                parameterSort = "balance";}
        System.out.println(parameterSort);
            session.setAttribute("actualParamSortCardMenu", parameterSort);

            List<String> paramSortList = Arrays.asList("balance", "number", "validity_period");
            session.setAttribute("paramSortListCardMenu", paramSortList);

            List<CardAccount> cardAccounts = dbCardService.findAllCardForClient((String) session.getAttribute("ClientEMail"));
            if (cardAccounts != null) {
               cardAccounts = sortCard(cardAccounts, parameterSort);
               session.setAttribute("cardAccountsForCardMenu", cardAccounts);
               response = "cardsMenu";
            } else response = "redirect:/infoMessage?message=error";

            return response;
        }
        private List<CardAccount> sortCard(List<CardAccount> cardAccounts, String parameterSort) {
            if(parameterSort==null||parameterSort.equals("balance")) {
                cardAccounts= cardAccounts
                        .stream()
                        .sorted(Comparator.comparing(s->s.getBalance()))
                        .collect(Collectors.toList());}
            else if(parameterSort.equals("number")) {
                cardAccounts= cardAccounts
                        .stream()
                        .sorted(Comparator.comparing(s->s.getNumber()))
                        .collect(Collectors.toList());}
            else {
                cardAccounts= cardAccounts
                        .stream()
                        .sorted(Comparator.comparing(s->s.getValidityPeriod()))
                        .collect(Collectors.toList());
            }
            return cardAccounts;
        }

    }




