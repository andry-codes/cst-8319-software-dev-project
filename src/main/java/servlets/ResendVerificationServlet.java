package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.RegistrationDao;
import services.EmailService;

@WebServlet("/resendVerification")
public class ResendVerificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        RegistrationDao dao = new RegistrationDao();

        String verificationCode = dao.getVerificationCode(email);
        if (verificationCode != null) {
            EmailService.sendVerificationEmail(email, verificationCode);
        } else {
            request.setAttribute("errorMessage", "No verification code found for this email.");
        }

        request.setAttribute("email", email);
        request.getRequestDispatcher("WEB-INF/views/verify.jsp").forward(request, response);
    }
}
