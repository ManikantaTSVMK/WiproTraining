<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Add Contact</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <h2>Add Contact</h2>

  <%
     Object errs = request.getAttribute("errors");
     if (errs != null && errs instanceof java.util.List && !((java.util.List)errs).isEmpty()) {
  %>
    <div class="alert alert-danger">
      <ul class="mb-0">
        <%
          for (Object e : (java.util.List)errs) {
            if (e != null && !e.toString().isBlank()) {
        %>
              <li><%= e %></li>
        <%
            }
          }
        %>
      </ul>
    </div>
  <% } %>

  <form action="ContactServlet" method="post" novalidate>
    <input type="hidden" name="action" value="add">
    <div class="mb-3">
      <label class="form-label">Name</label>
      <input type="text" class="form-control" name="name" required maxlength="100"
             value="<%= request.getAttribute("form_name") != null ? request.getAttribute("form_name") : "" %>">
    </div>
    <div class="mb-3">
      <label class="form-label">Email</label>
      <input type="email" class="form-control" name="email" required maxlength="120"
             value="<%= request.getAttribute("form_email") != null ? request.getAttribute("form_email") : "" %>">
    </div>
    <div class="mb-3">
      <label class="form-label">Phone</label>
      <input type="text" class="form-control" name="phone" required maxlength="20"
             value="<%= request.getAttribute("form_phone") != null ? request.getAttribute("form_phone") : "" %>">
    </div>
    <button type="submit" class="btn btn-primary">Add Contact</button>
    <a href="ContactServlet" class="btn btn-secondary">View Contacts</a>
  </form>
</div>
</body>
</html>
