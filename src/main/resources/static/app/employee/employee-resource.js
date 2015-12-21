'use strict';

angular.module('EmployeeResource', ['ngResource'])
.constant ('URL_CONFIG',
		 {
			BASE_URL : "employee",
			BASE_URL_ID : "employee/:id",
			URL_ALL : "employees"
		 }
		)

.factory('Employee', [ 
   '$resource',
   'URL_CONFIG',
   function($resource, URL_CONFIG) {
	   return $resource(
			   URL_CONFIG.BASE_URL_ID,
			   {},
			   {
				 all : {
					 method : "GET",
					 url : URL_CONFIG.URL_ALL,
					 params : {},
					 isArray : true
				 },
				 get : {
					 method : "GET",					 
					 params : {},
					 isArray : false
				 }

			   }	  
	   );
   }
])
