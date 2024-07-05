package servlets;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usernameOrEmail = request.getParameter("username_or_email");
        String password = request.getParameter("password");
        UserDao userdao = new UserDao();

        if (userdao.validateUser(usernameOrEmail, password)) {
            int userId = userdao.getUserIdByUsernameOrEmail(usernameOrEmail); // Get userID from database
            String email = userdao.getEmailByUserId(userId); // Get email from database using userID
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId); // Store userID in session
            session.setAttribute("email", email); // Store email in session

            if (!userdao.isUserVerified(usernameOrEmail)) {
                response.sendRedirect("verify");
            } else {
                request.getRequestDispatcher("WEB-INF/views/homepage.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Invalid username/email or password.");
            request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}
