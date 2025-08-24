<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Manage Users</title></head>
<body>
<h2>Manage Users</h2>
<form action="${pageContext.request.contextPath}/admin/users/save" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    Username: <input type="text" name="username" value="${user.username}"/><br/>
    Password: <input type="password" name="password" value="${user.password}"/><br/>
    <input type="submit" value="Save"/>
</form>
<hr/>
<table border="1">
    <tr>
        <th>ID</th><th>Username</th><th>Role</th><th>Actions</th>
    </tr>
    <c:forEach var="u" items="${users}">
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.role}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/users/delete/${u.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/admin/dashboard">Back to Dashboard</a>
</body>
</html>
