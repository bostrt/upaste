package net.upaste.client.view.impl;

import net.upaste.client.view.NewPasteView.Presenter;
import net.upaste.client.view.PasteView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
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
	TextArea content;

	public PasteViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setContent(String content) {
		assert content != null;
		this.content.setText(SafeHtmlUtils.htmlEscape(content));
	}

	@Override
	public void setEmail(String email) {
		assert email != null;
		this.email.setText(SafeHtmlUtils.htmlEscape(email));
	}
	
	@Override
	public void setTitle(String title) {
		assert title != null;
		this.title.setText(title);
	}

	@Override
	public void setIsPrivate(boolean isPrivate) {
		this.isPrivate.setText("Private: " + (isPrivate ? "Yes" : "No"));
	}
}
