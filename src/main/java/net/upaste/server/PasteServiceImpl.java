package net.upaste.server;

import java.util.Date;
import java.util.List;

import net.upaste.client.PasteService;
import net.upaste.server.data.dao.PasteDAO;
import net.upaste.shared.data.model.Paste;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PasteServiceImpl extends RemoteServiceServlet implements PasteService
{
	private static final long serialVersionUID = 1L;

	@Override
	public List<Paste> getRecentPastes(int count) {
		PasteDAO dao = new PasteDAO();
		return dao.getRecentPastes(count);
	}

	@Override
	public Paste addPaste(Paste paste) {
		PasteDAO dao = new PasteDAO();
		paste.setAddedOn(new Date());
		dao.insert(paste);
		return paste;
	}

	@Override
	public Paste loadPaste(long id) {
		PasteDAO dao = new PasteDAO();
		Paste paste = dao.getByID(id);
		return paste;
	}
}
