package net.upaste.client;

import net.upaste.client.component.UPasteTitle;
import net.upaste.client.view.BrowseView;
import net.upaste.client.view.NewPasteView;
import net.upaste.client.view.PasteView;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

public interface ClientFactory {
	EventBus getEventBus();
	NewPasteView getNewPasteView();
	PasteView getPasteView();
	BrowseView getBrowseView();
	UPasteTitle getTitle();
	PlaceController getPlaceController();
}
