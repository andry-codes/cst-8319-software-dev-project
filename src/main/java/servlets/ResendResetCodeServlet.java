package servlets;

import dao.UserDao;
import dao.TokenDao;
import services.EmailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/resendResetCode")
public class ResendResetCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        UserDao userdao = new UserDao();
        TokenDao tokendao = new TokenDao();

        if (userdao.emailCheck(email)) {
            String resetCode = tokendao.getResetCode(email);
            if (resetCode == null) {
                resetCode = UUID.randomUUID().toString().substring(0, 4);
                tokendao.saveResetCode(email, resetCode);
            }
            EmailService.sendResetPasswordEmail(email, resetCode);
            request.setAttribute("email", email);
            request.setAttribute("message", "Reset code resent successfully.");
        } else {
            request.setAttribute("errorMessage", "Email does not exist.");
        }
        request.getRequestDispatcher("WEB-INF/views/resetPassword.jsp").forward(request, response);
    }
}
