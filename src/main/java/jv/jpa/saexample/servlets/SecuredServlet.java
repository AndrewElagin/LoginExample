/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jv.jpa.saexample.servlets;

import jv.jpa.saexample.db.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Андрей
 */
@WebServlet(name = "secured", urlPatterns = {"/secured"})
public class SecuredServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession();
        User u = (User) s.getAttribute("user");
        System.out.println("User form secured "+u);
        String role = (String) s.getAttribute("role");
        req.setAttribute("user", u);
        req.setAttribute("role", role);

        req.getRequestDispatcher("/WEB-INF/success.jsp").forward(req, resp);
    }

       @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession();
        User u = (User) s.getAttribute("user");
        System.out.println("User form secured "+u);
        String role = (String) s.getAttribute("role");
        req.setAttribute("user", u);
        req.setAttribute("role", role);
        req.getRequestDispatcher("/WEB-INF/success.jsp").forward(req, resp);
       
    }

    @Override
    public void init() throws ServletException {
        System.out.println("SecuredServlet initialized");
    }

}
