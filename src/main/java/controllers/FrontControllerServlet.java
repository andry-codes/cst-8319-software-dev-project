package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/*")
public class FrontControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Map<String, Controller> controllers = new HashMap<>();

    @Override
    public void init() throws ServletException {
    	controllers.put("forgotPassword", new ForgotPasswordController());
    	controllers.put("homepage", new HomepageController());
    	controllers.put("login", new LoginController());
    	controllers.put("logout", new LogoutController());
    	controllers.put("mentalHealthActivities", new MentalHealthActivitiesController());
    	controllers.put("physicalExercises", new PhysicalExercisesController());
    	controllers.put("profile", new ProfileController());
    	controllers.put("register", new RegisterController());
    	controllers.put("resendResetCode", new ResendResetCodeController());
    	controllers.put("resendVerification", new ResendVerificationController());
    	controllers.put("resetPassword", new ResetPasswordController());
    	controllers.put("selectCategory", new SearchCategoryController());
    	controllers.put("searchExercise", new SearchExerciseController());
    	controllers.put("selectCategory", new SelectCategoryController());
    	controllers.put("selectExercise", new SelectExerciseController());
    	controllers.put("verify", new VerifyController());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo().substring(1);
        Controller command = controllers.get(path);
        if (command != null) {
            command.execute(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
