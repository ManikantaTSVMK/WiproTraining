<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My Tasks - WorkNest</title>
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

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        /* 🔗 Top Navigation */
        .top-nav {
            text-align: right;
            margin-bottom: 15px;
        }
        .top-nav a {
            color: #dc3545;
            font-weight: bold;
            text-decoration: none;
        }
        .top-nav a:hover {
            text-decoration: underline;
        }

        /* 📊 Task Table */
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

        /* 🎯 Status Badges */
        .status {
            display: inline-block;
            padding: 6px 12px;
            border-radius: 12px;
            font-size: 12px;
            font-weight: bold;
            color: white;
        }
        .status-TODO { background: #6c757d; }
        .status-INPROGRESS { background: #17a2b8; }
        .status-COMPLETED { background: #28a745; }
        .status-BLOCKED { background: #dc3545; }

        /* 📎 Links */
        a.task-link {
            color: #007bff;
            font-weight: bold;
            text-decoration: none;
        }
        a.task-link:hover {
            text-decoration: underline;
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
        }
    </style>
</head>
<body>

<div class="container">
    <h2>My Tasks</h2>

    <div class="top-nav">
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>

    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Assignee</th>
            <th>Status</th>
            <th>Start</th>
            <th>Due</th>
            <th>Comments</th>
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
                        <c:otherwise><em>Unassigned</em></c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <span class="status status-${t.status}">
                        ${t.status}
                    </span>
                </td>
                <td>${t.startDate}</td>
                <td>${t.dueDate}</td>
                <td><a class="task-link" href="${pageContext.request.contextPath}/user/tasks/${t.id}">Add/View</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
