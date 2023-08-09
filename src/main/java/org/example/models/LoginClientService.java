package org.example.models;

import org.example.dbServices.DBClientService;
import org.example.dbServices.Statuses;
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
        String eMailLogin = clientLogin.geteMail();
        String passwordLogin = clientLogin.getPassword();
        Client client = dbClientService.getClientInfo(eMailLogin);

        if (client == null) return  "redirect:/loginRegisterErrMessage?message=loginErr";

        if (client.getPassword().equals(passwordLogin) && client.getStatus()==Statuses.UNBLOCK) {

            userName = client.getuserName();
            String greeting = new Service().helloMake(userName);

            session.setAttribute("ClientEMail", eMailLogin);
            session.setAttribute("Password", passwordLogin);
            session.setAttribute("ClientName", userName);
            session.setAttribute("hello", greeting);

                    if(client.getRole()==Roles.USER) {
//                        LOG.info("Login client. Client role: user");
                        session.setAttribute("ClientRole", Roles.USER_STRING);
//            resp.sendRedirect("/register?lang=" + language);

                        return "personalOffice";
//                        return  new RegisterClientService().registerClientMake(request, dbClientService, clientLogin);
//                        "redirect:/register";
                    } else {
                        session.setAttribute("ClientRole", Roles.ADMIN_STRING);
//                        LOG.info("Login client. Client role: admin");
                        return  "adminCardsMenu";
                    }
                } else if (!client.getPassword().equals(passwordLogin)) {
                    return  "redirect:/loginRegisterErrMessage?message=loginErr";
                } else if (client.getStatus()==Statuses.BLOCK) {
                    return  "redirect:/loginRegisterErrMessage?message=loginBlockErr";
                }

        return "";
    }
//    public HttpServletRequest loginDoGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        return req;
//    }
}
