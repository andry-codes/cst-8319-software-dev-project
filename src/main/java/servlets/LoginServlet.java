package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.RegistrationDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usernameOrEmail = request.getParameter("username_or_email");
        String password = request.getParameter("password");
        RegistrationDao dao = new RegistrationDao();

        if (dao.validateUser(usernameOrEmail, password)) {
            if (!dao.isUserVerified(usernameOrEmail)) {
                request.setAttribute("email", usernameOrEmail);
                response.sendRedirect("verify");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("username", usernameOrEmail);
                request.getRequestDispatcher("WEB-INF/views/homepage.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Invalid username/email or password.");
            request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}
