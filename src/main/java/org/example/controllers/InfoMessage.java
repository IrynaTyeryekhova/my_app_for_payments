package org.example.controllers;

import org.example.services.Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/infoMessage")
public class InfoMessage {
    @GetMapping()
    public String createInfoMessage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String response;

        String message = new Service().getParameter(session, request, "message");
        String adminPage = new Service().getParameter(session, request, "adminPage");

        String infoMessage = "";
        String infoMessageMenu = "";

        if(message.equals("error")){
            infoMessage = "message.error";
            infoMessageMenu = "message.menu.error";
        } else if (message.equals("insertCardErr")) {
            infoMessage = "message.insertCardErr";
            infoMessageMenu = "message.menu.addingCard";
        } else if (message.equals("card")) {
            infoMessage = "message.card";
            infoMessageMenu = "message.menu.addingCard";
        } else if (message.equals("balance")) {
            infoMessage = "message.balanceChangedSuccessfully";
            infoMessageMenu = "message.menu.balance";
        } else if (message.equals("cardBalanceChangeErr")) {
            infoMessage = "message.cardBalanceChangeErr";
            infoMessageMenu = "message.menu.balance";
        } else if (message.equals("cardBalanceChangeErrPassword")) {
            infoMessage = "message.cardBalanceChangeErrPassword";
            infoMessageMenu = "message.menu.balance";
        } else if (message.equals("status")) {
            infoMessage = "message.status";
            infoMessageMenu = "message.menu.cardStatus";
        } else if (message.equals("sendRequestAdmin")) {
            infoMessage = "message.sendRequestAdmin";
            infoMessageMenu = "message.menu.cardStatus";
        } else if (message.equals("updateCardStatusErr")) {
            infoMessage = "message.updateCardStatusErr";
            infoMessageMenu = "message.menu.cardStatus";
        } else if (message.equals("updateCardStatusErrPassword")) {
            infoMessage = "message.updateCardStatusErrPassword";
            infoMessageMenu = "message.menu.cardStatus";
        } else if (message.equals("sendRequestAdminErr")) {
            infoMessage = "message.sendRequestAdminErr";
            infoMessageMenu = "message.menu.cardStatus";
        } else if (message.equals("sendRequestAdminErrPassword")) {
            infoMessage = "message.sendRequestAdminErrPassword";
            infoMessageMenu = "message.menu.cardStatus";
        } else if (message.equals("duplicateSendRequestAdminErr")) {
            infoMessage = "message.duplicateSendRequestAdminErr";
            infoMessageMenu = "message.menu.cardStatus";
        } else if (message.equals("choiceCardNumberEmpty")) {
            infoMessage = "message.choiceCardNumberEmpty";
            infoMessageMenu = "message.menu.creatingPayment";
        } else if (message.equals("insufficientBalance")) {
            infoMessage = "message.insufficientBalance";
            infoMessageMenu = "message.menu.creatingPayment";
        } else if (message.equals("cardPasswordAddPaymentNotEquals")) {
            infoMessage = "message.cardPasswordAddPaymentNotEquals";
            infoMessageMenu = "message.menu.creatingPayment";
        } else if (message.equals("addPayment")) {
            infoMessage = "message.addPayment";
            infoMessageMenu = "message.menu.creatingPayment";
        } else if (message.equals("addPaymentErr")) {
            infoMessage = "message.addPaymentErr";
            infoMessageMenu = "message.menu.creatingPayment";
        } else if (message.equals("paymentSent")) {
            infoMessage = "message.paymentSent";
            infoMessageMenu = "message.menu.sendingPayment";
        } else if (message.equals("paymentSentErr")) {
            infoMessage = "message.paymentSentErr";
            infoMessageMenu = "message.menu.sendingPayment";
        } else if (message.equals("paymentSentDuplicateErr")) {
            infoMessage = "message.paymentSentDuplicateErr";
            infoMessageMenu = "message.menu.sendingPayment";
        } else if (message.equals("paymentSentCardBlockErr")) {
            infoMessage = "message.paymentSentCardBlockErr";
            infoMessageMenu = "message.menu.sendingPayment";
        }

            session.setAttribute("infoMessage", infoMessage);
        session.setAttribute("infoMessageMenu", infoMessageMenu);

        if (adminPage.equals("true")) response = "adminInformationPage";
        else response = "informationPage";
        return response;
        }

    @PostMapping()
    public String createInfoMessagePost(HttpServletRequest request) {
        return createInfoMessage(request);
    }
}






