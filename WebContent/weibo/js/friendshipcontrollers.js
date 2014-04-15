'use strict';

/* Controllers */

var friendshipControllers = angular.module('friendshipControllers', []);


/**
 * 返回总页数,确定当前第一页
 */
friendshipControllers.factory("pageSum", function pageSum() {
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
friendshipControllers.factory("pageDirection", function pageDirection() {
	return {
		"direct" : function(page, pageSum) {
			var result = {},
			page = parseFloat(page),
			pageSum = parseFloat(pageSum);
			
			result["bPage"] = (page == 1) ? 1 : page - 1;
			result["fPage"] = (page >= pageSum) ? pageSum : page + 1;
			return result;	
		}
	}
});




friendshipControllers.controller('FollowerListCtrl', ['$scope', '$routeParams', 'pageSum', 'pageDirection',
  function($scope, $routeParams, pageSum, pageDirection) {
	
	$scope.page = ($routeParams.page == null) ? 1 : $routeParams.page;
	$scope.type = "followers";
	jQuery.ajax({
		url : "friendship/showFollowers.do",
		data : {
			"page" : $scope.page,
			"uid" : "1896325745"
		},
		async : true,
		success : function(data) {
			$scope.$apply(function(scope) {
				var jsonData = eval("(" + data + ")");
				if (jsonData != null) {
					
					scope.pageSum = pageSum.cal(jsonData["total_number"], 20);
					scope.pageDirect = pageDirection.direct(scope.page, scope.pageSum);
					scope.users = jsonData["users"];
				}				
			});	
			
		}
	});

    
  }]);

friendshipControllers.controller('FriendListCtrl', ['$scope', '$routeParams', 'pageSum', 'pageDirection',
    function($scope, $routeParams, pageSum, pageDirection) {
  	$scope.page = ($routeParams.page == null) ? 1 : $routeParams.page;
  	$scope.type = "friends";
  	jQuery.ajax({
  		url : "friendship/showFriends.do",
  		data : {
  			"page" : $scope.page,
  			"uid" : "1896325745"
  		},
  		async : true,
  		success : function(data) {
  			$scope.$apply(function(scope) {
  	  			var jsonData = eval("(" + data + ")");
  	  			if (jsonData != null) {
  	  				
  	  				$scope.pageSum = pageSum.cal(jsonData["total_number"], 20);
  	  				$scope.pageDirect = pageDirection.direct($scope.page, $scope.pageSum);
  	  				$scope.users = jsonData["users"];
  	  			}				
  			});
  			
  		}
  	});

 }]);

