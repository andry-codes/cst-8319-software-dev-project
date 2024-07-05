package servlets;

import services.EmailService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Register;
import dao.UserDao;
import dao.TokenDao;

import java.util.UUID;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Ensure UTF-8 encoding for parameters
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (email == null || email.isEmpty()) {
            request.setAttribute("errorMessage", "Email is required.");
            request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
            return;
        }

        UserDao userdao = new UserDao();
        TokenDao tokendao = new TokenDao();
        if (userdao.userCheck(username)) {
            request.setAttribute("errorMessage", "Username already exists.");
            request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
            return;
        }

        if (userdao.emailCheck(email)) {
            request.setAttribute("errorMessage", "Email already exists.");
            request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
            return;
        }

        Register newUser = new Register(email, username, password);
        userdao.newUser(newUser);

        String verificationCode = UUID.randomUUID().toString().substring(0, 4);
        EmailService.sendVerificationEmail(email, verificationCode);
        tokendao.saveVerificationCode(email, verificationCode);

        HttpSession session = request.getSession();
        int userId = userdao.getUserIdByEmail(email);
        session.setAttribute("userId", userId); // Store userID in session
        session.setAttribute("email", email); // Store email in session

        request.setAttribute("email", email);
        request.getRequestDispatcher("WEB-INF/views/verify.jsp").forward(request, response);
    }
}
