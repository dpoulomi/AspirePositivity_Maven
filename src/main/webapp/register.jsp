<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Registration Form</title>
<style type="text/css">
h3 {
	font-family: Calibri;
	font-size: 22pt;
	font-style: normal;
	font-weight: bold;
	color: SlateBlue;
	text-align: center;
	text-decoration: underline
}

table {
	font-family: Calibri;
	color: white;
	font-size: 11pt;
	font-style: normal;
	width: 50%;
	text-align:;
	background-color: SlateBlue;
	border-collapse: collapse;
	border: 2px solid navy
}

table.inner {
	border: 0px
}
</style>
</head>
<body>
	<h3>Student Registration Form</h3>
	<form action="/AspirePositivity/r" method="POST">
		<table align="center" cellpadding="10">
			<tr>
				<td>First Name</td>
				<td><input type="text" name="firstName" maxlength="30" /> (max
					30 characters a-z and A-Z)</td>
			</tr>
			<tr>
				<td>Middle Name</td>
				<td><input type="text" name="middleName" maxlength="30" /> (max
					30 characters a-z and A-Z)</td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lastName" maxlength="30" /> (max
					30 characters a-z and A-Z)</td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email" maxlength="100" /></td>
			</tr>
			<tr>
				<td>User Name</td>
				<td><input type="text" name="username" maxlength="100" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" maxlength="100" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Submit"> <input type="reset" value="Reset">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
