package net.upaste.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class PastePlace extends Place
{
	private Long pasteId;
	
	public PastePlace(Long pasteId) {
		this.pasteId = pasteId;
	}
	
	public Long getPasteId() {
		return pasteId;
	}

	@Prefix("paste")
	public static class Tokenizer implements PlaceTokenizer<PastePlace>
	{

		@Override
		public PastePlace getPlace(String token) {
			Long tokenLong;
			
			try {
				tokenLong = Long.valueOf(token);
			} catch(NumberFormatException e) {
				tokenLong = -1l;
			}
			return new PastePlace(tokenLong);
		}

		@Override
		public String getToken(PastePlace place) {
			if(place.getPasteId() != null) {
				return place.getPasteId().toString();
			}
			return null;
		}
	}
}
