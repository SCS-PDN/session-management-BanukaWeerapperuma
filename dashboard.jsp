<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Course Dash board</title>
</head>
<body>
    <h1>Welcome, ${username}!</h1>
    <a href="LogoutServlet">Logout</a>

    <c:if test="${not empty message}">
        <p style="color:green;">${message}</p>
    </c:if>

    <h2>Available Courses</h2>
    <table border="1">
        <tr>
            <th>Course ID</th>
            <th>Course Name</th>
            <th>Instructor</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${courses}" var="course">
            <tr>
                <td>${course.id}</td>
                <td>${course.name}</td>
                <td>${course.instructor}</td>
                <td><a href="EnrollServlet?courseId=${course.id}">Enroll</a></td>
            </tr>
        </c:forEach>
    </table>

    <h2>Your Enrolled Courses</h2>
    <ul>
        <c:forEach items="${enrolledCourses}" var="course">
            <li>${course.name} (${course.id})</li>
        </c:forEach>
    </ul>
</body>
</html>