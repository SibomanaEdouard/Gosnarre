package Controllers;

import DbConfig.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/users")
public class GetUsersControllers extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            if (resultSet.next()) {
                do {
                    String username = resultSet.getString("username");
                    out.println(username);
                } while (resultSet.next());
            } else {
                ((PrintWriter) out).println("No Friends to chat with");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // Handle connection close error
                }
            }
        }
    }
}