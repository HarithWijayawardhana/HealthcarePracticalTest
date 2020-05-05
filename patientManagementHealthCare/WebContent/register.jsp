<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.patient"%>

<%
	//Intialize
session.setAttribute("statusMsg", "");
System.out.println("Trying to process");

//Save---------------------------------
if (request.getParameter("userName") != null) {

	patient itemObj = new patient();
	String stsMsg = "";
	//Insert--------------------------
	if (request.getParameter("hidItemIDSave") == "") {
		stsMsg = itemObj.insertPatient(request.getParameter("userName"), request.getParameter("password"),
		request.getParameter("mobile"), request.getParameter("email"), request.getParameter("address"));
	} else//Update----------------------
	{
		stsMsg = itemObj.updatePatient(request.getParameter("hidItemIDSave"), request.getParameter("userName"),
		request.getParameter("password"), request.getParameter("mobile"), request.getParameter("email"),
		request.getParameter("address"));
	}
	session.setAttribute("statusMsg", stsMsg);
}

//Delete-----------------------------
if (request.getParameter("hidItemIDDelete") != null)
{
patient itemObj = new patient();
String stsMsg =
itemObj.deletePatient(request.getParameter("hidItemIDDelete"));
session.setAttribute("statusMsg", stsMsg);
}

%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.5.0.min.js"></script>
<script src="Components/main.js"></script>




<body>

	<header>
		<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
			<a class="navbar-brand" href="#">Health Care</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link"
						href="Home.jsp">Home <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
					<li class="nav-item"><a class="nav-link" href="register.jsp">Register</a></li>
					<li class="nav-item"><a class="nav-link" href="index.jsp">Doctors</a></li>
				</ul>
				<form class="form-inline my-2 my-lg-0">
					<input class="form-control mr-sm-2" type="search"
						placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</nav>
	</header>
	<div class="container center_div">
		<div class="row">
			<div class="col-8">
				<h1 class="m-3">Register details</h1>
				<!-- 				<form id="formItem" name="formItem" method="post"
					action="register.jsp">
					name: <input id="userName" name="userName" type="text"
						class="form-control form-control-sm"> password: <input
						id="password" name="password" type="text"
						class="form-control form-control-sm"> <br> Mobile: <input
						id="mobile" name="mobile" type="text"
						class="form-control form-control-sm"> <br> Email: <input
						id="email" name="email" type="text"
						class="form-control form-control-sm"> <br> Address: <input
						id="address" name="address" type="text"
						class="form-control form-control-sm"> <br>
					GENDER

					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>

					<input name="btnSubmit" type="submit" value="Save"
						class="btn btn-primary">

				</form> -->

				<form id="formItem" name="formItem" method="post"
					action="register.jsp">
					name: <input id="userName" name="userName" type="text"
						class="form-control form-control-sm"> password: <input
						id="password" name="password" type="text"
						class="form-control form-control-sm"> <br> Mobile: <input
						id="mobile" name="mobile" type="text"
						class="form-control form-control-sm"> <br> Email: <input
						id="email" name="email" type="text"
						class="form-control form-control-sm"> <br> Address: <input
						id="address" name="address" type="text"
						class="form-control form-control-sm"> <br> 
						<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary"> 
						<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">

				</form>
				

				<div id="alertSuccess" class="alert alert-success">

					<%
						out.print(session.getAttribute("statusMsg"));
					%>

				</div>
				<div id="alertError" class="alert alert-danger">

					<%
						out.print(session.getAttribute("statusMsg"));
					%>

				</div>


			</div>
		</div>

		<br>
		<div class="row">
			<div class="col-12" id="colStudents"></div>
		</div>
	</div>

	<div class="container">
		<%
			patient itemObj = new patient();
		out.print(itemObj.readPatient());
		%>
	</div>


</body>
</html>