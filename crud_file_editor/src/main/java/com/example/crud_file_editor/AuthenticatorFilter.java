package com.example.crud_file_editor;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/protected/*"})
public class AuthenticatorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpSession session =req.getSession(false);
        if(session!=null && session.getAttribute("username")!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else {
            HttpServletResponse resp=(HttpServletResponse) servletResponse;
            resp.sendRedirect("../error.jsp");
        }
    }
}
