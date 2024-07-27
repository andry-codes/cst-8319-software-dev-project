package servlets;

import dao.ExerciseDao;
import models.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/searchExercise")
public class SearchExerciseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (query != null && !query.trim().isEmpty()) {
            ExerciseDao exerciseDao = new ExerciseDao();
            List<Exercise> exercises = exerciseDao.searchExercises(query);
            if (exercises != null && !exercises.isEmpty()) {
                for (Exercise exercise : exercises) {
                    out.println("<div>");
                    out.println("<a href='selectExercise?exerciseId=" + exercise.getId() + "'>" + exercise.getName() + "</a>");
                    out.println("</div>");
                }
            } else {
                out.println("<p>No exercises found matching your search criteria.</p>");
            }
        }
    }
}
