package net.upaste.client.view.impl;

import com.google.gwt.user.client.ui.Composite;

import net.upaste.client.view.UPasteView;

public abstract class AbstractUPasteView extends Composite implements UPasteView
{
	protected Presenter presenter;
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
