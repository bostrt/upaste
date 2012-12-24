package net.upaste.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date addedOn = null;
		try {
			String dateStr = dateFormat.format(new Date());
			addedOn = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Error parsing date for UTC. Setting to a default 'new Date()'.");
			addedOn = new Date();
		}
		System.out.println(addedOn.toString());
		paste.setAddedOn(addedOn);
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
