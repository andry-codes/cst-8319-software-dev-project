package servlets;

import dao.UserDao;
import dao.TokenDao;
import services.EmailService;
import beans.Register;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/verify")
public class VerifyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        String usernameOrEmail = (String) session.getAttribute("usernameOrEmail");
        if (usernameOrEmail == null) {
            response.sendRedirect("login");
            return;
        }

        UserDao userDao = new UserDao();
        Register user = userDao.getUser(usernameOrEmail);
        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        String email = user.getEmail();
        session.setAttribute("email", email);
        session.setAttribute("usernameOrEmail", usernameOrEmail);

        request.setAttribute("email", email);
        request.getRequestDispatcher("WEB-INF/views/verify.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String verificationCode = request.getParameter("verification_code");
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        String email = (String) session.getAttribute("email");
        if (email == null) {
            String usernameOrEmail = (String) session.getAttribute("usernameOrEmail");
            if (usernameOrEmail == null) {
                response.sendRedirect("login");
                return;
            }

            UserDao userDao = new UserDao();
            Register user = userDao.getUser(usernameOrEmail);
            if (user == null) {
                response.sendRedirect("login");
                return;
            }
            email = user.getEmail();
            session.setAttribute("email", email);
        }

        TokenDao tokenDao = new TokenDao();

        if ("true".equals(request.getParameter("resend"))) {
            String newVerificationCode = UUID.randomUUID().toString().substring(0, 4);
            tokenDao.saveVerificationCode(email, newVerificationCode);
            EmailService.sendVerificationEmail(email, newVerificationCode);

            request.setAttribute("email", email);
            request.setAttribute("message", "Verification code resent successfully.");
            request.getRequestDispatcher("WEB-INF/views/verify.jsp").forward(request, response);
        } else {
            if (tokenDao.validateVerificationCode(email, verificationCode)) {
                UserDao userDao = new UserDao();
                userDao.markUserAsVerified(email); // Mark the user as verified
                tokenDao.deleteVerificationToken(email, verificationCode, "verification"); // Delete the verification token
                response.sendRedirect("homepage");
            } else {
                request.setAttribute("errorMessage", "Invalid verification code.");
                request.setAttribute("email", email);
                request.getRequestDispatcher("WEB-INF/views/verify.jsp").forward(request, response);
            }
        }
    }
}