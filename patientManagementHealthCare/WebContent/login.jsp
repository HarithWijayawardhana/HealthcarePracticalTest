<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.5.0.min.js"></script>
<script src="Components/main.js"></script>



</head>
<body class="bg-dark">
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


	<br>

	<div class="card ripe-malinka-gradient form-white mx-auto mt-5"
		style="width: 500px;">

		<form action="LoginController"
			class="text-center border border-light p-5" method="post">


			<p class="h4 mb-4">Sign in</p>


			<input type="text" name="username" class="form-control mx-auto" placeholder="Username"
				style="width: 400px;"> <br> <input type="password"
				name="password" class="form-control mx-auto mt-4"
				placeholder="Password" style="width: 400px;"><br>


			<button class="btn btn-outline-success" btn-block my-4 waves-effect
				z-depth-0"
					type="submit" value="Login" style="width: 400px;">Sign
				in</button>


		</form>

	</div>







</body>
</html>