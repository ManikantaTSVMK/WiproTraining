<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Task Comments</title></head>
<body>
<h2>Comments for Task ${taskId}</h2>
<ul>
    <c:forEach var="c" items="${comments}">
        <li><b>${c.user.username}:</b> ${c.content}</li>
    </c:forEach>
</ul>
<form action="${pageContext.request.contextPath}/user/comments/add" method="post">
    <input type="hidden" name="taskId" value="${taskId}"/>
    <input type="hidden" name="userId" value="${comment.user.id}"/>
    <textarea name="content"></textarea><br/>
    <input type="submit" value="Add Comment"/>
</form>
<a href="${pageContext.request.contextPath}/user/tasks?userId=${comment.user.id}">Back to Tasks</a>
</body>
</html>
