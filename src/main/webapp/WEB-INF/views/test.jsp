<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Test</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<style type="text/css">
body {
	padding-top: 20px;
	padding-bottom: 40px;
}

/* Custom container */
.container-narrow {
	margin: 0 auto;
	max-width: 700px;
}

.container-narrow>hr {
	margin: 30px 0;
}

.form-signin {
	max-width: 700px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"],.form-signin textarea
	{
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}

.form-signin a {
	font-size: 14px;
	height: auto;
	margin-left: 20px;
}
</style>
<link href="<c:url value="/resources/css/bootstrap-responsive.css" />"
	rel="stylesheet">
</head>
<body>
	<div class="container-narrow">
		<div class="masthead">
			<ul class="nav nav-pills pull-right">
				<li class="active"><a href="<c:url value="/" />">Home</a></li>
				<li><a href="#">About</a></li>
				<li><a href="<c:url value="/signout"/>">Sign out</a></li>
			</ul>
			<h3 class="muted">
				<a href="<c:url value="/" />">Spring MVC Blog</a>
			</h3>
		</div>
		<hr>
		<c:if test="${not empty name}">
			<h1>
				Username :
				<c:out value="${name}" />
			</h1>
		</c:if>
		<c:if test="${not empty one and not empty two}">
			<h2>
				One :
				<c:out value="${one}" />
			</h2>
			<h2>
				Two :
				<c:out value="${two}" />
			</h2>
		</c:if>
		<!--form method="post" action="<c:url value="/test" />">
			<input type="text" name="one"> <input type="text" name="two">
			<input type="submit" value="Submit">
		</form-->
		<form:form method="post" modelAttribute="object" action="/blog/test" >
			<form:input path="one" class="input-block-level" />
			<form:input path="two" class="input-block-level" />
			<input type="submit" />
		</form:form>
	</div>
</body>
</html>
