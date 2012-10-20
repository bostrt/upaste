package net.upaste;

import static spark.Spark.get;
import static spark.Spark.post;

import java.sql.SQLException;
import java.util.List;

import net.upaste.dao.PasteDAO;
import net.upaste.model.Paste;
import net.upaste.view.impl.NewPasteView;
import net.upaste.view.impl.PasteView;
import net.upaste.view.impl.PasteWrapperView;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

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

		get(new Route("/new-view") {
			@Override
			public Object handle(Request request, Response response) {
				return "";
			}
		});
		
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

					if(title.trim().isEmpty()){
						// Default title
						title = "Untitled";
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
				}
				Paste p = dao.getByID(id);

				// Build view
				PasteView view = new PasteView(p);
				PasteWrapperView wrapper = new PasteWrapperView(view, ps);
				return wrapper.render();
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
