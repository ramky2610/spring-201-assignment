<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery-1.11.1.js"></script>
<title>CONTENT MANAGEMENT SYSTEM</title>
</head>
<body >
	<h1 align="center" style="color:deepskyblue;">Content Management System</h1>
	<div id="wrapper">
		<div id="leftcolumn" style="background-color: burlywood;">
			<br /> <br /> <br /> <br /> <br /> <br /> <br />
			<div style="text-align: center;">
				<form:form action="/userLogin" method="get">
					<input type="submit" value="Guest" id="guest" />
				</form:form>
				<form:form action="/customLogin" method="get">
					<input type="submit" value="Admin" id="admin" />
				</form:form>
			</div>
		</div>

	</div>

	<div id="rightcolumn">
		<br /> <br /> <br /> <br /> <br /> <br /> <br />

		<form:form action="/viewBlog" method="GET"
			id="user-form" commandName="loginFo" style='text-align: center'>
			<form:errors path="guestUserName" cssClass="error" style="margin: 0px 0 0px 125px;color: red;"/>
			<div id="userview">
				Enter UserName:
				<form:input id="guestUserName" path="guestUserName" />
				<br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
					 <input type="submit" value="Submit"
					id="submit" />
			</div>

		</form:form>
	</div>
</body>
</html>
<style>
body {
	background-color: #444;
	margin: 0;
}

#wrapper {
	width: 1005px;
	margin: 0 auto;
}

#leftcolumn, #rightcolumn {
	border: 1px solid white;
	float: left;
	min-height: 450px;
	color: white;
}

#leftcolumn {
	width: 250px;
	background-color: #111;
}

#rightcolumn {
	width: 750px;
	background-color: #777;
}
</style>
