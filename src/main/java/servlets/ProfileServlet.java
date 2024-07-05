package servlets;

import beans.Profile;
import dao.ProfileDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login");
        } else {
            ProfileDao dao = new ProfileDao();
            Profile profile = dao.getProfileByUserId(userId);
            if (profile != null) {
                request.setAttribute("firstName", profile.getFirstName());
                request.setAttribute("lastName", profile.getLastName());
                request.setAttribute("age", profile.getAge());
                request.setAttribute("gender", profile.getGender());
                request.setAttribute("weight", profile.getWeight());
                request.setAttribute("height", profile.getHeight());
            }
            request.getRequestDispatcher("WEB-INF/views/profile.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login");
        } else {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            int age = Integer.parseInt(request.getParameter("age"));
            String gender = request.getParameter("gender");
            int weight = Integer.parseInt(request.getParameter("weight"));
            int height = Integer.parseInt(request.getParameter("height"));

            Profile profile = new Profile(userId, firstName, lastName, age, gender, weight, height);
            ProfileDao dao = new ProfileDao();
            dao.saveOrUpdateProfile(profile);

            request.setAttribute("message", "Profile updated successfully.");
            doGet(request, response);
        }
    }
}
