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
		<jsp:param value="relationship" name="title"/>
	</jsp:include>

	<style>
		.person-detailed {
			border-left : 3px  solid yellowgreen; 
		}
		
		@media all and (max-width: 280px) {
			.xs-case {
				width: 100%;
			}
			.head-img {
				margin-bottom: 10px;
			}
		}
	</style>
	
	<section id="content" ng-view>

	</section>
	
	<jsp:include page="footer.jsp">
		<jsp:param value="weibo/js/friendshipapp.js" name="jsfiles"/>
		<jsp:param value="weibo/js/friendshipcontrollers.js" name="jsfiles"/>	
	</jsp:include>

</html>