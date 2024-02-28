package com.example.crud_file_editor;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class BsicLoginServlet extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName=request.getParameter("username");
        String password=request.getParameter("password");

        List<String> passwords= Files.readAllLines(Path.of(getServletContext().getRealPath("protected\\passwords.txt")));

        if(isValidUser(userName,password,passwords)){
            HttpSession session= request.getSession();
            session.setAttribute("username",userName);
            session.setAttribute("role","adm");
            response.sendRedirect("success-servlet");
        }
        else{
            response.sendRedirect("error.jsp");
        }
    }
    private boolean isValidUser (String userName, String password, List<String> passwords){
        List<List<String>> fin=new ArrayList<>();
        for(int i=0;i<passwords.size();i++){
            List<String> tmp= List.of(passwords.get(i).split(";"));
            fin.add(tmp);
        }
        for(List<String> l:fin){
            if(userName.equals(l.get(0)) && password.equals(l.get(1))){
                return true;
            }
        }
        return false;
    }
}
