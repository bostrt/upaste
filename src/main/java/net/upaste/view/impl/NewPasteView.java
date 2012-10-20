package net.upaste.view.impl;

import org.stringtemplate.v4.ST;

import net.upaste.view.LeafView;
import net.upaste.view.annotation.Template;
import net.upaste.view.annotation.TemplateMethod;

@Template("new-paste.stg")
public class NewPasteView extends LeafView {

	@Override
	@TemplateMethod
	public String head(ST headST) {
		return headST.render();
	}

	@Override
	@TemplateMethod
	public String body(ST bodyST) {
		return bodyST.render();
	}

}
