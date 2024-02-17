package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageControllers extends HttpServlet {
Connection connection;
public void init() throws ServletException {
    super.init();
    createMessageTable();

}
private void createMessageTable(){
try {


    PreparedStatement preparedStatement= (PreparedStatement) connection.createStatement();
    String query="CREATE TABLE IF NOT EXIST messages(" +
            "id SERIAL PRIMARY KEY , " +
            "messages varchar(255) not null , " +
            "receiver  varchar(255) not null , " +
            "sender varchar(255) not null " +
            " )";
    preparedStatement.executeUpdate();
    System.out.println("Table of messages was created successfully!");


}catch (Exception e){
    System.out.println(e.getMessage());
}
}
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
    String message=req.getParameter("message");
//    String sender=
    try{
        PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO messages (message,receiver,sender)");
        preparedStatement.executeUpdate();
    }catch (Exception e){
        System.out.println(e.getMessage());
    }



    }

}
