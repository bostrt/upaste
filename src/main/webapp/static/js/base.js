$(document).ready(function() {
	$(".recent-paste-link").hover(
			function() {
				// highlight
				$(this).addClass("hovered");
			},
			function() {
				// unhighlight
				$(this).removeClass("hovered");
			});
	
	$(".recent-paste-link").click(function() {
		// Get href and go
		location.assign($(this).attr("href"));
	});
})