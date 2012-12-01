package net.upaste.client;

import java.util.List;

import net.upaste.shared.data.model.Paste;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("paste")
public interface PasteService extends RemoteService{
	List<Paste> getRecentPastes(int count);
	Paste addPaste(Paste paste);
	Paste loadPaste(long id);
}
