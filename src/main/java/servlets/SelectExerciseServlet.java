package servlets;

import dao.ExerciseDao;
import models.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/selectExercise")
public class SelectExerciseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String exerciseId = request.getParameter("exerciseId");
        if (exerciseId != null && !exerciseId.isEmpty()) {
            ExerciseDao exerciseDao = new ExerciseDao();
            Exercise exercise = exerciseDao.getExerciseById(Integer.parseInt(exerciseId));
            request.setAttribute("exercise", exercise);
            request.getRequestDispatcher("WEB-INF/views/exercise.jsp").forward(request, response);
        } else {
            response.sendRedirect("selectCategory");
        }
    }
}