<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to WorkNest</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #74ebd5 0%, #9face6 100%);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background: white;
            padding: 50px 40px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.15);
            text-align: center;
            max-width: 400px;
            width: 100%;
            animation: fadeIn 1s ease-in-out;
        }

        h1 {
            margin-bottom: 20px;
            color: #333;
            font-size: 28px;
        }

        p {
            margin-bottom: 30px;
            font-size: 16px;
            color: #666;
        }

        .btn {
            display: inline-block;
            margin: 10px;
            padding: 14px 28px;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            text-decoration: none;
            color: white;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }

        .btn-login {
            background-color: #007bff;
        }

        .btn-register {
            background-color: #28a745;
        }

        .btn:hover {
            transform: translateY(-3px);
            box-shadow: 0 6px 15px rgba(0,0,0,0.2);
        }

        @media (max-width: 500px) {
            .container {
                padding: 30px 20px;
            }
            h1 {
                font-size: 24px;
            }
            .btn {
                width: 100%;
                margin: 8px 0;
            }
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to WorkNest</h1>
        <p>Your smart task management system</p>
        <a href="<c:url value='/login' />" class="btn btn-login">Login</a>
        <a href="<c:url value='/register' />" class="btn btn-register">Register</a>
    </div>
</body>
</html>