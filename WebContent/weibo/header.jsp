<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/gdesign/";

%>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,  user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
		<link rel="stylesheet" href="../css/bootstrap-theme.min.css" type="text/css">
		<link rel="stylesheet" href="../css/common.css" type="text/css">
	
		<%
			String [] cssFiles = request.getParameterValues("cssfiles");
			String cssTagPre = "<link rel=\"stylesheet\" type=\"text/css\" href=\"",
				   cssTagSuf = "\">";
			if (cssFiles != null)
				for (String cssFile : cssFiles) {
					out.println(cssTagPre + cssFile + cssTagSuf);
				}
		%>
		
		<title>${param.title}</title>
		<base href="<%= basePath%>">
	</head>
	
	<body>

	<header  id="header" >
		<div class="header-container navbar-fixed-top navbar-inverse">
			<nav class="container navbar-inverse">
				<ul class="nav nav-pills">
					<li class="header-home 
						<% if (request.getParameter("activeitem").equals("home")) out.print("active"); %>
					">
						<a href="weibo/index.jsp" >
							<span class="glyphicon glyphicon-home"></span>
						</a>
					</li>

					<li class="header-blogger">
						<a href="javascript:void(0);" title="blogger" class="dropdown-toggle" data-toggle="dropdown">
							<span id="user-name" class="glyphicon glyphicon-user"><% if (session.getAttribute("screen_name") != null) out.println(session.getAttribute("screen_name").toString());%></span>
						</a>
	  					<ul class="dropdown-menu" role="menu">
						    <li class="<% if (request.getParameter("activeitem").equals("friend")) out.print("active"); %>"><a href="weibo/friendship.jsp#/friends">关注的人</a></li>
						    <li class="<% if (request.getParameter("activeitem").equals("follower")) out.print("active"); %>"><a href="weibo/friendship.jsp#/followers">粉丝</a></li>
						    <li class="<% if (request.getParameter("activeitem").equals("comment")) out.print("active"); %>"><a href="weibo/commentlist.jsp">评论列表</a></li>
						    <li class="<% if (request.getParameter("activeitem").equals("info")) out.print("active"); %>"><a href="weibo/blogger.jsp#/userinfo/<% if (session.getAttribute("uid") != null) out.println(session.getAttribute("uid").toString());%>">个人信息</a></li>
	  						<li class="divider"></li>
	  						<li class="" id="logout"><a href="javascript:void(0);" >退出</a></li>
	  					</ul>
					</li>

					<li class="header-write-blog 
					<% if (request.getParameter("activeitem").equals("write")) out.print("active"); %>
					">
						<a href="weibo/writeblog.jsp" title="write blog" >
							<span class="glyphicon glyphicon-pencil"></span>
						</a>
					</li>

				</ul>
			</nav>
		</div>
	</header>