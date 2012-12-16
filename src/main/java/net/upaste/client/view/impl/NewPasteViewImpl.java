package net.upaste.client.view.impl;

import java.util.List;

import net.upaste.client.component.RecentPasteList;
import net.upaste.client.view.NewPasteView;
import net.upaste.shared.data.model.Paste;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class NewPasteViewImpl extends AbstractUPasteView implements NewPasteView
{
	private static NewPasteViewImplUiBinder uiBinder = GWT.create(NewPasteViewImplUiBinder.class);
	
	interface NewPasteViewImplUiBinder extends UiBinder<Widget, NewPasteViewImpl> {
	}
	
	@UiField
	RecentPasteList recentPasteList;
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
	public void setRecentPasteList(List<Paste> pastes)
	{
		this.recentPasteList.setRecentPasteList(pastes);
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
