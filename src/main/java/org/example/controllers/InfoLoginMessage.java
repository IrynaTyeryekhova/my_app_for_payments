package org.example.controllers;

import org.example.services.ErrorsMessage;
import org.example.services.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/infoLoginMessage")

public class InfoLoginMessage {

        @GetMapping()
        public String createInfoLoginMessage(Model model, HttpServletRequest request) {
            HttpSession session = request.getSession();
            String message = new Service().getParameter(session, request, "message");
            String infoMessage = "";

            if(message.equals("registerDuplicate")) {
//                LOG.info("Client registration: client duplicate");
                infoMessage = ErrorsMessage.REGISTER_DUPLICATE_CLIENT_ERR;
            }
            else if(message.equals("loginErr")) {
//                LOG.info("Login client. Login error");
                infoMessage = ErrorsMessage.LOGIN_INFO_ERR;
            }
            else if(message.equals("loginBlockErr")) {
//                LOG.info("Login client. Login block error");
                infoMessage = ErrorsMessage.LOGIN_BLOCK_USER_ERR;
            }

            session.setAttribute("infoMessage", infoMessage);
            session.setAttribute("message", message);

            return "accessMessagePage";
        }

    @PostMapping()
    public String createInfoLoginMessagePost(Model model, HttpServletRequest request) {
            return createInfoLoginMessage(model, request);
    }
 }
