<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin Dashboard - WorkNest</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, sans-serif;
            background: linear-gradient(135deg, #f4f4f4, #eaeaea);
            margin: 0;
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
            max-width: 1200px;
            animation: fadeIn 0.8s ease-in-out;
        }

        h2, h3 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        /* 🔗 Navigation */
        .nav-links {
            margin-bottom: 20px;
            text-align: center;
        }
        .nav-links a {
            margin: 0 15px;
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
            transition: color 0.3s;
        }
        .nav-links a:hover {
            text-decoration: underline;
            color: #0056b3;
        }

        /* ✅ Alerts */
        .alert-success, .alert-error {
            font-weight: bold;
            margin: 15px auto;
            padding: 12px;
            border-radius: 6px;
            width: 60%;
            text-align: center;
        }
        .alert-success {
            color: #155724;
            background: #d4edda;
            border: 1px solid #c3e6cb;
        }
        .alert-error {
            color: #721c24;
            background: #f8d7da;
            border: 1px solid #f5c6cb;
        }

        /* 📊 Tables */
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 15px;
            border-radius: 8px;
            overflow: hidden;
        }
        th, td {
            border: 1px solid #e0e0e0;
            padding: 12px;
            text-align: center;
        }
        th {
            background: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background: #f9f9f9;
        }
        tr:hover {
            background: #f1f7ff;
        }

        /* 📊 Status Overview */
        .status-table {
            width: 60%;
            margin: 0 auto 20px auto;
            border: none;
        }
        .status-table th {
            background: #f0f0f0;
            color: #333;
        }
        .status-table td {
            font-weight: bold;
        }

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
        .cancel-btn {
            background-color: #6c757d;
            color: white;
        }
        .cancel-btn:hover {
            background-color: #5a6268;
        }

        /* ⚙️ Forms */
        select {
            padding: 6px;
            border-radius: 6px;
            border: 1px solid #ccc;
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
        }
        .form-section label {
            font-weight: bold;
        }

        /* 🏷️ Status Badges */
        .badge {
            display: inline-block;
            padding: 5px 10px;
            border-radius: 12px;
            font-size: 12px;
            font-weight: bold;
            color: white;
        }
        .badge-pending {
            background-color: orange;
        }
        .badge-progress {
            background-color: dodgerblue;
        }
        .badge-completed {
            background-color: green;
        }
        .badge-delayed {
            background-color: red;
        }

        /* 🎬 Animations */
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        /* 📱 Responsive */
        @media (max-width: 768px) {
            .status-table, table {
                font-size: 12px;
            }
            button {
                font-size: 12px;
                padding: 5px 10px;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Admin Dashboard</h2>

    <!-- ✅ Navigation -->
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/admin/users">Manage Users</a> |
        <a href="${pageContext.request.contextPath}/admin/tasks/new">Allocate Task</a> |
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>

    <!-- ✅ Show Alerts -->
    <c:if test="${not empty message}">
        <div class="alert-success">${message}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert-error">${error}</div>
    </c:if>

    <!-- ✅ Task Status Summary -->
    <h3>Task Status Overview</h3>
    <table class="status-table">
        <tr><th>Status</th><th>Count</th></tr>
        <tr><td><span class="badge badge-pending">Pending</span></td><td>${counts.PENDING != null ? counts.PENDING : 0}</td></tr>
        <tr><td><span class="badge badge-progress">In Progress</span></td><td>${counts.IN_PROGRESS != null ? counts.IN_PROGRESS : 0}</td></tr>
        <tr><td><span class="badge badge-completed">Completed</span></td><td>${counts.COMPLETED != null ? counts.COMPLETED : 0}</td></tr>
        <tr><td><span class="badge badge-delayed">Delayed</span></td><td>${counts.DELAYED != null ? counts.DELAYED : 0}</td></tr>
    </table>
    <p><strong>Total Tasks:</strong>
        ${(counts.PENDING != null ? counts.PENDING : 0) +
          (counts.IN_PROGRESS != null ? counts.IN_PROGRESS : 0) +
          (counts.COMPLETED != null ? counts.COMPLETED : 0) +
          (counts.DELAYED != null ? counts.DELAYED : 0)}
    </p>

    <!-- ✅ Task List -->
    <h3>All Tasks</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Assignee</th>
            <th>Status</th>
            <th>Start</th>
            <th>Due</th>
            <th>Comments</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${tasks}" var="t">
            <tr>
                <td>${t.id}</td>
                <td>${t.title}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty t.assignee}">
                            <c:out value="${t.assignee.username}"/>
                        </c:when>
                        <c:otherwise>Unassigned</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <span class="badge 
                        ${t.status.name() eq 'PENDING' ? 'badge-pending' : 
                          t.status.name() eq 'IN_PROGRESS' ? 'badge-progress' : 
                          t.status.name() eq 'COMPLETED' ? 'badge-completed' : 
                          'badge-delayed'}">
                        ${t.status}
                    </span>
                    <form method="post" action="${pageContext.request.contextPath}/admin/tasks/${t.id}/status" style="margin-top:5px;">
                        <select name="status">
                            <option value="PENDING" <c:if test="${t.status.name() eq 'PENDING'}">selected</c:if>>Pending</option>
                            <option value="IN_PROGRESS" <c:if test="${t.status.name() eq 'IN_PROGRESS'}">selected</c:if>>In Progress</option>
                            <option value="COMPLETED" <c:if test="${t.status.name() eq 'COMPLETED'}">selected</c:if>>Completed</option>
                            <option value="DELAYED" <c:if test="${t.status.name() eq 'DELAYED'}">selected</c:if>>Delayed</option>
                        </select>
                        <button type="submit">Update</button>
                    </form>
                </td>
                <td>${t.startDate}</td>
                <td>${t.dueDate}</td>
                <td><a href="${pageContext.request.contextPath}/admin/tasks/${t.id}/comments">View</a></td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/admin/tasks/${t.id}/delete" style="display:inline">
                        <button type="submit" class="delete-btn" onclick="return confirm('Are you sure you want to delete this task?')">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <!-- ✅ Dedicated Status Update Form -->
    <c:if test="${editTask != null}">
        <div class="form-section">
            <h3>Edit Task Status</h3>
            <form method="post" action="${pageContext.request.contextPath}/admin/tasks/${editTask.id}/status">
                <label>Task Title:</label> ${editTask.title} <br/><br/>
                <label>Assignee:</label>
                <c:choose>
                    <c:when test="${not empty editTask.assignee}">
                        ${editTask.assignee.username}
                    </c:when>
                    <c:otherwise>Unassigned</c:otherwise>
                </c:choose>
                <br/><br/>
                <label>Current Status:</label>
                <span class="badge 
                    ${editTask.status.name() eq 'PENDING' ? 'badge-pending' : 
                      editTask.status.name() eq 'IN_PROGRESS' ? 'badge-progress' : 
                      editTask.status.name() eq 'COMPLETED' ? 'badge-completed' : 
                      'badge-delayed'}">
                    ${editTask.status}
                </span>
                <br/><br/>
                <label>New Status:</label>
                <select name="status">
                    <c:forEach items="${statuses}" var="s">
                        <option value="${s}" ${editTask.status.name() eq s ? 'selected' : ''}>${s}</option>
                    </c:forEach>
                </select><br/><br/>
                <button type="submit">Update Status</button>
                <a href="${pageContext.request.contextPath}/admin/dashboard" class="button-link">
                    <button type="button" class="cancel-btn">Cancel</button>
                </a>
            </form>
        </div>
    </c:if>
</div>

</body>
</html>
