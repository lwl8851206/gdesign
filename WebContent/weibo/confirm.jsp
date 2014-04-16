<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>confirm</title>
</head>
<body>


	
	<%
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/gdesign/";
	
		if (session.getAttribute("token") != null) {
			out.println("认证成功");
			response.sendRedirect("index.jsp");
		}
			
		else {
			response.sendRedirect("https://api.weibo.com/oauth2/authorize?client_id=2717579641&redirect_uri=" + basePath +  "o/oauth.do&response_type=code&state=shit");
		}
	%>
	
	
</body>
</html>