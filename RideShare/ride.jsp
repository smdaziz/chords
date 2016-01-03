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
			#table-center {
				position: absolute;
				left: 40%;
				top: 40%;
			}
			.topcorner{
				position:absolute;
				top:0;
				right:0;
			}
		</style>
	</head>
	<body>
		<div class="topcorner">Welcome&nbsp;&nbsp;&nbsp;${userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="logout">logout</a>&nbsp;&nbsp;&nbsp;</div>
		<div id="table-center">
			<table>
				<tr><td><b>Select an option to proceed</b></td></tr>
				<tr><td><a href="selfRide.jsp">Self Ride</a></td></tr>
				<tr><td><a href="availableRides">Available Rides</a></td></tr>
				<tr><td><a href="viewActivity">View Activity</a></td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td><font face="verdana" color="green">${message}</font></td></tr>
			</table>
		</div>
	</body>
</html>