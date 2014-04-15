<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/gdesign/";
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<base href="<%= basePath%>">
	
</head>
<body>
	<iframe width="100%" height= "100%"  src="https://api.weibo.com/oauth2/authorize?client_id=2717579641&redirect_uri=http://kankanweixin1.duapp.com/&response_type=code&state=shit">
		
	
	</iframe>
	<script>
		window.onload = function() {
			var iframe = document.getElementsByTagName("iframe")[0];
			
			iframe.onload = function() {
			
			}
		}
	</script>
	
</body>
</html>