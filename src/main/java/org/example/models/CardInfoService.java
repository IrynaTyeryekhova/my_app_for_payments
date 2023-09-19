package org.example.models;

import org.example.dbServices.DBCardService;
import org.example.dbServices.DBPaymentService;
import org.example.entities.CardAccount;
import org.example.entities.Payment;
import org.example.services.PaginationService;
import org.example.services.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CardInfoService {
//    public static final Logger LOG=Logger.getLogger(CardInfoService.class.getName());

        public String cardInfoMake(HttpServletRequest request, DBCardService dbCardService, DBPaymentService dbPaymentService) throws ServletException, IOException {
//            LOG.info("CardInfoService is starting");
            HttpSession session = request.getSession();
            Service service = new Service();
            String cardNumber =  service.getParameter(session, request, "cardNumber");
            session.setAttribute("cardNumber", cardNumber);

            int countAllPayments = dbPaymentService.getCountAllPaymentsForCard(cardNumber);
            CardAccount cardAccount = dbCardService.getCardInfo(cardNumber);

            if(cardAccount == null || countAllPayments == -1) return ("redirect:/infoMessage?message=error");
            else {
//                String response = paymentForCardMake(countAllPayments, request);
                paymentForCardMake(request, dbPaymentService, countAllPayments);

//                session.setAttribute("cardInfoCardNumber",cardNumber);
//                session.setAttribute("cardInfoValidityPeriod",cardAccount.getValidityPeriod());
//                session.setAttribute("cardInfoBalance",cardAccount.getBalance());
//                session.setAttribute("cardInfoStatus",cardAccount.getStatusCard());
                session.setAttribute("cardInfo", cardAccount);
            }
            return "cardsInfo";
        }
        private String paymentForCardMake(HttpServletRequest request, DBPaymentService dbPaymentService, int countAllPayments) throws ServletException, IOException {
            HttpSession session = request.getSession();
//            Service service = new Service();
//            String language = service.getParameter(session, req, "lang");
            PaginationService paginationService = new PaginationService();
            paginationService.parametersSortMake(request, "listParamSortPaymentsForCard","selectParamSortPaymentsForCard", "id", "date", "sum");
//            "paramSortPaymentsForCardList"
            paginationService.paginationMake(request, countAllPayments);

            Integer limit = Integer.valueOf(String.valueOf(session.getAttribute("selectCountShow")));
            Integer numberPage = Integer.valueOf(String.valueOf(session.getAttribute("numberPage")));

            int offSet = (numberPage * limit) - limit;

            List<Payment> payments = dbPaymentService.findAllPaymentsForCardWithLimit((String) session.getAttribute("cardNumber"), "payments." + (String) session.getAttribute("selectParamSortPaymentsForCard"), (String) session.getAttribute("selectParamSortType"), limit, offSet);

            if (payments == null) return "redirect:/infoMessage?message=error";
            else session.setAttribute("paymentsForCard", payments);

            return "";
            }
    }







