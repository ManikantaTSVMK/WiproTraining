<html>
<head>
    <title>User Dashboard - WorkNest</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, sans-serif;
            background: linear-gradient(135deg, #f8f9fa, #e9ecef);
            padding: 40px;
            text-align: center;
        }

        .container {
            background: #fff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.15);
            display: inline-block;
            width: 90%;
            max-width: 600px;
            animation: fadeIn 0.6s ease-in-out;
        }

        h2 {
            color: #333;
            margin-bottom: 15px;
        }

        p {
            font-size: 16px;
            color: #555;
            margin-bottom: 25px;
        }

        .btn {
            display: inline-block;
            padding: 12px 24px;
            margin: 8px;
            font-size: 15px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            text-decoration: none;
            font-weight: bold;
            transition: 0.3s ease;
        }

        .btn-primary {
            background: #007bff;
            color: white;
        }
        .btn-primary:hover {
            background: #0056b3;
        }

        .btn-logout {
            background: #dc3545;
            color: white;
        }
        .btn-logout:hover {
            background: #b02a37;
        }

        /* Animation */
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        /* Responsive */
        @media (max-width: 600px) {
            .container { padding: 25px; }
            .btn { width: 100%; margin: 10px 0; }
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Welcome, User!</h2>
    <p>You are logged in as a regular user.</p>

    <a href="my-tasks.jsp" class="btn btn-primary">View My Tasks</a>
    <a href="logout" class="btn btn-logout">Logout</a>
</div>

</body>
</html>
