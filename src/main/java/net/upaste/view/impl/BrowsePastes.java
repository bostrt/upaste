package net.upaste.view.impl;

import java.util.List;

import net.upaste.model.Paste;
import net.upaste.view.LeafView;
import net.upaste.view.annotation.Template;
import net.upaste.view.annotation.TemplateMethod;

import org.stringtemplate.v4.ST;

@Template("browse-pastes.stg")
public class BrowsePastes extends LeafView 
{
	private List<Paste> pastes;
	
	public BrowsePastes(List<Paste> pastes) {
		this.pastes = pastes;
	}
	
	@Override
	@TemplateMethod
	public String head(ST headST) {
		return headST.render();
	}

	@Override
	@TemplateMethod
	public String body(ST bodyST) {
		bodyST.add("pastes", this.pastes);
		return bodyST.render();
	}
}
