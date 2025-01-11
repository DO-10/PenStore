package com.example.penstoreu;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.RegisterHandler;
import java.io.IOException;
import java.io.PrintWriter;

public class EmailCheckServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        RegisterHandler accountService = new RegisterHandler();
        String username = request.getParameter("q");
        boolean result1 = accountService.checkUsernameExists(username);
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        if (result1) {
            out.print("Exist");
        } else {
            out.print("Not Exist");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

