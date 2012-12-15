package net.upaste.client.view;

import java.util.List;

import net.upaste.client.view.NewPasteView.Presenter;
import net.upaste.shared.data.model.Paste;

import com.google.gwt.user.client.ui.IsWidget;

public interface PasteView extends IsWidget {
	void setPresenter(Presenter presenter);
	void setContent(String content);
	void setTitle(String title);
	void setEmail(String email);
	void setIsPrivate(boolean isPrivate);
	void setRecentPasteList(List<Paste> pastes);
}
