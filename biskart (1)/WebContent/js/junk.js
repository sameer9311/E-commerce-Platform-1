$scope.$watch(function(){return thefactory.get();}, function (newVal,oldVal) {
			if(newVal==1)
			{
				$scope.click=1;
	
				$scope.cart_amt=thefactory.amt;

				var i=0;
				while(i<thefactory.prods.length)
				{
					$scope.cartdata[i]=thefactory.prods[i];
					i++;	
				}
				
			}
     		
     	});