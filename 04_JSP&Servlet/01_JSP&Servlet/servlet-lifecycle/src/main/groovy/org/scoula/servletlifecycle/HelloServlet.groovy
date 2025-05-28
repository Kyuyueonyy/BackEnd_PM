package org.scoula.servletlifecycle

import javax.servlet.http.*
import javax.servlet.annotation.*

//@WebServlet(name = "helloServlet", value = "/hello-servlet")
class HelloServlet extends HttpServlet {
    private String message

    void init() {
        message = "서블릿 입문!"
    }

    void doGet(HttpServletRequest request, HttpServletResponse response) {
        //MIME 타입 설정
        response.setContentType("text/html;charset=URF-8")

        // Hello
        PrintWriter out = response.getWriter()
        out.println("<html><body>")
        out.println("<h1>" + message + "</h1>")
        out.println("</body></html>");
    }

    void destroy() {
    }
}