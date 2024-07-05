package servlets;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.RegistrationDao;
import services.EmailService;

@WebServlet("/forgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/forgotPassword.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usernameOrEmail = request.getParameter("username_or_email");
        RegistrationDao dao = new RegistrationDao();

        String email = dao.getEmailByUsernameOrEmail(usernameOrEmail);
        if (email != null) {
            String resetCode = UUID.randomUUID().toString().substring(0, 4);
            EmailService.sendResetPasswordEmail(email, resetCode);
            dao.saveVerificationCode(email, resetCode);

            request.setAttribute("email", email);
            request.getRequestDispatcher("WEB-INF/views/resetPassword.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Username/Email does not exist.");
            request.getRequestDispatcher("WEB-INF/views/forgotPassword.jsp").forward(request, response);
        }
    }
}
