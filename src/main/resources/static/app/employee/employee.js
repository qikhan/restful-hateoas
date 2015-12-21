'use strict';

angular.module('EmployeeModule', 
		[
		 'EmployeeResource'
		 ])

.controller('EmployeeListCtrl', 
		[
		 '$scope',
		 '$location',
		 'Employee',
		 function($scope, $location, Employee) {
			 
			 $scope.employees = null;
			 
			 Employee.all({},
				function(response) {
				 $scope.employees = response;
	 			},
	 			function(response) {
	 				alert("Failed to load data");
	 			}
			 ); 
			 
			 $scope.goToAdd = function() {
				 $location.path('/employee/add');
			 }
		 }
		 ]
)

.controller('EmployeeEditCtrl', 
		[
		 '$scope',
		 '$routeParams',
		 'Employee',
		 function($scope, $routeParams, Employee) {
			 
			 $scope.employee = {};
			 console.log("get detail of =" + $routeParams.id);
			 Employee.get({id:$routeParams.id},
				function(response) {
				 	$scope.employee = response;
	 			},
	 			function(response) {
	 				alert("Failed to load data");
	 			}
			 ); 
			 
			 $scope.save = function() {
				 
			 }
		 }
		 ]
)

.controller('EmployeeAddCtrl', 
		[
		 '$scope',
		 'Employee',
		 function($scope, Employee) {
			 
			 $scope.employee = {};
			 
			 $scope.create = function() {
				 
			 }			 
		 }
		 ]
)