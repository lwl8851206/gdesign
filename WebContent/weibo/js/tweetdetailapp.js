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
        templateUrl: 'partials/tweetdetailtp.html',
        controller: 'TweetdetailCtrl'
      }).
      when('/tweet/:mid/', {
        templateUrl: 'partials/tweetdetailtp.html',
        controller: 'TweetdetailCtrl'
      }).
      otherwise({
        redirectTo: '/tweet'
      });
  }]);
