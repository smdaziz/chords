<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Car Share App</title>
		<style>
			html,body {
				height: 100%;
				margin: 0;
				background-color: #F3F3F3;
			}
			#form-center {
				position: absolute;
				left: 40%;
				top: 40%;
			}
		</style>
	</head>
	<body>
		<div id="form-center">
			<form method="POST" action="login">
				<table>
					<tr>
						<td>User Name</td>
						<td><input type="text" name="userName"></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="password"></td>
					</tr>
					<tr></tr><tr></tr><tr></tr>
					<tr>
						<td><input type="submit" value="Login"></td>
						<td><input type="button" value="Register" onclick="window.location.href='http://localhost:8080/CarShare/register.html'"></td>
					</tr>
					<tr>
						<td colspan="2"><font face="verdana" color="red">${message}</font></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>