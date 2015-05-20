(function(){
var app;

app=angular.module("bis",['ngRoute']);

app.factory('thefactory',function($http,$q){
var factory={};
var ip='http://localhost:8080/biskart/api';
//factory.products=[{'heading':'Hersheys Chocolate Milkshake','prodid':'00001','sellers':[{'seller':'Jack Sparrow','price':500},{'seller':'Rahul Rokde','price':580}],'descr':'Rich and velvety, this extra thick shake pops with chocolate and finishes silky smooth.This milkshake will leave you in true chocolate pleasure.','img':'img/choclate.png'},{'heading':'Modern Operating Systems','descr':'Best book ever!','prodid':'00003','sellers':[{'seller':'Jack Sparrow','price':800},{'seller':'Rahul Rokde','price':880}],'img':'img/book.jpeg'},{'heading':'Nike Sports Shoes Limited Edition','prodid':'00002','sellers':[{'seller':'Jack Sparrow','price':5000},{'seller':'Rahul Rokde','price':6580}],'descr':'Best shoes ever','img':'img/shoes.jpeg'}];

factory.producs= function(){
	console.log("in function producs ");
  var defer5=$q.defer();
	$http.post(ip+'/products',{subcat:'Mobiles'}).
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

factory.cart_amt=0;
factory.prods=[];
factory.numcartelem=0;
var showcart=0;
factory.getcartval=function(){
  return showcart;
};

factory.setcartval=function(a){
  showcart=a;
};

factory.addprod2cart=function(a,b,c,d)
{
factory.cart_amt+=c;
  factory.numcartelem++;
factory.prods.push({'heading':a,'seller':b,'price':c,'img':d});
factory.setcartval(1);
};
factory.login=function(a,b){
  console.log("DSFdgrg");
  var defer1=$q.defer();
  $http.defaults.useXDomain = true;
  delete $http.defaults.headers.common['X-Requested-With'];
$http.post(ip+'login',{username:a,password:b}).
  success(function(data, status, headers, config) {
    console.log(data);
    if(data.id>0)
    {
        factory.user_id=data.id;
        factory.uname=data.name;
    }
    defer1.resolve(data);
      }).
  error(function(data, status, headers, config) {
    // called asynchronously if an error occurs
    // or server returns response with an error status.
  });
  return defer1.promise;
};

factory.getDetails=function(a){
var defer3=$q.defer();
	$http.defaults.useXDomain = true;
  delete $http.defaults.headers.common['X-Requested-With'];
$http.post(ip+'/fullproductdetails',{pid:a}).
  success(function(data, status, headers, config) {
    console.log(data);
    
    defer3.resolve(data);
      }).
  error(function(data, status, headers, config) {
    // called asynchronously if an error occurs
    // or server returns response with an error status.
  });
  return defer3.promise;
};
factory.usignup=function(a,b,c,d,e){
var defer2=$q.defer();
  $http.defaults.useXDomain = true;
  delete $http.defaults.headers.common['X-Requested-With'];
$http.post(ip+'login',{email:a,password:b,name:c,address:d,contact:e}).
  success(function(data, status, headers, config) {
    console.log(data);
    if(data==true)
    {
      
    }
    defer2.resolve(data);
      }).
  error(function(data, status, headers, config) {
    // called asynchronously if an error occurs
    // or server returns response with an error status.
  });
  return defer2.promise;
};

factory.search=function(a){
    var defer6=$q.defer();
    $http.defaults.useXDomain = true;
    delete $http.defaults.headers.common['X-Requested-With'];
	$http.get('http://localhost:8983/solr?q='+a+'&wt=json&indent=true&facet=true&facet.field=pname').
    success(function(data, status, headers, config) {
    console.log(data);
    
    defer6.resolve(data);
    }).
    error(function(data, status, headers, config) {
	// called asynchronously if an error occurs
	// or server returns response with an error status.
    });
	return defer6.promise;
};

return factory;
});

app.controller("listingController", function($scope,thefactory,$q){
$scope.products=[];
$scope.details=function(a){
location.href="#/product/"+a;
};
thefactory.producs().then(function(data){
	$scope.products=data;
});

});

app.controller("productController", function($scope,thefactory,$q,$routeParams){

$scope.data=[];
$scope.product_id = $routeParams.productId;
thefactory.getDetails($scope.product_id).then(function(data){
$scope.data=data;
});


$scope.addcart=function(a,b,c,d){
console.log("Add to cart called!");
thefactory.addprod2cart(a,b,c,d);
};
});

app.controller("checkoutController", function($scope,thefactory,$q,$routeParams){
$scope.phonenum;
$scope.submit=function(){
	if($scope.phonenum.length==10)
	{
		location.href="#/paymentsuccess";
	}

};

});

app.controller("navController", function($scope,thefactory,$q,$routeParams){
    $scope.count=0;
    $scope.click1=0;
    $scope.cart_amt=0;
    $scope.cartdata=[];         //create a login variable that hides the modal in link of the directive by watching it. addthe attribute that closes the modal
    $scope.email="";
    $scope.pw="";
    $scope.signupmail="";
    $scope.signuppw="";
    $scope.signupfname="";
    $scope.signuplname="";
    $scope.signuppw="";
    $scope.signupcpw="";
      $scope.showcrt=function(){
        $scope.cart_amt=thefactory.cart_amt;
        
        var i=0;
        while(i<thefactory.prods.length)
        {
          $scope.cartdata[i]=thefactory.prods[i];
          i++;  
        }
        thefactory.setcartval(1);
        $scope.click1=1;
      };
      $scope.hidecart=function(){
        console.log("Hidecart called");
        $scope.click1=0;
        thefactory.setcartval(0);
        };

      $scope.loginsubmit=function(){
        console.log("Login validated. Sending out login request through factory!");
        thefactory.login($scope.email,$scope.pw).then(
      function(data) {
        console.log(data);
        if(data.id>0)
        {
          $('#logsignform').modal('toggle');
          $("#loginame").html("Hello "+thefactory.uname);
        }
      });   
      };
		
	  $scope.search=function(){
		thefactory.search($scope.searchText).then(function(data){
		console.log(data);
		
		});

		};
      $scope.signupsubmit=function(){
          if($scope.signuppw==$scope.signupcpw)
          {
            console.log("Load nahi he! sign up request bhejte hain!");
            thefactory.usignup($scope.email,$scope.pw,$scope.signupname,$scope.address,$scope.contactno).then(
            function(data) {
              console.log(data);
              if(data.id>0)
              {
                $('#logsignform').modal('toggle');
                $("#loginame").html("Hello "+thefactory.uname);
              }
            });
          }
          else 
          {
            console.log("Sign up erro waali div bhar link se ek variable ko watch kar ke.");
          }
      };

        $scope.$watch(function(){ return thefactory;}, function (newVal,oldVal,scope) {
          console.log("factory me change aaya!");
          if (typeof newVal == 'undefined') {
            console.log("undefined hai abhi");
          }
          else
          {
              if(newVal.getcartval()==1)
              {
                  console.log("cart ko dikhaane ko bola");
                  scope.showcrt();
              }
              if(newVal.numcartelem>oldVal.numcartelem)
              {
                  scope.count=newVal.numcartelem;
              }
          }
        
      },true);
});


app.directive('mainNavbar',function(){
  return {
    restrict:'E',
    templateUrl:'partials/nav.html',
    controller:'navController',
    link:function(scope,element){
     	
    scope.$watch('click1',function(value){
      console.log("Click ka listener!");
      if(value==1)
      {
        $('#cart').css('display','block');
      }
      else 
      {
        $('#cart').css('display','none');
      }
    });
    }
      
    };
  });

app.directive('loginSignup',function(){
    return {
    restrict:'E',
    templateUrl:'partials/usrlogin.html',
    controller:'navController',
    link:function(scope,element){

    }
  };

});

app.directive('footerMain',function(){
    return {
    restrict:'E',
    templateUrl:'partials/footer.html',
    link:function(scope,element){

    }
  };

});
app.config(function($routeProvider){
  $routeProvider
  .when('/',
    {controller:'listingController',
      templateUrl:'partials/listing.html'})
  .when('/product/:productId',                                      //add to cart button
    {controller:'productController',
    templateUrl:'partials/product.html'})
  .when('/checkout',
    {controller:'checkoutController',
    templateUrl:'partials/checkout.html'})
  .when('/paymentsuccess',
    {controller:'paymentController',
    templateUrl:'partials/paymentsuccess.html'})
  .otherwise({redirectTo: '/' });
});

})();