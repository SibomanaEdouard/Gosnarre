<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: edouard--%>
<%--  Date: 2024-02-17--%>
<%--  Time: 15:41--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Gosnarre</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h4>Talk with friends</h4>--%>

<%--<form action="sendMessage" method="post">--%>
<%--    <input type="hidden" name="receiver" value="<%= request.getParameter("userId") %>">--%>
<%--    <label for="message">Message</label><br>--%>
<%--    <textarea id="message" name="message" rows="4" cols="50" required></textarea><br>--%>
<%--    <input type="submit" value="Send Message">--%>
<%--</form>--%>

<%--<form action="sendMessage" method="post">--%>
<%--    <input type="hidden" name="receiver" value="<%= request.getParameter("userId") %>">--%>
<%--    <button type="submit" name="sending">view sent messages</button>--%>
<%--</form>--%>

<%--<form action="sendMessage" method="post">--%>
<%--    <input type="hidden" name="receiver" value="<%= request.getParameter("userId") %>">--%>
<%--    <button type="submit" name="receiving" value="receiving">view received messages</button>--%>
<%--</form>--%>


<%--</body>--%>
<%--</html>--%>

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
    <label for="message">Message</label><br>
    <textarea id="message" name="message" rows="4" cols="50" required></textarea><br>
    <input type="submit" name="action" value="sendMessage">
</form>

<form action="sendMessage" method="post">
    <input type="hidden" name="receiver" value="<%= request.getParameter("userId") %>">
    <input type="hidden" name="action" value="viewSentMessages">
    <button type="submit">View Sent Messages</button>
</form>

<form action="sendMessage" method="post">
    <input type="hidden" name="receiver" value="<%= request.getParameter("userId") %>">
    <input type="hidden" name="action" value="viewReceivedMessages">
    <button type="submit">View Received Messages</button>
</form>

</body>
</html>
