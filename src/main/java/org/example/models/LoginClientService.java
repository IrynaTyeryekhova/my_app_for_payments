package org.example.models;

import org.example.dao.Statuses;
import org.example.dbServices.DBClientService;
import org.example.entities.Client;
import org.example.services.Roles;
import org.example.services.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginClientService {

    public String loginClientChecking(HttpServletRequest request, DBClientService dbClientService, Client clientLogin) {

        HttpSession session = request.getSession();
        String action = new Service().getParameter(session, request, "action");

        String userName;
        String response = "index";
        String eMailLogin = clientLogin.geteMail();
        String passwordLogin = clientLogin.getPassword();
        Client client = dbClientService.getClientInfo(eMailLogin);

        if (client == null) return "redirect:/loginRegisterErrMessage?&message=loginErr";
//        return "redirect:/infoMessage?lang=" + language + "&message=error"

        if (client.getPassword().equals(passwordLogin) && client.getStatus().equals(Statuses.UNBLOCK_STRING)) {

            userName = client.getName();
            String greeting = new Service().helloMake(userName);

            session.setAttribute("ClientEMail", eMailLogin);
            session.setAttribute("Password", passwordLogin);
            session.setAttribute("ClientName", userName);
            session.setAttribute("hello", greeting);

                    if(client.getRole().equals(Roles.USER_STRING)) {
//                        LOG.info("Login client. Client role: user");
//                        response = "redirect:/register";
                        response = "index";
                    } else {
                        session.setAttribute("ClientRole", Roles.ADMIN_STRING);
//                        LOG.info("Login client. Client role: admin");
                        response = "adminCardsMenu";
                    }
                } else if (!client.getPassword().equals(passwordLogin)) {
                    response = "redirect:/loginRegisterErrMessage?message=loginErr";
                } else if (client.getStatus().equals(Statuses.BLOCK_STRING)) {
                    response = "redirect:/loginRegisterErrMessage?message=loginBlockErr";
                }

        return response;
    }
//    public HttpServletRequest loginDoGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        return req;
//    }
}
