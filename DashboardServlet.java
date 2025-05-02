import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Course;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("username");
        request.setAttribute("username", username);

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("101", "Data Structures", "Dr. Namal"));
        courses.add(new Course("102", "Web Programming", "Prof. Kasun"));
        courses.add(new Course("103", "Databases", "Dr. Saman"));
        request.setAttribute("courses", courses);

        List<Course> enrolled = (List<Course>) session.getAttribute("enrolledCourses");
        if (enrolled == null) {
            enrolled = new ArrayList<>();
        }
        request.setAttribute("enrolledCourses", enrolled);

        String message = request.getParameter("message");
        if (message != null) {
            request.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
