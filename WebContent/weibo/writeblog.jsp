<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/gdesign/";

	if (session.getAttribute("token") == null)
		response.sendRedirect("https://api.weibo.com/oauth2/authorize?client_id=2717579641&redirect_uri=" + basePath +  "o/oauth.do&response_type=code&state=shit");
%>

<!DOCTYPE html>
<html>

	<jsp:include page="header.jsp">
		<jsp:param value="write" name="activeitem"/>
		<jsp:param value="write tweet" name="title"/>
	</jsp:include>

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
	
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>