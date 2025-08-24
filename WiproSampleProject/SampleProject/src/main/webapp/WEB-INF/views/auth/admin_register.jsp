<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Registration - WorkNest</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg">
                <div class="card-header bg-primary text-white text-center">
                    <h4>Admin Registration</h4>
                </div>
                <div class="card-body">
                    <form:form method="post" modelAttribute="user" action="${pageContext.request.contextPath}/auth/register/admin">
                        <div class="mb-3">
                            <label class="form-label">Username</label>
                            <form:input path="username" cssClass="form-control"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <form:input path="email" cssClass="form-control" type="email"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <form:password path="password" cssClass="form-control"/>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-success">Register Admin</button>
                        </div>
                    </form:form>
                    <c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>
                    
                </div>
                <div class="card-footer text-center">
                    <a href="${pageContext.request.contextPath}/auth/admin_login">Back to Login</a>
                </div>
                
            </div>
        </div>
    </div>
</div>
</body>
</html>
