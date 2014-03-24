'use strict';

/* Controllers */

var tweetControllers = angular.module('tweetControllers', ['ngSanitize']);

/**
 * 返回总页数,确定当前第一页
 */
tweetControllers.factory("pageSum", function pageSum() {
	return {
		"cal" : function(totalNum, count) {
					var pageSum = 0;
					var tempPageSum = parseFloat(totalNum) / parseFloat(count);
					if (Math.floor(tempPageSum) == tempPageSum)
						pageSum = tempPageSum;
					else
						pageSum = Math.floor(tempPageSum) + 1;		
					return pageSum;
		}
	}
});

/**
 * 决定上一页页数和下一页页数
 * @returns {___anonymous568_832}
 */
tweetControllers.factory("pageDirection", function pageDirection() {
	return {
		"direct" : function(page, pSum) {
			var result = {},
			page = parseFloat(page),
			pSum = parseFloat(pSum);
			
			result["bPage"] = (page == 1) ? 1 : page - 1;
			console.log("page is " + page + ", pagesum is " + pSum);
			result["fPage"] = (page >= pSum) ? pSum : page + 1;
			console.log("fpage is " + result["fPage"]);
			return result;	
		}
	}
});




tweetControllers.controller('TweetListCtrl', ['$scope', '$routeParams', 'pageSum', 'pageDirection', 
  function($scope, $routeParams, pageSum, pageDirection) {
	
	$scope.page = ($routeParams.page == null) ? 1 : $routeParams.page;
	$scope.cPageSum = {};
	$scope.cPageDirect = {};
	$scope.comments = {};
	$scope.cPage = {};
	$scope.retweetId = null;
	$scope.uid = $routeParams.uid;
	
	//参数为空或者false,即为false,否则为true
	$scope.checkShow = function(type) {
		return (type == null || type == false || type.length == 0) ? false : true;
	};
	
	//更新转发微薄mid
	$scope.updateMid = function(mid) {
		$scope.retweetId = mid;
	}
	
	//显示评论面板
	$scope.toggleCPanel = function(mid, index) {
		console.log("index's type is " + typeof index);
		$scope.cPanelShow[index] = !$scope.cPanelShow[index];
		console.log("panel index: " + index);
		//评论面板才发出ajax请求
		if ($scope.cPanelShow[index])
			$scope.showComments(mid, 1, index);
	}
			
	//显示某条微波的评论
	$scope.showComments = function(mid, cP, index) {
		$scope.cPage[index] = (cP == null) ? 1 : cP;
		$scope.comments = {};
		jQuery.ajax({
			"url" : "http://localhost:8080/gdesign/comment/showComments.do",
			"data" : {
				"page" : $scope.cPage[index],
				"count" : 10,
				"mid" : mid
			},
			"beforeSend" : function() {
				
			},
			"async" : true,
			"success" : function(data) {
				$scope.$apply(function(scope) {
					var jsonData = eval("(" + data + ")");
					if (jsonData != null) {
						scope.cPageSum[index] = pageSum.cal(jsonData["total_number"], 10);
						scope.cPageDirect[index] = pageDirection.direct(scope.cPage[index], scope.cPageSum[index]);
						scope.comments[index] = jsonData["comments"];
					}						
				});				
				
			}
		});
	}
	
	//评论微薄
	$scope.createComment = function(mid, index) {
		var inputArea = jQuery("#cm" + index);
		var text = inputArea.val();
		jQuery.ajax({
			"url" : "http://localhost:8080/gdesign/comment/createComment.do",
			"data" : {
				"mid" : mid,
				"comment" : text
			},
			"async" : true,
			"success" : function(data) {
				//data proccess
				$scope.$apply(function(scope) {
					inputArea.val("");
					scope.showComments(mid, 1, index);					
				});

			}
		});
	}
	
	//删除微薄评论
	$scope.destroyComment = function(cid) {
		
		
	}
	
	//删除微薄
	$scope.destroyTweet = function(mid) {
		
		jQuery.ajax({
			"url" : "http://localhost:8080/gdesign/tweet/destroyTweet.do",
			"data" : {
				"mid" : mid,
			},
			"async" : true,
			"success" : function(data) {
				//data proccess
				$scope.$apply(function(scope) {
					jQuery("#tweet" + mid).hide("fast", function() {
						$scope.showAllTweets();
					});
				});
				
			}
		});	
	}
	
	//收藏微薄
	$scope.createFavorite = function(mid) {
//		jQuery.ajax({
//		"url" : "http://localhost:8080/gdesign/favorite/createFavorite.do",
//		"data" : {
//			"mid" : mid,
//		},
//		"async" : false,
//		"success" : function(data) {
//			//data proccess
//			jQuery("#tweet" + mid).hide("slow");
//		}
//	});			
	}
	
	//取消收藏微薄
	$scope.destroyFavorite = function(mid) {
//		jQuery.ajax({
//		"url" : "http://localhost:8080/gdesign/favorite/destroyFavorite.do",
//		"data" : {
//			"mid" : mid,
//		},
//		"async" : false,
//		"success" : function(data) {
//			//data proccess
//			jQuery("#tweet" + mid).hide("slow");
//		}
//	});			
	}	
	
	$scope.retweet = function(mid, text) {
		console.log("mid :" + mid);
		jQuery.ajax({
			"url" : "http://localhost:8080/gdesign/tweet/repostTweet.do",
			"data" : {
				"mid" : mid,
				"text" : text
			},
			"beforeSend" : function() {
				jQuery("body").append("<div class='alert alert-success'  style='position:fixed;text-align:center;z-index:99999;top:0px;width:100%;' id='retweethint'>Retweeting......</div>");
			},
			"async" : true,
			"success" : function(data) {
				//data proccess
				$scope.$apply(function(scope) {
					jQuery("#retweethint").val("Completed...").fadeOut(1000, function() {
						jQuery(this).remove();
						jQuery("#retweet-text").val("");
					});
//					scope.showAllTweets();
				});
				
				
			}
		});		
		
		
	}
	
	
	$scope.showAllTweets = function() {
		
		if ($scope.uid == null) {
			jQuery.ajax({
				url : "http://localhost:8080/gdesign/tweet/showAllTweets.do",
				data : {
					"page" : $scope.page
				},
				"beforeSend" : function() {
					jQuery("body").append("<div class='alert alert-success' style='width:100%;text-align:center;position:fixed;z-index:99999;top:0px;' id='tweetshint'>Loading......</div>");
				},			
				async : true,
				success : function(data) {
					$scope.$apply(function(scope) {
						jQuery("#tweetshint").val("Completed...").fadeOut(1000, function() {
							$(this).remove();
						});
						var jsonData = eval("(" + data + ")");

						if (jsonData != null) {
							
							scope.pageSum = pageSum.cal(jsonData["total_number"], 20);
							scope.pageDirect = pageDirection.direct(scope.page, scope.pageSum);
							scope.tweets = jsonData["statuses"];
							
							scope.cPanelShow = [];
							var len = jsonData["statuses"].length;
							for (var i = 0; i < len; i++) {
								scope.cPanelShow.push(false);
							}
						}					
					});			
					
				}
			});				
		}
		else {
			jQuery.ajax({
				url : "http://localhost:8080/gdesign/tweet/showOnesTweets.do",
				data : {
					"page" : $scope.page,
					"uid" : $scope.uid
				},
				"beforeSend" : function() {
					jQuery("body").append("<div class='alert alert-success' style='width:100%;text-align:center;position:fixed;z-index:99999;top:0px;' id='tweetshint'>Loading......</div>");
				},			
				async : true,
				success : function(data) {
					$scope.$apply(function(scope) {
						jQuery("#tweetshint").val("Completed...").fadeOut(1000, function() {
							$(this).remove();
						});
						var jsonData = eval("(" + data + ")");

						if (jsonData != null) {
							
							scope.pageSum = pageSum.cal(jsonData["total_number"], 20);
							scope.pageDirect = pageDirection.direct(scope.page, scope.pageSum);
							scope.tweets = jsonData["statuses"];
							
							scope.cPanelShow = [];
							var len = jsonData["statuses"].length;
							for (var i = 0; i < len; i++) {
								scope.cPanelShow.push(false);
							}
						}					
					});			
					
				}
			});				
		}
		

		
	}
	
	$scope.showAllTweets();


    
  }]);

