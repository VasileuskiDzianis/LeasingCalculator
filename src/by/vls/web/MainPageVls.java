package by.vls.web;

import by.vls.model.user.Company;
import by.vls.model.user.Person;
import by.vls.model.user.UserValidator;
import by.vls.model.user.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class MainPageVls extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        User user = new User();
        UserValidator valid = new UserValidator();

        try {
            valid.checkCookies(request, user);
        } catch (Exception e) {
            e.printStackTrace();
        }



        switch (user.getUserStatus()) {
            case UserValidator.NEW_USER: {
                getServletContext().setAttribute("authform", FormCollection.getAuthForm());
                getServletContext().removeAttribute("logoutform");
                getServletContext().setAttribute("menu", FormCollection.getMenu(user.getUserStatus()));
                if (request.getParameter("link") != null) {
                    if (request.getParameter("link").equals("calculator")) {
                        getServletContext().setAttribute("main", FormCollection.getFormCalcReq());
                    } else if (request.getParameter("link").equals("about")) {
                        getServletContext().setAttribute("main", FormCollection.getAboutForm());
                    }
                } else getServletContext().setAttribute("main", FormCollection.getFormCalcReq());
                RequestDispatcher view = request.getRequestDispatcher("main.jsp");
                view.forward(request, response);
                break;
            }
            case UserValidator.IDENT_USER: {
                getServletContext().setAttribute("authform", FormCollection.getAuthForm());
                getServletContext().removeAttribute("logoutform");
                getServletContext().setAttribute("menu", FormCollection.getMenu(user.getUserStatus()));
                if (request.getParameter("link") != null) {
                    if (request.getParameter("link").equals("calculator")) {
                        getServletContext().setAttribute("main", FormCollection.getFormCalcReq());
                    } else if (request.getParameter("link").equals("about")) {
                        getServletContext().setAttribute("main", FormCollection.getAboutForm());
                    }
                } else getServletContext().setAttribute("main", FormCollection.getFormCalcReq());
                RequestDispatcher view = request.getRequestDispatcher("main.jsp");
                view.forward(request, response);
                break;
            }
            case UserValidator.AUTH_USER_BSN: {
                Company company = new Company(user);
                company.loadProfile();
                getServletContext().removeAttribute("authform");
                getServletContext().setAttribute("logoutform", FormCollection.getLogOutForm(FormCollection.replaceNull(company.getCompName())));
                getServletContext().setAttribute("menu", FormCollection.getMenu(user.getUserStatus()));
                if (request.getParameter("link") != null) {
                    if (request.getParameter("link").equals("calculator")) {
                        getServletContext().setAttribute("main", FormCollection.getFormCalcReq());
                    } else if (request.getParameter("link").equals("identbsn")) {
                        getServletContext().setAttribute("main", FormCollection.getIdentBsnForm(company));
                    } else if (request.getParameter("link").equals("orderbsn")) {
                        getServletContext().setAttribute("main", FormCollection.getOrderBsnForm());
                    } else if (request.getParameter("link").equals("about")) {
                        getServletContext().setAttribute("main", FormCollection.getAboutForm());
                    }
                } else getServletContext().setAttribute("main", FormCollection.getFormCalcReq());
                RequestDispatcher view = request.getRequestDispatcher("main.jsp");
                view.forward(request, response);
                break;
            }
            case UserValidator.AUTH_USER_PRVT: {
                Person person = new Person(user);
                person.loadProfile();
                getServletContext().removeAttribute("authform");
                getServletContext().setAttribute("logoutform", FormCollection.getLogOutForm(FormCollection.replaceNull(person.getLastName()) + " " + FormCollection.replaceNull(person.getFirstName()) + " " + FormCollection.replaceNull(person.getPatronymicName())));
                getServletContext().setAttribute("menu", FormCollection.getMenu(user.getUserStatus()));
                if (request.getParameter("link") != null) {
                    if (request.getParameter("link").equals("calculator")) {
                        getServletContext().setAttribute("main", FormCollection.getFormCalcReq());
                    } else if (request.getParameter("link").equals("identprvt")) {
                        getServletContext().setAttribute("main", FormCollection.getIdentPrvtForm(person));
                    } else if (request.getParameter("link").equals("orderprvt")) {
                        getServletContext().setAttribute("main", FormCollection.getOrderPrvtForm());
                    } else if (request.getParameter("link").equals("about")) {
                        getServletContext().setAttribute("main", FormCollection.getAboutForm());
                    }
                } else getServletContext().setAttribute("main", FormCollection.getFormCalcReq());
                RequestDispatcher view = request.getRequestDispatcher("main.jsp");
                view.forward(request, response);
                break;
            }
        }

    }
}
