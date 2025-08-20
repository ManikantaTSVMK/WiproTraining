<%@ page import="java.util.List" %>
<%@ page import="com.contactmanager.Contact" %>
<%
    List<Contact> contacts = (List<Contact>) request.getAttribute("contacts");
%>
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
        <a href="addContact.jsp" class="btn btn-primary mb-3">Add Contact</a>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                </tr>
            </thead>
            <tbody>
                <% if (contacts != null && !contacts.isEmpty()) {
                    for (Contact c : contacts) { %>
                        <tr>
                            <td><%= c.getName() %></td>
                            <td><%= c.getEmail() %></td>
                            <td><%= c.getPhone() %></td>
                        </tr>
                <%  }
                   } else { %>
                        <tr><td colspan="3" class="text-center">No contacts found.</td></tr>
                <% } %>
            </tbody>
        </table>
        <a href="index.jsp" class="btn btn-secondary">Home</a>
    </div>
</body>
</html>
