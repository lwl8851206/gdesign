<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/gdesign/";

	if (session.getAttribute("token") == null)
		response.sendRedirect("https://api.weibo.com/oauth2/authorize?client_id=2717579641&redirect_uri=" + basePath +  "o/oauth.do&response_type=code&state=shit");
%>
<!DOCTYPE html>
<html ng-app="phonecatApp">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0,  minimum-scale=1.0, maximum-scale=1.0" />
	<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="../css/bootstrap-theme.min.css" type="text/css">
	<link rel="stylesheet" href="../css/common.css" type="text/css">
	<link rel="stylesheet" href="../css/blogger.css" type="text/css">
	<title>Home</title>
	<base href="<%= basePath%>">
</head>

<body>
	<jsp:include page="header.jsp">
		<jsp:param value="" name="activeitem"/>
		<jsp:param value="info" name="title"/>
	</jsp:include>


	<section id="content" ng-view>
	</section>
	
	<jsp:include page="footer.jsp">
		<jsp:param value="weibo/js/bloggerapp.js" name="jsfiles"/>
		<jsp:param value="weibo/js/bloggercontroller.js" name="jsfiles"/>
	</jsp:include>

</html>