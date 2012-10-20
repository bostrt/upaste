jQuery.fn.selectText = function(){
    var doc = document
        , element = this[0]
        , range, selection
    ;
    if (doc.body.createTextRange) {
        range = document.body.createTextRange();
        range.moveToElementText(element);
        range.select();
    } else if (window.getSelection) {
        selection = window.getSelection();        
        range = document.createRange();
        range.selectNodeContents(element);
        selection.removeAllRanges();
        selection.addRange(range);
    }
};
$(document).ready(function() {
	prettyPrint();
	$("pre.prettyprint").dblclick(function() {
		$(this).selectText();
	});
	
	var highlightType = $("input[name=highlight]").attr("highlightType");
	$("select[name=highlight]").attr('value', highlightType);
	
	$("select[name=highlight]").change(function(e) {
		var code = $("pre.prettyprint");
		code.removeClass();
		code.addClass("prettyprint");
		if(this.value != "auto") {
			code.addClass("lang-" + this.value);
		}
		prettyPrint();
	});
})