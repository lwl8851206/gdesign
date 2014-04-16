<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/gdesign/";

	if (session.getAttribute("token") == null)
		response.sendRedirect("https://api.weibo.com/oauth2/authorize?client_id=2717579641&redirect_uri=" + basePath +  "o/oauth.do&response_type=code&state=shit");
%>

<!DOCTYPE html>
<html ng-app="phonecatApp">


	<jsp:include page="header.jsp">
		<jsp:param value="home" name="activeitem"/>
		<jsp:param value="home" name="title"/>
		<jsp:param value="../css/index.css" name="cssfiles"/>
		<jsp:param value="../css/common.css" name="cssfiles"/>
		<jsp:param value="../js/lib/slick/slick.css" name="cssfiles"/>
	</jsp:include>
	
	<section id="content" ng-view>
	</section>
	
	<jsp:include page="footer.jsp">
		<jsp:param value="js/lib/loadimg/jquery.loadimg.js" name="jsfiles"/>
		<jsp:param value="js/lib/slick/slick.min.js" name="jsfiles"/>
		<jsp:param value="weibo/js/tweetapp.js" name="jsfiles"/>
		<jsp:param value="weibo/js/tweetcontrollers.js" name="jsfiles"/>
	</jsp:include>

</html>