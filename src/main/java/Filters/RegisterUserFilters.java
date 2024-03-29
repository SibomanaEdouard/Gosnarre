package Filters;

import DbConfig.DbConnection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebFilter("/register") // Specify the URL pattern to which this filter should be applied
public class RegisterUserFilters implements Filter {
    Connection connection;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            // Initialize database connection
            connection = DbConnection.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int age = Integer.parseInt(request.getParameter("age"));

        try {
            // Check if the email is already in the database
            PreparedStatement emailCheckStmt = connection.prepareStatement("SELECT email FROM users WHERE email = ?");
            emailCheckStmt.setString(1, email);
            ResultSet emailResult = emailCheckStmt.executeQuery();

            PreparedStatement phoneCheck=connection.prepareStatement("SELECT phone FROM users WHERE phone=?");
            phoneCheck.setString(1,phone);
            ResultSet phoneResult=phoneCheck.executeQuery();

            if (emailResult.next() || phoneResult.next()) {
                response.getWriter().println("Email or phonenumber  already exists in the database. Please use a different email or phone.");
            }else {
                // If email is not in the database and age is valid, proceed with the chain
                chain.doFilter(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred. Please try again later.");
        }
    }

    @Override
    public void destroy() {
        // Close database connection
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
