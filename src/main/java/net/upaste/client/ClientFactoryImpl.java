package net.upaste.client;

import net.upaste.client.component.UPasteTitle;
import net.upaste.client.view.NewPasteView;
import net.upaste.client.view.PasteView;
import net.upaste.client.view.impl.NewPasteViewImpl;
import net.upaste.client.view.impl.PasteViewImpl;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

public class ClientFactoryImpl implements ClientFactory
{
	private final EventBus bus = new SimpleEventBus();
	private final NewPasteView newPasteView = new NewPasteViewImpl();
	private final PasteView pasteView = new PasteViewImpl();
	private final UPasteTitle title = new UPasteTitle();
	private final PlaceController placeController = new PlaceController(bus);
	
	@Override
	public EventBus getEventBus() {
		return bus;
	}

	@Override
	public NewPasteView getNewPasteView() {
		return newPasteView;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public PasteView getPasteView() {
		return pasteView;
	}
	
	@Override
	public UPasteTitle getTitle() {
		return title;
	}
}
