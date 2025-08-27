<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manage Users - WorkNest</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, sans-serif;
            background: linear-gradient(135deg, #f8f9fa, #e9ecef);
            padding: 40px;
            text-align: center;
        }

        .container {
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.15);
            display: inline-block;
            text-align: left;
            width: 95%;
            max-width: 1100px;
            animation: fadeIn 0.7s ease-in-out;
        }

        h2, h3 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        /* 🔗 Back Link */
        p a {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }
        p a:hover {
            text-decoration: underline;
            color: #0056b3;
        }

        /* ✅ Alerts */
        .alert-error, .alert-success {
            font-weight: bold;
            margin: 15px auto;
            padding: 12px;
            border-radius: 6px;
            width: 60%;
            text-align: center;
        }
        .alert-error {
            color: #721c24;
            background: #f8d7da;
            border: 1px solid #f5c6cb;
        }
        .alert-success {
            color: #155724;
            background: #d4edda;
            border: 1px solid #c3e6cb;
        }

        /* 📊 User Table */
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
            border-radius: 8px;
            overflow: hidden;
        }
        th, td {
            border: 1px solid #dee2e6;
            padding: 12px;
            text-align: center;
        }
        th {
            background: #007bff;
            color: white;
            font-size: 15px;
        }
        tr:nth-child(even) {
            background: #f9f9f9;
        }
        tr:hover {
            background: #f1f7ff;
        }

        /* 🏷️ Role Badges */
        .badge {
            display: inline-block;
            padding: 5px 10px;
            border-radius: 12px;
            font-size: 12px;
            font-weight: bold;
            color: white;
        }
        .badge-user { background: #17a2b8; }   /* Teal */
        .badge-admin { background: #6f42c1; }  /* Purple */

        /* 🔘 Buttons */
        button {
            padding: 6px 14px;
            font-size: 14px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            transition: all 0.3s ease;
        }
        button:hover {
            transform: translateY(-2px);
            box-shadow: 0 3px 6px rgba(0,0,0,0.2);
        }
        button[type="submit"] {
            background-color: #007bff;
            color: white;
        }
        .delete-btn {
            background-color: #dc3545;
            color: white;
        }
        .delete-btn:hover {
            background-color: #c82333;
        }

        /* ✏️ Forms */
        form {
            margin-top: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
            transition: border 0.3s;
        }
        input:focus, select:focus {
            border-color: #007bff;
            outline: none;
        }

        .form-section {
            margin-top: 30px;
            padding: 20px;
            border: 1px solid #ddd;
            background: #fdfdfd;
            border-radius: 8px;
        }
        .form-section h3 {
            margin-bottom: 15px;
            color: #007bff;
        }

        /* 🎬 Animations */
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        /* 📱 Responsive */
        @media (max-width: 768px) {
            .container { padding: 20px; }
            table, th, td { font-size: 12px; }
            button { font-size: 12px; padding: 5px 10px; }
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Manage Users</h2>
    <p><a href="${pageContext.request.contextPath}/admin/dashboard">Back to Dashboard</a></p>

    <!-- ✅ Alerts -->
    <c:if test="${not empty error}">
        <div class="alert-error">${error}</div>
    </c:if>
    <c:if test="${not empty message}">
        <div class="alert-success">${message}</div>
    </c:if>

    <!-- ✅ User Table -->
    <table>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Role</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${users}" var="u">
            <tr>
                <td>${u.id}</td>
                <td>${u.username}</td>
                <td>${u.name}</td>
                <td>${u.email}</td>
                <td>${u.phone}</td>
                <td>
                    <span class="badge ${u.role eq 'ADMIN' ? 'badge-admin' : 'badge-user'}">
                        ${u.role}
                    </span>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/users/${u.id}/delete" method="post" style="display:inline">
                        <button type="submit" class="delete-btn" onclick="return confirm('Delete user?')">Delete</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/admin/users/${u.id}/edit" method="get" style="display:inline">
                        <button type="submit">Edit</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <!-- ✅ User Form -->
    <div class="form-section">
        <h3>${editUser != null ? "Edit User" : "Add User"}</h3>
        <form action="${pageContext.request.contextPath}/admin/users/${editUser != null ? 'update' : ''}" method="post">
            <input type="hidden" name="id" value="${editUser.id}" />

            <label>Username</label>
            <input name="username" value="${editUser != null ? editUser.username : newUser.username}" required />

            <c:if test="${editUser == null}">
                <label>Password</label>
                <input type="password" name="password" required />
            </c:if>

            <label>Name</label>
            <input name="name" value="${editUser != null ? editUser.name : newUser.name}" required />

            <label>Email</label>
            <input type="email" name="email" value="${editUser != null ? editUser.email : newUser.email}" required />

            <label>Phone</label>
            <input name="phone" value="${editUser != null ? editUser.phone : newUser.phone}" />

            <label>Role</label>
            <select name="role">
                <option value="USER" ${editUser != null && editUser.role == 'USER' ? 'selected' : ''}>USER</option>
                <option value="ADMIN" ${editUser != null && editUser.role == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
            </select>

            <button type="submit">${editUser != null ? "Update" : "Add"}</button>
        </form>
    </div>
</div>

</body>
</html>
