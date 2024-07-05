package servlets;

import dao.TokenDao;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/resetPassword")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        if (email != null) {
            request.setAttribute("email", email);
        }
        request.getRequestDispatcher("WEB-INF/views/resetPassword.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String resetCode = request.getParameter("reset_code");
        String newPassword = request.getParameter("new_password");
        
        TokenDao tokendao = new TokenDao();
        UserDao userdao = new UserDao();
        
        if (resetCode != null && newPassword != null) {
            if (tokendao.validateResetCode(email, resetCode)) {
                userdao.updatePassword(email, newPassword);
                tokendao.deleteVerificationToken(email, resetCode, "reset");
                request.setAttribute("message", "Password reset successfully. Please login with your new password.");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Invalid reset code.");
                request.setAttribute("email", email);
                request.getRequestDispatcher("WEB-INF/views/resetPassword.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Please provide a reset code and a new password.");
            request.getRequestDispatcher("WEB-INF/views/resetPassword.jsp").forward(request, response);
        }
    }
}
