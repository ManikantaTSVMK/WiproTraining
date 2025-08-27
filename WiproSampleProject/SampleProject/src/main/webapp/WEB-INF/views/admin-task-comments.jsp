<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Task Comments - WorkNest</title>
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
            max-width: 900px;
            animation: fadeIn 0.6s ease-in-out;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        /* 🔗 Back link */
        .back-link {
            display: inline-block;
            margin-bottom: 20px;
            color: #007bff;
            font-weight: bold;
            text-decoration: none;
        }
        .back-link:hover {
            text-decoration: underline;
        }

        /* 📋 Comments Table */
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 10px;
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

        /* ✍️ Comment Content */
        td.comment-content {
            text-align: left;
            font-style: italic;
            color: #444;
        }

        /* 🎬 Animation */
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
    <h2>Task Comments</h2>

    <a class="back-link" href="${pageContext.request.contextPath}/admin/dashboard">Back to Dashboard</a>

    <table>
        <tr>
            <th>#</th>
            <th>Author</th>
            <th>Comment</th>
            <th>At</th>
        </tr>
        <c:forEach items="${comments}" var="cmt" varStatus="vs">
            <tr>
                <td>${vs.index + 1}</td>
                <td>${cmt.author.username}</td>
                <td class="comment-content">${cmt.content}</td>
                <td>${cmt.createdAt}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
