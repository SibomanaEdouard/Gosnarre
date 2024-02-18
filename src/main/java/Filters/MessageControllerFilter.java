package Filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/sendMessage")
public class MessageControllerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();
        String userId = (String) session.getAttribute("id");

        if (userId == null) {
            httpResponse.sendRedirect("index.jsp");
        } else {
            // User is logged in, continue with the request
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Clean-up code if needed
    }
}
