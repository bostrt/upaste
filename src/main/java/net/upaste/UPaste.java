package net.upaste;

import static spark.Spark.get;
import static spark.Spark.post;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import net.upaste.dao.PasteDAO;
import net.upaste.model.Paste;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class UPaste
{
	public static final int LINK_ID_LENGTH = 8; 
	
	public UPaste() throws SQLException
	{		
		Spark.setStaticResourceBase("static");
		Spark.setStaticVirtualDirectory("/static");

		// Default route. Display blank form
		get(new Route("/") {
			@Override
			public Object handle(Request request, Response response) {
				STGroupFile stg = new STGroupFile("stg/new-paste.stg", '$', '$');
				ST baseST = stg.getInstanceOf("base");

				PasteDAO dao = new PasteDAO();
				List<Paste> ps = dao.getRecentPastes();

				baseST.add("recentList", ps);
				
				return baseST.render();
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
					p.setTitle(title);
					p.setEmail(email);
					p.setContent(content);
					// TODO: Need another method of generating ID's. They need to be shorter.
					p.setUuid(UUID.randomUUID().toString());
					
					PasteDAO dao = new PasteDAO();
					
					dao.insert(p);
					
					response.redirect("/paste/"+p.getUuid());
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				return null;
			}
		});
		
		// Show a paste
		get(new Route("/paste/:uuid") {
			@Override
			public Object handle(Request request, Response response) {
				PasteDAO dao = new PasteDAO();
				STGroupFile stg = new STGroupFile("stg/paste.stg", '$', '$');
				ST baseST = stg.getInstanceOf("base");

				List<Paste> ps = dao.getRecentPastes();
				baseST.add("recentList", ps);
				
				String uuid = request.params("uuid");
				Paste p = dao.getByUUID(uuid);
				baseST.add("contextObject", p);
				
				return baseST.render();
			}
		});
	}

	/**
	 * Start here.
	 */
	public static void main(String[] args) {
		try {
			UPaste b = new UPaste();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}