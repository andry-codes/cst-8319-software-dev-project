package controllers;

import models.Exercise;
import dao.ExerciseDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class SearchCategoryController implements Controller {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    private void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
