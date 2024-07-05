package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TokenDao;
import services.EmailService;

@WebServlet("/resendVerification")
public class ResendVerificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        TokenDao tokendao = new TokenDao();
        
        String verificationCode = tokendao.getVerificationCode(email);
        if (verificationCode != null) {
            EmailService.sendVerificationEmail(email, verificationCode);
            request.setAttribute("email", email);
            request.setAttribute("message", "Verification code resent successfully.");
        } else {
            request.setAttribute("errorMessage", "Unable to resend verification code.");
        }
        request.getRequestDispatcher("WEB-INF/views/verify.jsp").forward(request, response);
    }
}
