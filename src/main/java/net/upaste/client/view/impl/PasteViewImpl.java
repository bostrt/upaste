package net.upaste.client.view.impl;

import java.util.List;

import net.upaste.client.component.RecentPasteList;
import net.upaste.client.view.NewPasteView.Presenter;
import net.upaste.client.view.PasteView;
import net.upaste.shared.data.model.Paste;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PasteViewImpl extends Composite implements PasteView
{
	private Presenter presenter;
	
	private static PasteViewImplUiBinder uiBinder = GWT
			.create(PasteViewImplUiBinder.class);

	interface PasteViewImplUiBinder extends UiBinder<Widget, PasteViewImpl> {
	}
	
	@UiField
	Label title;
	@UiField
	Label email;
	@UiField
	Label isPrivate;
	@UiField
	RecentPasteList recentPasteList;

	public PasteViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setContent(String content) {
		Element e = Document.get().getElementById("pre-content-container");
		e.setInnerHTML(content);
	}

	@Override
	public void setEmail(String email) {
		this.email.setText(SafeHtmlUtils.htmlEscape(email));
	}
	
	@Override
	public void setTitle(String title) {
		this.title.setText(title);
	}

	@Override
	public void setIsPrivate(boolean isPrivate) {
		this.isPrivate.setText("Private: " + (isPrivate ? "Yes" : "No"));
	}

	@Override
	public void setRecentPasteList(List<Paste> pastes) {
		this.recentPasteList.setRecentPasteList(pastes);
	}
}