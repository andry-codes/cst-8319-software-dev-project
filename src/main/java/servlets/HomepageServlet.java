package servlets;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/homepage")
public class HomepageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            response.sendRedirect("login");
            return;
        }
        
        UserDao userdao = new UserDao();
      
        if (!userdao.isUserVerified(userId)) {
        	response.sendRedirect("verify");
        	return;
        }
        
        String username = (String) session.getAttribute("username");
        
        if (username == null) {
        	response.sendRedirect("login");
        	return;
       }
        request.getRequestDispatcher("WEB-INF/views/homepage.jsp").forward(request, response);
    }
}
