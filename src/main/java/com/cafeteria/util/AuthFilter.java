/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.util;

/**
 *
 * @author andre
 */
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        String loginURI = httpRequest.getContextPath() + "/login.jsp";
        String loginAction = httpRequest.getContextPath() + "/usuario?action=login";
        String registerURI = httpRequest.getContextPath() + "/registro.jsp";
        String resourcesURI = httpRequest.getContextPath() + "/resources/";
        
        boolean loggedIn = session != null && session.getAttribute("usuario") != null;
        boolean loginRequest = httpRequest.getRequestURI().equals(loginURI) || 
                             httpRequest.getRequestURI().equals(loginAction);
        boolean registerRequest = httpRequest.getRequestURI().equals(registerURI);
        boolean resourceRequest = httpRequest.getRequestURI().startsWith(resourcesURI);
        
        if (loggedIn || loginRequest || registerRequest || resourceRequest) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(loginURI);
        }
    }
    
    @Override
    public void destroy() {}
}
