$(document).ready(function(){
    $(document).click(function(){
    	
    		$("#cart").toggle();
    	
   
    });
    $("#cart").click(function(e){
         e.stopPropagation();
    });
});