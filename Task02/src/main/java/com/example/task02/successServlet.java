package com.example.task02;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "successServlet", value = "/success-servlet")
public class successServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        List<List<String>> list=new ArrayList<>();
        Files.walk(Path.of(getServletContext().getRealPath("protected")))
                .filter(b->Files.isRegularFile(Paths.get(String.valueOf(b))) && String.valueOf(b).contains(".txt") && !String.valueOf(b).endsWith("passwords.txt"))
                .forEach(a->{
                    try {
                        System.out.println(String.valueOf(a));
                        List<String> tmp=Files.readAllLines(a);
                        tmp.add(0,String.valueOf(a).substring(String.valueOf(a).lastIndexOf("\\")+1));
                        list.add(tmp);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        PrintWriter out = response.getWriter();
        String str="<html>\n" +
                "<head>\n" +
                "    <title>Login Success</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1> Login success</h1>\n" +
                "<p>Hello!!!!!!!!!!!!!!!!!!!!</p>\n" +
                "\n";
        String columns="<table style=\"width: 200px\">\n"+
                    "<tr>\n" +
                    "    <td><b>FILE</b></td>\n" +
                    "    <td><b>OWNER</b></td>\n" +
                    "    <td><b>READ</b></td>\n" +
                    "  </tr>";
        for (int i=0;i<list.size();i++){
            columns+=" <tr>\n" +
                    "    <td>"+ list.get(i).get(0)+" </td>\n" +
                    "    <td>"+ list.get(i).get(1)+" </td>\n" +
                    "<td><form action=\"read-servlet\" method=\"get\">\n" +
                    "<input type=\"hidden\" name=\"file\" value=\""+list.get(i).get(0)+"\">\n"+
                    "  <input type=\"submit\" name=\"read\" value=\"read\"><br/><br/>\n" +
                    "</form></td>"+
                    "  </tr>\n";
        }
        columns+="</table>";

        String end="<br/><br/><a href=\"logout-servlet\">logout</a>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        out.println(str);
        out.println(columns);
        out.println(end);
    }
}
