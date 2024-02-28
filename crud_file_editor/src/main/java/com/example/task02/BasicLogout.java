package com.example.task02;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "logoutServlet", value = "/logout-servlet")
public class BasicLogout extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        if(session!=null) {
            session.invalidate();
        }


        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String str =
                "<html>"+
                "<head>"+
                "    <title>Delayed logout</title>"+
                "    <script type=\"text/javascript\">"+
                "    window.onload = function() {"+
                "        setTimeout("+
                "           function(){"+
                "               window.location.href = 'index.jsp';"+
                "           }, 5000"+
                "        );"+
                "    };"+
                "    </script>"+
                "</head>"+
                "<body>"+
                "<h1>Logout page</h1>"+
                "<p>Possible redirect</p>"+
                "</body>"+
                "</html>";
        out.println(str);


    }
}
