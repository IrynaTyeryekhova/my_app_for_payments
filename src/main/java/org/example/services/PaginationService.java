package org.example.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

public class PaginationService {
//    public static final Logger LOG=Logger.getLogger(PaginationService.class.getName());
    public void parametersSortMake(HttpServletRequest request, String paramSortListName, String parameterSortName, String... values) {
//        LOG.info("PaginationService is starting");
        HttpSession session = request.getSession();
        Service service = new Service();
        String parameterSort =  service.getParameter(session, request, parameterSortName);
        String parameterSortType =  service.getParameter(session, request, "selectParamSortType");

        if(parameterSort==null) {parameterSort = values[0];}
        if(parameterSortType==null) {parameterSortType = "ASC";}

        session.setAttribute(paramSortListName, values);
        session.setAttribute("listParamSortType", Arrays.asList("ASC", "DESC"));
        session.setAttribute(parameterSortName, parameterSort);
        session.setAttribute("selectParamSortType", parameterSortType);
       }

    public void paginationMake(HttpServletRequest request, int paymentCount) {
        HttpSession session = request.getSession();
        String nextPage =  request.getParameter("nextPage");
        String previousPage =  request.getParameter("previousPage");

        Integer numberPage = 1;
        Integer limit = 2;

        String page = request.getParameter("numberPage");
        String limitValue = request.getParameter("selectCountShow");

        if(limitValue==null && session.getAttribute("selectCountShow") != null) limitValue = String.valueOf(session.getAttribute("selectCountShow"));
        if(limitValue!=null) limit = Integer.valueOf(limitValue);
        if(page==null && session.getAttribute("numberPage") != null) page = String.valueOf(session.getAttribute("numberPage"));
        if(page!=null) numberPage = Integer.valueOf(page);
        if(paymentCount%limit!=0){
            if((paymentCount/limit)+1<numberPage) numberPage = 1;
        }
        else {
            if((paymentCount/limit)<numberPage) numberPage = 1;
        }

        if(nextPage != null && Integer.valueOf(nextPage)==1 && ((numberPage)*limit < paymentCount)) {++numberPage;}
        if(previousPage != null && Integer.valueOf(previousPage)==1 && numberPage-1 > 0) {--numberPage;}
        System.out.println(limit);
        session.setAttribute("numberPage", numberPage);
        session.setAttribute("selectCountShow", String.valueOf(limit));
        session.setAttribute("listCountShow", Arrays.asList("2", "3", "5"));
    }
}
