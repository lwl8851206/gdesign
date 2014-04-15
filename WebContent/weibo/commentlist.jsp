<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/gdesign/";
%>
<!DOCTYPE html >
<html ng-app="commentListControllers">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0,  minimum-scale=1.0, maximum-scale=1.0" />
	<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="../css/bootstrap-theme.min.css" type="text/css">
	<link rel="stylesheet" href="../css/common.css" type="text/css">
	<title>Home</title>
	<base href="<%= basePath%>">
	<style>
		.badge-new {
		  display: inline-block;
		  min-width: 10px;
		  padding: 3px 7px;
		  margin: 5px 0px;
		  font-size: 12px;
		  font-family: 微软雅黑;
		  background-image: linear-gradient(to bottom,#e8e8e8 0,#f5f5f5 100%);
		  -webkit-background-image: linear-gradient(to bottom,#e8e8e8 0,#f5f5f5 100%);
		  line-height: 1;
		  color: #000;
		  text-align: center;
		  white-space: nowrap;
		  vertical-align: baseline;
		  
		  border-radius: 10px;
		}
		
		.badge-new a{
			color: #000;
		}
		
		.badge-new:empty {
		  display: none;
		}

	</style>
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
						<a title="blogger" class="dropdown-toggle" data-toggle="dropdown">
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

	<section id="content" ng-controller="CommentListCtrl">
		<div class="bs-example bs-example-tabs">
		      <ul id="myTab" class="nav nav-tabs">
 		        <li class="active"><a href="#home" data-toggle="tab">发出的评论</a></li>
		        <li class=""><a href="#profile" data-toggle="tab">收到的评论</a></li> 
		      </ul>
		      
		      <div id="myTabContent" class="tab-content">
		        <div class="tab-pane fade active in" id="home">
		        	<div class="container">
						<div class="row">
							<div class="bg-col-autoresize commentlist clearfix">

								<!-- 评论列表 -->
								<div class="row">
									<div class="col-xs-12 comment glyphicon glyphicon-copyright-mark" ng-repeat="comment in OutComments">
										<span class="badge-new">From <a href="weibo/tweetdetail.jsp#/tweet/{{comment.status.mid}}" ng-bind-html="comment.status.text | substr : '20'"></a></span>
										<p ><span class="text-info"><a href="weibo/blogger.jsp#/userinfo/{{comment.user.id}}">{{comment.user.screen_name}}</a></span><span ng-show="checkShow(comment.reply_comment.user.screen_name)"> 回复 <a href="weibo/blogger.jsp#/userinfo/{{comment.reply_comment.user.id}}">{{comment.reply_comment.user.screen_name}}</a></span>: {{comment.text}}</p>	
																
									</div>									
								</div>
	
								<!-- end -->					
							
								<!-- 评论列表分页控件 -->
								<div class="row" ng-show="true">
				 					<div class="col-xs-2 pagination-info col-xs-offset-3">
										<ul class="pagination">
									        <li ng-class="{disabled:OutPageDirect.bPage == OutPage, active:OutPageDirect.bPage!=OutPage}" ><a ng-click="showOutComments(OutPageDirect.bPage, 10)">«</a></li>
									        <li class="disabled"><a  disabled href="javascript:void(0);" ng-bind="OutPage"> <span class="sr-only">(current)</span></a></li>
									        
									        <li ng-class="{disabled:OutPageDirect.fPage == OutPage, active:OutPageDirect.fPage!=OutPage}"><a ng-click="showOutComments(OutPageDirect.fPage, 10)">»</a></li>
									     </ul>
									</div>
				 					<div class="col-xs-4  page-action " style="margin: 20px 0;">
				 						<div class="row">
<!-- 				 							<div class="col-xs-6">
					 	 	 					<div class=" input-group">
						 					 	  <input type="text" class="form-control" ng-blur="updateCPageGo($index)">
										          <span class="input-group-btn">
								            		<a class="btn btn-default" ng-click="showComments(tweet.mid, cPageGo[$index], $index)" >跳到</a>
										          </span>						
						 						</div>							
				 							</div> -->
					 						<div class="col-xs-6" style="line-height: 34px;">
								            		共有<span class="text-info"><strong>{{OutPageSum}}</strong></span>页数
					 						</div>
				 						</div>
									</div> 
								</div>
								<!-- end -->							
							

							</div>
						</div>
					</div>
		        </div> 

		        <div class="tab-pane fade" id="profile">

		        	<div class="container">
						<div class="row">
							<div class="bg-col-autoresize commentlist clearfix">

								<!-- 评论列表 -->
								<div class="row">
									<div class="col-xs-12 comment glyphicon glyphicon-copyright-mark" ng-repeat="comment in InComments">
										<span class="badge-new" ng-show="checkShow(comment.status)">From <a href="weibo/tweetdetail.jsp#/tweet/{{comment.status.mid}}" ng-bind-html="comment.status.text | substr : '20'"></a></span>
										<p ><span class="text-info"><a href="weibo/blogger.jsp#/userinfo/{{comment.user.id}}">{{comment.user.screen_name}}</a></span><span ng-show="checkShow(comment.reply_comment.user.screen_name)"> 回复 <a href="weibo/blogger.jsp#/userinfo/{{comment.reply_comment.user.id}}">{{comment.reply_comment.user.screen_name}}</a></span>: {{comment.text}}</p>	
																
									</div>									
								</div>	
								<!-- end -->					
							
								<!-- 评论列表分页控件 -->
								<div class="row" ng-show="true">
				 					<div class="col-xs-2 pagination-info col-xs-offset-3">
										<ul class="pagination">
									        <li ng-class="{disabled:InPageDirect.bPage == InPage, active:InPageDirect.bPage!=InPage}" ><a ng-click="showInComments(InPageDirect.bPage, 10)">«</a></li>
									        <li class="disabled"><a  disabled href="javascript:void(0);" ng-bind="InPage"> <span class="sr-only">(current)</span></a></li>
									        
									        <li ng-class="{disabled:InPageDirect.fPage == InPage, active:InPageDirect.fPage!=InPage}"><a ng-click="showInComments(InPageDirect.fPage, 10)">»</a></li>
									     </ul>
									</div>
				 					<div class="col-xs-4  page-action " style="margin: 20px 0;">
				 						<div class="row">
<!-- 				 							<div class="col-xs-6">
					 	 	 					<div class=" input-group">
						 					 	  <input type="text" class="form-control" ng-blur="updateCPageGo($index)">
										          <span class="input-group-btn">
								            		<a class="btn btn-default" ng-click="showComments(tweet.mid, cPageGo[$index], $index)" >跳到</a>
										          </span>						
						 						</div>							
				 							</div> -->
					 						<div class="col-xs-6" style="line-height: 34px;">
								            		共有<span class="text-info"><strong>{{InPageSum}}</strong></span>页数
					 						</div>
				 						</div>
									</div> 
								</div>
								<!-- end -->							
							

							</div>
						</div>
					</div>

		        </div> 		        

		      </div>
		    </div>	
		    
		    
		    
		    

		    
		    
		    
		    

	

		
		
	</section>
	
 
	
	<footer id="footer">

			<div class="col-xs-12 text-center text-muted">归伟龙所有</div>
		
	</footer>
  	<script src="weibo/lib/angular/angular.js"></script>
  	<script src="weibo/lib/angular/angular-route.js"></script>
  	<script src="weibo/lib/angular/angular-sanitize.min.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
  	<script src="weibo/js/commentlistcontroller.js"></script>

</body>
</html>