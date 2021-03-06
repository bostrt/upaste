package net.upaste.view.impl;

import net.upaste.model.Paste;
import net.upaste.view.LeafView;
import net.upaste.view.annotation.Template;
import net.upaste.view.annotation.TemplateMethod;

import org.stringtemplate.v4.ST;

@Template("paste.stg")
public class PasteView extends LeafView
{
	private Paste paste;
	
	public PasteView(Paste paste) {
		this.paste = paste;
	}

	@Override
	@TemplateMethod
	public String head(ST headST) {
		return headST.render();
	}

	@Override
	@TemplateMethod
	public String body(ST bodyST) {
		bodyST.add("paste", paste);
		return bodyST.render();
	}

}
