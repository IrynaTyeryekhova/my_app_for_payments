package org.example.models;

import org.example.dbServices.DBCardService;
import org.example.dbServices.Statuses;
import org.example.entities.CardAccount;
import org.example.services.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PaymentCreateInfoService {
//    public static final Logger LOG=Logger.getLogger(PaymentCreateInfoService.class.getName());
    public String paymentAddInfoMake(HttpServletRequest request, DBCardService cardService) {
//        LOG.info("PaymentCreateInfoService is starting");
        HttpSession session = request.getSession();
        Service service = new Service();
        String eMail = service.getParameter(session, request,"ClientEMail");

        List<CardAccount> cards = cardService.findAllCardForClientWithStatus(eMail, Statuses.UNBLOCK);

        if(cards != null) {
            session.setAttribute("selectCardNumber", cards);
            return "paymentAddInfo";
        } else return "/infoMessage?message=error";
     }
}
