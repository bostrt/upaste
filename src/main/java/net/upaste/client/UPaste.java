package net.upaste.client;

import net.upaste.client.component.UPasteTitle;
import net.upaste.client.place.NewPastePlace;
import net.upaste.client.place.UPastePlaceHistoryMapper;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class UPaste implements EntryPoint
{
	private NewPastePlace defaultPlace = new NewPastePlace();
	private SimplePanel panel = new SimplePanel();

	@Override
	public void onModuleLoad() {
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		EventBus bus = clientFactory.getEventBus();
		PlaceController controller = clientFactory.getPlaceController();
		
		UPasteActivityMapper activityMapper = new UPasteActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(activityMapper, bus);
		activityManager.setDisplay(panel);
		
		UPastePlaceHistoryMapper placeHistoryMapper = GWT.create(UPastePlaceHistoryMapper.class);
		PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(placeHistoryMapper);
		placeHistoryHandler.register(controller, bus, defaultPlace);
		
		RootPanel.get("title").add(new UPasteTitle());
		RootPanel.get("content").add(panel);
		placeHistoryHandler.handleCurrentHistory();
	}

}
