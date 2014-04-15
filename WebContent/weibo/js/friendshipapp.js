'use strict';

/* App Module */

var phonecatApp = angular.module('phonecatApp', [
  'ngRoute',
  'friendshipControllers'
]);

phonecatApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/followers', {
        templateUrl: 'weibo/partials/friendshiptp.html',
        controller: 'FollowerListCtrl'
      }).
      when('/followers/:page', {
        templateUrl: 'weibo/partials/friendshiptp.html',
        controller: 'FollowerListCtrl'
      }).
      when("/friends",{
    	  templateUrl : "weibo/partials/friendshiptp.html",
    	  controller : "FriendListCtrl"
      }).
      when("/friends/:page",{
    	  templateUrl : "weibo/partials/friendshiptp.html",
    	  controller : "FriendListCtrl"
      }).
      otherwise({
        redirectTo: '/followers'
      });
  }]);
