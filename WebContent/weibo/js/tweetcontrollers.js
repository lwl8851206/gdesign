'use strict';

/* Controllers */

var tweetControllers = angular.module('tweetControllers', []);

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
		"direct" : function(page, pageSum) {
			var result = {};
			page = parseFloat(page);
			pageSum = parseFloat(pageSum);
			
			result["bPage"] = (page == 1) ? 1 : page - 1;
			result["fPage"] = (page == pageSum) ? pageSum : page + 1;
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
		
		jQuery.ajax({
			"url" : "http://localhost:8080/gdesign/comment/showComments.do",
			"data" : {
				"page" : $scope.cPage[index],
				"count" : 20,
				"mid" : mid
			},
			"async" : false,
			"success" : function(data) {
				
				var jsonData = eval("(" + data + ")");
				if (jsonData != null) {
					$scope.cPageSum[index] = pageSum.cal(jsonData["total_number"], 20);
					console.log("comment pageSum[" + index + "]:" + $scope.cPageSum[index]);
					$scope.cPageDirect[index] = pageDirection.direct($scope.cPage[index], $scope.cPageSum);
					$scope.comments[index] = jsonData["comments"];
				}	
				
			}
		});
	}
	
	//评论微薄
	$scope.createComment = function(mid, index) {
		var text = jQuery("#cm" + index).val();
		jQuery.ajax({
			"url" : "http://localhost:8080/gdesign/comment/createComment.do",
			"data" : {
				"mid" : mid,
				"comment" : text
			},
			"async" : false,
			"success" : function(data) {
				//data proccess
				
			}
		});
	}
	
	//删除微薄评论
	$scope.destroyComment = function(cid) {
		
	}
	
	//删除微薄
	$scope.destroyTweet = function(mid) {
		
//		jQuery.ajax({
//			"url" : "http://localhost:8080/gdesign/tweet/destroyTweet.do",
//			"data" : {
//				"mid" : mid,
//			},
//			"async" : false,
//			"success" : function(data) {
//				//data proccess
//				jQuery("#tweet" + mid).hide("slow");
//			}
//		});	
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
	
	
	
	jQuery.ajax({
		url : "http://localhost:8080/gdesign/tweet/showAllTweets.do",
		data : {
			"page" : $scope.page
		},
		async : false,
		success : function(data) {
			
			var jsonData = eval("(" + data + ")");

			if (jsonData != null) {
				
				$scope.pageSum = pageSum.cal(jsonData["total_number"], 20);
				$scope.pageDirect = pageDirection.direct($scope.page, $scope.pageSum);
				$scope.tweets = jsonData["statuses"];
				$scope.cPanelShow = [];
				var len = jsonData["statuses"].length;
				for (var i = 0; i < len; i++)
					$scope.cPanelShow.push(false);
			}
			
			
		}
	});

    
  }]);

tweetControllers.controller('TweetDetailCtrl', ['$scope', '$routeParams', '$http',
  function($scope, $routeParams, $http) {
    $http.get('http://localhost:8080/gdesign/tweet/showJson.do' + $routeParams.phoneId + '.json').success(function(data) {
      $scope.tweets = data;
    });
  }]);
