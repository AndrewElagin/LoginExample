package jv.jpa.saexample.servlets;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jv.jpa.saexample.db.DbUtil;
import jv.jpa.saexample.db.User;

/**
 *
 * @author Андрей
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer wr = resp.getWriter();
        wr.append("Wrong login or pasword " + new Date());
        wr.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String pasword = req.getParameter("password");
        User u = DbUtil.getUserByNameAndPassword(name, pasword);
        System.out.println("\n\n " + u + "\n\n");
        String role = null;
        if (u != null) {
            role = (String) u.getAuthority();

            HttpSession s = req.getSession(true);
            s.setAttribute("user", u);
            System.out.println("s.getAttribute(\"user\") " + s.getAttribute("user"));
            s.setAttribute("role", role);
            System.out.println("s.getAttribute(\"role\") " + s.getAttribute("role"));
            Cookie c = new Cookie("sid", s.getId());
            resp.addCookie(c);
            req.getRequestDispatcher("secured").forward(req, resp);
            //resp.sendRedirect("secured");
        } else {
            resp.sendRedirect("login");
        }

        //resp.sendRedirect("secured");
        //System.out.println("\n IndexController doGet()\n");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("LoginServlet initialized");
    }

}
