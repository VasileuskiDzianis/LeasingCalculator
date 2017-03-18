package by.vls.web;

import by.vls.database.DataBaseConnector;
import by.vls.model.user.UserValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Zenbook on 17.02.2017.
 */
public class EmailChecker extends HttpServlet {
        public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException{
        if (request.getParameter("user")!= null){
            DataBaseConnector db = new DataBaseConnector();
            db.setUserPswChecked(request.getParameter("user").replaceAll(" ", "+"));
            getServletContext().setAttribute("authform", FormCollection.getAuthForm());
            getServletContext().removeAttribute("logoutform");
            getServletContext().setAttribute("menu", FormCollection.getMenu(UserValidator.NEW_USER));
            getServletContext().setAttribute("main", FormCollection.getErrorForm("Учётная запись активирована. Авторизуйтесь."));
            RequestDispatcher view = request.getRequestDispatcher("main.jsp");
            view.forward(request, response);
        }
    }
}
