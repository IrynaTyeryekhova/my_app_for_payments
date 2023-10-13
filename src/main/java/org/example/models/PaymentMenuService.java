package org.example.models;

import org.example.dbServices.DBPaymentService;
import org.example.entities.Payment;
import org.example.services.PaginationService;
import org.example.services.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PaymentMenuService {
//    public static final Logger LOG=Logger.getLogger(PaymentMenuService.class.getName());

    public String paymentMenuMake(HttpServletRequest request, DBPaymentService paymentsService) {
//            LOG.info("PaymentMenuService is starting");
        HttpSession session = request.getSession();
        Service service = new Service();
        String email = service.getParameter(session, request, "ClientEMail");

        PaginationService paginationService = new PaginationService();
        paginationService.parametersSortMake(request, "listParamSortPayment", "selectParamSortPayments", "paymentNumber", "date", "sum");

        int countAllPaymentsForClient = paymentsService.getCountAllPaymentsForClient(email);

        if (countAllPaymentsForClient == -1) return "redirect:/infoMessage?message=error";
        else {
            paginationService.paginationMake(request, countAllPaymentsForClient);

            Integer limit = Integer.valueOf(String.valueOf(session.getAttribute("selectCountShow")));
            Integer numberPage = Integer.valueOf(String.valueOf(session.getAttribute("numberPage")));

            int offSet = (numberPage * limit) - limit;

            List<Payment> payments = paymentsService.findAllPaymentsForClientWithLimit(email, "payments." + (String) session.getAttribute("selectParamSortPayments"), (String) session.getAttribute("selectParamSortType"), limit, offSet);

            if (payments == null) return "redirect:/infoMessage?message=error";
            else {
                session.setAttribute("payments", payments);
                return "paymentsInfo";
            }
        }
    }
}

