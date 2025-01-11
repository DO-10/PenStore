package com.example.penstoreu;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import jakarta.servlet.http.HttpSession;
import models.LoginHandler;
import models.UserService;


public class ChangeIfoServlet extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UserService userService=new UserService();

        String nm=request.getParameter("newname");
        String username=request.getParameter("username");
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        userService.changename(nm,username);
        request.setAttribute("username",username);
        session.setAttribute("username",nm);


        request.getRequestDispatcher("home.jsp").forward(request, response);

    }

}
