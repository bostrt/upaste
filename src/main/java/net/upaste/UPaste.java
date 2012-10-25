package net.upaste;

import static spark.Spark.get;
import static spark.Spark.post;

import java.sql.SQLException;
import java.util.List;

import net.upaste.dao.PasteDAO;
import net.upaste.model.Paste;
import net.upaste.view.impl.BrowsePastes;
import net.upaste.view.impl.NewPasteView;
import net.upaste.view.impl.PasteView;
import net.upaste.view.impl.PasteWrapperView;

import org.apache.commons.lang.StringEscapeUtils;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class UPaste
{
	public static final int LINK_ID_LENGTH = 8;
	public static final int RECENT_PASTE_LIMIT_SMALL = 10;
	public static final int RECENT_PASTE_LIMIT_BIG = 30;
	
	public void init() throws SQLException
	{
		Spark.setStaticResourceBase("static");
		Spark.setStaticVirtualDirectory("/static");

		// Default route. Display blank form
		get(new Route("/") {
			@Override
			public Object handle(Request request, Response response) {
				PasteDAO dao = new PasteDAO();
				List<Paste> ps = dao.getRecentPastes(RECENT_PASTE_LIMIT_SMALL);
				
				PasteWrapperView v = new PasteWrapperView(new NewPasteView(), ps);
				return v.render();
			}
		});
		
		// Create new Paste
		post(new Route("/new") {
			@Override
			public Object handle(Request request, Response response) {
				try {
					boolean isPrivate = request.queryParams("private") != null;
					String highlightType = request.queryParams("highlight");
					String title = request.queryParams("title");
					String email = request.queryParams("email");
					String content = request.queryParams("content");
					
					content = StringEscapeUtils.escapeHtml(content);
					title = StringEscapeUtils.escapeHtml(title);
					email = StringEscapeUtils.escapeHtml(email);
					highlightType = StringEscapeUtils.escapeHtml(highlightType);

					if(title.trim().isEmpty()){
						// Default title
						title = "Untitled";
					}
					
					if(email.trim().isEmpty()) {
						// Default name
						email = "anonymous";
					}
					
					Paste p = new Paste();
					p.setPrivate(isPrivate);
					p.setHighlightType(highlightType);
					p.setTitle(title.trim());
					p.setEmail(email.trim());
					p.setContent(content);
					
					PasteDAO dao = new PasteDAO();
					
					dao.insert(p);
					
					response.redirect("/paste/"+p.getID());
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				return null;
			}
		});
		
		// Show a paste
		get(new Route("/paste/:id") {
			@Override
			public Object handle(Request request, Response response) {
				PasteDAO dao = new PasteDAO();

				List<Paste> ps = dao.getRecentPastes(RECENT_PASTE_LIMIT_SMALL);
				
				String idStr = request.params("id");
				Long id = null;
				try {
					id = Long.valueOf(idStr);
				} catch(NumberFormatException e) {
					response.redirect("/");
					return "";
				}
				
				Paste p = dao.getByID(id);

				// Build view
				PasteView view = new PasteView(p);
				PasteWrapperView wrapper = new PasteWrapperView(view, ps);
				return wrapper.render();
			}
		});
		
		// Browse pastes
		get(new Route("/browse") {
			@Override
			public Object handle(Request request, Response response) {
				PasteDAO dao = new PasteDAO();
				List<Paste> pastes = dao.getPastes(0);
				
				BrowsePastes view = new BrowsePastes(pastes);
				return view.render();
			}
		});

		// Browse pastes
		get(new Route("/browse/:start") {
			@Override
			public Object handle(Request request, Response response) {
				String startStr = request.params("start");
				Integer start = null;
				try {
					start  = Integer.valueOf(startStr);
				} catch(NumberFormatException e) {
					response.redirect("/");
					return "";
				}
				
				PasteDAO dao = new PasteDAO();
				List<Paste> pastes = dao.getPastes(start);
				
				BrowsePastes view = new BrowsePastes(pastes);
				return view.render();
			}
		});
	}

	/**
	 * Start here.
	 */
	public static void main(String[] args) {
		try {
			UPaste p = new UPaste();
			p.init();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
