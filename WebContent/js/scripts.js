
$(function () {

	$('.quick-view').click(function(){
		var id = $(this).attr('id');
		window.location.href = "product?id="+id;
		System.out.println("EXECUTED");
	});
})
