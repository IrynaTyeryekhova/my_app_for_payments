package org.example.models;

import org.example.entities.CardAccount;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CardStatusInfoService {
//    public static final Logger LOG=Logger.getLogger(CardStatusInfoService.class.getName());

    public String cardStatusInfoMake(HttpServletRequest request) {
//            LOG.info("CardStatusInfoService is starting");
            HttpSession session = request.getSession();
            CardAccount cardAccount = (CardAccount) session.getAttribute("cardInfo");
            String statusChangeMessage = "";
            String button = "";
            if(cardAccount.getStatusCard().equals("unblock")) {
                    statusChangeMessage = "message.statusChangeMessageBlock";
                    button = "button.block";
            } else {
                    statusChangeMessage = "message.statusChangeMessageUnblock";
                    button = "button.unblock";
            }

           session.setAttribute("StatusChangeMessage", statusChangeMessage);
           session.setAttribute("ButtonChangeStatus", button);

           return "cardStatusChange";
        }
    }
