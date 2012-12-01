package net.upaste.client.view.impl;

import java.util.List;

import net.upaste.client.view.NewPasteView;
import net.upaste.shared.data.model.Paste;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class NewPasteViewImpl extends Composite implements NewPasteView
{
	private static NewPasteViewImplUiBinder uiBinder = GWT.create(NewPasteViewImplUiBinder.class);
	private Presenter presenter;
	
	interface NewPasteViewImplUiBinder extends UiBinder<Widget, NewPasteViewImpl> {
	}
	
	@UiField
	HTMLPanel recentPastes;
	@UiField
	FormPanel pasteForm;
	@UiField
	TextArea content;
	@UiField
	TextBox title;
	@UiField
	TextBox email;
	@UiField
	CheckBox isPrivate;

	public NewPasteViewImpl()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setRecentPastes(List<Paste> pastes)
	{
		for(Paste p : pastes) {
			recentPastes.add(new Label(p.getTitle()));
			recentPastes.add(new HTML("<br/>"));
		}
	}
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public String getContent() {
		return SafeHtmlUtils.htmlEscape(content.getText());
	}
	
	@Override
	public String getTitle() {
		return SafeHtmlUtils.htmlEscape(title.getText());
	}
	
	@Override
	public String getEmail() {
		return SafeHtmlUtils.htmlEscape(email.getText());
	}
	
	@Override
	public boolean getIsPrivate() {
		return isPrivate.getValue() == null ? false : isPrivate.getValue();
	}

	@Override
	public void addSubmitCompleteHandler(SubmitCompleteHandler handler) {
		pasteForm.addSubmitCompleteHandler(handler);
	}

	@Override
	public void addSubmitHandler(SubmitHandler handler) {
		pasteForm.addSubmitHandler(handler);
	}
}
