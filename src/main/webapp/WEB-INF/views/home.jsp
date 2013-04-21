<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
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

.create_btn {
	margin: 10px 0;
	text-align: center;
}

.create_btn .btn {
	font-size: 21px;
	padding: 14px 24px;
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
			<h3 class="muted"><a href="<c:url value="/" />">Spring MVC Blog</a></h3>
		</div>
		<hr>
		<div class="create_btn">
			<a class="btn btn-large btn-success" 
				href="<c:url value="/post/new" />">Create New Post</a>
		</div>
		<c:if test="${not empty allPostFromUser}">
			<c:forEach items="${allPostFromUser}" var="singlePost">
				<h2>
					<a href="<c:url value="/post/"/>/<c:out value="${singlePost.id}"/>">${singlePost.title}</a>
				</h2>
				<!--h3><a href="<c:out value="${singlePost.user_id}"/>">${singlePost.user_username}</a></h3 -->
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
