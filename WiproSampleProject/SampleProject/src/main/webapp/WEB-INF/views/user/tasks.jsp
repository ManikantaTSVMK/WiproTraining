<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>My Tasks</title></head>
<body>
<h2>My Tasks</h2>
<table border="1">
    <tr>
        <th>ID</th><th>Title</th><th>Status</th><th>Start</th><th>Due</th><th>Action</th><th>Comments</th>
    </tr>
    <c:forEach var="task" items="${tasks}">
        <tr>
            <td>${task.id}</td>
            <td>${task.title}</td>
            <td>${task.status}</td>
            <td>${task.startDate}</td>
            <td>${task.dueDate}</td>
            <td>
                <form action="${pageContext.request.contextPath}/user/tasks/updateStatus" method="post">
                    <input type="hidden" name="taskId" value="${task.id}"/>
                    <input type="hidden" name="userId" value="${task.user.id}"/>
                    <select name="status">
                        <option>Pending</option>
                        <option>In Progress</option>
                        <option>Completed</option>
                    </select>
                    <input type="submit" value="Update"/>
                </form>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/user/comments/${task.id}">View Comments</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/auth/logout">Logout</a>
</body>
</html>
