'use strict';

/* App Module */

var phonecatApp = angular.module('phonecatApp', [
  'ngRoute',
  'tweetControllers'
]);

phonecatApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/tweets', {
        templateUrl: 'partials/tweettp.html',
        controller: 'TweetListCtrl'
      }).
      when('/tweets/:page', {
        templateUrl: 'partials/tweettp.html',
        controller: 'TweetListCtrl'
      }).
      otherwise({
        redirectTo: '/tweets'
      });
  }]);
