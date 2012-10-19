package net.upaste.view.impl;

import net.upaste.view.CompositeView;
import net.upaste.view.View;

public class PasteView extends CompositeView {
	public PasteView(View child) {
		super(child);
	}

	@Override
	public String head() {
		return "test head";
	}

	@Override
	public String body() {
		return "<b>test body</b><h3>headerrrrr</h3>";
	}
}
