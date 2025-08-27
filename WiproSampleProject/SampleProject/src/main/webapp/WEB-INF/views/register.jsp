<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register - WorkNest</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #74ebd5 0%, #9face6 100%);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            padding-top: 50px;
        }

        .home-button {
            margin-bottom: 20px;
            text-align: center;
        }

        .home-button a {
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            font-weight: bold;
            border-radius: 6px;
            font-size: 16px;
            transition: all 0.3s ease;
        }

        .home-button a:hover {
            background-color: #218838;
            transform: translateY(-2px);
            box-shadow: 0 4px 10px rgba(0,0,0,0.2);
        }

        .container {
            background: white;
            padding: 40px 30px;
            border-radius: 10px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.2);
            width: 400px;
            animation: fadeIn 0.8s ease-in-out;
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            font-size: 26px;
            color: #333;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
            color: #444;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 18px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
            transition: all 0.3s ease;
        }

        input:focus, select:focus {
            border-color: #007bff;
            outline: none;
            box-shadow: 0 0 6px rgba(0,123,255,0.4);
        }

        button {
            width: 100%;
            padding: 12px;
            font-size: 16px;
            font-weight: bold;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
            box-shadow: 0 5px 12px rgba(0,0,0,0.2);
        }

        .alert {
            margin: 15px 0;
            font-weight: bold;
            text-align: center;
            padding: 10px;
            border-radius: 6px;
        }

        .alert-error {
            color: #721c24;
            background: #f8d7da;
            border: 1px solid #f5c6cb;
        }

        .nav-links {
            margin-top: 20px;
            text-align: center;
        }

        .nav-links a {
            margin: 0 10px;
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
            transition: color 0.3s;
        }

        .nav-links a:hover {
            text-decoration: underline;
            color: #0056b3;
        }

        /* Fade-in animation */
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        /* Responsive */
        @media (max-width: 480px) {
            .container {
                width: 90%;
                padding: 30px 20px;
            }
        }
    </style>
</head>
<body>

    <div class="container">
        <!-- 🏠 Home Button -->
        <div class="home-button">
            <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
        </div>

        <h2>Register</h2>

        <!-- ✅ Feedback Message -->
        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>

        <!-- ✅ Registration Form -->
        <form action="${pageContext.request.contextPath}/register" method="post">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" required />

            <label for="email">Email</label>
            <input type="email" id="email" name="email" required />

            <label for="phone">Phone</label>
            <input type="text" id="phone" name="phone" required />

            <label for="username">Username</label>
            <input type="text" id="username" name="username" required />

            <label for="password">Password</label>
            <input type="password" id="password" name="password" required />

            <label for="role">Role</label>
            <select id="role" name="role">
                <option value="USER">USER</option>
                <option value="ADMIN">ADMIN</option>
            </select>

            <button type="submit">Register</button>
        </form>

        <!-- ✅ Navigation Link -->
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/login">Back to Login</a>
        </div>
    </div>

</body>
</html>
