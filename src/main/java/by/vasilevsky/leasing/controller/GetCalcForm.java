package by.vasilevsky.leasing.controller;

import by.vasilevsky.leasing.domain.User;
import by.vasilevsky.leasing.service.CalcRequest;
import by.vasilevsky.leasing.service.LeasingRateCalculator;
import by.vasilevsky.leasing.service.UserValidator;
import by.vasilevsky.leasing.view.FormCollection;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class GetCalcForm extends HttpServlet {
    private static ServletContext sctx;

    public static ServletContext getContext() {
        return sctx;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
        sctx = getServletContext();
        LeasingRateCalculator rc = new LeasingRateCalculator();
        CalcRequest cr = new CalcRequest();
        cr.setCurrency(request.getParameter("currency"));
        cr.setObjectType(request.getParameter("objecttype"));


        if (request.getParameter("cost") != "") {
            cr.setCost(request.getParameter("cost"));
        } else cr.setCost("0");


        cr.setPrepay(request.getParameter("prepay"));
        cr.setDuration(request.getParameter("duration"));
        cr.setByuingOutPercent(request.getParameter("byuingoutpercent"));
        cr.setObjAge(request.getParameter("age"));
        int flg;
        flg = 0;
        if (request.getParameter("novatoncostflag") != null) {
            flg = Integer.parseInt(request.getParameter("novatoncostflag"));
        }
        if (flg == 1) {
            cr.setNoVatInCost();
        }
        flg = 0;
        if (request.getParameter("showinsurancecol") != null) {
            flg = Integer.parseInt(request.getParameter("showinsurancecol"));
        }
        if (flg == 1) {
            cr.setShowInsuranceCol();
        }
        rc.getRate(cr);
        cr.buildPaymentList();
        //check cookies for menu type
        User user = new User();
        UserValidator valid = new UserValidator();
        try {
            valid.checkCookies(request, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getServletContext().removeAttribute("authform");
        getServletContext().removeAttribute("logoutform");
        getServletContext().setAttribute("menu", FormCollection.getMenu(user.getUserStatus()));
        getServletContext().setAttribute("main", FormCollection.getCalcListForm(cr));
        RequestDispatcher view = request.getRequestDispatcher("main.jsp");
        view.forward(request, response);
    }
}
