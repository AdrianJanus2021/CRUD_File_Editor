package com.example.task02;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "readServlet", value = "/read-servlet")
public class readServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String file=getServletContext().getRealPath("protected")+"/"+request.getParameter("file");

        List<String> list=new ArrayList<>();
        list= Files.readAllLines(Path.of(file));
        String str="";
        for (int i=1;i<list.size();i++){
            str+=list.get(i)+"\n";
        }
        String page="<html>\n" +
                "<head>\n" +
                "    <title>Login error</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<pre>"+
                str +
                "</pre>"+
                "</body>\n" +
                "</html>";
        PrintWriter out = response.getWriter();
        out.println(page);
    }
}
