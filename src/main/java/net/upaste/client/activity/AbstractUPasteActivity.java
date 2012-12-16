package net.upaste.client.activity;

import net.upaste.client.ClientFactory;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.place.shared.Place;

public abstract class AbstractUPasteActivity<P extends Place> extends AbstractActivity
{
	protected ClientFactory clientFactory;
	protected P place;
	
	public AbstractUPasteActivity(P place, ClientFactory clientFactory)
	{
		this.place = place;
		this.clientFactory = clientFactory;
	}
	
	public void goTo(Place place)
	{
		clientFactory.getPlaceController().goTo(place);
	}
}
