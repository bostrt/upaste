package net.upaste.client.activity;

import net.upaste.client.ClientFactory;
import net.upaste.client.place.BrowsePlace;
import net.upaste.client.view.BrowseView;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class BrowseActivity extends AbstractUPasteActivity<BrowsePlace> 
{
	private BrowseView view;
	
	public BrowseActivity(BrowsePlace place, ClientFactory clientFactory) {
		super(place, clientFactory);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getBrowseView();
		
		panel.setWidget(view);
	}
}
