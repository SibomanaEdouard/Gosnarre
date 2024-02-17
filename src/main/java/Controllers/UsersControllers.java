package Controllers;

import DbConfig.DbConnection;
import jakarta.servlet.http.HttpServlet;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersControllers extends HttpServlet {
    Connection connection= DbConnection.getConnection();

    public UsersControllers() throws SQLException, ClassNotFoundException {
    }
}
