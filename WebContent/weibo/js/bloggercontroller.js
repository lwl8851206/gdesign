'use strict';

/* Controllers */

var bloggerControllers = angular.module('bloggerControllers', []);

bloggerControllers.controller('BloggerCtrl', ['$scope', '$routeParams',
  function($scope, $routeParams) {
	
	$scope.uid = ($routeParams.uid == null) ? '1896325745' : $routeParams.uid;
	
	//关注当前用户
	$scope.createFriend = function(uid) {
	
		jQuery.ajax({
			url : "friendship/follow.do",
			data : {
				"uid" : $scope.uid
			},
			async : true,
			success : function(data) {
				
				$scope.$apply(function(scope) {
					
					var jsonData = eval("(" + data + ")");
					if (jsonData != null) {
						scope.user = jsonData;
						console.log("create friend :" + scope.user.following);
					}					
				});	
				
			}
		});		
	}
	
	//取消关注当前用户
	$scope.destroyFriend = function(uid) {
		jQuery.ajax({
			url : "friendship/destroyFriend.do",
			data : {
				"uid" : $scope.uid
			},
			async : true,
			success : function(data) {
				
				$scope.$apply(function(scope) {
					var jsonData = eval("(" + data + ")");
					if (jsonData != null) {
						scope.user = jsonData;
						console.log("destroy friend :" + scope.user.following);
					}					
				});	
				
			}
		});		
	}	
	
	//取得用户数据
	jQuery.ajax({
		url : "user/showUserInfo.do",
		data : {
			"uid" : $scope.uid
		},
		async : true,
		success : function(data) {
			
			$scope.$apply(function(scope) {
				var jsonData = eval("(" + data + ")");
				if (jsonData != null) {
					
					//获取大的头像
					var imgArr = jsonData["profile_image_url"].split("/");
					imgArr[4] = "180";
					jsonData["profile_image_url"] = imgArr.join("/");
					
					//性别
					jsonData["gender"] = jsonData["gender"] == "f" ? "女" : "男";
					
					//在线状态
					jsonData["online_status"] = jsonData["online_status"] == 0 ? "不在线上" : "在线";
					
					$scope.user = jsonData;
				}				
			});	
			
		}
	});
	
    
  }]);


