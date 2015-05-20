/**
 * 
 */
function authenticate()
{
	$.ajax({
		type : 'GET',
		url:'http://localhost:8080/biskart/api/login/john@gmail.com/johnpass',
		dataType : 'json',
		success : show
	});
}

function show(data)
{
	console.log('HURAAH'+data);
}