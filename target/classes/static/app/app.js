'use strict';

angular.module('QApplication', 
[
 'ngRoute',
 'EmployeeModule',
 'EmployeeResource'
 ])
 
 .config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/employees', {
        templateUrl: 'app/employee/empolyee-list.tpl.html',
        controller: 'EmployeeListCtrl'
      }).
      when('/employee/:id/edit', {
          templateUrl: 'app/employee/empolyee-edit.tpl.html',
          controller: 'EmployeeEditCtrl'
        }).
      when('/employee/add', {
        templateUrl: 'app/employee/empolyee-add.tpl.html',
        controller: 'EmployeeAddCtrl'
      }).
      otherwise({
        redirectTo: '/employees'
      });
  }]);
  
