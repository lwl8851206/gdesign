'use strict';

/* App Module */

var phonecatApp = angular.module('phonecatApp', [
  'ngRoute',
  'bloggerControllers'
]);

phonecatApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/userinfo', {
        templateUrl: 'partials/bloggertp.html',
        controller: 'BloggerCtrl'
      }).
      when('/userinfo/:uid', {
          templateUrl: 'partials/bloggertp.html',
          controller: 'BloggerCtrl'
      }).
      otherwise({
        redirectTo: '/userinfo'
      });
  }]);
