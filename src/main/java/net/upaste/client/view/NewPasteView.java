package net.upaste.client.view;

import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;

public interface NewPasteView extends UPasteView, HasRecentPastes 
{
	String getContent();
	String getTitle();
	String getEmail();
	boolean getIsPrivate();
	void addSubmitCompleteHandler(SubmitCompleteHandler handler);
	void addSubmitHandler(SubmitHandler handler);
}
