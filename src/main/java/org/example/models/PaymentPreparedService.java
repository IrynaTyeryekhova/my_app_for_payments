package org.example.models;

import org.example.dbServices.DBPaymentService;
import org.example.dbServices.Statuses;
import org.example.entities.Payment;
import org.example.services.PaginationService;
import org.example.services.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PaymentPreparedService {
//    public static final Logger LOG = Logger.getLogger(PaymentPreparedService.class.getName());

    public String paymentPreparedMake(HttpServletRequest request, DBPaymentService paymentsService) {
//        LOG.info("PaymentPreparedService is starting");
        HttpSession session = request.getSession();
        Service service = new Service();
        String email = service.getParameter(session, request, "ClientEMail");

        PaginationService paginationService = new PaginationService();
        paginationService.parametersSortMake(request, "listParamSortPreparedPayment", "selectParamSortPreparedPayments", "paymentNumber", "date", "sum");

        int countAllPaymentsForStatus = paymentsService.getCountAllPaymentsForStatus(email, Statuses.PREPARED);
        if (countAllPaymentsForStatus == -1) return "redirect:/infoMessage?message=error";
        else {
            paginationService.paginationMake(request, countAllPaymentsForStatus);

            Integer limit = Integer.valueOf(String.valueOf(session.getAttribute("selectCountShow")));
            Integer numberPage = Integer.valueOf(String.valueOf(session.getAttribute("numberPage")));

            int offSet = (numberPage * limit) - limit;

            List<Payment> payments = paymentsService.findAllPaymentsForStatusWithLimit(email, Statuses.PREPARED, "payments." + (String) session.getAttribute("selectParamSortPreparedPayments"), (String) session.getAttribute("selectParamSortType"), limit, offSet);
            if (payments == null) return "redirect:/infoMessage?message=error";
            else {
                session.setAttribute("preparedPayments", payments);
                return "paymentPreparedSend";
            }
        }
    }
}