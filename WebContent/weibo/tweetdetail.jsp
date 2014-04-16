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
		<jsp:param value="" name="activeitem"/>
		<jsp:param value="detail of tweet" name="title"/>
		<jsp:param value="../css/tweetdetail.css" name="cssfiles"/>
		
	</jsp:include>

	<section ng-view id="content">
	
	
	</section>

	<jsp:include page="footer.jsp">
		<jsp:param value="weibo/js/tweetdetailapp.js" name="jsfiles"/>
		<jsp:param value="weibo/js/tweetdetailcontroller.js" name="jsfiles"/>		
	</jsp:include>

</html>