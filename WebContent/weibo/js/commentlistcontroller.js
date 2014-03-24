'use strict';

/* Controllers */

var commentListControllers = angular.module('commentListControllers', ['ngSanitize']);


/**
 * 返回总页数,确定当前第一页
 */
commentListControllers.factory("pageSum", function pageSum() {
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
commentListControllers.factory("pageDirection", function pageDirection() {
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

commentListControllers.filter("substr", function substr() {
	return function(str, subLen) {
		if (!subLen)
			return str;
		
		subLen = parseInt(subLen);
		if (subLen < str.length)
			return str.substr(0, subLen) + "....";
		else
			return str;
	};
});




commentListControllers.controller('CommentListCtrl', ['$scope', 'pageSum', 'pageDirection',
  function($scope, pageSum, pageDirection) {
	
	
	
	//参数为空或者false,即为false,否则为true
	$scope.checkShow = function(type) {
		return (type == null || type == false || type.length == 0) ? false : true;
	};	
	
	//获得用户收到的评论
	$scope.showInComments = function(page, count) {
		$scope.InPage = page;
		jQuery.ajax({
			url : "http://localhost:8080/gdesign/comment/showInComments.do",
			data : {
				"page" : page,
				"count" : count
			},
			async : true,
			success : function(data) {
				$scope.$apply(function(scope) {
					var jsonData = eval("(" + data + ")");
					if (jsonData != null) {
						
						scope.InPageSum = pageSum.cal(jsonData["total_number"], count);
						scope.InPageDirect = pageDirection.direct(page, scope.InPageSum);
						scope.InComments = jsonData["comments"];
					}					
				});			
				
			}
		});
	}
	
	
	
	//获得用户发出的评论
	$scope.showOutComments = function(page, count) {
		$scope.OutPage = page;
		jQuery.ajax({
			url : "http://localhost:8080/gdesign/comment/showOutComments.do",
			data : {
				"page" : page,
				"count" : count
			},
			async : true,
			success : function(data) {
				$scope.$apply(function(scope) {
					var jsonData = eval("(" + data + ")");
					if (jsonData != null) {
						
						$scope.OutPageSum = pageSum.cal(jsonData["total_number"], count);
						$scope.OutPageDirect = pageDirection.direct(page, $scope.OutPageSum);
						$scope.OutComments = jsonData["comments"];
					}					
				});	
				
			}
		});		
	}
	
	//第一次加载先取得用户发出与收到的评论
	$scope.showInComments(1, 10);
	$scope.showOutComments(1, 10);

    
  }]);

