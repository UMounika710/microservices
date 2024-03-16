<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column; /* Added to align content vertically */
            height: 100%;
        }

        .navbar {
            background-color: #343a40;
            color: white;
            width: 100%;
            text-align: center;
            padding: 10px 0;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .login-container {
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px; /* Adjusted margin to create space between navbar and form */
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .form-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        .login-button {
            background-color: #4CAF50;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .footer {
	position: fixed;
	bottom: 0;
	width: 100%;
	background-color: #343a40; /* Adjust the color as needed */
	color: white;
	text-align: center;
	padding: 10px 0;
}
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark mt-2">
    <div class="container">
        <h2>MATERIAL INSPECTION MODULE</h2>
    </div>
</nav>

<div class="login-container">
    <h2>Login</h2>
    <form action="login" method="post" modelAttribute="user">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" path="userName"  name="userName" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" path="password" name="password" required>
        </div>
        <input type="submit" value="Login" class="login-button"/>
    </form>
</div>
<footer class="footer">
			<div class="container pt-2 pb-2">
				<span>&copy; 2024 Zettamine Labs Pvt Ltd. All rights
					reserved.</span>
			</div>
		</footer>
</body>
</html>