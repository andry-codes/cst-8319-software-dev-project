package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.RegistrationDao;

@WebServlet("/resetPassword")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/resetPassword.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String resetCode = request.getParameter("reset_code");
        String newPassword = request.getParameter("new_password");
        String confirmPassword = request.getParameter("confirm_password");

        RegistrationDao dao = new RegistrationDao();

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.getRequestDispatcher("WEB-INF/views/resetPassword.jsp").forward(request, response);
            return;
        }

        if (dao.verifyUser(email, resetCode)) {
            dao.updatePassword(email, newPassword);
            request.setAttribute("message", "Password reset successful. You can now login.");
            request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Invalid reset code.");
            request.getRequestDispatcher("WEB-INF/views/resetPassword.jsp").forward(request, response);
        }
    }
}
