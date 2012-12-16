package net.upaste.client;

import net.upaste.client.activity.BrowseActivity;
import net.upaste.client.activity.NewPasteActivity;
import net.upaste.client.activity.PasteActivity;
import net.upaste.client.place.BrowsePlace;
import net.upaste.client.place.NewPastePlace;
import net.upaste.client.place.PastePlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class UPasteActivityMapper implements ActivityMapper
{
	private ClientFactory clientFactory;
	
	public UPasteActivityMapper(ClientFactory clientFactory)
	{
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if(place instanceof NewPastePlace) {
			return new NewPasteActivity((NewPastePlace) place, clientFactory);
		}
		else if(place instanceof PastePlace) {
			return new PasteActivity((PastePlace)place, clientFactory);
		}
		else if(place instanceof BrowsePlace) {
			return new BrowseActivity((BrowsePlace)place, clientFactory);
		}
		return null;
	}

}
