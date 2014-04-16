<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/gdesign/";
%>
<!DOCTYPE html >
<html ng-app="commentListControllers">

	<jsp:include page="header.jsp">
		<jsp:param value="" name="activeitem"/>
		<jsp:param value="comments" name="title"/>
	</jsp:include>
	
		<style>
		.badge-new {
		  display: inline-block;
		  min-width: 10px;
		  padding: 5px 9px;
		  margin: 5px 0px;
		  font-size: 12px;
		  font-family: 微软雅黑;
		  background-image: linear-gradient(to bottom,#e8e8e8 0,#f5f5f5 100%);
		  -webkit-background-image: linear-gradient(to bottom,#e8e8e8 0,#f5f5f5 100%);
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
		
		.comment p{
			line-height: 1.4em;
		}
	</style>

	<section id="content" ng-controller="CommentListCtrl">
		<div class="bg-col-autoresize clearfix">
		      <ul id="myTab" class="nav nav-tabs col-xs-12">
 		        <li class="active col-xs-6"><a href="#home" data-toggle="tab">发出的评论</a></li>
		        <li class="col-xs-6"><a href="#profile" data-toggle="tab">收到的评论</a></li> 
		      </ul>
		      
		      <div id="myTabContent" class="tab-content col-xs-12">
		        <div class="tab-pane fade active in" id="home">
		        	<div class="container">
						<div class="row">
							<div class="commentlist clearfix">

								<!-- 评论列表 -->
								<div class="row">
									<div class="col-xs-12 comment glyphicon glyphicon-copyright-mark" ng-repeat="comment in OutComments">
										<p class="badge-new">From <a href="weibo/tweetdetail.jsp#/tweet/{{comment.status.mid}}" ng-bind-html="comment.status.text | substr : '20'"></a></p>
										<p ><span class="text-info"><a href="weibo/blogger.jsp#/userinfo/{{comment.user.id}}">{{comment.user.screen_name}}</a></span><span ng-show="checkShow(comment.reply_comment.user.screen_name)"> 回复 <a href="weibo/blogger.jsp#/userinfo/{{comment.reply_comment.user.id}}">{{comment.reply_comment.user.screen_name}}</a></span>: {{comment.text}}</p>	
																
									</div>									
								</div>
	
								<!-- end -->					
							
								<!-- 评论列表分页控件 -->
								<div class="row" ng-show="OutComments">
				 					<div class="col-md-2  col-sm-4 pagination-info col-md-offset-3 col-sm-offset-2">
										<ul class="pagination">
									        <li ng-class="{disabled:OutPageDirect.bPage == OutPage, active:OutPageDirect.bPage!=OutPage}" ><a ng-click="showOutComments(OutPageDirect.bPage, 10)">«</a></li>
									        <li class="disabled"><a  disabled href="javascript:void(0);" ng-bind="OutPage"> <span class="sr-only">(current)</span></a></li>
									        
									        <li ng-class="{disabled:OutPageDirect.fPage == OutPage, active:OutPageDirect.fPage!=OutPage}"><a ng-click="showOutComments(OutPageDirect.fPage, 10)">»</a></li>
									     </ul>
									</div>
				 					<div class="col-md-4 col-sm-6  page-action " style="margin: 20px 0;">
				 						<div class="row">
<!--  				 							<div class="col-md-6 col-sm-6">
					 	 	 					<div class=" input-group">
						 					 	  <input type="text" class="form-control" ng-blur="updateCPageGo($index)">
										          <span class="input-group-btn">
								            		<a class="btn btn-default" ng-click="showOutComments(OutPageDirect.fPage, 10)" >跳到</a>
										          </span>						
						 						</div>							
				 							</div>  -->
					 						<div class="col-md-6 col-sm-6" style="line-height: 34px;">
								            		共<span class="text-info"><strong>{{OutPageSum}}</strong></span>页
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
							<div class="commentlist clearfix">

								<!-- 评论列表 -->
								<div class="row">
									<div class="col-xs-12 comment glyphicon glyphicon-copyright-mark" ng-repeat="comment in InComments">
										<span class="badge-new" ng-show="checkShow(comment.status)">From <a href="weibo/tweetdetail.jsp#/tweet/{{comment.status.mid}}" ng-bind-html="comment.status.text | substr : '20'"></a></span>
										<p ><span class="text-info"><a href="weibo/blogger.jsp#/userinfo/{{comment.user.id}}">{{comment.user.screen_name}}</a></span><span ng-show="checkShow(comment.reply_comment.user.screen_name)"> 回复 <a href="weibo/blogger.jsp#/userinfo/{{comment.reply_comment.user.id}}">{{comment.reply_comment.user.screen_name}}</a></span>: {{comment.text}}</p>	
																
									</div>									
								</div>	
								<!-- end -->					
							
								<!-- 评论列表分页控件 -->
								<div class="row" ng-show="InComments">
				 					<div class="col-md-2  col-sm-4 pagination-info col-md-offset-3 col-sm-offset-2">
										<ul class="pagination">
									        <li ng-class="{disabled:InPageDirect.bPage == InPage, active:InPageDirect.bPage!=InPage}" ><a ng-click="showInComments(InPageDirect.bPage, 10)">«</a></li>
									        <li class="disabled"><a  disabled href="javascript:void(0);" ng-bind="InPage"> <span class="sr-only">(current)</span></a></li>
									        
									        <li ng-class="{disabled:InPageDirect.fPage == InPage, active:InPageDirect.fPage!=InPage}"><a ng-click="showInComments(InPageDirect.fPage, 10)">»</a></li>
									     </ul>
									</div>
				 					<div class="col-md-4 col-sm-6 page-action " style="margin: 20px 0;">
				 						<div class="row">
<!-- 				 							<div class="col-md-6 col-sm-6">
					 	 	 					<div class=" input-group">
						 					 	  <input type="text" class="form-control" ng-blur="updateCPageGo($index)">
										          <span class="input-group-btn">
								            		<a class="btn btn-default" ng-click="showComments(tweet.mid, cPageGo[$index], $index)" >跳到</a>
										          </span>						
						 						</div>							
				 							</div> -->
					 						<div class="col-md-6 col-sm-6" style="line-height: 34px;">
								            		共<span class="text-info"><strong>{{InPageSum}}</strong></span>页
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
	
 
	
	<jsp:include page="footer.jsp">
		<jsp:param value="weibo/js/commentlistcontroller.js" name="jsfiles"/>
	</jsp:include>

</html>