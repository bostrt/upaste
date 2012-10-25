var cm;

$(document).ready(function() {
	$("input[name=title]").watermark("Title...");
	$("input[name=email]").watermark("Name or Email...");
	cm = CodeMirror.fromTextArea($("textarea[name=content]")[0], 
				{
					lineNumbers: true,
					lineWrapping: true,
					autoFocus: true
				});

	doResize();

	// Listen for window resizes
	$(window).resize(function() {
		doResize();
	});
	
	$("#paste-form").submit(function() {
		cm.save();
	})
})

function doResize() {
	var docHeight = $(window).height();
	// Subtract top offset.
	var offset = $(".CodeMirror").offset().top;
	// Subtract 10%
	cm.setSize(null, (docHeight - offset) / 1.1);
}