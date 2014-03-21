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
        templateUrl: 'partials/friendshiptp.html',
        controller: 'FollowerListCtrl'
      }).
      when('/followers/:page', {
        templateUrl: 'partials/friendshiptp.html',
        controller: 'FollowerListCtrl'
      }).
      when("/friends",{
    	  templateUrl : "partials/friendshiptp.html",
    	  controller : "FriendListCtrl"
      }).
      when("/friends/:page",{
    	  templateUrl : "partials/friendshiptp.html",
    	  controller : "FriendListCtrl"
      }).
      otherwise({
        redirectTo: '/followers'
      });
  }]);
