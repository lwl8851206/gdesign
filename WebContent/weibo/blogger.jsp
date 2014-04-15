<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/gdesign/";
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
	<header  id="header">
		<div class="header-container navbar-fixed-top navbar-inverse">
			<nav class="container navbar-inverse">
				<ul class="nav nav-pills">
					<li class=" header-home">
						<a href="weibo/index.jsp" >
							<span class="glyphicon glyphicon-home"></span>
						</a>
					</li>

					<li class="header-blogger">
						<a href="javacript:void(0);" title="blogger" class="dropdown-toggle" data-toggle="dropdown">
							<span class="glyphicon glyphicon-user">do_once_long</span>
						</a>
	  					<ul class="dropdown-menu" role="menu">
						    <li><a href="weibo/friendship.jsp#/friends">关注的人</a></li>
						    <li><a href="weibo/commentlist.jsp">评论列表</a></li>
						    <li><a href="weibo/friendship.jsp#/followers">粉丝</a></li>
						    <li class="divider"></li>
						    <li><a href="weibo/blogger.jsp">我的主页</a></li>
	  					</ul>
					</li>

					<li class="header-write-blog">
						<a href="weibo/writeblog.jsp" title="write blog" >
							<span class="glyphicon glyphicon-pencil"></span>
						</a>
					</li>

				</ul>
			</nav>
		</div>
	</header>


	<section id="content" ng-view>
	</section>
	
<footer id="footer">

			<div class="col-xs-12 text-center text-muted">归伟龙所有</div>
		
	</footer>
  	<script src="weibo/lib/angular/angular.js"></script>
  	<script src="weibo/lib/angular/angular-route.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script src="weibo/js/bloggerapp.js"></script>
  	<script src="weibo/js/bloggercontroller.js"></script>

</body>
</html>