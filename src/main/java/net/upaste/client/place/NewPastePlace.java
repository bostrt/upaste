package net.upaste.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NewPastePlace extends Place
{
	public static class Tokenizer implements PlaceTokenizer<NewPastePlace>
	{

		@Override
		public NewPastePlace getPlace(String token) {
			return new NewPastePlace();
		}

		@Override
		public String getToken(NewPastePlace place) {
			return null;
		}
	}
}
