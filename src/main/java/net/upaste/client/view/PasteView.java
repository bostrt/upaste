package net.upaste.client.view;

import net.upaste.client.view.NewPasteView.Presenter;

import com.google.gwt.user.client.ui.IsWidget;

public interface PasteView extends IsWidget {
	void setPresenter(Presenter presenter);
	void setContent(String content);
	void setTitle(String title);
	void setEmail(String email);
	void setIsPrivate(boolean isPrivate);
}
