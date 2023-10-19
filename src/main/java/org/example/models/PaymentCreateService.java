package org.example.models;

import org.example.dbServices.DBCardService;
import org.example.dbServices.DBPaymentService;
import org.example.entities.CardAccount;
import org.example.entities.Payment;
import org.example.services.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PaymentCreateService {
//    public static final Logger LOG = Logger.getLogger(PaymentCreateService.class.getName());

    public String paymentAddMake(HttpServletRequest request, DBCardService cardService, DBPaymentService paymentsService, Payment payment) throws ServletException, IOException{
//        LOG.info("PaymentCreateService is starting");
        HttpSession session = request.getSession();
        Service service = new Service();
        String choiceCardNumber = service.getParameter(session, request, "choiceCardNumber");
        String cardPasswordAddPayment = service.getParameter(session, request, "cardPasswordAddPayment");
        String eMail = service.getParameter(session, request, "ClientEMail");

        if (choiceCardNumber == null) return "redirect:/infoMessage?message=choiceCardNumberEmpty";
        else {
            CardAccount cardAccount = cardService.getCardInfo(choiceCardNumber);
            if (cardAccount == null) return "redirect:/infoMessage?message=error";
            else if (cardAccount.getBalance() < payment.getSum()) return "redirect:/infoMessage?message=insufficientBalance";
            else if (!cardAccount.getPassword().equals(cardPasswordAddPayment)) return "redirect:/infoMessage?message=cardPasswordAddPaymentNotEquals";
            else {
                int insertPayment = paymentsService.insertPayment(payment, eMail, choiceCardNumber);

                if (insertPayment > 0) return "redirect:/infoMessage?message=addPayment";
                else return "redirect:/infoMessage?message=addPaymentErr";
            }
        }
    }
}
