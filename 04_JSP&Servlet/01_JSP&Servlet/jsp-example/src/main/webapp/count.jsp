<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2025-05-29
  Time: 오후 3:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //application
    //서버 내부에서 공유되는 객체
    Integer count = (Integer)application.getAttribute("count");

    if(count == null) {
        count = 1;
    } else count++;

    application.setAttribute("count", count);
%>
<p>현재까지 방문자수 : <%= count%></p>
</body>
</html>
