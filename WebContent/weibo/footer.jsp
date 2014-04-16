<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<footer id="footer">
		<div class="footer-bottom">
			<div class="col-xs-12 text-center text-muted"><span class="glyphicon glyphicon-copyright-mark">归伟龙所有</span></div>		
		</div>
		
	</footer>

  	<script src="weibo/lib/angular/angular.js"></script>
  	<script src="weibo/lib/angular/angular-route.js"></script>
  	<script src="weibo/lib/angular/angular-sanitize.min.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script>
 		$(function() {
 			
 			$(window).bind("scroll", function() {
 	 			$window = $(window);
 	 			var bodyScrHg = document.body.scrollHeight;
 	 			
 	 			if ($window.scrollTop() < bodyScrHg - $window.height() | $window.scrollTop() == 0 && bodyScrHg != $window.height()) {
 	 				$(".footer-bottom").css("display", "none");
 	 			}
 	 			else {
 	 				$(".footer-bottom").css("display", "block");
 	 			}				
 			}).trigger("scroll");
 			
			
 			$("#logout").click(function() {
 	 			$.ajax({
 					url : "o/destroy.do",
 					
 					beforeSend : function() {
 						jQuery("body").append("<div class='loading'></div>");
 						jQuery(".loading").css("height", document.body.scrollHeight + "px");
 						jQuery(".loading").append("<img src='img/loading1.gif'>");
// 						jQuery("body").append("<div class='alert alert-success' style='width:100%;text-align:center;position:fixed;z-index:99999;top:0px;' id='tweetshint'>Loading......</div>");
 					},			
 					
 					success : function(data) {
 							
 							location.replace("weibo/login.jsp");
 					}
 				});	 				
 			});

 			
		}); 
	</script>
	<%
		String [] jsFiles = request.getParameterValues("jsfiles");
		String jsTagPre = "<script type=\"text/javascript\" src=\"",
			   jsTagSuf = "\"></script>";
		if (jsFiles != null)
			for (String jsFile : jsFiles) {
				out.println(jsTagPre + jsFile + jsTagSuf);
			}
	%>
  	
</body>