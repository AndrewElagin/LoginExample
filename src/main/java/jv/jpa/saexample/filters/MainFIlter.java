/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jv.jpa.saexample.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rich
 */
@WebFilter(filterName = "MainFIlter", urlPatterns = {"/secured1"})
public class MainFIlter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public MainFIlter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession s = req.getSession();
        Cookie[] cookies = req.getCookies();
        Cookie sid = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("sid")) {
                sid = c;
                System.out.println("sid "+sid.getValue());
                
                break;
            }
        }
        System.out.println("session ID "+s.getId());
        if(sid != null && (sid.getValue().equals(s.getId()))){
            chain.doFilter(request, response);
        }else{
             ((HttpServletResponse)response).sendRedirect("login");
        }

    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {

    }

    /**
     * Return a String representation of this object.
     */
}
