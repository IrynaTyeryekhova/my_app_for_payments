package org.example.models;

import org.example.dbServices.DBCardService;
import org.example.entities.CardAccount;
import org.example.services.PaginationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminBlockUnblockCardsService {
    public String blockUnblockCard(HttpServletRequest request, DBCardService cardService) {
//            LOG.info("AdminCardMenuService is starting");
        HttpSession session = request.getSession();

        PaginationService paginationService = new PaginationService();
        paginationService.parametersSortMake(request, "listParamSortCards","selectParamSortCards","number", "balance", "validityPeriod");

        int findCountAllCard = cardService.getCountAllCard();
        if(findCountAllCard != -1) paginationService.paginationMake(request, findCountAllCard);
        else return "redirect:/infoMessage?message=error";

        Integer limit = Integer.valueOf(String.valueOf(session.getAttribute("selectCountShow")));
        Integer numberPage = Integer.valueOf(String.valueOf(session.getAttribute("numberPage")));

        int offSet = (numberPage * limit) - limit;

        List<CardAccount> cards = cardService.findAllCardWithLimit((String) session.getAttribute("selectParamSortCards"), (String) session.getAttribute("selectParamSortType"), limit, offSet);

        session.setAttribute("cards", cards);

        if(cards == null) return "redirect:/infoMessage?message=error";
        return "adminAllCardsStatusChange";
    }
}
