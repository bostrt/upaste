package net.upaste.view.impl;

import java.util.List;

import net.upaste.model.Paste;
import net.upaste.view.CompositeView;
import net.upaste.view.View;
import net.upaste.view.annotation.Template;
import net.upaste.view.annotation.TemplateMethod;

import org.stringtemplate.v4.ST;

@Template("pastewrapper.stg")
public class PasteWrapperView extends CompositeView
{
	private List<Paste> recentPastes;
	
	public PasteWrapperView(View child, List<Paste> recentPastes) {
		super(child);
		this.recentPastes = recentPastes;
	}

	@Override
	@TemplateMethod
	public String head(ST headST) {
		headST.add("childHead", getChild().head());
		return headST.render();
	}

	@Override
	@TemplateMethod
	public String body(ST bodyST) {
		bodyST.add("recentList", recentPastes);
		bodyST.add("content", getChild().body());
		return bodyST.render();
	}
}
