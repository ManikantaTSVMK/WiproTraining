<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Allocate Task</title></head>
<body>
<h2>Allocate Task</h2>
<form action="${pageContext.request.contextPath}/admin/allocate/save" method="post">
    Title: <input type="text" name="title"/><br/>
    Description: <textarea name="description"></textarea><br/>
    Start Date: <input type="date" name="startDate"/><br/>
    Due Date: <input type="date" name="dueDate"/><br/>
    Assign To:
    <select name="user.id">
        <c:forEach var="u" items="${users}">
            <option value="${u.id}">${u.username}</option>
        </c:forEach>
    </select><br/>
    <input type="submit" value="Allocate"/>
</form>
<a href="${pageContext.request.contextPath}/admin/dashboard">Back to Dashboard</a>
</body>
</html>
