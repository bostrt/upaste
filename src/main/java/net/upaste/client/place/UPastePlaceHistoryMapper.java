package net.upaste.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({NewPastePlace.Tokenizer.class, PastePlace.Tokenizer.class})
public interface UPastePlaceHistoryMapper extends PlaceHistoryMapper{

}
