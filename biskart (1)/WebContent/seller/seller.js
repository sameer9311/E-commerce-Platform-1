(function(){
	
	var app = angular.module('seller',[]);
	
	app.factory('thefactory',function($http,$q){

		var factory={};
		var ip='http://localhost:8080/biskart/api/addproduct';


		factory.send_data = function(a,b,c,d,e,f,g,h){
			
			var defer1=$q.defer();
  			//ip address to be specified
  			//changes to be made here:bcat
  			$http.post(ip,{sid:1,pname:a,cat:b,subcat:c,Brand:d,OS:e,Color:f,ScreenSize:g,RAM:h},headers).
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
		factory.recieve_attr = function(){
			
			var defer1=$q.defer();
			//ip address to be specified
			//changes to be made here
  			$http.get(ip+'').
  			success(function(data) {
	    		console.log(data);

    			defer1.resolve(data);
	    	}).
	    	error(function(data, status, headers, config) {
	    		// called asynchronously if an error occurs
    			// or server returns response with an error status.
  			});
  			return defer1.promise;
		};

		return factory;
	});

	app.controller('SellerController',function($scope){

		var arr =[];
		arr=data;
		$scope.attr1="";
		$scope.attr2="";
		$scope.attr3="";
		$scope.attr4="";
		$scope.attr5="";
		
		$scope.fields={};
		$scope.submitForm = function(){
			console.log("Send data called!");
			
			thefactory.send_data($scope.prodname,$scope.category,$scope.subcategory,$scope.attr1,$scope.attr2,$scope.attr3,$scope.attr4,$scope.attr5).then(function(){
				console.log(data);
			});
		};
});
})();
