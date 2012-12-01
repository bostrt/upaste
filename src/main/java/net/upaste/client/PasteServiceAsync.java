package net.upaste.client;

import java.util.List;

import net.upaste.shared.data.model.Paste;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PasteServiceAsync {
	void getRecentPastes(int count, AsyncCallback<List<Paste>> callback);
	void addPaste(Paste paste, AsyncCallback<Paste> callback);
	void loadPaste(long id, AsyncCallback<Paste> callback);
}
