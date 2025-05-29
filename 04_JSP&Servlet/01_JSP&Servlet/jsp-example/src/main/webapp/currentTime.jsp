<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2025-05-29
  Time: 오후 2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--현재 시간 출력--%>
<h2>현재 시간</h2>
<p>
<%
    Date now = new Date();
    out.println(now.toString());
%>
</p>
</body>
</html>
