<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Task Details - WorkNest</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, sans-serif;
            background: #f4f6f9;
            padding: 40px;
            text-align: center;
        }

        .container {
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 6px 18px rgba(0,0,0,0.15);
            display: inline-block;
            width: 90%;
            max-width: 900px;
            text-align: left;
            animation: fadeIn 0.5s ease-in-out;
        }

        h2, h3 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        a {
            display: inline-block;
            margin-bottom: 20px;
            color: #007bff;
            font-weight: bold;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }

        /* Table Styling */
        table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 25px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }

        th {
            background-color: #f1f1f1;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #fafafa;
        }

        /* Comment Form */
        form {
            margin-top: 20px;
            text-align: center;
        }

        textarea {
            width: 100%;
            max-width: 700px;
            padding: 12px;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 14px;
            margin-bottom: 15px;
            resize: vertical;
        }

        input[type="submit"] {
            background: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 15px;
            border-radius: 6px;
            cursor: pointer;
            transition: 0.3s ease;
        }

        input[type="submit"]:hover {
            background: #0056b3;
        }

        /* Animation */
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-15px); }
            to { opacity: 1; transform: translateY(0); }
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Task Details</h2>
    <p><a href="${pageContext.request.contextPath}/user/tasks">Back to My Tasks</a></p>

    <!-- ✅ Comments Table -->
    <h3>Comments</h3>
    <table>
        <tr><th>Author</th><th>Content</th><th>Created At</th></tr>
        <c:forEach items="${comments}" var="c">
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${not empty c.author}">
                            <c:out value="${c.author.username}"/>
                        </c:when>
                        <c:otherwise>Unknown</c:otherwise>
                    </c:choose>
                </td>
                <td><c:out value="${c.content}"/></td>
                <td>${c.createdAt}</td>
            </tr>
        </c:forEach>
    </table>

    <!-- ✅ Add Comment Form -->
    <h3>Add Comment</h3>
    <form action="${pageContext.request.contextPath}/user/tasks/${taskId}/comments" method="post">
        <textarea name="content" rows="4" placeholder="Write your comment here..." required></textarea><br/>
        <input type="submit" value="Add Comment"/>
    </form>
</div>

</body>
</html>
