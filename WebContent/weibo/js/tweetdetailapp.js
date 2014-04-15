'use strict';

/* App Module */

var phonecatApp = angular.module('phonecatApp', [
  'ngRoute',
  'tweetdetailController'
]);

phonecatApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/tweet', {
        templateUrl: 'weibo/partials/tweetdetailtp.html',
        controller: 'TweetdetailCtrl'
      }).
      when('/tweet/:mid/', {
        templateUrl: 'weibo/partials/tweetdetailtp.html',
        controller: 'TweetdetailCtrl'
      }).
      otherwise({
        redirectTo: '/tweet'
      });
  }]);
