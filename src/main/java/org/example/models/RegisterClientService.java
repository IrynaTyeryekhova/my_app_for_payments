package org.example.models;

import org.example.dbServices.DBClientService;
import org.example.entities.Client;
import org.example.services.Roles;
import org.example.services.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class RegisterClientService {

        public String registerClientMake(HttpServletRequest request, DBClientService dbClientService, Client clientRegister) {
            //        LOG.info("Client registration is starting");
            HttpSession session = request.getSession();

            int insertResult = dbClientService.insertClient(clientRegister);

            if (insertResult == 0) return "redirect:/loginRegisterErrMessage?message=registerDuplicate";
//                else LOG.info("Client registration was successful");

            session.setAttribute("ClientEMail", clientRegister.geteMail());
            session.setAttribute("Password", clientRegister.getPassword());
            session.setAttribute("ClientName", clientRegister.getuserName());

            String greeting = new Service().helloMake(clientRegister.getuserName());
            session.setAttribute("hello", greeting);
            session.setAttribute("ClientRole", Roles.USER_STRING);

            return "personalOffice";
        }
}
