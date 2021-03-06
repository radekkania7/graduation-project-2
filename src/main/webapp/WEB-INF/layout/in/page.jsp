<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>


<!DOCTYPE html>
<html lang="pl">
	<head>
		<title> Life active </title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="<c:url value="/resources/css/app.css" />" rel="stylesheet">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	<body class="global-back-color">
		<div id="menu">
			<t:insertAttribute name="menu" />
		</div>

		<div class="container text-center">
			<div class="row">
				<div class="col-sm-3 well ticket-2">

					<c:choose>
						<c:when test="${userProfile ne null && userProfile ne ''}" >
							<div class="well ticket-2">
								<img class="img-circle" src="<c:url value="/resources/otheruser.png" />" alt="avatar" height="65" width="65"/>
							</div>
						</c:when>
						<c:otherwise>
							<div class="well ticket-2">
								<img class="img-circle" src="<c:url value="/resources/anno.jpg" />" alt="avatar" height="65" width="65"/>
							</div>
						</c:otherwise>
					</c:choose>

					<div>
						<c:choose>
						<c:when test="${userProfile ne null && userProfile ne ''}" >
							${userProfile}
						</c:when>
						<c:otherwise>
							${loggedInUserName}
						</c:otherwise>
					</c:choose>
					</div>
				</div>
				<div class="col-sm-9">
					<div id="content" class="col-sm-12">
						<div class="col-sm-12">
								<t:insertAttribute name="body" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<foo id="footer">
			<t:insertAttribute name="footer" />
		</foo>
	</body>
</html>

