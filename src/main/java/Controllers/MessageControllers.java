package Controllers;

import DbConfig.DbConnection;
import Models.Messages;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
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
Connection connection= DbConnection.getConnection();

    public MessageControllers() throws SQLException, ClassNotFoundException {
    }

    public void init() throws ServletException {
    super.init();
    createMessageTable();

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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out=res.getWriter();
       HttpSession session=req.getSession();;
    String message=req.getParameter("message");
    String sender = (String) session.getAttribute("id");
    String receiver=req.getParameter("receiver");
        Messages messages=new Messages();
        messages.setMessage(message);
        messages.setReceiver(receiver);
        messages.setSender(sender);
    try{
        PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO messages (message,receiver,sender) VALUES (?,?,?)");
        preparedStatement.setString(1,messages.getMessage());
        preparedStatement.setString(2,messages.getReceiver());
        preparedStatement.setString(3,messages.getSender());
        int resultSet= preparedStatement.executeUpdate();
        if(resultSet>0){
            out.println("Message sent successfully!");

        }else {
            out.println("Failed to send message!");
        }
    }catch (Exception e){
        System.out.println(e.getMessage());
    }



    }

}
