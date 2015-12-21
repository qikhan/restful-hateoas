'use strict';

angular.module('EmployeeModule', 
		[
		 'EmployeeResource'
		 ])

.controller('EmployeeListCtrl', 
		[
		 '$scope',
		 'Employee',
		 function($scope, Employee) {
			 
			 $scope.employees = {};
			 
			 Employee.all({},
				function(response) {
				 console.log("Success " + response.length);
				 $scope.employees = response;
	 			},
	 			function(response) {
	 				console.log("error");
	 			}
			 ); 
		 }
		 ]
)

.controller('EmployeeEditCtrl', 
		[
		 '$scope',
		 'Employee',
		 function($scope, Employee) {
			 
		 }
		 ]
)

.controller('EmployeeAddCtrl', 
		[
		 '$scope',
		 'Employee',
		 function($scope, Employee) {
			 
		 }
		 ]
)