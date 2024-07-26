package servlets;

import dao.ExerciseDao;
import models.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectCategory")
public class SelectCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        if (category != null && !category.isEmpty()) {
            ExerciseDao exerciseDao = new ExerciseDao();
            List<Exercise> exercises = exerciseDao.getExercisesByCategory(category);
            request.setAttribute("category", category);
            request.setAttribute("exercises", exercises);
            request.getRequestDispatcher("WEB-INF/views/category.jsp").forward(request, response);
        } else {
            response.sendRedirect("physicalExercises");
        }
    }
}
