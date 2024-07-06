package servlets;

import dao.UserDao;
import dao.TokenDao;
import services.EmailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/forgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/forgotPassword.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        UserDao userDao = new UserDao();
        TokenDao tokenDao = new TokenDao();

        if (userDao.emailCheck(email)) {
            String resetCode = tokenDao.getResetCode(email);
            if (resetCode == null) {
                resetCode = UUID.randomUUID().toString().substring(0, 4);
                tokenDao.saveResetCode(email, resetCode);
            }
            
            EmailService.sendResetPasswordEmail(email, resetCode);
            HttpSession session = request.getSession();
            session.setAttribute("email", email); // Set email in session
            request.setAttribute("email", email); // Set email in request
            request.getRequestDispatcher("WEB-INF/views/resetPassword.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Email does not exist.");
            request.getRequestDispatcher("WEB-INF/views/forgotPassword.jsp").forward(request, response);
        }
    }
}
