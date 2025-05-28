package org.scoula.ex02;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//@WebServlet(name = "helloServlet", value = "/hello-servlet")
@WebServlet(name = "helloServlet2", urlPatterns={"/xxx", "/yyy" })
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {

        message = "Hello World!";
        System.out.println("init() 메소드 출력");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
        System.out.println("destroy() 메소드 출력");
    }
}