<%--
  Created by IntelliJ IDEA.
  User: edouard
  Date: 2024-02-17
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gosnarre</title>
</head>
<body>
<h4>Talk with friends</h4>

<form action="sendMessage" method="post">
    <input type="hidden" name="receiver" value="<%= request.getParameter("userId") %>">
    <label for="message">Message:</label><br>
    <textarea id="message" name="message" rows="4" cols="50" required></textarea><br>
    <input type="submit" value="Send Message">
</form>

</body>
</html>
