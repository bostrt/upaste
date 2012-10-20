package net.upaste.view.impl;

import net.upaste.view.LeafView;
import net.upaste.view.annotation.Template;
import net.upaste.view.annotation.TemplateMethod;

import org.stringtemplate.v4.ST;

@Template("")
public class NullLeaf extends LeafView {

	@Override
	@TemplateMethod
	public String head(ST headST) {
		return "";
	}

	@Override
	@TemplateMethod
	public String body(ST bodyST) {
		return "<i>Nothing to see here...</i>";
	}

}
