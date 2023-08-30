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






