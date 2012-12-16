package net.upaste.client.view.impl;

import net.upaste.client.view.BrowseView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

public class BrowseViewImpl extends AbstractUPasteView implements BrowseView
{
	private static BrowseViewImplUiBinder uiBinder = GWT
			.create(BrowseViewImplUiBinder.class);

	interface BrowseViewImplUiBinder extends UiBinder<Widget, BrowseViewImpl> {
	}

	public BrowseViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
