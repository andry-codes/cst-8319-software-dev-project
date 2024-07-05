package servlets;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Register;
import dao.RegistrationDao;
import services.EmailService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("user_email");
        String username = request.getParameter("new_user");
        String password = request.getParameter("new_password");
        RegistrationDao dao = new RegistrationDao();

        if (dao.userCheck(username)) {
            request.setAttribute("errorMessage", "Username already exists.");
            request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
            return;
        }

        if (dao.emailCheck(email)) {
            request.setAttribute("errorMessage", "Email already exists.");
            request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
            return;
        }

        Register newUser = new Register(email, username, password);
        dao.newUser(newUser);

        // Create and save verification token
        String verificationCode = UUID.randomUUID().toString().substring(0, 4);
        dao.saveVerificationCode(email, verificationCode);

        // Send verification email
        EmailService.sendVerificationEmail(email, verificationCode);

        // Redirect to verification page
        request.setAttribute("email", email);
        response.sendRedirect("verify");
    }
}
