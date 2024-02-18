//package Controllers;
//
//import DbConfig.DbConnection;
//import Models.Messages;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@WebServlet("/sendMessage")
//public class MessageControllers extends HttpServlet {
//Connection connection= DbConnection.getConnection();
//
//    public MessageControllers() throws SQLException, ClassNotFoundException {
//    }
//
//    public void init() throws ServletException {
//    super.init();
//    createMessageTable();
//
//}
//    private void createMessageTable() {
//        try {
//            String query = "CREATE TABLE IF NOT EXISTS messages(" +
//                    "id SERIAL PRIMARY KEY, " +
//                    "message varchar(255) not null, " +
//                    "receiver varchar(255) not null, " +
//                    "sender varchar(255) not null)";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            int resultSet = preparedStatement.executeUpdate();
//            if (resultSet > 0) {
//                System.out.println("Table of messages was created successfully!");
//            } else {
//                System.out.println("Failed to create table!");
//            }
//            preparedStatement.close(); // Close the PreparedStatement
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        PrintWriter out=res.getWriter();
//       HttpSession session=req.getSession();
//    String message=req.getParameter("message");
//    String sender = (String) session.getAttribute("id");
//    String receiver=req.getParameter("receiver");
//        Messages messages=new Messages();
//        messages.setMessage(message);
//        messages.setReceiver(receiver);
//        messages.setSender(sender);
//    try{
//        PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO messages (message,receiver,sender) VALUES (?,?,?)");
//        preparedStatement.setString(1,messages.getMessage());
//        preparedStatement.setString(2,messages.getReceiver());
//        preparedStatement.setString(3,messages.getSender());
//        int resultSet= preparedStatement.executeUpdate();
//        if(resultSet>0){
//            out.println("Message sent successfully!");
//
//        }else {
//            out.println("Failed to send message!");
//        }
//
//    }catch (Exception e){
//        System.out.println(e.getMessage());
//    }
//
//
//
//    }
//
//
//
//    //this is the function to get messages
//    public void getReceivedMessages(HttpServletRequest req,HttpServletResponse res) throws IOException {
//        PrintWriter out =res.getWriter();
//        res.setContentType("text/html");
//        HttpSession session=req.getSession();
//        String sender = (String) session.getAttribute("id");
//        String receiver=req.getParameter("receiver");
//        try{
//            PreparedStatement preparedStatement=connection.prepareStatement("SELECT message FROM messages WHERE sender= ?  AND receiver= ?");
//            preparedStatement.setString(1,sender);
//            preparedStatement.setString(2,receiver);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            out.println("<ul>");
//
//            while (resultSet.next()) {
//                String message = resultSet.getString("message");
//                int messageId = resultSet.getInt("id");
//                // Generate a link for each user
//                out.println("<li><a href='Message.jsp?userId=" + messageId + "'>" + message + "</a></li>");
//            }
//
//            out.println("</ul>");
//            out.println("</body></html>");
//
//            resultSet.close();
//            preparedStatement.close();
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//
//
//    }
//
//    public void getSentMessages(HttpServletRequest req, HttpServletResponse res) throws  IOException{
//
//        PrintWriter out =res.getWriter();
//        res.setContentType("text/html");
//        HttpSession session=req.getSession();
//        String sender = (String) session.getAttribute("id");
//        String receiver=req.getParameter("receiver");
//        try{
//            PreparedStatement preparedStatement=connection.prepareStatement("SELECT message FROM messages WHERE sender= ?  AND receiver= ?");
//            preparedStatement.setString(1,receiver);
//            preparedStatement.setString(2,sender);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            out.println("<ul>");
//
//            while (resultSet.next()) {
//                String message = resultSet.getString("message");
//                int messageId = resultSet.getInt("id");
//                // Generate a link for each user
//                out.println("<li><a href='Message.jsp?userId=" + messageId + "'>" + message + "</a></li>");
//            }
//
//            out.println("</ul>");
//            out.println("</body></html>");
//
//            resultSet.close();
//            preparedStatement.close();
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//    }
//
//}


package Controllers;

import DbConfig.DbConnection;
import Models.Messages;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/sendMessage")
public class MessageControllers extends HttpServlet {
    Connection connection;

    public MessageControllers() {
        try {
            connection = DbConnection.getConnection();
            createMessageTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createMessageTable() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS messages(" +
                    "id SERIAL PRIMARY KEY, " +
                    "message varchar(255) not null, " +
                    "receiver varchar(255) not null, " +
                    "sender varchar(255) not null)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet > 0) {
                System.out.println("Table of messages was created successfully!");
            } else {
                System.out.println("Failed to create table!");
            }
            preparedStatement.close(); // Close the PreparedStatement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        String action = req.getParameter("action");
        if (action != null && !action.isEmpty()) {
            switch (action) {
                case "sendMessage":
                    sendMessage(req, res);
                    break;
                case "viewSentMessages":
                    getSentMessages(req, res);
                    break;
                case "viewReceivedMessages":
                    getReceivedMessages(req, res);
                    break;
                default:
                    res.getWriter().println("Invalid action");
            }
        }
    }

    private void sendMessage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        String message = req.getParameter("message");
        String sender = (String) session.getAttribute("id");
        String receiver = req.getParameter("receiver");
        Messages messages = new Messages();
        messages.setMessage(message);
        messages.setReceiver(receiver);
        messages.setSender(sender);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO messages (message,receiver,sender) VALUES (?,?,?)");
            preparedStatement.setString(1, messages.getMessage());
            preparedStatement.setString(2, messages.getReceiver());
            preparedStatement.setString(3, messages.getSender());
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet > 0) {
                out.println("Message sent successfully!");
            } else {
                out.println("Failed to send message!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getReceivedMessages(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        String sender = (String) session.getAttribute("id");
        String receiver = req.getParameter("receiver");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, message FROM messages WHERE sender= ?  AND receiver= ?");
            preparedStatement.setString(2, sender);
            preparedStatement.setString(1, receiver);
            ResultSet resultSet = preparedStatement.executeQuery();
            displayMessages(out, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getSentMessages(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        String sender = (String) session.getAttribute("id");
        String receiver = req.getParameter("receiver");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id ,message FROM messages WHERE sender= ?  AND receiver= ?");
            preparedStatement.setString(2, receiver);
            preparedStatement.setString(1, sender);
            ResultSet resultSet = preparedStatement.executeQuery();
            displayMessages(out, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayMessages(PrintWriter out, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String message = resultSet.getString("message");
            // Remove the points at the start of the message
            message = message.trim(); // Trim any leading or trailing whitespace
            out.println("<div style='background-color: grey; padding: 10px; border: 1px solid black; border-radius: 10px; margin-top: 10px; margin-bottom: 10px; width:50%'>" + message + "</div>");
        }
    }

}
