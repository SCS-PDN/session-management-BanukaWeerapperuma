import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Course;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseId = request.getParameter("courseId");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        // Hardcoded list of courses
        List<Course> allCourses = new ArrayList<>();
        allCourses.add(new Course("101", "Data Structures", "Dr. Smith"));
        allCourses.add(new Course("102", "Web Programming", "Prof. Lee"));
        allCourses.add(new Course("103", "Databases", "Dr. Johnson"));

        Course selectedCourse = null;
        for (Course c : allCourses) {
            if (c.getId().equals(courseId)) {
                selectedCourse = c;
                break;
            }
        }

        if (selectedCourse != null) {
            List<Course> enrolled = (List<Course>) session.getAttribute("enrolledCourses");
            if (enrolled == null) {
                enrolled = new ArrayList<>();
            }

            boolean alreadyEnrolled = false;
            for (Course c : enrolled) {
                if (c.getId().equals(selectedCourse.getId())) {
                    alreadyEnrolled = true;
                    break;
                }
            }

            if (!alreadyEnrolled) {
                enrolled.add(selectedCourse);
                session.setAttribute("enrolledCourses", enrolled);
            }
        }

        response.sendRedirect("DashboardServlet?message=Enrolled+successfully");
    }
}