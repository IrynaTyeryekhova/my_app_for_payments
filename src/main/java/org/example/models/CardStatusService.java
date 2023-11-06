package org.example.models;

import org.example.dbServices.DBCardService;
import org.example.dbServices.Statuses;
import org.example.entities.CardAccount;
import org.example.services.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CardStatusService {
//    public static final Logger LOG=Logger.getLogger(CardStatusService.class.getName());

    public String cardStatusMake(HttpServletRequest request, DBCardService cardService) {
//        LOG.info("CardStatusService is starting");
        HttpSession session = request.getSession();
        Service service = new Service();

//        String change = service.getParameter(session, request,"change");
//        String adminChange = service.getParameter(session, request,"admin");
//        String cardStatusSend = service.getParameter(session, request, "cardStatusSend");
//        String cardEmailChangeStatus = service.getParameter(session, request, "cardEmailChangeStatus");

//        if (adminChange.equals("true") && change.equals("request")) statusChangeRequestAdmin(cardStatusSend, request);
//        else if (adminChange.equals("true") && change.equals("all")) statusChangeAdmin(cardStatusSend, request);
//        else if (adminChange.equals("true") && change.equals("client")) clientChangeAdmin(cardEmailChangeStatus, language, req, resp);
//        else {
        CardAccount cardAccount = (CardAccount)session.getAttribute("cardInfo");
        String cardNumber = cardAccount.getNumber();
        String actualStatus = cardAccount.getStatusCard();
        String password = service.getParameter(session, request, "passwordCard");
        int updateResult;

            if (actualStatus.equals(Statuses.UNBLOCK_STRING)) {
                if(cardAccount.getPassword().equals(password)) {
                    updateResult = cardService.cardStatusChange(cardNumber, Statuses.BLOCK);
                    if (updateResult == 0) return "redirect:/infoMessage?message=updateCardStatusErr";
                    else return "redirect:/infoMessage?message=status";
                } else return "redirect:/infoMessage?&message=updateCardStatusErrPassword";
            } else {
                if(cardAccount.getPassword().equals(password)) {
                    int getRequestAdmin = cardService.getRequestAdmin(cardNumber, Statuses.NEW);
                    if (getRequestAdmin == 0) {
                        updateResult = cardService.insertRequestAdmin(cardNumber, Statuses.BLOCK);
                        if (updateResult == 0) return "redirect:/infoMessage?message=sendRequestAdminErr";
                        else return "redirect:/infoMessage?message=sendRequestAdmin";
                    } else return "redirect:/infoMessage?message=duplicateSendRequestAdminErr";
                }else return "redirect:/infoMessage?message=sendRequestAdminErrPassword";
            }
    }
    
//    private HttpServletRequest statusChangeAdmin(String cardNumber, HttpServletRequest request) throws ServletException, IOException {
//        DBCardService cardService = DBCardService.getInstance();
//        CardAccount cardAccount = cardService.getCardInfo(cardNumber);
//        String status = "";
//        if (cardAccount != null) { status = cardAccount.getStatus();}
//
//        if(cardAccount == null) request.getRequestDispatcher("/infoMessage?lang=" + language + "&message=error").forward(req,resp);
//        else if ((cardAccount.getValidityPeriod()).compareTo(LocalDate.now().toString()) < 0 && status.equals("block")) {
//            request.getRequestDispatcher("/infoMessage?lang=" + language + "&message=sendRequestAdminErrValidityPeriod&adminPage=true").forward(req, resp);
//        } else {
//            int updateResult = 0;
//
//            if(status.equals("block")) updateResult = cardService.updateCardStatus(cardNumber, Statuses.UNBLOCK, "false");
//            else if(status.equals("unblock")) updateResult = cardService.updateCardStatus(cardNumber, Statuses.BLOCK, "false");
//
//            if (updateResult == 1) req.getRequestDispatcher("/infoMessage?lang=" + language + "&message=status&adminPage=true").forward(req, resp);
//            else req.getRequestDispatcher("/infoMessage?lang=" + language + "&message=sendRequestAdminErr&adminPage=true").forward(req, resp);
//        }
//       return req;
//    }
//    private HttpServletRequest clientChangeAdmin(String email, String language, HttpServletRequest req, HttpServletResponse resp) throws DBException, ServletException, IOException {
//        DBClientService clientService = DBClientService.getInstance();
//        Client client = clientService.getClientInfo(email);
//
//        int updateResult = 0;
//        if(client != null) {
//            String status = client.getStatus();
//
//            if (status.equals(Statuses.BLOCK_STRING)) updateResult = clientService.updateClientStatus(email, Statuses.UNBLOCK);
//            else if (status.equals(Statuses.UNBLOCK_STRING)) updateResult = clientService.updateClientStatus(email, Statuses.BLOCK);
//
//            if (updateResult == 1) req.getRequestDispatcher("/infoMessage?lang=" + language + "&message=status&adminPage=true").forward(req, resp);
//        }
//        if(client == null || updateResult == 0)  req.getRequestDispatcher("/infoMessage?lang=" + language + "&message=sendRequestAdminErr&adminPage=true").forward(req, resp);
//        return req;
//    }
//    private HttpServletRequest statusChangeClient(String actualStatus, HttpSession session, HttpServletRequest request) throws ServletException, IOException {
//        Service service = new Service();
//        String cardNumber = service.getParameter(session, req, "cardNumber");
//        String password = service.getParameter(session, req, "passwordCard");
//        int updateResult;
//        DBCardService cardService = DBCardService.getInstance();
//
//        if (actualStatus.equals("unblock")) {
//            updateResult = cardService.updateCardStatus(cardNumber, Statuses.BLOCK, password);
//            if (updateResult == 0) req.getRequestDispatcher("/infoMessage?lang=" + language + "&message=updateCardStatusErr").forward(req, resp);
//            else if (updateResult == 2) req.getRequestDispatcher("/infoMessage?lang=" + language + "&message=updateCardStatusErrPassword").forward(req, resp);
//        } else {
//            int getRequestAdmin = cardService.getRequestAdmin(cardNumber, Statuses.NEW);
//            if (getRequestAdmin == 0) {
//                updateResult = cardService.insertRequestAdmin(cardNumber, Statuses.BLOCK, password);
//                if (updateResult == 0) req.getRequestDispatcher("/infoMessage?lang=" + language + "&message=sendRequestAdminErr").forward(req, resp);
//                else if (updateResult == 2) req.getRequestDispatcher("/infoMessage?lang=" + language + "&message=sendRequestAdminErrPassword").forward(req, resp);
//            } else if (getRequestAdmin == -1) req.getRequestDispatcher("/infoMessage?lang=" + language + "&message=error").forward(req, resp);
//            else {
//                if (cardService.equalsCardPassword(cardNumber, password)) req.getRequestDispatcher("/infoMessage?lang=" + language + "&message=duplicateSendRequestAdminErr").forward(req, resp);
//                else req.getRequestDispatcher("/infoMessage?lang=" + language + "&message=sendRequestAdminErrPassword").forward(req, resp);
//            }
//        } return req;
//    }
}



