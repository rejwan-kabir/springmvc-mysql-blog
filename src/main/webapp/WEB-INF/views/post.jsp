<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- %@ page session="false"% -->
<html>
<head>
<title>Create New Post</title>
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

.comment_sec {
	text-align: right;
}

.form-signin {
	max-width: 700px;
	padding: 19px 9px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 0px solid #e5e5e5;
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
				<c:if test="${empty sessionScope.loggedInUser}">
					<li><a href="<c:url value="/signin"/>">Sign In</a></li>
				</c:if>
				<c:if test="${not empty sessionScope.loggedInUser}">
					<li><a href="<c:url value="/signout"/>">Sign out</a></li>
				</c:if>

			</ul>
			<h3 class="muted">
				<a href="<c:url value="/" />">Spring MVC Blog</a>
			</h3>
		</div>
		<hr>
		<h2>
			<c:out value="${postOfId.title}" />
		</h2>
		<h3>
			<a href="/blog/user/<c:out value="${postOfId.user_id}"/>">${postOfId.user_username}</a>
		</h3>
		<p>${postOfId.content}</p>
		<p>${postOfId.date}</p>
		<div class="comment_sec">
			<c:if test="${not empty allCommentOfPost}">
				<c:forEach items="${allCommentOfPost}" var="singleComment">
					<hr>
					<h3>
						<a
							href="<c:url value="/user"/>/<c:out value="${singleComment.user_id}"/>">${singleComment.user_username}</a>
					</h3>
					<p>${singleComment.content}</p>
					<p>${singleComment.date}</p>
				</c:forEach>
			</c:if>
		</div>
		<c:if test="${not empty sessionScope.loggedInUser}">
			<form:form method="post" modelAttribute="comment" class="form-signin">
				<form:textarea path="content" class="input-block-level" rows="5"
					placeholder="Write Comment Here" />
				<input type="submit" value="Post Comment"
					class="btn btn-large btn-success">
			</form:form>
		</c:if>
		<c:if test="${empty sessionScope.loggedInUser}">
			<p>
				Please <a href="<c:url value="/signin" />">Sign in</a> to post
				comment
			</p>
		</c:if>
	</div>
</body>
</html>
