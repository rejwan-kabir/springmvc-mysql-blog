<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--%@ page session="false"%-->
<html>
<head>
<title>Home</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
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
</style>
<link href="<c:url value="/resources/css/bootstrap-responsive.css" />" rel="stylesheet">
</head>
<body>
	<div class="container-narrow">
		<div class="masthead">
			<ul class="nav nav-pills pull-right">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="#">About</a></li>
				<c:if test="${empty sessionScope.loggedInUser}">
					<li><a href="<c:url value="/signin"/>">Sign In</a></li>
				</c:if>
				<c:if test="${not empty sessionScope.loggedInUser}">
					<li><a href="<c:url value="/signout"/>">Sign out</a></li>
				</c:if>
			</ul>
			<h3 class="muted"><a href="<c:url value="/" />">Spring MVC Blog</a></h3>
		</div>
		<hr>
		<c:if test="${not empty allPost}">
			<c:forEach items="${allPost}" var="singlePost">
				<h1><a href="post/<c:out value="${singlePost.id}"/>">${singlePost.title}</a></h1>
				<h2><a href="user/<c:out value="${singlePost.user_id}"/>">${singlePost.user_username}</a></h2>
				<p>${singlePost.content}</p>
				<p>${singlePost.date}</p>
				<hr>
			</c:forEach>
		</c:if>
		<div class="footer">
			<p>&copy; Company 2012</p>
		</div>
	</div>
</body>
</html>
