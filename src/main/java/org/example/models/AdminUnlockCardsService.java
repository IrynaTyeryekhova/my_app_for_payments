package org.example.models;

import org.example.dbServices.DBCardService;
import org.example.dbServices.Statuses;
import org.example.entities.CardAccount;
import org.example.services.PaginationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminUnlockCardsService {
    public String unlockCard (HttpServletRequest request, DBCardService cardService) {
//            LOG.info("AdminCardMenuService is starting");
        HttpSession session = request.getSession();

        PaginationService paginationService = new PaginationService();
        paginationService.parametersSortMake(request, "listParamSortCards","selectParamSortCards","number", "balance", "validityPeriod");

        int countCardsForRequestAdmin = cardService.getCountAllCardForRequestAdmin(Statuses.NEW);
        if (countCardsForRequestAdmin != -1) paginationService.paginationMake(request, countCardsForRequestAdmin);
        else return "/infoMessage?message=error";

        Integer limit = Integer.valueOf(String.valueOf(session.getAttribute("selectCountShow")));
        Integer numberPage = Integer.valueOf(String.valueOf(session.getAttribute("numberPage")));

        int offSet = (numberPage * limit) - limit;

        List<CardAccount> cardsForRequestAdmin = cardService.findAllCardForRequestAdminWithLimit(Statuses.NEW, "cards." + (String) session.getAttribute("selectParamSortCards"), (String) session.getAttribute("selectParamSortType"), limit, offSet);

        session.setAttribute("cardsForRequestAdmin", cardsForRequestAdmin);

        if(cardsForRequestAdmin == null) return "/infoMessage?message=error";
        else return "adminCardsStatusChange";
    }
}
