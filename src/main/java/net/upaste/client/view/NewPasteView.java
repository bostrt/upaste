package net.upaste.client.view;

import java.util.List;

import net.upaste.shared.data.model.Paste;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.IsWidget;

public interface NewPasteView extends IsWidget {
	void setPresenter(Presenter presenter);
	void setRecentPastes(List<Paste> pastes);
	String getContent();
	String getTitle();
	String getEmail();
	boolean getIsPrivate();
	void addSubmitCompleteHandler(SubmitCompleteHandler handler);
	void addSubmitHandler(SubmitHandler handler);

	public interface Presenter {
		void goTo(Place place);
	}
}
