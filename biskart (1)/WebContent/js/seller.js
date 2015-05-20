(function(){
	
	var app = angular.module('seller',['ngRoute']);
	
	app.factory('thefactory',function($http,$q){

		var factory={};
		var ip='http://localhost:8080/biskart/api/';


		factory.send_data = function(a,b,c,d,e,f,g,h){
			
			var defer1=$q.defer();
  			//ip address to be specified
  			//changes to be made here:bcat
  			$http.post(ip+'',{sid:factory.sid,pname:a,cat:b,subcat:c,Brand:d,OS:e,Color:f,ScreenSize:g,RAM:h}).
  			success(function(data, status, headers, config) {
	    		console.log(data);
    			defer1.resolve(data);
	    	}).
	    	error(function(data) {
	    		// called asynchronously if an error occurs
    			// or server returns response with an error status.
  			});
  			return defer1.promise;
		};
		
		factory.signup=function(a,b,c,d,e,f,g,h,i){
			var defer5=$q.defer();
			$http.defaults.useXDomain = true;
			delete $http.defaults.headers.common['X-Requested-With'];
			$http.post('http://localhost:8080/biskart/api/signup/seller',{name:a,email:b,password:c,address:d,mobile:e,city:f,state:g,pincode:h,alt_contact:i}).
			success(function(data, status, headers, config) {
			console.log(data);
			defer5.resolve(data);
			  }).
		  error(function(data, status, headers, config) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
		  });
		  return defer5.promise;
		};
		
		factory.login=function(a,b){
			var defer5=$q.defer();
			$http.defaults.useXDomain = true;
			delete $http.defaults.headers.common['X-Requested-With'];
			$http.post('http://localhost:8080/biskart/api/login/seller',{email:a,password:b}).
			success(function(data, status, headers, config) {
				console.log(data);
				factory.sid=data.id;
				defer5.resolve(data);
     		}).
		  error(function(data, status, headers, config) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
		  });
		  return defer5.promise;
		};
		return factory;
	});

	app.controller('addProductController',function($scope,thefactory){

		var arr =[];
		$scope.show2=1;
		$scope.success=0;
		$scope.attr1="";
		$scope.attr2="";
		$scope.attr3="";
		$scope.attr4="";
		$scope.attr5="";
		
		$scope.fields={};
		$scope.submitForm = function(){
			console.log("Send data called!");
			
			thefactory.send_data($scope.prodname,$scope.category,$scope.subcategory,$scope.attr1,$scope.attr2,$scope.attr3,$scope.attr4,$scope.attr5).then(function(data){
				console.log(data);
				if(data==true)
				{
					$scope.show2=0;
					$scope.success=1;
				}
			});
		};
	});

	app.controller("sellerController", function($scope,$http,thefactory,$q,$routeParams){
		console.log("came here");
		$scope.show=0;
		
		$scope.signout=function(){
			location.href="#/";
		
		};
		$scope.loginsubmit=function(){
			thefactory.login($scope.email,$scope.password).then(function(data){
				console.log(data);
				if(data.status==true)
				{
					location.href="#/dashboard";
				}
			});
		};
		$scope.signupsubmit=function(){
			console.log("aff");
			thefactory.signup($scope.name,$scope.email,$scope.password,$scope.address,$scope.mobilenumber,$scope.city,$scope.state,$scope.pincode,$scope.altaddr).then(function(data){
				console.log(data);
				if(data==true)
				{
				  location.href="slrlogin.html";
				  $('#signupsuccess').html("Signup successful! Please login to continue.");
				  $('#signupsuccess').css('display','block');
				}
				else
				{
				  location.href="slrlogin.html";
				  $('#login-alert').html("Signup failed!");
				  $('#login-alert').css('display','block');
				}
			});
			
		};

	});
	app.config(function($routeProvider){
  $routeProvider
  .when('/',
    {controller:'sellerController',
      templateUrl:'partials/slrlogin.html'})
	.when('/dashboard',
    {controller:'sellerController',
      templateUrl:'partials/slrbrd.html'})
  .otherwise({redirectTo: '/' });
});
})();
