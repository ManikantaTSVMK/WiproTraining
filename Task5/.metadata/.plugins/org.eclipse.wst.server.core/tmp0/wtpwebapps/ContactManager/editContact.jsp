<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.contactmanager.Contact" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit Contact</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <h2>Edit Contact</h2>

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

  <%
    Contact contact = (Contact) request.getAttribute("contact");
    Integer contactId = (Integer) request.getAttribute("contact_id");
    String nameVal = contact != null ? contact.getName() : String.valueOf(request.getAttribute("form_name") != null ? request.getAttribute("form_name") : "");
    String emailVal = contact != null ? contact.getEmail() : String.valueOf(request.getAttribute("form_email") != null ? request.getAttribute("form_email") : "");
    String phoneVal = contact != null ? contact.getPhone() : String.valueOf(request.getAttribute("form_phone") != null ? request.getAttribute("form_phone") : "");
    int idVal = contact != null ? contact.getId() : (contactId != null ? contactId : 0);
  %>

  <form action="ContactServlet" method="post" novalidate>
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="<%= idVal %>">
    <div class="mb-3">
      <label class="form-label">Name</label>
      <input type="text" class="form-control" name="name" required maxlength="100" value="<%= nameVal %>">
    </div>
    <div class="mb-3">
      <label class="form-label">Email</label>
      <input type="email" class="form-control" name="email" required maxlength="120" value="<%= emailVal %>">
    </div>
    <div class="mb-3">
      <label class="form-label">Phone</label>
      <input type="text" class="form-control" name="phone" required maxlength="20" value="<%= phoneVal %>">
    </div>
    <button type="submit" class="btn btn-primary">Update</button>
    <a href="ContactServlet" class="btn btn-secondary">Cancel</a>
  </form>
</div>
</body>
</html>
