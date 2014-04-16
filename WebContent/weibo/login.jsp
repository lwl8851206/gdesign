<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/gdesign/";
	String oauthPath = "https://api.weibo.com/oauth2/authorize?client_id=2717579641&redirect_uri=" + basePath + "o/oauth.do&response_type=code&state=shit";
	if (session.getAttribute("token") != null)
		response.sendRedirect("index.jsp");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>login</title>
	<base href="<%= basePath%>">
	
	<style>
		* {
			font-family : 微软雅黑;
			padding: 0px 0px;
			margin: 0px 0px;
		}
		
		body {
			/* background-size: 100% 100%; */
		}
	
		#log-area {
			
			margin: auto auto;
			position: relative;
			border-radius: 3px;
			background: rgba(0, 0, 0, 0.6);
		}
		
		#log-area:before, #log-area:after {
			clear: both;
		}
		
		
		
		#log-area h1 {
			
			color: white;
			text-align: center;
		}
		
		#log-button-area {
			
			padding-top: 20px;
			padding-bottom: 10px;
			position: relative;
			text-align: center;
		}
		
		#log-button-area span {
			color: white;
		}
		
		#log-button-area a {
			width: 80%;
			margin: 0 auto;
			display: block;
			position: relative;
			background: #39b3d7;
			padding: 10px 15px;
			text-decoration: none;
			color: white;
			font-weight: bold;
			-webkit-transition: all linear 0.2s;
			-o-transition: all linear 0.2s;
			transition: all linear 0.2s;		
		}
		
		#log-button-area a:hover {
			background: #ed9c28;
		}
		
		@media all and (max-width: 500px) {
			body {
				background: url(img/smalllogin.jpg) no-repeat;
			}
		
			#log-area {
				
				margin-top: 50%;
				width: 80%;
				padding: 10px;
			}
			
			#log-area h1 {
				font-size: 1em;
			}
			
			

		}
		
		@media all and (min-width: 501px) {
			body {
				background: url(img/biglogin.jpg) no-repeat;
				
			}
		
			#log-area {
				margin-top: 15%;
				width: 35%;
				padding: 15px;
			}
			
			
			#log-button-area a {
				
				margin-left: 20px;
			}
			
			
		}
		
	</style>
</head>
<body>
	
	<div id="log-area">
		<h1>欢迎您,按捺不住?赶紧</h1>
		<div id="log-button-area">
			<a href="<%=oauthPath %>">登陆</a>
		</div>
	</div>
	
</body>
</html>