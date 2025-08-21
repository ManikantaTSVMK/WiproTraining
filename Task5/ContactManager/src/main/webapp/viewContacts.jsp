<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.contactmanager.Contact" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>View Contacts</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <h2>My Contacts</h2>

  <%
    String flashSuccess = (String) session.getAttribute("flash_success");
    String flashError = (String) session.getAttribute("flash_error");
    if (flashSuccess != null && !flashSuccess.isBlank()) {
  %>
      <div class="alert alert-success"><%= flashSuccess %></div>
  <%
      session.removeAttribute("flash_success");
    }
    if (flashError != null && !flashError.isBlank()) {
  %>
      <div class="alert alert-danger"><%= flashError %></div>
  <%
      session.removeAttribute("flash_error");
    }
  %>

  <div class="d-flex gap-2 mb-3">
    <a href="addContact.jsp" class="btn btn-primary">Add Contact</a>
  </div>

  <table class="table table-bordered table-striped align-middle">
    <thead>
      <tr>
        <th style="width:25%;">Name</th>
        <th style="width:30%;">Email</th>
        <th style="width:20%;">Phone</th>
        <th style="width:25%;">Actions</th>
      </tr>
    </thead>
    <tbody>
      <%
        List<Contact> list = (List<Contact>) request.getAttribute("contacts");
        if (list != null && !list.isEmpty()) {
          for (Contact c : list) {
      %>
            <tr>
              <td><%= c.getName() %></td>
              <td><%= c.getEmail() %></td>
              <td><%= c.getPhone() %></td>
              <td>
                <a class="btn btn-sm btn-outline-primary"
                   href="ContactServlet?action=edit&id=<%= c.getId() %>">Edit</a>
                <form action="ContactServlet" method="post" class="d-inline"
                      onsubmit="return confirm('Delete this contact?');">
                  <input type="hidden" name="action" value="delete"/>
                  <input type="hidden" name="id" value="<%= c.getId() %>"/>
                  <button type="submit" class="btn btn-sm btn-outline-danger">Delete</button>
                </form>
              </td>
            </tr>
      <%
          }
        } else {
      %>
          <tr><td colspan="4" class="text-center">No contacts found.</td></tr>
      <%
        }
      %>
    </tbody>
  </table>

  <a href="index.jsp" class="btn btn-secondary">Home</a>
</div>
</body>
</html>
