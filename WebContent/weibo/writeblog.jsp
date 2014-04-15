<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/gdesign/";
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0,  minimum-scale=1.0, maximum-scale=1.0" />
	<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="../css/bootstrap-theme.min.css" type="text/css">
	<link rel="stylesheet" href="../css/common.css" type="text/css">
	<title>Home</title>
	<base href="<%= basePath%>">
	<style>


	</style>
</head>

<body>
	<header  id="header" >
		<div class="header-container navbar-fixed-top navbar-inverse">
			<nav class="container navbar-inverse">
				<ul class="nav nav-pills">
					<li class="header-home">
						<a href="weibo/index.jsp" >
							<span class="glyphicon glyphicon-home"></span>
						</a>
					</li>

					<li class="header-blogger">
						<a href="javascript:void(0);" title="blogger" class="dropdown-toggle" data-toggle="dropdown">
							<span class="glyphicon glyphicon-user">do_once_long</span>
						</a>
	  					<ul class="dropdown-menu" role="menu">
						    <li><a href="weibo/friendship.jsp#/friends">关注的人</a></li>
						    <li><a href="weibo/commentlist.jsp">评论列表</a></li>
						    <li><a href="weibo/friendship.jsp#/followers">粉丝</a></li>
						    <li class="divider"></li>
						    <li><a href="weibo/blogger.jsp">个人信息</a></li>
	  					</ul>
					</li>

					<li class="header-write-blog active ">
						<a href="weibo/writeblog.jsp" title="write blog" >
							<span class="glyphicon glyphicon-pencil"></span>
						</a>
					</li>

				</ul>
			</nav>
		</div>
	</header>

	<section id="content">
		<div class="container">
			<div class="row">
				<div class="bg-col-autoresize clearfix">
<!-- 					<h1>
						写微薄
					</h1>
					<form name="write-blog" method="post" action="http://localhost:8080/gdesign/tweet/publishTweet.do">
						<textarea name="text"></textarea>
						<input type="submit" value="发布" class="btn btn-success"> 
					</form> -->
					
					
					<div class="panel panel-success">
					  <!-- Default panel contents -->
					  <div class="panel-heading"><h1>发表微博</h1></div>
					  <div class="panel-body">
						<form role="form" name="write-blog" method="post" enctype="multipart/form-data"  action="up/publishTweet.do">
						  <div class="form-group">
						    <textarea class="form-control" rows="4" name="text" placeholder="说点什么..."></textarea>
						  </div>
						  <div class="form-group">
						    <label for="upload-pictures">选择图片上传</label>
						    <input type="file" id="upload-pictures" name="uploadImg">
						  </div>
					<!-- 	  <div class="checkbox">
						    <label>
						      <input type="checkbox"> Check me out
						    </label>
						  </div> -->
						  <button type="submit" class="btn btn-danger pull-right">发布</button>
						</form>		
					  </div>
					  
					</div>										
						
					
				</div>
			</div>
		</div>
	</section>
<footer id="footer">

			<div class="col-xs-12 text-center text-muted">归伟龙所有</div>
		
	</footer>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>

</body>
</html>