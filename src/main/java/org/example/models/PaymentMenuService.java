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
        paginationService.parametersSortMake(request, "listParamSortPayment", "selectParamSortPayments", "id", "date", "sum");

        int countAllPaymentsForClient = paymentsService.getCountAllPaymentsForClient(email);

        if (countAllPaymentsForClient == -1) {
            System.out.println("1");
            return "redirect:/infoMessage?message=error";
        }
        else {
            paginationService.paginationMake(request, countAllPaymentsForClient);

            Integer limit = Integer.valueOf(String.valueOf(session.getAttribute("selectCountShow")));
            Integer numberPage = Integer.valueOf(String.valueOf(session.getAttribute("numberPage")));
            System.out.println(limit + " limit");
            System.out.println(numberPage + " number page");
            int offSet = (numberPage * limit) - limit;
            System.out.println(offSet + " offset");;
            List<Payment> payments = paymentsService.findAllPaymentsForCardWithLimit(email, "payments." + (String) session.getAttribute("selectParamSortPayments"), (String) session.getAttribute("selectParamSortType"), limit, offSet);

            if (payments == null) {
                System.out.println("2");
                return "redirect:/infoMessage?message=error";
            }
            else {
                session.setAttribute("payments", payments);
                return "paymentsInfo";
            }
        }
    }
}

