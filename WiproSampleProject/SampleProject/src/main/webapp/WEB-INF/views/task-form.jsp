<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create Task - WorkNest</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, sans-serif;
            background: linear-gradient(135deg, #f8f9fa, #e9ecef);
            padding: 40px;
            text-align: center;
        }

        .container {
            background: #fff;
            padding: 35px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.15);
            display: inline-block;
            text-align: left;
            width: 95%;
            max-width: 600px;
            animation: fadeIn 0.6s ease-in-out;
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
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

        /* 📝 Form Styling */
        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin: 10px 0 5px;
            font-weight: bold;
            color: #444;
        }

        input, textarea, select {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
            margin-bottom: 15px;
            width: 100%;
            box-sizing: border-box;
        }

        textarea {
            resize: vertical;
        }

        button {
            padding: 12px;
            font-size: 16px;
            background: #007bff;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            transition: 0.3s ease;
        }

        button:hover {
            background: #0056b3;
        }

        /* 🎬 Animation */
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        /* 📱 Responsive */
        @media (max-width: 600px) {
            .container { padding: 20px; }
            input, textarea, select, button { font-size: 14px; }
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Allocate Task</h2>

    <a class="back-link" href="${pageContext.request.contextPath}/admin/dashboard">Back to Dashboard</a>

    <form action="${pageContext.request.contextPath}/admin/tasks" method="post">
        <label>Title</label>
        <input name="title" required/>

        <label>Description</label>
        <textarea name="description" rows="4"></textarea>

        <label>Start Date</label>
        <input type="date" name="startDate" required/>

        <label>Due Date</label>
        <input type="date" name="dueDate" required/>

        <label>Assign To</label>
        <select name="assigneeId" required>
            <c:forEach items="${users}" var="u">
                <option value="${u.id}">${u.username} (${u.role})</option>
            </c:forEach>
        </select>

        <button type="submit">Create Task</button>
    </form>
</div>

</body>
</html>
