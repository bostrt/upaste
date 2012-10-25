var codeMirror;

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
	var textArea = $("textarea[name=content]")[0];
	
	codeMirror = CodeMirror(function(elt) {
		  textArea.parentNode.replaceChild(elt, textArea);
		},
		{
			value: textArea.value,
			lineNumbers: true,
			readOnly: "nocursor", 
			lineWrapping: true,
		}
		);

	$("#paste-form").submit(function() {
		codeMirror.save();
	})
})
