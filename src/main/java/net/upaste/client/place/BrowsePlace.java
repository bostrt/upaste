package net.upaste.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class BrowsePlace extends Place
{
	@Prefix("browse")
	public static class Tokenizer implements PlaceTokenizer<BrowsePlace>
	{

		@Override
		public BrowsePlace getPlace(String token) {
			return new BrowsePlace();
		}

		@Override
		public String getToken(BrowsePlace place) {
			return null;
		}
	}
}
