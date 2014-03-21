'use strict';

/* Controllers */

var bloggerControllers = angular.module('bloggerControllers', []);

bloggerControllers.controller('BloggerCtrl', ['$scope', '$routeParams',
  function($scope, $routeParams) {
	
	$scope.uid = ($routeParams.uid == null) ? 1 : $routeParams.uid;
	
	//关注当前用户
	$scope.createFriend = function(uid) {
		jQuery.ajax({
			url : "http://localhost:8080/gdesign/friendship/createFriend.do",
			data : {
				"uid" : $scope.uid
			},
			async : false,
			success : function(data) {
				
				var jsonData = eval("(" + data + ")");
				if (jsonData != null) {
					$scope.user = jsonData;
				}
				
				
			}
		});		
	}
	
	//取消关注当前用户
	$scope.destroyFriend = function(uid) {
		jQuery.ajax({
			url : "http://localhost:8080/gdesign/friendship/destroyFriend.do",
			data : {
				"uid" : $scope.uid
			},
			async : false,
			success : function(data) {
				
				var jsonData = eval("(" + data + ")");
				if (jsonData != null) {
					$scope.user = jsonData;
				}
				
				
			}
		});		
	}	
	
	//取得用户数据
	jQuery.ajax({
		url : "http://localhost:8080/gdesign/user/showUserInfo.do",
		data : {
			"uid" : $scope.uid
		},
		async : false,
		success : function(data) {
			
			var jsonData = eval("(" + data + ")");
			if (jsonData != null) {
				$scope.user = jsonData;
			}
			
			
		}
	});
	
    
  }]);


