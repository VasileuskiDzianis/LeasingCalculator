package by.vasilevsky.leasing.controller;

import by.vasilevsky.leasing.domain.User;
import by.vasilevsky.leasing.service.UserValidator;
import by.vasilevsky.leasing.view.FormCollection;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

public class UserRegister extends HttpServlet {
    private static ServletContext sctx;

    public static ServletContext getContext() {
        return sctx;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
        sctx = getServletContext();
        User user = new User();
        UserValidator validator = new UserValidator();
        int errcode;
        if (request.getParameter("login") != "" && validator.checkEmail(request.getParameter("login"))) {
            if (request.getParameter("password1") != "" && request.getParameter("password1").equals(request.getParameter("password2"))) {
                User newUser = new User();
                newUser.setUserLogin(request.getParameter("login"));
                newUser.setUserPassword(request.getParameter("password1"));
                if (request.getParameter("usertype").equals("company")) {
                    newUser.setUserStatus(UserValidator.AUTH_USER_BSN);
                } else if (request.getParameter("usertype").equals("private")) {
                    newUser.setUserStatus(UserValidator.AUTH_USER_PRVT);
                }
                errcode = validator.registerNewUser(newUser);
            } else errcode = 2;
        } else errcode = 1;
        getServletContext().removeAttribute("authform");
        getServletContext().removeAttribute("logoutform");
        getServletContext().setAttribute("menu", FormCollection.getMenu(UserValidator.NEW_USER));
        getServletContext().setAttribute("main", FormCollection.getRegForm(errcode));

        RequestDispatcher view = request.getRequestDispatcher("main.jsp");
        view.forward(request, response);


    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        getServletContext().removeAttribute("authform");
        getServletContext().removeAttribute("logoutform");
        getServletContext().setAttribute("menu", FormCollection.getMenu(UserValidator.NEW_USER));
        getServletContext().setAttribute("main", FormCollection.getRegForm(-1));
        RequestDispatcher view = request.getRequestDispatcher("main.jsp");
        view.forward(request, response);

    }
}
