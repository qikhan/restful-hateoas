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
			 
			 $scope.delete = function(id) {
				 Employee.delete({id:id},
					function(response) {
					 	for(var i=0; i<$scope.employees.length; i++) {
					 		if ($scope.employees[i].eid == id) {
					 			$scope.employees.splice(i,1);
					 			return;
					 		}
					 	}
		 			},
		 			function(response) {
		 				alert("Failed to delete data");
		 			}
				 );
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
			 Employee.get({id:$routeParams.id},				 
				function(response) {
				 	$scope.employee = response;
	 			},
	 			function(response) {
	 				alert("Failed to load data");
	 			}
			 ); 
			 
			 $scope.save = function() {
				 
				 Employee.update(
					{id:$scope.employee.eid},
					$scope.employee,
					function(response) {
					 	alert("Saved");
		 			},
		 			function(response) {
		 				alert("Failed to update data");
		 			}
				 );				 
			 }
		 }
		 ]
)

.controller('EmployeeAddCtrl', 
		[
		 '$scope',
		 'Employee',
		 function($scope, Employee) {
			
			 $scope.employee = null;
			 
			 $scope.create = function() {
				 
				 Employee.create(
					{},
					$scope.employee,
					function(response) {
					 	alert("Created");
					 	$scope.employee.firstName = "";
					 	$scope.employee.lastName = "";
		 			},
		 			function(response) {
		 				alert("Failed to save data");
		 			}
				 );				 
			 }
		 }
		 ]
)