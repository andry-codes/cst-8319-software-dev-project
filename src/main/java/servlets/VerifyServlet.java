package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.RegistrationDao;

@WebServlet("/verify")
public class VerifyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/verify.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String verificationCode = request.getParameter("verification_code");
        RegistrationDao dao = new RegistrationDao();

        if (dao.verifyUser(email, verificationCode)) {
            request.setAttribute("message", "Verification successful. You can now login.");
            request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Invalid verification code.");
            request.getRequestDispatcher("WEB-INF/views/verify.jsp").forward(request, response);
        }
    }
}
