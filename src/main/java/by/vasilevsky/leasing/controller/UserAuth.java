package by.vasilevsky.leasing.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import by.vasilevsky.leasing.domain.User;
import by.vasilevsky.leasing.service.UserValidator;
import by.vasilevsky.leasing.view.FormCollection;

import org.apache.commons.codec.binary.Base64;

public class UserAuth extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
        User userCheck = new User();
        if (request.getParameter("login") != "") {
            userCheck.setUserLogin(request.getParameter("login"));
        } else userCheck.setUserLogin("-1");

        if (request.getParameter("password") != "") {
            userCheck.setUserPassword(request.getParameter("password"));
            System.out.println("Input psw in form: " + userCheck.getUserPassword());
        } else userCheck.setUserPassword("-1");

        UserValidator UV = new UserValidator();
        try {
            UV.validateUser(userCheck, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (userCheck.getUserStatus()) {
            case UserValidator.NEW_USER: {
                getServletContext().setAttribute("authform", FormCollection.getAuthForm());
                getServletContext().removeAttribute("logoutform");
                getServletContext().setAttribute("menu", FormCollection.getMenu(userCheck.getUserStatus()));
                getServletContext().setAttribute("main", FormCollection.getErrorForm("Не верные логин или пароль"));

                RequestDispatcher view = request.getRequestDispatcher("main.jsp");
                view.forward(request, response);
                break;
            }
            case UserValidator.IDENT_USER: {
                getServletContext().setAttribute("authform", FormCollection.getAuthForm());
                getServletContext().removeAttribute("logoutform");
                getServletContext().setAttribute("menu", FormCollection.getMenu(userCheck.getUserStatus()));
                getServletContext().setAttribute("main", FormCollection.getErrorForm("Адрес e-mail не подтверждён."));

                RequestDispatcher view = request.getRequestDispatcher("main.jsp");
                view.forward(request, response);
                break;
            }
            case UserValidator.AUTH_USER_BSN: {
                Cookie cookie_userID = new Cookie("userid", Base64.encodeBase64String(userCheck.getUserID().toString().getBytes()));
                cookie_userID.setMaxAge(30 * 60);
                String[] saltAndPass = userCheck.getUserPswFromDB().split("\\$");
                Cookie cookie_userPSW = new Cookie("userpsw", saltAndPass[1]);
                cookie_userID.setMaxAge(-1);
                response.addCookie(cookie_userID);
                response.addCookie(cookie_userPSW);
                response.sendRedirect("vlsmain");
                break;
            }
            case UserValidator.AUTH_USER_PRVT: {
                Cookie cookie_userID = new Cookie("userid", Base64.encodeBase64String(userCheck.getUserID().toString().getBytes()));
                cookie_userID.setMaxAge(30 * 60);
                String[] saltAndPass = userCheck.getUserPswFromDB().split("\\$");
                Cookie cookie_userPSW = new Cookie("userpsw", saltAndPass[1]);
                cookie_userID.setMaxAge(-1);
                response.addCookie(cookie_userID);
                response.addCookie(cookie_userPSW);
                response.sendRedirect("vlsmain");
                break;
            }
        }


    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        if (request.getParameter("auth") != null) {
            if (request.getParameter("auth").equals("logout")) {
                Cookie killMyCookie = new Cookie("userpsw", null);
                killMyCookie.setMaxAge(-1);
                response.addCookie(killMyCookie);
                response.sendRedirect("vlsmain");
            }
        } else {
            getServletContext().setAttribute("authform", FormCollection.getAuthForm());
            getServletContext().removeAttribute("logoutform");
            getServletContext().setAttribute("menu", FormCollection.getMenu(UserValidator.NEW_USER));
            getServletContext().removeAttribute("main");
            RequestDispatcher view = request.getRequestDispatcher("main.jsp");
            view.forward(request, response);
        }


    }
}

