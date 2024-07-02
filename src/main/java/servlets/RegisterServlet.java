package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Register;
import dao.RegistrationDao;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String email = request.getParameter("user_email");
    	String username = request.getParameter("new_user");
    	String password = request.getParameter("new_password");
    	RegistrationDao dao = new RegistrationDao();
    	
    	/* No check if password & confirm password match here since the html/jsp form already requires that. 
    	 * Could be implemented though.
    	 * However, check to see if user name already exists in DB and forwards an error message to the .jsp page
    	 * to inform user. */
    	if (dao.userCheck(username)) {
    		request.setAttribute("errorMessage", "Username already exists.");
            request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
            return;
    	}
    	
    	/* same thing as above, but with email address. */ 
    	if (dao.emailCheck(email)) {
    		request.setAttribute("errorMessage", "Email already exists.");
            request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
            return;
    	}
    	
    	/* creates new user, and prints a new page alerting user to validate account, along with a link to return to the login page. */
    	Register newUser = new Register(email, username, password);
    	dao.newUser(newUser);
    	
    	PrintWriter writer = response.getWriter();
    	writer.print("<p>New user " + username + " created successfully.</p>");
    	writer.print("<p>A validation link has been sent to your email address. You must validate your account before your first log in.</p>");
    	writer.print("<p>Click <a href='login'>here</a> to return to login form.</p>");

   }
}