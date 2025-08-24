<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Admin Dashboard</title></head>
<body>
<h2>Dashboard - All Tasks</h2>
<table border="1">
    <tr>
        <th>ID</th><th>Title</th><th>User</th><th>Status</th><th>Start</th><th>Due</th>
    </tr>
    <c:forEach var="task" items="${tasks}">
        <tr>
            <td>${task.id}</td>
            <td>${task.title}</td>
            <td>${task.user.username}</td>
            <td>${task.status}</td>
            <td>${task.startDate}</td>
            <td>${task.dueDate}</td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/admin/users">Manage Users</a><br/>
<a href="${pageContext.request.contextPath}/admin/allocate">Allocate Task</a><br/>
<a href="${pageContext.request.contextPath}/auth/logout">Logout</a>
</body>
</html>
