
$(function () {

	$('.product').click(function(){
		var id = $(this).attr('id');
		window.location.href = "product?id="+id;
	});
	
	
})
