'use strict';

/* App Module */

var phonecatApp = angular.module('phonecatApp', [
  'ngRoute',
  'writeController'
]);

phonecatApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/write', {
        templateUrl: 'partials/writetp.html',
        controller: 'WriteCtrl'
      }).
      otherwise({
        redirectTo: '/write'
      });
  }]);
