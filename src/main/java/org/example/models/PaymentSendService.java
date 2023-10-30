package org.example.models;

import org.example.dbServices.DBCardService;
import org.example.dbServices.DBPaymentService;
import org.example.dbServices.Statuses;
import org.example.entities.CardAccount;
import org.example.entities.Payment;
import org.example.services.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PaymentSendService {
//    public static final Logger LOG = Logger.getLogger(PaymentSendService.class.getName());

    public String paymentSendMake(HttpServletRequest req, DBPaymentService paymentsService, DBCardService cardService) throws ServletException, IOException {
//        LOG.info("PaymentSendService is starting");
        HttpSession session = req.getSession();
        Service service = new Service();

        int paymentIdSend = Integer.parseInt(String.valueOf(service.getParameter(session, req, "paymentIdSend")));

        Payment payment = paymentsService.getPaymentInfo(paymentIdSend);
        CardAccount cardAccount = cardService.getCardInfo(payment.getCardNumber());

        if (cardAccount == null || payment == null) return "redirect:/infoMessage?message=error";
        else {
            String paymentStatus = payment.getStatus();

            if (cardAccount.getStatusCard().equals(Statuses.BLOCK_STRING)) return "redirect:/infoMessage?message=paymentSentCardBlockErr";
            else if (payment.getSum() <= cardAccount.getBalance() && paymentStatus.equals(Statuses.PREPARED_STRING)) {
                int cardBalanceChangeResult = cardService.cardBalanceChange(payment.getCardNumber(), cardAccount.getBalance() - payment.getSum());
                int updatePaymentStatusResult = paymentsService.updatePaymentStatus(paymentIdSend, Statuses.SENT);
                if (cardBalanceChangeResult > 0 && updatePaymentStatusResult > 0) return "redirect:/infoMessage?message=paymentSent";
                else return "redirect:/infoMessage?message=error";
            } else if (paymentStatus.equals(Statuses.SENT_STRING)) return "redirect:/infoMessage?message=paymentSentDuplicateErr";
            else return "redirect:/infoMessage?message=paymentSentErr";
        }
    }
}
